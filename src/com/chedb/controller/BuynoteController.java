package com.chedb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forum.model.ModelBusinote;

@Controller
public class BuynoteController {
	@RequestMapping("/queryBuynoteByUserId.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteByUserId(HttpServletRequest req) {
		return new ArrayList<ModelBusinote>();
	}

	@RequestMapping("/queryBuynoteByProviderId.do")
	@ResponseBody
	public List<ModelBusinote> queryBuynoteByProviderId(HttpServletRequest req) {
		return new ArrayList<ModelBusinote>();
	}

	@RequestMapping("/queryBusinoteById.do")
	@ResponseBody
	public String queryBusinoteById(HttpServletRequest req) {
		return "";
	}

	@RequestMapping("/updateBusinoteInfo.do")
	@ResponseBody
	public String updateBusinoteInfo(HttpServletRequest req) {
		return "";
	}

	@RequestMapping("/buyService.do")
	@ResponseBody
	public String buyService(HttpServletRequest req) {
		return "";
	}

}
