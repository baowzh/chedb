package com.chedb.service;

import java.util.List;

import com.forum.model.ModelSysItem;

public interface SystemItemService {
	public boolean delete(int id) throws Exception;

	public boolean update(String userNo, String type, String content)
			throws Exception;

	/**
	 * 查询系统项目类别
	 * 
	 * @param level
	 *            1为修理厂，2为配件商
	 * @return
	 */
	public List<ModelSysItem> getSysItemClass(String level) throws Exception;

	/**
	 * 查询商家的服务项目所对应的系统项目
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelSysItem> getProviderSysItem(String providerId)
			throws Exception;

	/**
	 * 查询系统项目
	 * 
	 * @param level
	 *            1为修理厂，2为配件商
	 * @return
	 */
	public List<ModelSysItem> getSysItemByClassId(String classId)
			throws Exception;

	/**
	 * 查询系统项目
	 * 
	 * @param level
	 *            1为修理厂，2为配件商
	 * @return
	 */
	public ModelSysItem getSysItemById(String itemId) throws Exception;
}
