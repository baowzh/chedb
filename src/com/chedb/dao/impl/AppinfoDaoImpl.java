package com.chedb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chedb.dao.AppinfoDao;
import com.forum.model.ModelPublicItem;

@Repository("appinfoDaoImpl")
public class AppinfoDaoImpl implements AppinfoDao {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public boolean userCommit(String type, String strSingleTitleList,
			String text, String userId) {
		String maxIdSql = "select max(id) as maxid from user_commit";
		long nowMaxId = 1;
		Long maxid = jdbcTemplate
				.queryForObject(maxIdSql, java.lang.Long.class);
		if (maxid != null) {
			nowMaxId = maxid + 1;
		}

		String sql = "insert into user_commit (id,type,content_list,text,user_id,commit_time) values('"
				+ nowMaxId
				+ "','"
				+ type
				+ "','"
				+ strSingleTitleList
				+ "','"
				+ text + "','" + userId + "',now())";
		this.jdbcTemplate.execute(sql);

		return true;
	}

	/**
	 * 查询系统项目类别
	 * 
	 * @param level
	 *            1为修理厂，2为配件商
	 * @return
	 */
	public String getAppinfoContentById(String id) {
		String sql = "select * from app_topic where id='" + id + "'";
		return this.jdbcTemplate.queryForObject(sql, java.lang.String.class);
		
	}

	public List<ModelPublicItem> getAppinfoListByType(String type) {
		// int start = 10*page;
		String sql = "select * from app_topic where status=1 and type='" + type
				+ "'";
		return getListBySql(sql);
	}

	private List<ModelPublicItem> getListBySql(String sql) {
		return this.jdbcTemplate.query(sql, new RowMapper<ModelPublicItem>() {

			@Override
			public ModelPublicItem mapRow(ResultSet arg0, int arg1)
					throws SQLException {

				ModelPublicItem item = new ModelPublicItem();
				item.setId(arg0.getString("id"));
				item.setTitle(arg0.getString("name"));
				return item;
			}

		});
		
	}

}
