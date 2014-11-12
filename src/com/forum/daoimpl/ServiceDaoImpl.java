package com.forum.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forum.model.ModelProvider;
import com.forum.model.ModelService;
import com.forum.model.ModelServiceClass;
import com.forum.model.ModelUserAppraise;
import com.forum.util.DaoUtil;

//
public class ServiceDaoImpl {
	private Connection conn;
	private Statement stat;
	
	public ModelService getServiceInfo(String serviceId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		Statement stat2 = DaoUtil.getStatement(conn);
		String sql = "select * from service where enable=1 and service_id='" + serviceId + "'";
		ModelService item = null;
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				item = new ModelService();
//				String serviceId = rs.getString("service_id");
				item.setServiceId(rs.getString("service_id"));

				item.setClassId(rs.getString("service_class_id"));//
				item.setName(rs.getString("service_name"));
				item.setNeedCar(rs.getInt("need_car"));
				item.setFirstKm(rs.getInt("first_km"));
				item.setPeriodKm(rs.getInt("period_km"));

				item.setPrice(rs.getInt("price"));//
				item.setAllowJifen(rs.getInt("allow_jifen"));
				item.setPriceJifen(rs.getInt("price_jifen"));//
				item.setReturnJifen(rs.getInt("return_jifen"));//
				item.setHasRaw(rs.getInt("has_raw"));//
//				item.setBusinessNum(businessNum);(rs.getInt("business_initnum"));//
				item.setBusinessNum(rs.getInt("business_num"));//
				item.setWorkTitle(rs.getString("wrok_title"));//
				item.setWorkUrlPath(rs.getString("workdoc_path"));//
				item.setRawUrlPath(rs.getString("rawdoc_path"));//
				
				String sqlItem = "select item_name, raw_name from service_item where enable=1 and service_id='" + serviceId + "'";	
				ResultSet rsItem = stat2.executeQuery(sqlItem);
				int count = 0;
				while (rsItem.next()){
					if (count==0) {
						item.setItem1ClassTxt(rsItem.getString("item_name"));
						item.setItem1Txt(rsItem.getString("raw_name"));
					} else if (count==1) {
						item.setItem2ClassTxt(rsItem.getString("item_name"));
						item.setItem2Txt(rsItem.getString("raw_name"));
					} else if (count==2) {
						item.setItem3ClassTxt(rsItem.getString("item_name"));
						item.setItem3Txt(rsItem.getString("raw_name"));
					} else if (count==3) {
						item.setItem4ClassTxt(rsItem.getString("item_name"));
						item.setItem4Txt(rsItem.getString("raw_name"));
					} 
					count++;
				}
				item.setItemNum(count);
				break;
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelService> getServiceListByClassId(String classId, String carId){
		String sql = ""; 
		ServiceClassDaoImpl classDao= new ServiceClassDaoImpl();
		ModelServiceClass serviceClass = classDao.queryServiceClassById(classId);
		if (serviceClass.getNeedCar() == 1) {
			sql = "select * from service where enable=1 and service_class_id='" + classId 
					+ "' and service_id in (select service_id from car_service where car_id='"+carId+"')";		
		} else {
			sql = "select * from service where enable=1 and service_class_id='" + classId + "'";			
		}
		return getServiceListBySql(sql);	
	}
	
	private List<ModelService> getServiceListBySql(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		Statement stat2 = DaoUtil.getStatement(conn);
		ArrayList<ModelService> list = new ArrayList<ModelService>();
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				ModelService item = new ModelService();
				
				String serviceId = rs.getString("service_id");
				item.setServiceId(rs.getString("service_id"));

				item.setClassId(rs.getString("service_class_id"));//
				item.setName(rs.getString("service_name"));
//				item.setRemark(rs.getString("remark"));
				item.setNeedCar(rs.getInt("need_car"));
				item.setFirstKm(rs.getInt("first_km"));
				item.setPeriodKm(rs.getInt("period_km"));

				item.setPrice(rs.getInt("price"));//
				item.setAllowJifen(rs.getInt("allow_jifen"));
				item.setPriceJifen(rs.getInt("price_jifen"));//
				item.setReturnJifen(rs.getInt("return_jifen"));//
				item.setHasRaw(rs.getInt("has_raw"));//
				item.setBusinessNum(rs.getInt("business_num"));//
				item.setWorkTitle(rs.getString("wrok_title"));//
				item.setWorkUrlPath(rs.getString("workdoc_path"));//
				item.setRawUrlPath(rs.getString("rawdoc_path"));//
				
				String sqlItem = "select item_name, raw_name from service_item where enable=1 and service_id='" + serviceId + "'";	
				ResultSet rsItem = stat2.executeQuery(sqlItem);
				int count = 0;
				while (rsItem.next()){
					if (count==0) {
						item.setItem1ClassTxt(rsItem.getString("item_name"));
						item.setItem1Txt(rsItem.getString("raw_name"));
					} else if (count==1) {
						item.setItem2ClassTxt(rsItem.getString("item_name"));
						item.setItem2Txt(rsItem.getString("raw_name"));
					} else if (count==2) {
						item.setItem3ClassTxt(rsItem.getString("item_name"));
						item.setItem3Txt(rsItem.getString("raw_name"));
					} else if (count==3) {
						item.setItem4ClassTxt(rsItem.getString("item_name"));
						item.setItem4Txt(rsItem.getString("raw_name"));
					} 
					count++;
				}
				item.setItemNum(count);
				
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

	public int getProviderCountByServiceId(String serviceId) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);

		int count = 0;
		String sql = "SELECT count(1) as count FROM service_provider where service_id='"+serviceId+"' and enable=1";
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				count = rs.getInt("count");
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public List<ModelProvider> getProviderListByServiceId(String serviceId, String start, String count
			, String latitude, String longitude) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelProvider> list = new ArrayList<ModelProvider>();
		String sql = "select * from provider where status=1 and level=1 and id in(select provider_id from service_provider where service_id='"+serviceId+"') ";
		// 默认按位置排序
		if (latitude != null && latitude.length()>0 && longitude != null && longitude.length()>0) {
			sql += " order by abs(latitude-"+latitude+")+abs(longitude-"+longitude+")";
		}
		sql += " limit "+start+","+count;
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
				item.setImgIdListStr(rs.getString("imgid_list"));
				
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
	
	public ModelUserAppraise doAppraise(String serviceClassId, String serviceId, String content, String userId) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);
		
