package com.chedb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.CarDao;
import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;
import com.forum.util.DaoUtil;

//
@Repository("buynoteDaoImpl")
public class CarDaoImpl implements CarDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	private Connection conn;
	private Statement stat;

	public List<ModelCarBrand> getCarBrandList() {
		String sql = "select id,name from car_brand where enable=1 and id in(select brand_id from car) order by sort ";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCarBrand>() {

			@Override
			public ModelCarBrand mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelCarBrand item = new ModelCarBrand();

				item.setId(rs.getString("id"));//
				item.setName(rs.getString("name"));
				return item;
			}

		});

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return null; }
		 * stat = DaoUtil.getStatement(conn); ArrayList<ModelCarBrand> list =
		 * new ArrayList<ModelCarBrand>();
		 * 
		 * String sql =
		 * "select id,name from car_brand where enable=1 and id in(select brand_id from car) order by sort "
		 * ; try { ResultSet rs = stat.executeQuery(sql); while (rs.next()){
		 * ModelCarBrand item = new ModelCarBrand();
		 * 
		 * item.setId(rs.getString("id"));// item.setName(rs.getString("name"));
		 * // item.setRemark(rs.getString("remark"));
		 * 
		 * list.add(item); } conn.close(); stat.close(); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return
		 * list;
		 */
	}

	public List<ModelCarSerise> getCarSeriseListByBrand(String brandId) {
		conn = DaoUtil.getConnection();
		if (conn == null) {
			return null;
		}
		stat = DaoUtil.getStatement(conn);
		ArrayList<ModelCarSerise> list = new ArrayList<ModelCarSerise>();
		String sql = "select series_id, series_name from car_series where enable=1 and brand_id='"
				+ brandId + "' order by sort";

		try {
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				ModelCarSerise item = new ModelCarSerise();

				item.setId(rs.getString("series_id"));

				item.setName(rs.getString("series_name"));//
				//
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

	public List<ModelCar> getCarListBySerise(String seriseId) {
		String sql = "select car_id, car_name, maintain_path from car where enable=1 and series_id='"
				+ seriseId + "' order by sort";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCar>() {

			@Override
			public ModelCar mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				ModelCar item = new ModelCar();

				item.setId(rs.getString("car_id"));//
				item.setName(rs.getString("car_name"));//
				item.setMaintainPath(rs.getString("maintain_path"));//
				return item;
			}

		});

		/*
		 * conn = DaoUtil.getConnection(); if (conn == null) { return null; }
		 * stat = DaoUtil.getStatement(conn); ArrayList<ModelCar> list = new
		 * ArrayList<ModelCar>(); String sql =
		 * "select car_id, car_name, maintain_path from car where enable=1 and series_id='"
		 * + seriseId + "' order by sort";
		 * 
		 * try { ResultSet rs = stat.executeQuery(sql); while (rs.next()) {
		 * ModelCar item = new ModelCar();
		 * 
		 * item.setId(rs.getString("car_id"));//
		 * item.setName(rs.getString("car_name"));//
		 * item.setMaintainPath(rs.getString("maintain_path"));//
		 * 
		 * list.add(item); } conn.close(); stat.close(); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return
		 * list;
		 */
	}
}
