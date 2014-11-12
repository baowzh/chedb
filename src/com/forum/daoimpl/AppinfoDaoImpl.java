package com.forum.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ModelPublicItem;
import com.forum.util.DaoUtil;


public class AppinfoDaoImpl {
	private Connection conn;
	private Statement stat;
//	
//	public boolean add(ModelBuynote t) {
//		conn = DaoUtil.getConnection();
//		stat = DaoUtil.getStatement(conn);
//		try {
//			String maxSortSql = "select max(id) as maxid from buynote";
//			long nowMaxId= 1;
//			ResultSet rs = stat.executeQuery(maxSortSql);
//			while (rs.next()){
//				nowMaxId = rs.getLong("maxid")+1;
//				break;
//			}
//			String sqlAdd = "insert into buynote(id,provider_id,provider_title,item_id,item_title,user_no,user_name,buy_date,price,type)"+
//					 "values("+ nowMaxId+",'" +	t.getProviderId()+"','"+t.getProviderName()+"','"+t.getItemId()+"','"+t.getItemName()
//					 + "','"+t.getUserId()+"','"+t.getUserName()+"',now(),"+t.getPrice()+"," +t.getType()+	")";
//			stat.executeUpdate(sqlAdd);
//			conn.close();
//			stat.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

	public boolean userCommit(String type, String strSingleTitleList, String text, String userId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return false;
		}
		stat = DaoUtil.getStatement(conn);
		try {
			String maxIdSql = "select max(id) as maxid from user_commit";
			long nowMaxId= 1;
			ResultSet rs = stat.executeQuery(maxIdSql);
			while (rs.next()){
				nowMaxId = rs.getLong("maxid")+1;
				break;
			}
			
			String sql = "insert into user_commit (id,type,content_list,text,user_id,commit_time) values('" +
					nowMaxId + "','"+ type + "','" + strSingleTitleList + "','" + text + "','" + userId + "',now())";
			stat.executeUpdate(sql);

			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 查询系统项目类别
	 * @param level 1为修理厂，2为配件商
	 * @return
	 */
	public String getAppinfoContentById(String id){
		String sql = "select * from app_topic where id='" + id + "'";
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		String content = null;
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				content = rs.getString("content");
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return content;
	}
	
	public List<ModelPublicItem> getAppinfoListByType(String type){
//		int start = 10*page;
		String sql = "select * from app_topic where status=1 and type='" + type + "'";
			
		return getListBySql(sql);	
	}
	
	private List<ModelPublicItem> getListBySql(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelPublicItem> list = new ArrayList<ModelPublicItem>();
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				ModelPublicItem item = new ModelPublicItem();
				
				item.setId(rs.getString("id"));
				item.setTitle(rs.getString("name"));

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
