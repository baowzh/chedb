package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.UserDao;
import com.forum.model.ModelCar;
import com.forum.model.ModelUser;
import com.forum.util.ServerConfig;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
	// private Connection conn;
	// private Statement stat;
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean delete(int id) {
		try {
			this.jdbcTemplate.execute("delete from user where id=" + id);
		} catch (Exception ex) {
			return false;
		}
		return true;
		// conn = DaoUtil.getConnection();
		// stat = DaoUtil.getStatement(conn);
		// try {
		// stat.executeUpdate("delete from user where id=" + id);
		// conn.close();
		// stat.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return false;
		// }
		// return true;
	}

	//
	/**
	 * ��ȡһ���µ��û�ID
	 * 
	 * @param type
	 *            ���û����ͣ�1Ϊ�����û���2Ϊ�����û���3Ϊ����̼��û�
	 * @return �µ��û�ID
	 *         ��õ���ID����ע�ᣬ���ܿ��ܲ���ע�ᡣ�µ�ID��user����״̬Ϊ0������ע���״̬Ϊ1����ID���ɺ��Сʱ��û������ע����ɱ��ظ�ʹ��
	 */
	public String getNewId(int type) throws Exception {

		String newId = null;
		int noLen = 8;// �����û�id����
		if (type == 2)
			noLen = 6;// �̼��û�
		else if (type == 3) {
			noLen = 5;// ����̼�
		}
		// �Ȳ�ѯ��û��δΪע���ID��������ID�����ڱ��У����û��ڰ�Сʱ��û��������ע��
		// Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		// c.setTime(nowDate);
		c.add(Calendar.MINUTE, -30);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time30m = sdf.format(c.getTime());
		String unuseIdSql = "select no from user where status=0 and LENGTH(no)="
				+ noLen
				+ " and regdate < '"
				+ time30m
				+ "'- INTERVAL 30 MINUTE order by regdate limit 1";
		newId = this.jdbcTemplate.queryForObject(unuseIdSql,
				java.lang.String.class);
		// ResultSet rs = stat.executeQuery(unuseIdSql);
		// while (rs.next()) {
		// newId = rs.getString("no");
		// }

		// ����п��õ�ID������������������IDʱ�����
		if (newId != null) {
			// stat.executeUpdate("update user set regdate=now() where no='"
			// + newId + "'");
			this.jdbcTemplate.update("update user set regdate=now() where no='"
					+ newId + "'");
		} else {
			// û�п��õ�ID��������һ����ȡ�ִ� �����ֵ
			String maxNoSql = "select max(no) as maxno from user where LENGTH(no)="
					+ noLen;
			newId = this.jdbcTemplate.queryForObject(maxNoSql,
					java.lang.String.class);
			// ResultSet rsMaxNo = stat.executeQuery(maxNoSql);
			// while (rsMaxNo.next()) {
			// newId = rsMaxNo.getString("maxno");
			// break;
			// }
			if (newId != null) {
				newId = (Long.parseLong(newId) + 1) + "";
				// ���µ�IDд����У�statusΪ0
				String addIdSql = "insert into user(no,name,level,status,regdate) "
						+ "values('" + newId + "','���û�'," + type + ",0,now())";
				this.jdbcTemplate.execute(addIdSql);
				// stat.executeUpdate(addIdSql);
			}
		}
		// conn.close();
		// stat.close();

		return newId;
		/*
		 * String newId = null; conn = DaoUtil.getConnection(); if (conn ==
		 * null) { return null; } stat = DaoUtil.getStatement(conn); int noLen =
		 * 8;// �����û�id���� if (type == 2) noLen = 6;// �̼��û� else if (type == 3) {
		 * noLen = 5;// ����̼� } try { // �Ȳ�ѯ��û��δΪע���ID��������ID�����ڱ��У����û��ڰ�Сʱ��û��������ע��
		 * // Date nowDate = new Date(); Calendar c = Calendar.getInstance(); //
		 * c.setTime(nowDate); c.add(Calendar.MINUTE, -30);
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * String time30m = sdf.format(c.getTime()); String unuseIdSql =
		 * "select no from user where status=0 and LENGTH(no)=" + noLen +
		 * " and regdate < '" + time30m +
		 * "'- INTERVAL 30 MINUTE order by regdate limit 1"; ResultSet rs =
		 * stat.executeQuery(unuseIdSql); while (rs.next()) { newId =
		 * rs.getString("no"); }
		 * 
		 * // ����п��õ�ID������������������IDʱ����� if (newId != null) {
		 * stat.executeUpdate("update user set regdate=now() where no='" + newId
		 * + "'"); } else { // û�п��õ�ID��������һ����ȡ�ִ� �����ֵ String maxNoSql =
		 * "select max(no) as maxno from user where LENGTH(no)=" + noLen;
		 * ResultSet rsMaxNo = stat.executeQuery(maxNoSql); while
		 * (rsMaxNo.next()) { newId = rsMaxNo.getString("maxno"); break; } if
		 * (newId != null) { newId = (Long.parseLong(newId) + 1) + ""; //
		 * ���µ�IDд����У�statusΪ0 String addIdSql =
		 * "insert into user(no,name,level,status,regdate) " + "values('" +
		 * newId + "','���û�'," + type + ",0,now())";
		 * stat.executeUpdate(addIdSql); } } conn.close(); stat.close();
		 * 
		 * return newId; } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } return null;
		 */
	}

	public boolean register(ModelUser user) throws Exception {
		// �Ƿ�ע��
		String esxitSql = "select no from user where status=0 and no='"
				+ user.getNo() + "'";
		List<Map<String, Object>> result = this.jdbcTemplate
				.queryForList(esxitSql);
		int row = 0;
		if (!result.isEmpty()) {
			row++;
		}
		if (row == 0) {
			return false;
		}
		// �޸�״̬Ϊ1
		String updateSql = "update user set status=1,name='" + user.getName()
				+ "',passwd='" + user.getPasswd() + "',phone='"
				+ user.getPhone() + "' where no='" + user.getNo() + "'";
		this.jdbcTemplate.execute(updateSql);
		// stat.executeUpdate(updateSql);
		// conn.close();
		// stat.close();
		return true;

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return false; }
		 * stat = DaoUtil.getStatement(conn); try { // �Ƿ�ע�� String esxitSql =
		 * "select no from user where status=0 and no='" + user.getNo() + "'";
		 * ResultSet rs = stat.executeQuery(esxitSql);
		 * 
		 * int row = 0; while (rs.next()) { row++; break; } if (row == 0) {
		 * return false; } // �޸�״̬Ϊ1 String updateSql =
		 * "update user set status=1,name='" + user.getName() + "',passwd='" +
		 * user.getPasswd() + "',phone='" + user.getPhone() + "' where no='" +
		 * user.getNo() + "'"; stat.executeUpdate(updateSql); conn.close();
		 * stat.close(); } catch (SQLException e) { e.printStackTrace(); return
		 * false; } return true;
		 */
	}

	//
	// public boolean save(User t) {
	// conn = DaoUtil.getConnection();
	// stat = DaoUtil.getStatement(conn);
	// try {
	// stat.executeUpdate("insert into user(username,password,truename) values('"
	// + t.getUserName()
	// + "','"
	// + t.getPassword()
	// + "','"
	// + t.getTrueName() + "')");
	// conn.close();
	// stat.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return false;
	// }
	// return true;
	// }

	public boolean update(String userNo, String type, String content) {

		String updateStr = "";
		if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Name)
			updateStr = "name='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Passwd)
			updateStr = "passwd='" + content + "'";
		else if (Integer.parseInt(type) == ServerConfig.OperType_EditUser_Phone)
			updateStr = "phone='" + content + "'";
		else
			return false;

		this.jdbcTemplate.execute("update user set " + updateStr + " where no="
				+ userNo);
		return true;

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return false; }
		 * stat = DaoUtil.getStatement(conn);
		 * 
		 * String updateStr = ""; if (Integer.parseInt(type) ==
		 * ServerConfig.OperType_EditUser_Name) updateStr = "name='" + content +
		 * "'"; else if (Integer.parseInt(type) ==
		 * ServerConfig.OperType_EditUser_Passwd) updateStr = "passwd='" +
		 * content + "'"; else if (Integer.parseInt(type) ==
		 * ServerConfig.OperType_EditUser_Phone) updateStr = "phone='" + content
		 * + "'"; else return false; try { stat.executeUpdate("update user set "
		 * + updateStr + " where no=" + userNo); conn.close(); stat.close(); }
		 * catch (SQLException e) { e.printStackTrace(); return false; } return
		 * true;
		 */
	}

	public ModelUser getUserByPhone(String phone) {

		return this.jdbcTemplate.queryForObject(
				"select * from user where phone='" + phone + "'",
				new RowMapper<ModelUser>() {

					@Override
					public ModelUser mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelUser user = new ModelUser();
						user.setNo(rs.getString("no"));
						user.setName(rs.getString("name"));
						user.setPasswd(rs.getString("passwd"));
						user.setLevel(rs.getInt("level"));
						user.setPhone(rs.getString("phone"));
						return user;
					}

				});

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return null; }
		 * stat = DaoUtil.getStatement(conn); ModelUser user = null; try {
		 * ResultSet rs = stat.executeQuery("select * from user where phone='" +
		 * phone + "'"); while (rs.next()) { user = new ModelUser();
		 * 
		 * user.setNo(rs.getString("no")); user.setName(rs.getString("name"));
		 * user.setPasswd(rs.getString("passwd"));
		 * user.setLevel(rs.getInt("level"));
		 * user.setPhone(rs.getString("phone"));
		 * 
		 * break; } conn.close(); stat.close(); } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } return user;
		 */
	}

	public ModelUser getUser(String userNo) {

		return this.jdbcTemplate.queryForObject("select * from user where no='"
				+ userNo + "'", new RowMapper<ModelUser>() {
			@Override
			public ModelUser mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				ModelUser user = new ModelUser();
				user.setNo(rs.getString("no"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setLevel(rs.getInt("level"));
				user.setPhone(rs.getString("phone"));
				user.setJifen(rs.getInt("jifen"));
				return user;
			}

		});

		// return user;

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return null; }
		 * stat = DaoUtil.getStatement(conn); ModelUser user = null; try { //
		 * ResultSet rs = //
		 * stat.executeQuery("select * from user where username='"
		 * +userName+"' AND password='"+password+"'"); ResultSet rs =
		 * stat.executeQuery("select * from user where no='" + userNo + "'");
		 * while (rs.next()) { user = new ModelUser();
		 * 
		 * user.setNo(rs.getString("no")); user.setName(rs.getString("name"));
		 * user.setPasswd(rs.getString("passwd"));
		 * user.setLevel(rs.getInt("level"));
		 * user.setPhone(rs.getString("phone"));
		 * user.setJifen(rs.getInt("jifen")); } conn.close(); stat.close(); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return user;
		 */
	}

	public List<ModelCar> getChoosedCarByUser(String userId) {

		String sql = "select car.car_id, car.car_name, car.maintain_path from car, user_car "
				+ " where user_car.user_id='"
				+ userId
				+ "' and car.car_id=user_car.car_id "
				+ " order by choose_date desc limit 2";
		List<ModelCar> resultlist = this.jdbcTemplate.query(sql,
				new RowMapper<ModelCar>() {
					@Override
					public ModelCar mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelCar item = new ModelCar();
						item.setId(rs.getString("car_id"));//
						item.setName(rs.getString("car_name"));//
						item.setMaintainPath(rs.getString("maintain_path"));//
						return item;
					}
				});

		if (resultlist.size() == 0) {// Ĭ�ϵ�
			ModelCar item = new ModelCar();
			item.setId("4012011003");//
			item.setName("Ĭ�ϳ���");//
			resultlist.add(item);
		}
		return resultlist;

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return null; }
		 * stat = DaoUtil.getStatement(conn); ArrayList<ModelCar> list = new
		 * ArrayList<ModelCar>(); String sql =
		 * "select car.car_id, car.car_name, car.maintain_path from car, user_car "
		 * + " where user_car.user_id='" + userId +
		 * "' and car.car_id=user_car.car_id " +
		 * " order by choose_date desc limit 2"; try { ResultSet rs =
		 * stat.executeQuery(sql); while (rs.next()) { ModelCar item = new
		 * ModelCar();
		 * 
		 * item.setId(rs.getString("car_id"));//
		 * item.setName(rs.getString("car_name"));//
		 * item.setMaintainPath(rs.getString("maintain_path"));//
		 * 
		 * list.add(item); } conn.close(); stat.close(); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } if
		 * (list.size() == 0) {// Ĭ�ϵ� ModelCar item = new ModelCar();
		 * item.setId("4012011003");// item.setName("Ĭ�ϳ���");//
		 * 
		 * list.add(item); } return list;
		 */
	}

	public boolean addChoosedCarByAppId(String carId, String appId) {

		String id = null;
		String existSql = "select id from user_car " + " where user_id='"
				+ appId + "' and car_id='" + carId + "'";
		id=this.jdbcTemplate.queryForObject(existSql, java.lang.String.class);
		// ResultSet rs = stat.executeQuery(existSql);
		// while (rs.next()) {
		// id = rs.getString("id");
		// }
		if (id == null) {
			String addIdSql = "insert into user_car(user_id,car_id,choose_date) "
					+ "values('" + appId + "','" + carId + "',now())";
			//stat.executeUpdate(addIdSql);
			this.jdbcTemplate.execute(addIdSql);
		} else {
			String updatedSql = "update user_car set choose_date=now() where id="
					+ id;
			//stat.executeUpdate(updatedSql);
			this.jdbcTemplate.execute(updatedSql);
		}

		return true;

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return false; }
		 * stat = DaoUtil.getStatement(conn); try { //
		 * ����û��Ƿ�ѡ����ó��ͣ����ѡ����������ѡ��ʱ�� String id = null; String existSql =
		 * "select id from user_car " + " where user_id='" + appId +
		 * "' and car_id='" + carId + "'"; ResultSet rs =
		 * stat.executeQuery(existSql); while (rs.next()) { id =
		 * rs.getString("id"); } if (id == null) { String addIdSql =
		 * "insert into user_car(user_id,car_id,choose_date) " + "values('" +
		 * appId + "','" + carId + "',now())"; stat.executeUpdate(addIdSql); }
		 * else { String updatedSql =
		 * "update user_car set choose_date=now() where id=" + id;
		 * stat.executeUpdate(updatedSql); } conn.close(); stat.close(); } catch
		 * (SQLException e) { e.printStackTrace(); return false; } return true;
		 */
	}

	// 1001:�򿪳���
	// 1002:�˳�����
	// 2001��ע��
	// 2002����¼
	// 3001��ѡ���³���
	// 3002��������
	public boolean addUserConfig(String operType, String appId,
			String operContent) {
		String addIdSql = "insert into user_config(app_id, oper_type, oper_content, oper_time) "
				+ "values('"
				+ appId
				+ "','"
				+ operType
				+ "','"
				+ operContent
				+ "',now())";
		this.jdbcTemplate.execute(addIdSql);
		return true;
		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return false; }
		 * stat = DaoUtil.getStatement(conn); try { String addIdSql =
		 * "insert into user_config(app_id, oper_type, oper_content, oper_time) "
		 * + "values('" + appId + "','" + operType + "','" + operContent +
		 * "',now())"; stat.executeUpdate(addIdSql);
		 * 
		 * conn.close(); stat.close(); } catch (SQLException e) {
		 * e.printStackTrace(); return false; } return true;
		 */
	}
}
