package com.chedb.dao;

import java.util.List;

import com.forum.model.ModelProviderItem;
import com.forum.model.ModelSysItem;

//
public interface ProviderItemDao {

	public boolean add(ModelProviderItem item) throws Exception;

	public boolean delete(String itemId) throws Exception;

	public boolean update(String itemId, String type, String content)
			throws Exception;

	public boolean updatePrice(String itemId, String price, String priceOld)
			throws Exception;

	public boolean appendBusinessCount(String itemId) throws Exception;

	/**
	 * 移动项目位置，互换当前项目和目标项目的排序值
	 * 
	 * @param type
	 *            -1上移，1下移
	 * @param itemId
	 *            当前项目
	 * @param targetItemId
	 *            移动目标项目
	 * @return
	 */
	public boolean moveItem(String itemId, String targetItemId)
			throws Exception;

	/**
	 * mode : 0表示查询符合条件的全部，1表示查询前面多少条，2表示查询后面所有数据
	 */
	public List<ModelProviderItem> getProviderItemByProviderId(int mode,
			String providerId, String strSysItemList, String priceStart,
			String priceEnd) throws Exception;

	/**
	 * 查询商家的某个系统项目下有几个服务项目
	 * 
	 * @param providerId
	 * @param sysItemId
	 * @return
	 */
	public List<ModelSysItem> getProviderItemBySysItemId(String providerId,
			String sysItemId) throws Exception;

	public ModelProviderItem getProviderItemById(String itemId)
			throws Exception;

	public ModelProviderItem getProviderItemDetailById(String itemId)
			throws Exception;

}
