package com.chedb.service;

import com.forum.model.ModelServiceClass;

//
public interface ServiceClassService {
	
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelServiceClass queryServiceClassById(String classId)throws Exception ;
	
}
