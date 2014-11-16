package com.forum.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.ServiceDaoImpl;
import com.forum.model.ModelProvider;
import com.forum.model.ModelService;
import com.forum.model.ModelUserAppraise;
import com.forum.util.GsonUtil;

public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;
	
	private static final String JPG = "image/jpeg;charset=GB2312";   


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		test(req, resp);
		myGet(req, resp);
	}

	private void myGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// test(req, resp);
		myPost(req, resp);
	}

	private void myPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String jsonStr = "failed";
		req.setCharacterEncoding("UTF-8");

		String function = req.getParameter("function");
		System.out.println("ServiceServlet:" + function);
		
		List<ModelService> list = null;
		ServiceDaoImpl daoImpl = new ServiceDaoImpl();
		if (function.equals("queryServiceList")) {
			// 根据项目类型和车型（如果需要的话）查询服务信息
			String serviceClassId = req.getParameter("serviceClassId");
			String carId = req.getParameter("carId");
			
			list = daoImpl.getServiceListByClassId(serviceClassId, carId);//
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryServiceProviderCount")) {
			// 根据服务id查询相应的商家个数
			String serviceId = req.getParameter("serviceId");
			int count = daoImpl.getProviderCountByServiceId(serviceId);
			jsonStr = count+"";
		} else if (function.equals("queryServiceProviderList")) {
			// 根据服务id查询相应的商家信息
			String serviceId = req.getParameter("serviceId");
			String start = req.getParameter("start");
			String count = req.getParameter("count");
			String latitude = req.getParameter("latitude");
			String longitude = req.getParameter("longitude");
			List<ModelProvider> listProvider = daoImpl.getProviderListByServiceId(serviceId, start, count, latitude, longitude);//
			if (listProvider != null) {
				jsonStr = GsonUtil.getGson().toJson(listProvider);
			}
		} else if (function.equals("queryServiceAppraiseCount")) {
			// 根据服务id查询相应的商家个数
			String serviceClassId = req.getParameter("serviceClassId");
			String serviceId = req.getParameter("serviceId");
			int count = daoImpl.getAppraiseCountByServiceId(serviceClassId, serviceId);
			jsonStr = count+"";
		} else if (function.equals("serviceAppraiseDo")) {
			// 用户评价
			String serviceClassId = req.getParameter("serviceClassId");
			String serviceId = req.getParameter("serviceId");
			String content = req.getParameter("content");
			String userId = req.getParameter("userId");
//			boolean ok = daoImpl.doAppraise(serviceClassId, serviceId, content, userId);
//			if (ok) {
//				jsonStr = "success";
//			}
			ModelUserAppraise app = daoImpl.doAppraise(serviceClassId, serviceId, content, userId);
			if (app != null) {
				jsonStr = GsonUtil.getGson().toJson(app);
			}
		} else if (function.equals("queryServiceAppraiseList")) {
			// 根据服务id查询相应的商家信息
			String serviceClassId = req.getParameter("serviceClassId");
			String serviceId = req.getParameter("serviceId");
			String start = req.getParameter("start");
			String count = req.getParameter("count");

			List<ModelUserAppraise> listAppraise = daoImpl.getAppraiseListByServiceId(serviceClassId, serviceId, start, count);//
			if (listAppraise != null) {
				jsonStr = GsonUtil.getGson().toJson(listAppraise);
			}
		} else if (function.equals("queryServiceInfo")) {
			String serviceId = req.getParameter("serviceId");

			ModelService service = daoImpl.getServiceInfo(serviceId);
			if (service != null) {
				jsonStr = GsonUtil.getGson().toJson(service);
			}
		} else {
			System.out.println("ServiceServlet : Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, ServiceServlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
