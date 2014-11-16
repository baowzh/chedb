package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ProviderDao;
import com.chedb.service.ProviderService;
import com.forum.model.ModelProvider;

@Service("providerServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ProviderServiceImpl implements ProviderService {
	@Resource(name = "providerDaoImpl")
	private ProviderDao providerDao;

	@Override
	public List<ModelProvider> getProviderList(String level,
			String strSysItemList, String sort, String priceStart,
			String priceEnd, String latitude, String longitude)
			throws Exception {
		// TODO Auto-generated method stub
		return providerDao.getProviderList(level, strSysItemList, sort,
				priceStart, priceEnd, latitude, longitude);
	}

	@Override
	public List<ModelProvider> getProviderListBySearch(String strSearch)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.getProviderListBySearch(strSearch);
	}

	@Override
	public String getImgIdAndUpdateImgList(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.getImgIdAndUpdateImgList(providerId);
	}

	@Override
	public String editImgSpace(String providerId, String oldImgIdList,
			String imgId, String edittype) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.editImgSpace(providerId, oldImgIdList, imgId,
				edittype);
	}

	@Override
	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.delete(id);
	}

	@Override
	public boolean add(ModelProvider t) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.add(t);
	}

	@Override
	public boolean updateScore(String providerId, String score)
			throws Exception {
		// TODO Auto-generated method stub
		return this.updateScore(providerId, score);
	}

	@Override
	public boolean update(String providerId, String type, String content)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.update(providerId, type, content);
	}

	@Override
	public boolean appendBusinessCount(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.appendBusinessCount(providerId);
	}

	@Override
	public boolean appendBrowseCount(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.appendBrowseCount(providerId);
	}

	@Override
	public ModelProvider queryProviderById(String providerId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerDao.queryProviderById(providerId);
	}

}
