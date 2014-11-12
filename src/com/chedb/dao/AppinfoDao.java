package com.chedb.dao;

import java.util.List;

import com.forum.model.ModelPublicItem;

public interface AppinfoDao {
	/**
	 * 
	 * @param type
	 * @param strSingleTitleList
	 * @param text
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean userCommit(String type, String strSingleTitleList,
			String text, String userId) throws Exception;

	/**
	 * 查询系统项目类别
	 * 
	 * @param level
	 *            1为修理厂，2为配件商
	 * @return
	 */
	public String getAppinfoContentById(String id) throws Exception;

	/**
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<ModelPublicItem> getAppinfoListByType(String type)
			throws Exception;

}
