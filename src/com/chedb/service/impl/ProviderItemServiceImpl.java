package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ProviderItemDao;
import com.chedb.service.ProviderItemService;
import com.forum.model.ModelProviderItem;
import com.forum.model.ModelSysItem;

@Service("providerItemServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ProviderItemServiceImpl implements ProviderItemService {
	@Resource(name = "providerItemDaoImpl")
	private ProviderItemDao providerItemDao;

	@Override
	public boolean add(ModelProviderItem item) throws Exception {
		// TODO Auto-generated method stub
		return providerItemDao.add(item);
	}

	@Override
	public boolean delete(String itemId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.delete(itemId);
	}

	@Override
	public boolean update(String itemId, String type, String content)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.update(itemId, type, content);
	}

	@Override
	public boolean updatePrice(String itemId, String price, String priceOld)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.updatePrice(itemId, price, priceOld);
	}

	@Override
	public boolean appendBusinessCount(String itemId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.appendBusinessCount(itemId);
	}

	@Override
	public boolean moveItem(String itemId, String targetItemId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.moveItem(itemId, targetItemId);
	}

	@Override
	public List<ModelProviderItem> getProviderItemByProviderId(int mode,
			String providerId, String strSysItemList, String priceStart,
			String priceEnd) throws Exception {
		// TODO Auto-generated method stub
		return this.getProviderItemByProviderId(mode, providerId,
				strSysItemList, priceStart, priceEnd);
	}

	@Override
	public List<ModelSysItem> getProviderItemBySysItemId(String providerId,
			String sysItemId) throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.getProviderItemBySysItemId(providerId, sysItemId);
	}

	@Override
	public ModelProviderItem getProviderItemById(String itemId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.getProviderItemById(itemId);
	}

	@Override
	public ModelProviderItem getProviderItemDetailById(String itemId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.providerItemDao.getProviderItemDetailById(itemId);
	}

}
