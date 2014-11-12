package com.forum.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.CarDaoImpl;
import com.forum.model.ModelCar;
import com.forum.model.ModelCarBrand;
import com.forum.model.ModelCarSerise;
import com.forum.util.GsonUtil;

public class CarServlet extends HttpServlet {
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
		
		CarDaoImpl daoImpl = new CarDaoImpl();
		if (function.equals("queryCarBrandList")) {
			// 根据项目类型和车型（如果需要的话）查询服务信息
//			String serviceClassId = req.getParameter("serviceClassId");
//			String carId = req.getParameter("carId");
			
			List<ModelCarBrand> list = daoImpl.getCarBrandList();//
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryCarSeriseListByBrand")) {
			// 根据服务id查询相应的商家个数
			String breadId = req.getParameter("breadId");
			List<ModelCarSerise> list = daoImpl.getCarSeriseListByBrand(breadId);//
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryCarListBySerise")) {
			// 根据服务id查询相应的商家信息
			String seriseId = req.getParameter("seriseId");

			List<ModelCar> list = daoImpl.getCarListBySerise(seriseId);//
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
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