		ModelUserAppraise app = new ModelUserAppraise();
		String sqlAdd = "insert into service_appraise(service_class_id,service_id,user_id,content,appr_date,is_anonymous,agree_num,enable)"+
				 "values('"+ serviceClassId+"','" + serviceId+"','"+userId+"','"+content+"',now(),0,0,1)";		
		try {
			stat.executeUpdate(sqlAdd);
			
			String sql="select max(id) as id from service_appraise";
//			rs = ps.executeQuery(sql);//查询出最大ID
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				app.setTheId(rs.getInt("id")+"");
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Date tt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		app.setDateStr(sdf.format(tt));
		return app;
	}
	public int getAppraiseCountByServiceId(String serviceClassId, String serviceId) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);

		int count = 0;
		String sql = "SELECT count(1) as count FROM service_appraise where service_class_id='"+serviceClassId+"' and enable=1";
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				count = rs.getInt("count");
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public List<ModelUserAppraise> getAppraiseListByServiceId(String serviceClassId, String serviceId, String start, String count) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelUserAppraise> list = new ArrayList<ModelUserAppraise>();
		String sql = "select a.id, a.content,a.appr_date,a.is_anonymous,a.agree_num,u.no,u.name "+
					" from service_appraise as a, user as u " +
					" where a.enable=1 and a.service_class_id='"+serviceClassId+"' and a.user_id=u.no order by a.appr_date desc limit "+start+","+count;

		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				ModelUserAppraise item = new ModelUserAppraise();
				
				item.setTheId(rs.getInt("id")+"");
				item.setServiceClassId(serviceClassId);
				item.setServiceId(serviceId);//
				item.setAppraise(rs.getString("content"));
				item.setAgreeNum(rs.getInt("agree_num"));
				Date dt = rs.getTimestamp("appr_date");
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String str = df.format(dt);
				item.setDateStr(str);
				item.setUserId(rs.getString("no"));//
				item.setUserName(rs.getString("name"));//
				
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
}
