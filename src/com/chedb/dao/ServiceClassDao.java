package com.chedb.dao;

import com.forum.model.ModelServiceClass;

//
public interface ServiceClassDao {
	
	
	/**
	 * 
	 * @param providerId
	 * @return
	 */
	public ModelServiceClass queryServiceClassById(String classId)throws Exception ;
	
}
