package com.forum.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.ServiceClassDaoImpl;
import com.forum.model.ModelServiceClass;
import com.forum.util.GsonUtil;

public class ServiceClassServlet extends HttpServlet {
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
		System.out.println("SeviceClassServlet:" + function);
		
		ServiceClassDaoImpl daoImpl = new ServiceClassDaoImpl();
		if (function.equals("queryServiceClassInfo")) {
			String serviceClassId = req.getParameter("serviceClassId");

			ModelServiceClass serviceClass = daoImpl.queryServiceClassById(serviceClassId);
			if (serviceClass != null) {
				jsonStr = GsonUtil.getGson().toJson(serviceClass);
			}
		} else {
			System.out.println("ProviderServlet:Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, Provider Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
