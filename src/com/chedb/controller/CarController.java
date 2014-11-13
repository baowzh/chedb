package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.service.CarService;
import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;

public class CarController {
	@Resource(name = "carServiceImpl")
	private CarService carService;

	@RequestMapping("/queryCarBrandList.do")
	@ResponseBody
	public List<ModelCarBrand> queryCarBrandList(HttpServletRequest req)
			throws Exception {
		return carService.getCarBrandList();
	}
	@RequestMapping("/queryCarSeriseListByBrand.do")
	@ResponseBody
	public List<ModelCarSerise> queryCarSeriseListByBrand(HttpServletRequest req)
			throws Exception {
		return carService.getCarSeriseListByBrand(breadid)();
	}
	@RequestMapping("/queryCarListBySerise.do")
	@ResponseBody
	public List<ModelCar> queryCarListBySerise(HttpServletRequest req)
			throws Exception {
		return carService.getCarListBySerise(breadid)();
	}
	
}
