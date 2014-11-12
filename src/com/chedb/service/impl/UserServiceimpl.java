package com.chedb.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.service.UserService;
import com.forum.model.ModelCar;
import com.forum.model.ModelUser;
@Service("userServiceimpl")
@Transactional
public class UserServiceimpl implements UserService {

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNewId(int type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(ModelUser user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String userNo, String type, String content)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModelUser getUserByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelUser getUser(String userNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelCar> getChoosedCarByUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addChoosedCarByAppId(String carId, String appId)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserConfig(String operType, String appId,
			String operContent) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
