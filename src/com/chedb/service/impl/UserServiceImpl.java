package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.UserDao;
import com.chedb.service.UserService;
import com.forum.model.ModelCar;
import com.forum.model.ModelUser;

@Service("userServiceimpl")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	@Resource(name = "userDaoImpl")
	private UserDao UserDao;

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.delete(id);
	}

	@Override
	public String getNewId(int type) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getNewId(type);
	}

	@Override
	public boolean register(ModelUser user) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.register(user);
	}

	@Override
	public boolean update(String userNo, String type, String content)
			throws Exception {
		// TODO Auto-generated method stub
		return UserDao.update(userNo, type, content);
	}

	@Override
	public ModelUser getUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getUserByPhone(phone);
	}

	@Override
	public ModelUser getUser(String userNo) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getUser(userNo);
	}

	@Override
	public List<ModelCar> getChoosedCarByUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getChoosedCarByUser(userId);
	}

	@Override
	public boolean addChoosedCarByAppId(String carId, String appId)
			throws Exception {
		// TODO Auto-generated method stub
		return UserDao.addChoosedCarByAppId(carId, appId);
	}

	@Override
	public boolean addUserConfig(String operType, String appId,
			String operContent) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.addUserConfig(operType, appId, operContent);
	}

}
