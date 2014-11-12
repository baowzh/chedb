package com.forum.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.SysItemDaoImpl;
import com.forum.daoimpl.UserDaoImpl;
import com.forum.model.ModelSysItem;
import com.forum.util.GsonUtil;

public class SysItemServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		test(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// test(req, resp);
		myPost(req, resp);
	}

	private void myPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String jsonStr = "failed";
		String function = req.getParameter("function");

		 System.out.println("SysItemServlet:" + function);// + "-level=" +
		// level+",providerId="+providerId+",classItemId="+classItemId);
		SysItemDaoImpl daoImpl = new SysItemDaoImpl();
		List<ModelSysItem> list = null;// getData(function, level);
		if (function.equals("querySysItemClassList")) {
			String level = req.getParameter("level");
			System.out.println("SysItemServlet: level=" + level);
//			list = NoDbData.querySysItemClassList(Integer.parseInt(level));
			list = daoImpl.getSysItemClass(level);
		} else if (function.equals("queryProviderSysItem")) {
			String providerId = req.getParameter("providerId");
			System.out.println("SysItemServlet: providerId=" + providerId);
//			list = NoDbData.queryProviderSysItem(providerId);
			list = daoImpl.getProviderSysItem(providerId);
		} else if (function.equals("querySysItemByClassId")) {
			String classItemId = req.getParameter("classItemId");
			System.out.println("SysItemServlet: classItemId=" + classItemId);
//			list = NoDbData.querySysItemByClassId(classItemId);
			list = daoImpl.getSysItemByClassId(classItemId);
//		} else if (function.equals("querySysItemById")) {
//			String sysItemId = req.getParameter("sysItemId");
//			System.out.println("SysItemServlet: sysItemId=" + sysItemId);
//			list = NoDbData.querySysItemById(sysItemId);
		} else {
			System.out.println("SysItemServlet: ERROR : unnow function=" + function);
		}

		if (list != null) {
			jsonStr = GsonUtil.getGson().toJson(list);
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, SysItemServlet Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
