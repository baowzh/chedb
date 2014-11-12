package com.forum.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ModelProvider;
import com.forum.model.ModelServiceClass;
import com.forum.util.DaoUtil;
import com.forum.util.ServerConfig;

//
public class ServiceClassDaoImpl {
	private Connection conn;
	private Statement stat;
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelServiceClass queryServiceClassById(String classId){
		String sql = "select * from service_class where enable=1 and class_id = '" + classId + "'";

		return getServiceClassInfo(sql);
	}
	
	private ModelServiceClass getServiceClassInfo(String sql) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ModelServiceClass item = null;
		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()){
				item = new ModelServiceClass();
				item.setClassId(rs.getString("class_id"));

				item.setClassName(rs.getString("class_name"));//

				item.setBusinessNum(rs.getInt("business_num"));

				item.setWorkdocPath(rs.getString("workdoc_path"));//

				item.setNeedCar(rs.getInt("need_car"));//

				item.setRemark(rs.getString("remark"));
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
