package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.ServiceClassDao;
import com.forum.model.ModelServiceClass;

@Repository("serviceClassDaoImpl")
public class ServiceClassDaoImpl implements ServiceClassDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelServiceClass queryServiceClassById(String classId) {
		String sql = "select * from service_class where enable=1 and class_id = '"
				+ classId + "'";

		return getServiceClassInfo(sql);
	}

	private ModelServiceClass getServiceClassInfo(String sql) {

		return this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelServiceClass>() {

					@Override
					public ModelServiceClass mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelServiceClass item = new ModelServiceClass();
						item.setClassId(rs.getString("class_id"));

						item.setClassName(rs.getString("class_name"));//

						item.setBusinessNum(rs.getInt("business_num"));

						item.setWorkdocPath(rs.getString("workdoc_path"));//

						item.setNeedCar(rs.getInt("need_car"));//

						item.setRemark(rs.getString("remark"));
						return item;
					}

				});

	}

}
