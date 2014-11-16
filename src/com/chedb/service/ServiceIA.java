package com.chedb.service;

import java.util.List;

import com.forum.model.ModelProvider;
import com.forum.model.ModelService;
import com.forum.model.ModelUserAppraise;

public interface ServiceIA {
	public ModelService getServiceInfo(String serviceId) throws Exception;

	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public List<ModelService> getServiceListByClassId(String classId,
			String carId) throws Exception;

	public int getProviderCountByServiceId(String serviceId) throws Exception;

	public List<ModelProvider> getProviderListByServiceId(String serviceId,
			String start, String count, String latitude, String longitude)
			throws Exception;

	public ModelUserAppraise doAppraise(String serviceClassId,
			String serviceId, String content, String userId) throws Exception;

	public int getAppraiseCountByServiceId(String serviceClassId,
			String serviceId) throws Exception;

	public List<ModelUserAppraise> getAppraiseListByServiceId(
			String serviceClassId, String serviceId, String start, String count)
			throws Exception;
}
