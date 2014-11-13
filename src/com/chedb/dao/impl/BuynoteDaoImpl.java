package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.BuynoteDao;
import com.forum.model.BsiRecordValue;
import com.forum.model.ModelBusinote;

@Repository("buynoteDaoImpl")
public class BuynoteDaoImpl implements BuynoteDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean addBusi(ModelBusinote t) {
		String updateJifen = "";
		if (t.getPayType() == 3) {// 如果是积分抵扣，扣用户积分
			String sqlQUser = "select jifen from user where no='"
					+ t.getUserId() + "'";
			int jifen = 0;
			jifen = this.jdbcTemplate.queryForObject(sqlQUser,
					java.lang.Integer.class);
			if (t.getPriceJifen() > jifen) {// 积分不够
				return false;
			}
			updateJifen = "update user set jifen=jifen-" + t.getPriceJifen()
					+ " where no='" + t.getUserId() + "'";
			this.jdbcTemplate.execute(updateJifen);
		}

		Date tt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-");
		int random = (int) (Math.random() * 900);
		String no = sdf.format(tt) + (random + 100);
		String sqlAdd = "insert into busi_record(no,pay_type,price,price_jifen,return_jifen,pay_time,service_class_id,service_id,status,provider_id,user_id,enable)"
				+ "values('"
				+ no
				+ "',"
				+ t.getPayType()
				+ ","
				+ t.getPrice()
				+ ","
				+ t.getPriceJifen()
				+ ","
				+ t.getReturnJifen()
				+ ",now(),'"
				+ t.getServiceClassId()
				+ "','"
				+ t.getServiceId()
				+ "',1,'" + t.getProviderId() + "','" + t.getUserId() + "',1)";
		this.jdbcTemplate.execute(sqlAdd);
		return true;
	}

	public boolean updateBusinoteInfo(String busiNo, String newStatus,
			String score) {
		String sqlQBusi = "select pay_type, price_jifen, return_jifen, service_class_id, service_id, provider_id, user_id from busi_record where no='"
				+ busiNo + "'";
		int type = 1;
		int price_jifen = 0;
		int return_jifen = 0;
		String serviceId = "";
		String serviceClassId = "";
		String providerId = "";
		String user_id = "";
		BsiRecordValue bsiRecordValuei = this.jdbcTemplate.queryForObject(
				sqlQBusi, new RowMapper<BsiRecordValue>() {
					@Override
					public BsiRecordValue mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						BsiRecordValue bsiRecordValue = new BsiRecordValue();
						bsiRecordValue.setType(rs.getInt("pay_type"));
						bsiRecordValue.setPrice_jifen(rs.getInt("price_jifen"));
						bsiRecordValue.setReturn_jifen(rs
								.getInt("return_jifen"));
						bsiRecordValue.setServiceClassId(rs
								.getString("service_class_id"));
						bsiRecordValue.setServiceId(rs.getString("service_id"));
						bsiRecordValue.setProviderId(rs.getString("service_id"));
						bsiRecordValue.setUser_id(rs.getString("user_id"));
						return bsiRecordValue;
					}

				});
		type = bsiRecordValuei.getType();
		price_jifen = bsiRecordValuei.getPrice_jifen();
		return_jifen = bsiRecordValuei.getReturn_jifen();
		serviceClassId = bsiRecordValuei.getServiceClassId();
		serviceId = bsiRecordValuei.getServiceId();
		providerId = bsiRecordValuei.getProviderId();
		user_id = bsiRecordValuei.getUser_id();

		String sqlUpdate = "";
		if (newStatus != null) {
			// 积分处理
			if (Integer.parseInt(newStatus) == 0) {// 取消购买
				if (type == 3) {// 如果是积分抵付，把积分归还回去
					String updateJifen = "update user set jifen=jifen+"
							+ price_jifen + " where no='" + user_id + "'";
					this.jdbcTemplate.execute(updateJifen);
				}
			} else if (Integer.parseInt(newStatus) == 3) {// 用户点“完成”
				if (type == 1) {// 如果是现金，则返回积分
					String updateJifen = "update user set jifen=jifen+"
							+ return_jifen + " where no='" + user_id + "'";

					this.jdbcTemplate.execute(updateJifen);
				}
				// 将商家服务次数加1，将服务类交易次数加1
				String updateProvider = "update provider set business=business+1 where id='"
						+ providerId + "'";
				this.jdbcTemplate.execute(updateProvider);

				String updateServiceClass = "update service_class set business_num=business_num+1 where class_id='"
						+ serviceClassId + "'";
				this.jdbcTemplate.execute(updateServiceClass);

				String updateService = "update service set business_num=business_num+1 where service_id='"
						+ serviceId + "'";
				this.jdbcTemplate.execute(updateService);

			}
			sqlUpdate = "update busi_record set status=" + newStatus
					+ " where no='" + busiNo + "'";
		} else if (score != null) {
			sqlUpdate = "update busi_record set status=4, score=" + score
					+ " where no='" + busiNo + "'";
			// 更新商家得分
			ProviderDaoImpl pdao = new ProviderDaoImpl();
			pdao.updateScore(providerId, score);
		} else {
			return false;
		}
		// 交易状态处理
		this.jdbcTemplate.execute(sqlUpdate);
		return true;
	}

	/**
	 * 查询系统项目类别
	 * 
	 * @param
	 * @return
	 */
	public List<ModelBusinote> getBuynoteListByUser(String userId, int page) {
		int start = 10 * page;
		String sql = "select busi_record.*, service.service_name, provider.title, user.name "
				+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
				+ " and busi_record.user_id='"
				+ userId
				+ "' "
				+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and user.no='"
				+ userId
				+ "' order by no desc limit  "
				+ start
				+ ","
				+ (start + 10);

		return getListBySql(sql);
	}

	public List<ModelBusinote> getBuynoteListByProviderId(String providerId,
			int page) {
		int start = 10 * page;
		String sql = "select busi_record.*, service.service_name, provider.title, user.name "
				+ " from busi_record, service, provider, user where busi_record.enable=1 and busi_record.status>0"
				+ " and busi_record.provider_id='"
				+ providerId
				+ "' "
				+ " and service.service_id=busi_record.service_id and busi_record.provider_id=provider.id and busi_record.user_id=user.no"
				+ " order by no desc limit  " + start + "," + (start + 10);

		return getListBySql(sql);
	}

	public ModelBusinote getBusinoteById(String no) {
		String sql = "select status from busi_record where no='" + no + "'";
		ModelBusinote item = this.jdbcTemplate.queryForObject(sql,
				new RowMapper<ModelBusinote>() {

					@Override
					public ModelBusinote mapRow(ResultSet rs, int arg1)
							throws SQLException {
						// TODO Auto-generated method stub
						ModelBusinote itemi = new ModelBusinote();
						itemi.setStatus(rs.getInt("status"));
						return itemi;
					}

				});
		return item;

	}

	private List<ModelBusinote> getListBySql(String sql) {
		return this.jdbcTemplate.query(sql, new RowMapper<ModelBusinote>() {

			@Override
			public ModelBusinote mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ModelBusinote item = new ModelBusinote();
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
				return item;
			}

		});

	}
	

}
