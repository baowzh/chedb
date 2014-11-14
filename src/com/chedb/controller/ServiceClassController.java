package com.chedb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.chedb.service.ServiceClassService;
import com.forum.model.ModelServiceClass;

@Controller
public class ServiceClassController {
	@Resource(name = "serviceClassServiceImpl")
	private ServiceClassService serviceClassService;

	public ModelServiceClass queryServiceClassInfo(String serviceClassId)
			throws Exception {
		return serviceClassService.queryServiceClassById(serviceClassId);
	}
}
