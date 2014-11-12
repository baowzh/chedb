package com.forum.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.BuynoteDaoImpl;
import com.forum.daoimpl.ProviderDaoImpl;
import com.forum.model.ModelSysItem;
import com.forum.util.GsonUtil;

public class AppraiseServlet extends HttpServlet {
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

		System.out.println("AppraiseServlet:" + function);
		List<ModelSysItem> list = null;

		BuynoteDaoImpl dao = new BuynoteDaoImpl();
		if (function.equals("queryAppraiseLabel")) {
			String level = req.getParameter("level");

			list = NoDbData.queryAppraiseLabel(Integer.parseInt(level));
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryProviderAppraiseLabel")) {
			String providerId = req.getParameter("providerId");

			list = NoDbData.queryProviderAppraiseLabel(providerId);
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("commitAppraise")) {
			String buynoteId = req.getParameter("buynoteId");
			String providerId = req.getParameter("providerId");
			// String userId = req.getParameter("userId");
			String score = req.getParameter("score");
			String appraiseListStr = req.getParameter("appraiseListStr");

			System.out
					.println("ProviderItemServlet : buynoteId=" + buynoteId
							+ ",score=" + score + ",appraiseListStr="
							+ appraiseListStr);
			// 进行“评价”的数据操作
			// 更新商家评价分数
			ProviderDaoImpl pdao = new ProviderDaoImpl();
			if (pdao.updateScore(providerId, score) == true) {
//				if (dao.updateAppraiseInfo(buynoteId, score) == true) {
//					jsonStr = "success";// failed
//				}
			}
		} else {
			System.out.println("AppraiseServlet : Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, AppraiseServlet Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
