package com.chedb.service;

import java.util.List;

import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;

public interface CarService {
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ModelCarBrand> getCarBrandList() throws Exception;

	/**
	 * 
	 * @param breadid
	 * @return
	 * @throws Exception
	 */
	public List<ModelCarSerise> getCarSeriseListByBrand(String breadid)
			throws Exception;

	/**
	 * 
	 * @param seriseId
	 * @return
	 * @throws Exception
	 */
	public List<ModelCar> getCarListBySerise(String seriseId) throws Exception;

	/**
	 * 选择默认车型
	 * 
	 * @param carid
	 * @return
	 * @throws Exception
	 */
	public List<ModelCar> getDefaultCars(String carid) throws Exception;

}
