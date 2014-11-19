package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.ServiceClassDao;
import com.chedb.dao.ServiceDao;
import com.forum.model.ModelProvider;
import com.forum.model.ModelService;
import com.forum.model.ModelServiceClass;
import com.forum.model.ModelUserAppraise;

@Repository("serviceDaoImpl")
public class ServiceDaoImpl implements ServiceDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "serviceClassDaoImpl")
	private ServiceClassDao serviceClassDao;

	public ModelService getServiceInfo(final String serviceId) {

		String sql = "select * from service where enable=1 and service_id='"
				+ serviceId + "'";
		return this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelService>() {

					@Override
					public ModelService mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelService item = new ModelService();
						// String serviceId = rs.getString("service_id");
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
						// item.setBusinessNum(businessNum);(rs.getInt("business_initnum"));//
						item.setBusinessNum(rs.getInt("business_num"));//
						item.setWorkTitle(rs.getString("wrok_title"));//
						item.setWorkUrlPath(rs.getString("workdoc_path"));//
						item.setRawUrlPath(rs.getString("rawdoc_path"));//
						String sqlItem = "select item_name, raw_name from service_item where enable=1 and service_id='"
								+ serviceId + "'";
						List<Map<String, Object>> result = jdbcTemplate
								.queryForList(sqlItem);
						int count = 0;
						for (Map<String, Object> row : result) {
							if (count == 0) {
								item.setItem1ClassTxt("" + row.get("item_name"));
								item.setItem1Txt("" + row.get("raw_name"));
							} else if (count == 1) {
								item.setItem2ClassTxt("" + row.get("item_name"));
								item.setItem2Txt("" + row.get("raw_name"));
							} else if (count == 2) {
								item.setItem3ClassTxt("" + row.get("item_name"));
								item.setItem3Txt("" + row.get("raw_name"));
							} else if (count == 3) {
								item.setItem4ClassTxt("" + row.get("item_name"));
								item.setItem4Txt("" + row.get("raw_name"));
							}
							count++;
						}
						item.setItemNum(count);
						return item;
					}

				});

	}

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelService> getServiceListByClassId(String classId,
			String carId) throws Exception {
		String sql = "";
		// ServiceClassDaoImpl classDao = new ServiceClassDaoImpl();
		ModelServiceClass serviceClass = serviceClassDao
				.queryServiceClassById(classId);
		if (serviceClass.getNeedCar() == 1) {
			sql = "select * from service where enable=1 and service_class_id='"
					+ classId
					+ "' and service_id in (select service_id from car_service where car_id='"
					+ carId + "')";
		} else {
			sql = "select * from service where enable=1 and service_class_id='"
					+ classId + "'";
		}
		return getServiceListBySql(sql);
	}

	private List<ModelService> getServiceListBySql(String sql) {

		return this.jdbcTemplate.query(sql, new RowMapper<ModelService>() {

			@Override
			public ModelService mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelService item = new ModelService();
				String serviceId = rs.getString("service_id");
				item.setServiceId(rs.getString("service_id"));
				item.setClassId(rs.getString("service_class_id"));//
				item.setName(rs.getString("service_name"));
				// item.setRemark(rs.getString("remark"));
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
				String sqlItem = "select item_name, raw_name from service_item where enable=1 and service_id='"
						+ serviceId + "'";
				List<Map<String, Object>> results = jdbcTemplate
						.queryForList(sqlItem);
				int count = 0;
				for (Map<String, Object> row : results) {
					if (count == 0) {
						item.setItem1ClassTxt("" + row.get("item_name"));
						item.setItem1Txt("" + row.get("raw_name"));
					} else if (count == 1) {
						item.setItem2ClassTxt("" + row.get("item_name"));
						item.setItem2Txt("" + row.get("raw_name"));
					} else if (count == 2) {
						item.setItem3ClassTxt("" + row.get("item_name"));
						item.setItem3Txt("" + row.get("raw_name"));
					} else if (count == 3) {
						item.setItem4ClassTxt("" + row.get("item_name"));
						item.setItem4Txt("" + row.get("raw_name"));
					}
					count++;
				}
				item.setItemNum(count);
				return item;
			}

		});

	}

	public int getProviderCountByServiceId(String serviceId) {
		int count = 0;
		String sql = "SELECT count(1) as count FROM service_provider where service_id='"
				+ serviceId + "' and enable=1";
		Integer counti = this.jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);
		if (counti != null) {
			count = counti.intValue();
		}
		return count;

	}

	public List<ModelProvider> getProviderListByServiceId(String serviceId,
			String start, String count, String latitude, String longitude) {
		String sql = "select * from provider where status=1 and level=1 and id in(select provider_id from service_provider where service_id='"
				+ serviceId + "') ";
		// Ĭ�ϰ�λ������
		if (latitude != null && latitude.length() > 0 && longitude != null
				&& longitude.length() > 0) {
			sql += " order by abs(latitude-" + latitude + ")+abs(longitude-"
					+ longitude + ")";
		}
		sql += " limit " + start + "," + count;

		return this.jdbcTemplate.query(sql, new RowMapper<ModelProvider>() {

			@Override
			public ModelProvider mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelProvider item = new ModelProvider();
				item.setProviderId(rs.getString("id"));
				item.setTitle(rs.getString("title"));//
				item.setBrowse(rs.getInt("browse"));
				item.setBusiness(rs.getInt("business"));
				item.setScore(rs.getFloat("score"));//
				item.setScoreCount(rs.getInt("score_count"));//
				item.setAddr(rs.getString("addr"));//
				item.setLatitude(rs.getDouble("latitude"));//
				item.setLongitude(rs.getDouble("longitude"));//
				item.setImgIdListStr(rs.getString("imgid_list"));
				return null;
			}

		});

	}

	public ModelUserAppraise doAppraise(String serviceClassId,
			String serviceId, String content, String userId) {

		ModelUserAppraise app = new ModelUserAppraise();
		String sqlAdd = "insert into service_appraise(service_class_id,service_id,user_id,content,appr_date,is_anonymous,agree_num,enable)"
				+ "values('"
				+ serviceClassId
				+ "','"
				+ serviceId
				+ "','"
				+ userId + "','" + content + "',now(),0,0,1)";
		this.jdbcTemplate.execute(sqlAdd);
		String sql = "select max(id) as id from service_appraise";
		Integer idi = this.jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);
		if (idi != null) {
			app.setTheId("" + idi.intValue());
		}

		Date tt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		app.setDateStr(sdf.format(tt));
		return app;

	}

	public int getAppraiseCountByServiceId(String serviceClassId,
			String serviceId) {
		String sql = "SELECT count(1) as count FROM service_appraise where service_class_id='"
				+ serviceClassId + "' and enable=1";
		Integer counti = this.jdbcTemplate.queryForObject(sql,
				java.lang.Integer.class);
		int count = 0;
		if (counti != null) {
			count = counti.intValue();
		}
		return count;

	}

	public List<ModelUserAppraise> getAppraiseListByServiceId(
			final String serviceClassId, final String serviceId,
			final String start, final String count) {
		String sql = "select a.id, a.content,a.appr_date,a.is_anonymous,a.agree_num,u.no,u.name "
				+ " from service_appraise as a, user as u "
				+ " where a.enable=1 and a.service_class_id='"
				+ serviceClassId
				+ "' and a.user_id=u.no order by a.appr_date desc limit "
				+ start + "," + count;
		return this.jdbcTemplate.query(sql, new RowMapper<ModelUserAppraise>() {

			@Override
			public ModelUserAppraise mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelUserAppraise item = new ModelUserAppraise();
				item.setTheId(rs.getInt("id") + "");
				item.setServiceClassId(serviceClassId);
				item.setServiceId(serviceId);//
				item.setAppraise(rs.getString("content"));
				item.setAgreeNum(rs.getInt("agree_num"));
				Date dt = rs.getTimestamp("appr_date");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String str = df.format(dt);
				item.setDateStr(str);
				item.setUserId(rs.getString("no"));//
				item.setUserName(rs.getString("name"));//
				return item;
			}

		});

	}
}
