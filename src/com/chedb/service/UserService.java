package com.chedb.service;

import java.util.List;

import com.forum.model.ModelCar;
import com.forum.model.ModelUser;

//
public interface UserService {

	public boolean delete(int id) throws Exception;

	//
	/**
	 * 获取一个新的用户ID
	 * 
	 * @param type
	 *            ：用户类型，1为车主用户，2为修理厂用户，3为配件商家用户
	 * @return 新的用户ID
	 *         获得的新ID用来注册，但很可能不被注册。新的ID在user表中状态为0，真正注册后状态为1，新ID生成后半小时内没有真正注册则可被重复使用
	 */
	public String getNewId(int type) throws Exception;

	public boolean register(ModelUser user) throws Exception;

	public boolean update(String userNo, String type, String content)
			throws Exception;

	public ModelUser getUserByPhone(String phone) throws Exception;

	public ModelUser getUser(String userNo) throws Exception;

	public List<ModelCar> getChoosedCarByUser(String userId) throws Exception;

	public boolean addChoosedCarByAppId(String carId, String appId)
			throws Exception;

	// 1001:打开程序
	// 1002:退出程序
	// 2001：注册
	// 2002：登录
	// 3001：选择新车型
	// 3002：查修理厂
	public boolean addUserConfig(String operType, String appId,
			String operContent) throws Exception;
}
