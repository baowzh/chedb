package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chedb.dao.CarDao;
import com.chedb.service.CarService;
import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;
@Service("carServiceImpl")
public class CarServiceImpl implements CarService {
	@Resource(name = "carDaoImpl")
	private CarDao carDao;

	@Override
	public List<ModelCarBrand> getCarBrandList() throws Exception {
		// TODO Auto-generated method stub
		return carDao.getCarBrandList();
	}

	@Override
	public List<ModelCarSerise> getCarSeriseListByBrand(String breadid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.carDao.getCarSeriseListByBrand(breadid);
	}

	@Override
	public List<ModelCar> getCarListBySerise(String seriseId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.carDao.getCarListBySerise(seriseId);
	}
	



}
