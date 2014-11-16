package com.chedb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ServiceClassDao;
import com.chedb.service.ServiceClassService;
import com.forum.model.ModelServiceClass;

@Service("serviceClassServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ServiceClassServiceImpl implements ServiceClassService {
	@Resource(name = "serviceClassDaoImpl")
	private ServiceClassDao serviceClassDao;

	@Override
	public ModelServiceClass queryServiceClassById(String classId)
			throws Exception {
		// TODO Auto-generated method stub
		return serviceClassDao.queryServiceClassById(classId);
	}

}
