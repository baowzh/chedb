package com.chedb.service;

import java.util.List;

import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;

public interface CarService {
	public List<ModelCarBrand> getCarBrandList() throws Exception;

	public List<ModelCarSerise> getCarSeriseListByBrand(String breadid)
			throws Exception;

	public List<ModelCar> getCarListBySerise(String seriseId)
			throws Exception;
	
}
