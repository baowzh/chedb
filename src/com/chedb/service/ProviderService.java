package com.chedb.service;

import java.util.List;

import com.forum.model.ModelProvider;

public interface ProviderService {
	public boolean delete(int id) throws Exception;

	public boolean add(ModelProvider t) throws Exception;

	public boolean updateScore(String providerId, String score)
			throws Exception;

	public boolean update(String providerId, String type, String content)
			throws Exception;

	public boolean appendBusinessCount(String providerId) throws Exception;

	public boolean appendBrowseCount(String providerId) throws Exception;

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelProvider queryProviderById(String providerId) throws Exception;

	//
	// /**
	// *
	// * @param userId
	// * @return
	// */
	// public ModelProvider getProviderByUserId(String userId){
	// String sql = "select * from provider where id = '" + userId + "'";
	//
	// return getProvider(sql);
	// }
	/**
	 * 查询系统项目类别
	 * 
	 * @param level
	 *            1为修理厂，2为配件商
	 * @return
	 */
	public List<ModelProvider> getProviderListBySearch(String strSearch)
			throws Exception;

	public String getImgIdAndUpdateImgList(String providerId) throws Exception;

	/**
	 * 调整图片的位置，或者删除图片
	 * 
	 * @param providerId
	 * @param oldImgIdList
	 * @param imgId
	 *            图片ID，对应图片名
	 * @param edittype
	 *            1后移，0删除，-1前移
	 * @return
	 */
	public String editImgSpace(String providerId, String oldImgIdList,
			String imgId, String edittype) throws Exception;

	// 根据位置排序

	/**
	 * 查询商家
	 * 
	 * @param level
	 *            商家类别，1为修理厂商家，2为配件商家
	 * @param strSysItemList
	 *            系统项目，过滤使用
	 * @param sort
	 *            排序方式：1按距离，2按口碑，3按价格从小到大
	 * @param priceStart
	 *            价格最小值
	 * @param priceEnd
	 *            价格最大值
	 * @param latitude
	 *            ,longitude 查询用户当前的经纬度，用于根据距离排序时使用
	 * @return 查询到的商家列表
	 */
	public List<ModelProvider> getProviderList(String level,
			String strSysItemList, String sort, String priceStart,
			String priceEnd, String latitude, String longitude)
			throws Exception;

}
