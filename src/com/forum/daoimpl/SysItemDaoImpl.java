package com.forum.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ModelSysItem;
import com.forum.util.ServerConfig;
import com.forum.util.DaoUtil;
//import com.forum.dao.BaseDao;

//
public class SysItemDaoImpl {
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
//
//	public User get(int id) {
//		conn = DaoUtil.getConnection();
//		stat = DaoUtil.getStatement(conn);
//		User user = null;
//		try {
//			ResultSet rs = stat.executeQuery("select * from user where id="+id);
//			while (rs.next()){
//				User temp = new User();
//				temp.setId(rs.getInt(1));
//				temp.setUserName(rs.getString(2));
//				temp.setPassword(rs.getString(3));
//				temp.setTrueName(rs.getString(4));
//				user = temp;
//			}
//			conn.close();
//			stat.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return user;
//	}

	public boolean update(String userNo, String type, String content) {
		conn = DaoUtil.getConnection();
		stat = DaoUtil.getStatement(conn);
		
		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Name)
			updateStr = "name='"+content+"'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Passwd)
			updateStr = "passwd='"+content+"'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Phone)
			updateStr = "phone='"+content+"'";
		else 
			return false;
		try {
			stat.executeUpdate("update user set "+updateStr+" where no="+userNo);
			conn.close();
			stat.close();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
//	
//	public List<User> getAll() {
//		conn = DaoUtil.getConnection();
//		stat = DaoUtil.getStatement(conn);
//		ArrayList<User> list = new ArrayList<User>();
//		try {
//			ResultSet rs = stat.executeQuery("select * from user");
//			while (rs.next()){
//				User temp = new User();
//				temp.setId(rs.getInt(1));
//				temp.setUserName(rs.getString(2));
//				temp.setPassword(rs.getString(3));
//				temp.setTrueName(rs.getString(4));
//				list.add(temp);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
	
//	public boolean exsitUser()
	/**
	 * 查询系统项目类别
	 * @param level 1为修理厂，2为配件商
	 * @return
	 */
	public List<ModelSysItem> getSysItemClass(String level){
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelSysItem> list = new ArrayList<ModelSysItem>();
		try {
//			ResultSet rs = stat.executeQuery("select * from user where username='"+userName+"' AND password='"+password+"'");
			ResultSet rs = stat.executeQuery("select * from sys_item_class where status=1 and level="+level+" order by sort");
			while (rs.next()){
				ModelSysItem item = new ModelSysItem();
				
				item.setLabelId(rs.getString("id"));
				item.setTitle(rs.getString("title"));
//				item.setPasswd(rs.getString("passwd"));
//				item.setLevel(rs.getInt("level"));
//				item.setPhone(rs.getString("phone"));
				
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
	
	/**
	 * 查询商家的服务项目所对应的系统项目
	 * @param providerId
	 * @return
	 */
	public List<ModelSysItem> getProviderSysItem(String providerId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelSysItem> list = new ArrayList<ModelSysItem>();
		try {
			ResultSet rs = stat.executeQuery("select DISTINCT sys_item_id from provider_item where status=1 and provider_id='"+providerId+"'");
			while (rs.next()){
				String sysItemId = rs.getString("sys_item_id");
				ModelSysItem item = getSysItemById(sysItemId);
				if (item != null) {			
					list.add(item);
				}
			}
			conn.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询系统项目
	 * @param level 1为修理厂，2为配件商
	 * @return
	 */
	public List<ModelSysItem> getSysItemByClassId(String classId){
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelSysItem> list = new ArrayList<ModelSysItem>();
		try {
//			ResultSet rs = stat.executeQuery("select * from user where username='"+userName+"' AND password='"+password+"'");
			ResultSet rs = stat.executeQuery("select * from sys_item where status=1 and class_item_id='"+classId+"' order by sort");
			while (rs.next()){
				ModelSysItem item = new ModelSysItem();
				
				item.setLabelId(rs.getString("id"));
				item.setTitle(rs.getString("title"));
				item.setParentId(classId);
//				item.setLevel(rs.getInt("level"));
//				item.setPhone(rs.getString("phone"));
				
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
	
	/**
	 * 查询系统项目
	 * @param level 1为修理厂，2为配件商
	 * @return
	 */
	public ModelSysItem getSysItemById(String itemId){
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ModelSysItem item = null;
//		ArrayList<ModelSysItem> list = new ArrayList<ModelSysItem>();
		try {
//			ResultSet rs = stat.executeQuery("select * from user where username='"+userName+"' AND password='"+password+"'");
			ResultSet rs = stat.executeQuery("select * from sys_item where status=1 and id='"+itemId+"'");
			while (rs.next()){
				item = new ModelSysItem();
				
				item.setLabelId(itemId);
				item.setTitle(rs.getString("title"));
				item.setParentId(rs.getString("class_item_id"));
//				item.setLevel(rs.getInt("level"));
//				item.setPhone(rs.getString("phone"));
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
}
