package com.chedb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.forum.model.ModelBusinote;

public interface BuynoteService {

	public List<ModelBusinote> queryBuynoteByUserId(HttpServletRequest req)
			throws Exception;

	public List<ModelBusinote> queryBuynoteByProviderId(HttpServletRequest req)
			throws Exception;

	public String queryBusinoteById(HttpServletRequest req) throws Exception;

	public String updateBusinoteInfo(HttpServletRequest req) throws Exception;

	public String buyService(HttpServletRequest req) throws Exception;
}
