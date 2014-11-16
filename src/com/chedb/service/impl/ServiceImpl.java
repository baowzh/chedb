package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ServiceDao;
import com.chedb.service.ServiceIA;
import com.forum.model.ModelProvider;
import com.forum.model.ModelService;
import com.forum.model.ModelUserAppraise;

@Service("serviceImpl")
@Transactional(rollbackFor = Exception.class)
public class ServiceImpl implements ServiceIA {
	@Resource(name = "serviceDaoImpl")
	private ServiceDao serviceDao;

	@Override
	public ModelService getServiceInfo(String serviceId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getServiceInfo(serviceId);
	}

	@Override
	public List<ModelService> getServiceListByClassId(String classId,
			String carId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getServiceListByClassId(classId, carId);
	}

	@Override
	public int getProviderCountByServiceId(String serviceId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getProviderCountByServiceId(serviceId);
	}

	@Override
	public List<ModelProvider> getProviderListByServiceId(String serviceId,
			String start, String count, String latitude, String longitude)
			throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getProviderListByServiceId(serviceId, start, count,
				latitude, longitude);
	}

	@Override
	public ModelUserAppraise doAppraise(String serviceClassId,
			String serviceId, String content, String userId) throws Exception {
		// TODO Auto-generated method stub
		return this.serviceDao.doAppraise(serviceClassId, serviceId, content,
				userId);
	}

	@Override
	public int getAppraiseCountByServiceId(String serviceClassId,
			String serviceId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao
				.getAppraiseCountByServiceId(serviceClassId, serviceId);
	}

	@Override
	public List<ModelUserAppraise> getAppraiseListByServiceId(
			String serviceClassId, String serviceId, String start, String count)
			throws Exception {
		// TODO Auto-generated method stub
		return this.serviceDao.getAppraiseListByServiceId(serviceClassId,
				serviceId, start, count);
	}

}
