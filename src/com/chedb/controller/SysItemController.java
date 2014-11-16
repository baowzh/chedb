package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.service.SystemItemService;
import com.forum.model.ModelSysItem;

public class SysItemController {
	@Resource(name = "systemItemServiceimpl")
	private SystemItemService systemItemService;

	@RequestMapping("/function.do")
	@ResponseBody
	public List<ModelSysItem> querySysItemClassList(HttpServletRequest req)
			throws Exception {
		String level = req.getParameter("level");
		return systemItemService.getSysItemClass(level);
	}

	@RequestMapping("/queryProviderSysItem.do")
	@ResponseBody
	public List<ModelSysItem> queryProviderSysItem(HttpServletRequest req)
			throws Exception {
		String providerId = req.getParameter("providerId");
		return systemItemService.getProviderSysItem(providerId);
	}

	@RequestMapping("/querySysItemByClassId.do")
	@ResponseBody
	public List<ModelSysItem> querySysItemByClassId(HttpServletRequest req)
			throws Exception {
		String classItemId = req.getParameter("classItemId");
		System.out.println("SysItemServlet: classItemId=" + classItemId);
		return systemItemService.getSysItemByClassId(classItemId);
	}

}
