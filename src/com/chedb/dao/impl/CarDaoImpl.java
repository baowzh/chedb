package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.CarDao;
import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;

//
@Repository("carDaoImpl")
public class CarDaoImpl implements CarDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

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

	}

	public List<ModelCarSerise> getCarSeriseListByBrand(String brandId) {

		String sql = "select series_id, series_name from car_series where enable=1 and brand_id='"
				+ brandId + "' order by sort";
		return this.jdbcTemplate.query(sql, new RowMapper<ModelCarSerise>() {

			@Override
			public ModelCarSerise mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelCarSerise item = new ModelCarSerise();

				item.setId(rs.getString("series_id"));

				item.setName(rs.getString("series_name"));//
				return item;
			}

		});

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

	}
}
