package com.chedb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.service.BuynoteService;
import com.forum.model.ModelBusinote;

@Controller
public class BuynoteController {
	@Resource(name = "buynoteServiceImpl")
	private BuynoteService buynoteService;

	@RequestMapping("/queryBuynoteByUserId.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteByUserId(HttpServletRequest req)
			throws Exception {
		String userId = req.getParameter("userId");
		String page = req.getParameter("page");
		return this.buynoteService.queryBuynoteByUserId(userId,
				Integer.parseInt(page));
	}

	@RequestMapping("/queryBuynoteByProviderId.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteByProviderId(HttpServletRequest req)
			throws Exception {
		String providerId = req.getParameter("providerId");
		String page = req.getParameter("page");
		this.buynoteService.queryBuynoteByProviderId(providerId,
				Integer.parseInt(page));
		return new ArrayList<ModelBusinote>();
	}

	@RequestMapping("/queryBusinoteById.do")
	@ResponseBody
	public ModelBusinote queryBusinoteById(HttpServletRequest req)
			throws Exception {
		String busiNo = req.getParameter("busiNo");
		ModelBusinote businote = buynoteService.queryBusinoteById(busiNo);
		return businote;
	}

	@RequestMapping("/updateBusinoteInfo.do")
	@ResponseBody
	public String updateBusinoteInfo(HttpServletRequest req) {
		String busiNo = req.getParameter("busiNo");
		String newStatus = req.getParameter("newStatus");
		String score = req.getParameter("score");
		String jsonStr = "failed";

		try {
			this.buynoteService.updateBusinoteInfo(busiNo, newStatus, score);
			jsonStr = "success";
		} catch (Exception ex) {
			jsonStr = "failed";
		}
		return jsonStr;
	}

	@RequestMapping("/buyService.do")
	@ResponseBody
	public String buyService(HttpServletRequest req) {
		String buynoteStr = req.getParameter("buynoteStr");
		String jsonStr = "failed";
		try {
			this.buynoteService.buyService(buynoteStr);
			jsonStr = "success";
		} catch (Exception ex) {
			jsonStr = "failed";
		}
		return jsonStr;
	}

}
