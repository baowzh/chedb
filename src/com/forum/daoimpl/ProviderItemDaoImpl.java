package com.forum.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ModelProviderItem;
import com.forum.model.ModelSysItem;
import com.forum.util.ServerConfig;
import com.forum.util.DaoUtil;


//
public class ProviderItemDaoImpl {
	private Connection conn;
	private Statement stat;

	public boolean add(ModelProviderItem item) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		int maxSort = 1;
		String maxSortSql = "select max(sort) as maxsort from provider_item where provider_id='"+item.getProviderId()+ "'";
		String maxItemId = item.getProviderId() + item.getSysItemId() + "001";
		String maxItemIdSql = "select max(id) as maxid from provider_item where provider_id='"+item.getProviderId()
				+"' and sys_item_id='"  + item.getSysItemId() + "'";

		try {
			String nowMaxId= null;
			ResultSet rs = stat.executeQuery(maxItemIdSql);
			while (rs.next()){
				nowMaxId = rs.getString("maxid");
				break;
			}
			if (nowMaxId != null) {
				maxItemId = (Long.parseLong(nowMaxId) + 1)+"";
			}
			
			ResultSet rsSort = stat.executeQuery(maxSortSql);
			while (rsSort.next()){
				maxSort = rsSort.getInt("maxsort") + 1;
				break;
			}
			
			String insertSql = "insert into provider_item (id, title, sys_item_id, sys_item_name, provider_id,status,sort) "
					+ "values('"+maxItemId+"','"+item.getTitle()+"','"+item.getSysItemId()+"','"+item.getSysItemName()+"','"+item.getProviderId()+"',1,"+maxSort+")";
			stat.executeUpdate(insertSql);
			
			item.setItemId(maxItemId);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean delete(String itemId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		try {
			stat.executeUpdate("delete from provider_item  where id='" + itemId +"'");
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean update(String itemId, String type, String content) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);

		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderItemTitle)
			updateStr = "title='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderItemSummary)
			updateStr = "summary='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditProviderItemRemark)
			updateStr = "remark='" + content + "'";
		else
			return false;
		try {
			stat.executeUpdate("update provider_item set " + updateStr
					+ " where id=" + itemId);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updatePrice(String itemId, String price, String priceOld) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);

		String updateStr = "";
		if (Double.parseDouble(priceOld)>0) {
			updateStr = "update provider_item set price="+price +", price_old="+priceOld + " where id=" + itemId;
		} else {
			updateStr = "update provider_item set price="+price + " where id=" + itemId;
		}

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
	
	public boolean appendBusinessCount(String itemId) {
		String sql = "update provider_item set business_count=business_count+1 where id="+itemId;
		return executeUpdate(sql);
	}

	/**
	 * �ƶ���Ŀλ�ã�������ǰ��Ŀ��Ŀ����Ŀ������ֵ
	 * @param type -1���ƣ�1����
	 * @param itemId ��ǰ��Ŀ
	 * @param targetItemId �ƶ�Ŀ����Ŀ
	 * @return
	 */
	public boolean moveItem(String itemId, String targetItemId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);

		int nItemSort = -1;
		String itemSort = "select sort from provider_item where id='"+ itemId + "'";
		
		int nItemTargetSort = -1;
		String itemTargetSort = "select sort from provider_item where id='"+ targetItemId + "'";
		try {
			ResultSet rs = stat.executeQuery(itemSort);
			while (rs.next()){
				nItemSort = rs.getInt("sort");
			}
			if (nItemSort<0) {
				return false;
			}
			ResultSet rsTarget = stat.executeQuery(itemTargetSort);
			while (rsTarget.next()){
				nItemTargetSort = rsTarget.getInt("sort");
			}
			if (nItemTargetSort<0) {
				return false;
			}
			
			stat.executeUpdate("update provider_item set sort=" + nItemTargetSort
					+ " where id=" + itemId);
			
			stat.executeUpdate("update provider_item set sort=" + nItemSort
					+ " where id=" + targetItemId);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//
	// public List<User> getAll() {
	// conn = DaoUtil.getConnection();
	// stat = DaoUtil.getStatement(conn);
	// ArrayList<User> list = new ArrayList<User>();
	// try {
	// ResultSet rs = stat.executeQuery("select * from user");
	// while (rs.next()){
	// User temp = new User();
	// temp.setId(rs.getInt(1));
	// temp.setUserName(rs.getString(2));
	// temp.setPassword(rs.getString(3));
	// temp.setTrueName(rs.getString(4));
	// list.add(temp);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return list;
	// }

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	// public ProviderModel queryProviderById(String providerId){
	// String sql = "select * from provider where id = '" + providerId + "'";
	//
	// List<ProviderModel> list = getProviderListBySql(sql);
	// if (list == null || list.size()==0) {
	// return null;
	// }
	// return list.get(0);
	// }

	/**
	 * 
	 * @param userId
	 * @return
	 */
	// public ProviderModel getProviderByUserId(String userId){
	// String sql = "select * from provider where id = '" + userId + "'";
	//
	// List<ProviderModel> list = getProviderListBySql(sql);
	// if (list == null || list.size()==0) {
	// return null;
	// }
	// return list.get(0);
	// }
	/**
	 * ��ѯϵͳ��Ŀ���
	 * 
	 * @param level
	 *            1Ϊ������2Ϊ�����
	 * @return
	 */
	// public List<ProviderModel> getProviderListBySearch(String strSearch){
	// String sql = "";
	// boolean mat = strSearch.matches("\\d+");//����trueΪ������,����Ͳ��Ǵ�����
	// if (mat) {
	// sql = "select * from provider where id like '%" + strSearch +
	// "%' limit 100";
	// } else {
	// sql = "select * from provider where title like '%" + strSearch +
	// "%' limit 100";
	//
	// }
	// return getProviderListBySql(sql);
	// }
	/**
	 * mode : 0��ʾ��ѯ����������ȫ����1��ʾ��ѯǰ���������2��ʾ��ѯ������������
	 */
	public List<ModelProviderItem> getProviderItemByProviderId(int mode,
			String providerId, String strSysItemList,
			String priceStart, String priceEnd) {
		String sql = "select * from provider_item where status=1 and provider_id='"
				+ providerId + "' ";
		if (strSysItemList != null && strSysItemList.equals("") == false) {
			sql += " and sys_item_id in (" + strSysItemList + ")";
		}
		if (priceStart != null && priceStart.length()>0) {
			sql += " and price>="+priceStart;
		}
		if (priceEnd != null && priceEnd.length()>0) {
			sql += " and price<="+priceEnd;
		}
		sql += " order by sort";
		if (mode==1) {
			sql += " limit "+ServerConfig.ItemPos;
		} else if (mode==2) {
			sql += " limit "+ServerConfig.ItemPos + ", 100";
		}
		return getItemListBySql(sql);
	}

	/**
	 * ��ѯ�̼ҵ�ĳ��ϵͳ��Ŀ���м���������Ŀ
	 * 
	 * @param providerId
	 * @param sysItemId
	 * @return
	 */
	public List<ModelSysItem> getProviderItemBySysItemId(String providerId,
			String sysItemId) {
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();

		// List<String> labellist = new ArrayList<String>();
		// // ModelSysItem item = new ModelSysItem();
		// // item.setLabelId(sysItemId);
		// labellist.add(sysItemId);
		List<ModelProviderItem> itemlist = getProviderItemByProviderId(0, 
				providerId, sysItemId, null, null);
		if (itemlist == null) {
			return list;
		}
		for (int i = 0; i < itemlist.size(); i++) {
			ModelSysItem lbl = new ModelSysItem();
			lbl.setLabelId(itemlist.get(i).getItemId());
			lbl.setParentId(sysItemId);
			// lbl.setSelect(1);
			lbl.setTitle(itemlist.get(i).getTitle());
			list.add(lbl);

		}
		return list;
	}

	public ModelProviderItem getProviderItemById(String itemId) {
		String sql = "select * from provider_item where id = '" + itemId + "'";

		List<ModelProviderItem> list = getItemListBySql(sql);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	public ModelProviderItem getProviderItemDetailById(String itemId) {
		String sql = "select * from provider_item where id = '" + itemId + "'";

		List<ModelProviderItem> list = getItemListBySql(sql);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	private List<ModelProviderItem> getItemListBySql(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelProviderItem> list = new ArrayList<ModelProviderItem>();
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				ModelProviderItem item = new ModelProviderItem();

				item.setItemId(rs.getString("id"));
				item.setProviderId(rs.getString("provider_id"));
				item.setSysItemId(rs.getString("sys_item_id"));
				item.setSysItemName(rs.getString("sys_item_name"));
				item.setTitle(rs.getString("title"));
				item.setSummary(rs.getString("summary"));
				item.setRemark(rs.getString("remark"));
				item.setPrice(rs.getFloat("price"));
				item.setPriceOld(rs.getFloat("price_old"));

				item.setBusiness(rs.getInt("business_count"));
				// item.setScore(rs.getFloat("score"));
				// item.setScoreCount(rs.getInt("score_count"));
				// item.setAddr(rs.getString("addr"));
				// item.setLatitude(rs.getDouble("latitude"));
				// item.setLongitude(rs.getDouble("longitude"));
				// item.setPhone(rs.getString("phone"));
				// item.setRenzheng(rs.getInt("renzheng"));
				// item.setS4(rs.getInt("s4"));
				// item.setLiansuo(rs.getInt("liansuo"));

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
