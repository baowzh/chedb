package com.forum.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forum.model.ModelBusinote;
import com.forum.util.DaoUtil;


public class BuynoteDaoImpl {
	private Connection conn;
	private Statement stat;
	
	public boolean addBusi(ModelBusinote t) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);
		try {
			String updateJifen="";

			if (t.getPayType()==3) {// 如果是积分抵扣，扣用户积分
				String sqlQUser = "select jifen from user where no='"+t.getUserId()+"'";
				ResultSet rs = stat.executeQuery(sqlQUser);
				int jifen = 0;
				while (rs.next()){
					jifen = rs.getInt("jifen");
					break;
				}
				if (t.getPriceJifen() > jifen) {// 积分不够
					return false;
				}
				updateJifen = "update user set jifen=jifen-"+t.getPriceJifen()+" where no='" + t.getUserId()+ "'";
				stat.executeUpdate(updateJifen);	
			}		
			
			Date tt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-");
			int random = (int)(Math.random()*900);
			String no = sdf.format(tt)+(random + 100);
			String sqlAdd = "insert into busi_record(no,pay_type,price,price_jifen,return_jifen,pay_time,service_class_id,service_id,status,provider_id,user_id,enable)"+
					 "values('"+ no+"'," + t.getPayType()+","+t.getPrice()+","+t.getPriceJifen()+","+t.getReturnJifen()+",now(),'"+
					  t.getServiceClassId()+"','"+t.getServiceId()+"',1,'"+t.getProviderId()+"','"+t.getUserId()+"',1)";
			stat.executeUpdate(sqlAdd);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateBusinoteInfo(String busiNo, String newStatus, String score) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);

		String sqlQBusi = "select pay_type, price_jifen, return_jifen, service_class_id, service_id, provider_id, user_id from busi_record where no='"
				+ busiNo + "'";
		int type = 1;
		int price_jifen = 0;
		int return_jifen = 0;
		String serviceId = "";
		String serviceClassId = "";
		String providerId = "";
		String user_id = "";
		try {
			ResultSet rs = stat.executeQuery(sqlQBusi);
			while (rs.next()) {
				type = rs.getInt("pay_type");
				price_jifen = rs.getInt("price_jifen");
				return_jifen = rs.getInt("return_jifen");
				serviceClassId = rs.getString("service_class_id");
				serviceId = rs.getString("service_id");
				providerId = rs.getString("provider_id");
				user_id = rs.getString("user_id");
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sqlUpdate = "";
		if (newStatus != null) {			
			// 积分处理
			try {
				if (Integer.parseInt(newStatus)== 0) {// 取消购买
					if (type == 3) {// 如果是积分抵付，把积分归还回去			
						String updateJifen = "update user set jifen=jifen+"+price_jifen+" where no='" + user_id+ "'";
						stat.executeUpdate(updateJifen);
					}
				} else if (Integer.parseInt(newStatus)== 3) {// 用户点“完成”
					if (type == 1) {// 如果是现金，则返回积分			
						String updateJifen = "update user set jifen=jifen+"+return_jifen+" where no='" + user_id+ "'";
						stat.executeUpdate(updateJifen);
					}
					// 将商家服务次数加1，将服务类交易次数加1
					String updateProvider = "update provider set business=business+1 where id='" + providerId+ "'";
					stat.executeUpdate(updateProvider);
					String updateServiceClass = "update service_class set business_num=business_num+1 where class_id='" + serviceClassId+ "'";
					stat.executeUpdate(updateServiceClass);
					String updateService = "update service set business_num=business_num+1 where service_id='" + serviceId+ "'";
					stat.executeUpdate(updateService);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlUpdate = "update busi_record set status="+newStatus+" where no='" + busiNo+ "'";
		} else if (score != null) {			
			sqlUpdate = "update busi_record set status=4, score="+score+" where no='" + busiNo+ "'";
			
			// 更新商家得分
			ProviderDaoImpl pdao = new ProviderDaoImpl();
			pdao.updateScore(providerId, score);
		} else {
			return false;
		}
		// 交易状态处理
		try {
			stat.executeUpdate(sqlUpdate);
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 查询系统项目类别
	 * @param 
	 * @return
	 */
	public List<ModelBusinote> getBuynoteListByUser(String userId, int page){
		int start = 10*page;
		String sql = "select busi_record.*, service.service_name, provider.title, user.name "+
					" from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"+
					" and busi_record.user_id='"+userId+"' "+
					" and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and user.no='"+userId+
					"' order by no desc limit  "+start+","+(start+10);
			
		return getListBySql(sql);	
	}
	public List<ModelBusinote> getBuynoteListByProviderId(String providerId, int page){
		int start = 10*page;
		String sql = "select busi_record.*, service.service_name, provider.title, user.name "+
					" from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"+
					" and busi_record.provider_id='"+providerId+"' "+
					" and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and busi_record.user_id=user.no"+
					" order by no desc limit  "+start+","+(start+10);
			
		return getListBySql(sql);	
	}
	
	public ModelBusinote getBusinoteById(String no) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ModelBusinote item = null;
		String sql = "select status from busi_record where no='"+no+"'";
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				item = new ModelBusinote();
				item.setStatus(rs.getInt("status"));
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
	
//	public List<ModelBusinote> getBuynoteListByProvider(String prividerId, int page){
//		int start = 10*page;
//		String sql = "select * from buynote where provider_id='" + prividerId + "' order by id desc " + " limit "+start+","+(start+10);
//			
//		return getListBySql(sql);	
//	}
	
	private List<ModelBusinote> getListBySql(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelBusinote> list = new ArrayList<ModelBusinote>();
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				ModelBusinote item = new ModelBusinote();
				
//				item.setId(rs.getString("id"));
				item.setBuynoteNo(rs.getString("no"));
				item.setPayType(rs.getInt("pay_type"));
				item.setPrice(rs.getInt("price"));
				item.setPriceJifen(rs.getInt("price_jifen"));
				item.setReturnJifen(rs.getInt("return_jifen"));
				item.setServiceId(rs.getString("service_id"));
				item.setStatus(rs.getInt("status"));
				item.setProviderId(rs.getString("provider_id"));
				item.setProviderName(rs.getString("title"));
				item.setScore(rs.getInt("score"));
				item.setServiceClassId(rs.getString("service_class_id"));
				item.setServiceName(rs.getString("service_name"));
				item.setUserName(rs.getString("name"));
//				
//				Timestamp date  = rs.getTimestamp("buy_date");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//				item.setDate(sdf.format(date));
//				
//				item.setPrice(rs.getDouble("price"));
//				item.setAppraiseList(rs.getString("appraise_list"));
//				item.setType(rs.getInt("type"));
				
				list.add(item);
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
}
