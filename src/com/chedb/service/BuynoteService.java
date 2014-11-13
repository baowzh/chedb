package com.chedb.service;

import java.util.List;

import com.forum.model.ModelBusinote;

public interface BuynoteService {

	public List<ModelBusinote> queryBuynoteByUserId(String userId, Integer page)
			throws Exception;

	public List<ModelBusinote> queryBuynoteByProviderId(String providerId,
			Integer page) throws Exception;

	public ModelBusinote queryBusinoteById(String busiNo) throws Exception;

	public void updateBusinoteInfo(String busiNo, String newStatus, String score)
			throws Exception;

	public boolean buyService(String buynoteStr) throws Exception;
}
