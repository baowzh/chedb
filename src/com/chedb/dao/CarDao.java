package com.chedb.dao;

import java.util.List;

import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;

//
public interface CarDao {

	public List<ModelCarBrand> getCarBrandList() throws Exception;

	public List<ModelCarSerise> getCarSeriseListByBrand(String brandId)
			throws Exception;

	public List<ModelCar> getCarListBySerise(String seriseId) throws Exception;
}
