package com.forum.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.BuynoteDaoImpl;
import com.forum.model.ModelBusinote;
import com.forum.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class BuynoteServlet extends HttpServlet {
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
		req.setCharacterEncoding("UTF-8");
		
		String jsonStr = "failed";
		String function = req.getParameter("function");

		System.out.println("BuynoteServlet:" + function);
		List<ModelBusinote> list = null;

		BuynoteDaoImpl daoImpl = new BuynoteDaoImpl();
		
		if (function.equals("queryBuynoteByUserId")) {
			String userId = req.getParameter("userId");
			String page = req.getParameter("page");

			System.out.println("BuynoteServlet: userId=" + userId+", page="+page);
		
			list = daoImpl.getBuynoteListByUser(userId, Integer.parseInt(page));
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryBuynoteByProviderId")) {
			String providerId = req.getParameter("providerId");
			String page = req.getParameter("page");

			System.out.println("BuynoteServlet: providerId=" + providerId+", page="+page);
		
			list = daoImpl.getBuynoteListByProviderId(providerId, Integer.parseInt(page));
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryBusinoteById")) {
			String busiNo = req.getParameter("busiNo");
			
			ModelBusinote businote = daoImpl.getBusinoteById(busiNo);
			if (businote != null) {
				jsonStr = GsonUtil.getGson().toJson(businote);
			}
		} else if (function.equals("updateBusinoteInfo")) {
			String busiNo = req.getParameter("busiNo");
			String newStatus = req.getParameter("newStatus");
			String score = req.getParameter("score");
			if (daoImpl.updateBusinoteInfo(busiNo, newStatus, score) == true) {
				jsonStr = "success";
			}
		} else if (function.equals("buyService")) {
			String buynoteStr = req.getParameter("buynoteStr");
			
			ModelBusinote buynote = GsonUtil.getGson().fromJson(buynoteStr,
					new TypeToken<ModelBusinote>() {}.getType());
			
			// 添加 一条购买记录
			if (daoImpl.addBusi(buynote) == true) {
				jsonStr = "success";
			}
		} else {
			System.out
					.println("BuynoteServlet : Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, BuynoteServlet Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
