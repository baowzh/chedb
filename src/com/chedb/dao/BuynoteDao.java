package com.chedb.dao;

import java.util.List;

import com.forum.model.ModelBusinote;

public interface BuynoteDao {

	public boolean addBusi(ModelBusinote t) throws Exception;

	public boolean updateBusinoteInfo(String busiNo, String newStatus,
			String score) throws Exception;

	/**
	 * 查询系统项目类别
	 * 
	 * @param
	 * @return
	 */
	public List<ModelBusinote> getBuynoteListByUser(String userId, int page)
			throws Exception;

	public ModelBusinote getBusinoteById(String no) throws Exception;

	public List<ModelBusinote> getBuynoteListByProviderId(String providerId,
			int page) throws Exception;

}
