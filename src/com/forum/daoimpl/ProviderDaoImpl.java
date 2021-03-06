package com.forum.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.forum.model.ModelProvider;
import com.forum.util.DaoUtil;
import com.forum.util.ServerConfig;
@Repository("providerDaoImpl")
public class ProviderDaoImpl {
	private Connection conn;
	private Statement stat;
	public boolean delete(int id) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);
		try {
			stat.executeUpdate("delete from user where id="+id);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean add(ModelProvider t) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		try {
			stat.executeUpdate("insert into provider(id,level,title,status) values('"+
					t.getProviderId()+"',1,'"+t.getTitle()+"',1"+ ")");
			
			System.out.println("------好好好------:" + "insert into provider(id,level,title,status) values('"+
					t.getProviderId()+"',1,'"+t.getTitle()+"',1"+ ")");
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateScore (String providerId, String score) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		try {
			int score_total = 0;
			int score_count = 0;
			ResultSet rs = stat.executeQuery("select score_total,score_count from provider where id='"+providerId+"'");
			while (rs.next()){
				score_total = rs.getInt("score_total");
				score_count = rs.getInt("score_count");
			}
			score_total += Integer.parseInt(score);
			score_count++;
			float srore = (score_total)/((float)score_count);
			BigDecimal b = new BigDecimal(srore); 
			srore = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue(); 
			stat.executeUpdate("update provider set score="+srore+",score_total="+score_total+",score_count="+score_count+ " where id='"+providerId+"'");

			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean update(String providerId, String type, String content) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		
		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderTitle)
			updateStr = "title='"+content+"'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderSummary)
			updateStr = "summary='"+content+"'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderRemark)
			updateStr = "remark='"+content+"'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderAddr) {
			int pos = content.lastIndexOf("|");
			String x="",y="";
			if (pos>0) {
				int xpos = content.lastIndexOf("-");
				x = content.substring(pos+1, xpos);
				y = content.substring(xpos+1);
				updateStr = "addr='"+content.substring(0, pos)+"',latitude="+x+",longitude="+y;
			} else {
				updateStr = "addr='"+content+"'";
			}
		}
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderPhone)
			updateStr = "phone='"+content+"'";
		else 
			return false;
		try {
			stat.executeUpdate("update provider set "+updateStr+" where id="+providerId);
			conn.close();
			stat.close();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean appendBusinessCount(String providerId) {
		String sql = "update provider set business=business+1 where id="+providerId;
		return executeUpdate(sql);
	}
	
	public boolean appendBrowseCount(String providerId) {
		String sql = "update provider set browse=browse+1 where id="+providerId;
		return executeUpdate(sql);
	}
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelProvider queryProviderById(String providerId){
		String sql = "select * from provider where id = '" + providerId + "'";

		return getProvider(sql);
	}
//	
//	/**
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public ModelProvider getProviderByUserId(String userId){
//		String sql = "select * from provider where id = '" + userId + "'";
//
//		return getProvider(sql);
//	}
	/**
	 * 查询系统项目类别
	 * @param level 1为修理厂，2为配件商
	 * @return
	 */
	public List<ModelProvider> getProviderListBySearch(String strSearch){
		String sql = "";
		boolean mat = strSearch.matches("\\d+");//返回true为纯数字,否则就不是纯数字
		if (mat) {
			sql = "select * from provider where id like '%" + strSearch + "%' limit 100";
		} else {
			sql = "select * from provider where title like '%" + strSearch + "%' limit 100";			
		}
		return getProviderListBySql(sql);	
	}
	
	public String getImgIdAndUpdateImgList(String providerId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		
		String selStr = "1";
		int nowid = 1;
		
		try {
			String imgidList = "";
			ResultSet rs = stat.executeQuery("select imgid_list from provider where id='"+providerId+"'");
			while (rs.next()){
				imgidList = rs.getString("imgid_list");
			}
			if (imgidList!=null && imgidList.length()>0) {
				String idlist[] = imgidList.split(",");
				if (idlist!=null && idlist.length>0) {
					for (int i=0; i<idlist.length; i++) {
						int id = Integer.parseInt(idlist[i]);
						if (nowid<id) {
							nowid = id;
						}
					}
				}
				nowid++;
				selStr = imgidList + "," + nowid;
			} else {
				selStr = nowid+"";
			}
			stat.executeUpdate("update provider set imgid_list='"+selStr+"' where id='"+providerId+"'");
			conn.close();
			stat.close();
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return nowid+"";
	}
	
	/**
	 * 调整图片的位置，或者删除图片
	 * @param providerId
	 * @param oldImgIdList
	 * @param imgId 图片ID，对应图片名
	 * @param edittype 1后移，0删除，-1前移
	 * @return
	 */
	public String editImgSpace(String providerId, String oldImgIdList, String imgId, String edittype) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		
		String newImgIdList = "";

		String ids[] = oldImgIdList.split(",");
		if (ids!=null && ids.length>0) {
			int index = 0;
			for (int i=0; i<ids.length; i++) {
				if (ids[i].equals(imgId)) {
					index = i;
					break;
				}
			}
			if (edittype.equals("1")) {
				for (int i=0; i<index; i++) {
					newImgIdList += ids[i]+",";
				}
				if (ids.length > index+1)  {
					newImgIdList += ids[index+1]+",";
				}
				newImgIdList += ids[index]+",";
				for (int i=index+2; i<ids.length; i++) {					
					newImgIdList += ids[i]+",";
				}
			} else	if (edittype.equals("0")) {
				for (int i=0; i<index; i++) {
					newImgIdList += ids[i]+",";
				}
				for (int i=index+1; i<ids.length; i++) {					
					newImgIdList += ids[i]+",";
				}
			} else if (edittype.equals("-1")) {
				for (int i=0; i<index-1; i++) {
					newImgIdList += ids[i]+",";
				}
				newImgIdList += ids[index]+",";
				if (index>0)  {
					newImgIdList += ids[index-1]+",";
				}
				for (int i=index+1; i<ids.length; i++) {					
					newImgIdList += ids[i]+",";
				}
			} 
		}
		// 1,3,5,4
		// 0,1,2,3
		if (newImgIdList.length()>0) {
			newImgIdList = newImgIdList.substring(0, newImgIdList.length()-1);
		}
		try {
			stat.executeUpdate("update provider set imgid_list='"+newImgIdList+"' where id='"+providerId+"'");
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		return newImgIdList;
	}
	
	// 根据位置排序
	private String sqlProviderListBySpace(String level, String strSysItemList,String priceStart, String priceEnd
			, String latitude, String longitude) {
		String sql = "select * from provider where status=1 and level="+level;// +" order by score limit 100";
		boolean sysItemListOk = strSysItemList!=null && strSysItemList.length()>0;
		boolean priceStartOk = priceStart != null && priceStart.length()>0;
		boolean priceEndOk = priceEnd != null && priceEnd.length()>0;
		if (sysItemListOk || priceStartOk || priceEndOk) {
			sql += " and id in (select distinct provider_id from provider_item where status=1 ";
			if (sysItemListOk) {
				sql += " and sys_item_id in ("+strSysItemList+")";
			}
			if (priceStartOk) {
				sql += " and price>="+priceStart;
			}
			if (priceEndOk) {
				sql += " and price<="+priceEnd;
			}
			sql += ")";
		}
		if (latitude != null && latitude.length()>0 && longitude != null && longitude.length()>0) {
			sql += " order by abs(latitude-"+latitude+")+abs(longitude-"+longitude+")";
		}
		sql += " limit 100";
		
		return sql;
	}
	// 根据口碑（得分）排序
	private String sqlProviderListByScore(String level, String strSysItemList,String priceStart, String priceEnd) {
		String sql = "select * from provider where status=1 and level="+level;// +" order by score limit 100";
		boolean sysItemListOk = strSysItemList!=null && strSysItemList.length()>0;
		boolean priceStartOk = priceStart != null && priceStart.length()>0;
		boolean priceEndOk = priceEnd != null && priceEnd.length()>0;
		if (sysItemListOk || priceStartOk || priceEndOk) {
			sql += " and id in (select distinct provider_id from provider_item where status=1 ";
			if (sysItemListOk) {
				sql += " and sys_item_id in ("+strSysItemList+")";
			}
			if (priceStartOk) {
				sql += " and price>="+priceStart;
			}
			if (priceEndOk) {
				sql += " and price<="+priceEnd;
			}
			sql += ")";
		}
		sql += " order by score desc ";
		sql += " limit 100";
		
		return sql;
	}
	// 根据价格排序
	private String sqlProviderListByPrice(String level, String strSysItemList,String priceStart, String priceEnd) {
		String sql = "select distinct provider_item.provider_id, provider.* from provider_item  "+
					" INNER JOIN provider ON provider.id=provider_item.provider_id " +
					" where provider.status=1 and provider.level="+level+" and provider_item.status=1 ";
//		if (strSysItemList!=null && strSysItemList.length()>0) {
//			sql += " and sys_item_id in ("+strSysItemList+") ";
//			if (priceStart != null && priceStart.length()>0) {
//				sql += " and price>="+priceStart;
//			}
//			if (priceEnd != null && priceEnd.length()>0) {
//				sql += " and price<="+priceEnd;
//			}
//		}
		boolean sysItemListOk = strSysItemList!=null && strSysItemList.length()>0;
		boolean priceStartOk = priceStart != null && priceStart.length()>0;
		boolean priceEndOk = priceEnd != null && priceEnd.length()>0;
		if (sysItemListOk || priceStartOk || priceEndOk) {
			if (sysItemListOk) {
				sql += " and sys_item_id in ("+strSysItemList+")";
			}
			if (priceStartOk) {
				sql += " and price>="+priceStart;
			}
			if (priceEndOk) {
				sql += " and price<="+priceEnd;
			}
		}
		sql += " order by price ";
		sql += " limit 100";
		return sql;
	}
	/**
	 * 查询商家
	 * @param level 商家类别，1为修理厂商家，2为配件商家
	 * @param strSysItemList 系统项目，过滤使用
	 * @param sort 排序方式：1按距离，2按口碑，3按价格从小到大
	 * @param priceStart 价格最小值
	 * @param priceEnd 价格最大值
	 * @param latitude,longitude 查询用户当前的经纬度，用于根据距离排序时使用
	 * @return 查询到的商家列表
	 */
	public List<ModelProvider> getProviderList(String level, String strSysItemList, String sort
				, String priceStart, String priceEnd, String latitude, String longitude){
		String sql = null;
		if (sort.equals("1")) {// 按距离
			sql = sqlProviderListBySpace(level, strSysItemList, priceStart, priceEnd, latitude, longitude);
		} else if (sort.equals("2")) {
			sql = sqlProviderListByScore(level, strSysItemList, priceStart, priceEnd);
		} else if (sort.equals("3")) {			
			sql = sqlProviderListByPrice(level, strSysItemList, priceStart, priceEnd);
		}
		return getProviderListBySql(sql);			
	}
	
	private List<ModelProvider> getProviderListBySql(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelProvider> list = new ArrayList<ModelProvider>();
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				ModelProvider item = new ModelProvider();
				
				item.setProviderId(rs.getString("id"));

				item.setTitle(rs.getString("title"));//
//				item.setSummary(rs.getString("summary"));
//				item.setRemark(rs.getString("remark"));
				item.setBrowse(rs.getInt("browse"));
				item.setBusiness(rs.getInt("business"));
				item.setScore(rs.getFloat("score"));//
				item.setScoreCount(rs.getInt("score_count"));//
				item.setAddr(rs.getString("addr"));//
				item.setLatitude(rs.getDouble("latitude"));//
				item.setLongitude(rs.getDouble("longitude"));//
//				item.setPhone(rs.getString("phone"));
//				item.setRenzheng(rs.getInt("renzheng"));//
//				item.setS4(rs.getInt("s4"));//
//				item.setLiansuo(rs.getInt("liansuo"));//
//				item.setImgidList(rs.getString("imgid_list"));
				
				list.add(item);
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private ModelProvider getProvider(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ModelProvider item = null;
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				item = new ModelProvider();
				item.setProviderId(rs.getString("id"));

				item.setTitle(rs.getString("title"));//
				item.setSummary(rs.getString("summary"));
				item.setRemark(rs.getString("remark"));
				item.setBrowse(rs.getInt("browse"));
				item.setBusiness(rs.getInt("business"));
				item.setScore(rs.getFloat("score"));//
				item.setScoreCount(rs.getInt("score_count"));//
				item.setAddr(rs.getString("addr"));//
				item.setLatitude(rs.getDouble("latitude"));//
				item.setLongitude(rs.getDouble("longitude"));//
				item.setPhone(rs.getString("phone"));
				item.setRenzheng(rs.getInt("renzheng"));//
				item.setS4(rs.getInt("s4"));//
				item.setLiansuo(rs.getInt("liansuo"));//
				item.setImgIdListStr(rs.getString("imgid_list"));
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	
	private boolean executeUpdate(String updateStr) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		try {
			stat.executeUpdate(updateStr);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
