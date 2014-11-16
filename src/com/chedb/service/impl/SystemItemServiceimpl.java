package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.SysItemDao;
import com.chedb.service.SystemItemService;
import com.forum.model.ModelSysItem;

@Service("systemItemServiceimpl")
@Transactional(rollbackFor = Exception.class)
public class SystemItemServiceimpl implements SystemItemService {
	@Resource(name = "sysItemDaoImpl")
	private SysItemDao sysItemDao;

	@Override
	public boolean delete(int id) throws Exception {
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
	public List<ModelSysItem> getSysItemClass(String level) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelSysItem> getProviderSysItem(String providerId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelSysItem> getSysItemByClassId(String classId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelSysItem getSysItemById(String itemId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
