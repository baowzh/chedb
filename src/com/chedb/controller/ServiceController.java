package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.service.CarService;
import com.chedb.service.ServiceIA;
import com.forum.model.ModelCar;
import com.forum.model.ModelProvider;
import com.forum.model.ModelService;
import com.forum.model.ModelUserAppraise;

@Controller
public class ServiceController {
	@Resource(name = "serviceImpl")
	private ServiceIA ServiceIA;
	@Resource(name = "carServiceImpl")
	private CarService carService;

	@RequestMapping("/queryServiceList.do")
	@ResponseBody
	public List<ModelService> queryServiceList(HttpServletRequest req)
			throws Exception {
		String serviceClassId = req.getParameter("serviceClassId");
		String carId = req.getParameter("carId");
		List<ModelService> modelServices = ServiceIA.getServiceListByClassId(
				serviceClassId, carId);
		return modelServices;
	}

	@RequestMapping("/queryServiceProviderCount.do")
	@ResponseBody
	public String queryServiceProviderCount(HttpServletRequest req)
			throws Exception {
		String jsonStr = "failed";
		String serviceId = req.getParameter("serviceId");
		int count = ServiceIA.getProviderCountByServiceId(serviceId);
		jsonStr = count + "";
		return jsonStr;
	}

	@RequestMapping("/queryServiceProviderList.do")
	@ResponseBody
	public List<ModelProvider> queryServiceProviderList(HttpServletRequest req)
			throws Exception {
		String serviceId = req.getParameter("serviceId");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		List<ModelProvider> listProvider = ServiceIA
				.getProviderListByServiceId(serviceId, start, count, latitude,
						longitude);//
		return listProvider;
	}

	@RequestMapping("/queryServiceAppraiseCount.do")
	@ResponseBody
	public String queryServiceAppraiseCount(HttpServletRequest req)
			throws Exception {
		String jsonStr = "failed";
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceId = req.getParameter("serviceId");
		int count = ServiceIA.getAppraiseCountByServiceId(serviceClassId,
				serviceId);
		jsonStr = count + "";
		return jsonStr;
	}

	@RequestMapping("/serviceAppraiseDo.do")
	@ResponseBody
	public ModelUserAppraise serviceAppraiseDo(HttpServletRequest req)
			throws Exception {
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceId = req.getParameter("serviceId");
		String content = req.getParameter("content");
		String userId = req.getParameter("userId");
		// boolean ok = daoImpl.doAppraise(serviceClassId, serviceId, content,
		// userId);
		// if (ok) {
		// jsonStr = "success";
		// }
		ModelUserAppraise app = ServiceIA.doAppraise(serviceClassId, serviceId,
				content, userId);
		return app;
	}

	@RequestMapping("/queryServiceAppraiseList.do")
	@ResponseBody
	public List<ModelUserAppraise> queryServiceAppraiseList(
			HttpServletRequest req) throws Exception {
		String serviceClassId = req.getParameter("serviceClassId");
		String serviceId = req.getParameter("serviceId");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		List<ModelUserAppraise> listAppraise = ServiceIA
				.getAppraiseListByServiceId(serviceClassId, serviceId, start,
						count);//
		return listAppraise;
	}

	@RequestMapping("/queryServiceInfo.do")
	@ResponseBody
	public ModelService queryServiceInfo(HttpServletRequest req)
			throws Exception {
		String serviceId = req.getParameter("serviceId");
		ModelService service = ServiceIA.getServiceInfo(serviceId);
		return service;
	}

	@RequestMapping("/serviceindex.do")
	public ModelAndView carcleanindex(HttpServletRequest req, ModelMap map)
			throws Exception {
		// 选择默认车型
		List<ModelCar> cars = this.carService.getDefaultCars(req
				.getParameter("carId"));
		List<ModelService> modelServices = ServiceIA.getServiceListByClassId(
				req.getParameter("classId"), req.getParameter("carId"));
		map.put("servicelist", modelServices);
		map.put("cars", cars);
		// 查询车型
		return new ModelAndView(req.getParameter("view"), map);
	}

	@RequestMapping("/serviceItemDetail.do")
	public ModelAndView serviceItemDetail(HttpServletRequest req, ModelMap map) {
		return new ModelAndView("maintainitems", map);
	}

}
