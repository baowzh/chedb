package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.service.BuynoteService;
import com.chedb.service.ProviderService;
import com.forum.model.ModelSysItem;
import com.forum.servlet.NoDbData;

@Controller
public class AppraiseController {
	@Resource(name = "buynoteServiceImpl")
	private BuynoteService buynoteService;
	@Resource(name = "providerServiceImpl")
	private ProviderService providerService;

	@RequestMapping("/test.do")
	@ResponseBody
	public String test(HttpServletRequest req) {
		return "Hello, AppraiseServlet Servlet !!!!";
	}

	@RequestMapping("/queryAppraiseLabel.do")
	@ResponseBody
	public List<ModelSysItem> queryAppraiseLabel(HttpServletRequest req) {
		String level = req.getParameter("level");
		return NoDbData.queryAppraiseLabel(Integer.parseInt(level));
	}

	@RequestMapping("/queryProviderAppraiseLabel.do")
	@ResponseBody
	public List<ModelSysItem> queryProviderAppraiseLabel(HttpServletRequest req) {
		String providerId = req.getParameter("providerId");
		return NoDbData.queryProviderAppraiseLabel(providerId);
	}

	@RequestMapping("/commitAppraise.do")
	@ResponseBody
	public String commitAppraise(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		// String buynoteId = req.getParameter("buynoteId");
		String providerId = req.getParameter("providerId");
		// String userId = req.getParameter("userId");
		String score = req.getParameter("score");
		// String appraiseListStr = req.getParameter("appraiseListStr");
		// ProviderDaoImpl pdao = new ProviderDaoImpl();
		if (this.providerService.updateScore(providerId, score) == true) {
			// if (dao.updateAppraiseInfo(buynoteId, score) == true) {
			// jsonStr = "success";// failed
			// }
		}
		return jsonStr;
	}

}
