package com.chedb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forum.model.ModelProviderItem;
import com.forum.model.ModelSysItem;

@Controller
public class ProviderItemController {
	@RequestMapping("/summary.do")
	@ResponseBody
	public String summary(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目简单信息，可以填写以下内容：<br/>");
		buffer.append("1、项目简要说明；<br/>");
		buffer.append("2、所使用的原材料说明；<br/>");
		buffer.append("3、大概所需时间说明；<br/>");
		return buffer.toString();
	}

	@RequestMapping("/remark.do")
	@ResponseBody
	public String remark(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目说明，可以填写以下信息。<br/>");
		buffer.append("1、操作工序；<br/>");
		buffer.append("2、使用的设备；<br/>");
		buffer.append("3、不解决存在哪些隐患等；<br/>");
		return buffer.toString();
	}

	@RequestMapping("/ItemProcesses.do")
	@ResponseBody
	public String ItemProcesses(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String itemId = req.getParameter("itemId");
		System.out.println("AppinfoServlet : itemId:" + itemId);
		buffer.append("项目" + itemId + "的标准操作流程");
		buffer.append("\r\n");
		return buffer.toString();
	}

	@RequestMapping("/whatBuy.do")
	@ResponseBody
	public String whatBuy(HttpServletRequest req) throws Exception {
		String itemId = req.getParameter("itemId");
		System.out.println("AppinfoServlet : itemId:" + itemId);
		StringBuffer buffer = new StringBuffer();
		buffer.append("当前版本只是一次服务的记录，最后生成您的养车费用报告，不进行实际的支付。");
		buffer.append("\r\n");
		return buffer.toString();
	}

	@RequestMapping("/queryProviderItemByProviderId.do")
	@ResponseBody
	public List<ModelProviderItem> queryProviderItemByProviderId(
			HttpServletRequest req) throws Exception {
		return null;
	}

	@RequestMapping("/queryProviderItemBySysItemId.do")
	@ResponseBody
	public List<ModelSysItem> queryProviderItemBySysItemId(
			HttpServletRequest req) throws Exception {
		return null;
	}

	@RequestMapping("/queryProviderItemById.do")
	@ResponseBody
	public ModelProviderItem queryProviderItemById(HttpServletRequest req)
			throws Exception {
		return null;
	}

	@RequestMapping("/queryProviderItemDetailById.do")
	@ResponseBody
	public ModelProviderItem queryProviderItemDetailById(HttpServletRequest req)
			throws Exception {
		return null;
	}

	@RequestMapping("/modifyProviderItemInfo.do")
	@ResponseBody
	public ModelProviderItem modifyProviderItemInfo(HttpServletRequest req)
			throws Exception {
		return null;
	}

	@RequestMapping("/modifyProviderItemPrice.do")
	@ResponseBody
	public ModelProviderItem modifyProviderItemPrice(HttpServletRequest req)
			throws Exception {
		return null;
	}

	@RequestMapping("/addProviderItem.do")
	@ResponseBody
	public ModelProviderItem addProviderItem(HttpServletRequest req)
			throws Exception {
		return null;
	}

	@RequestMapping("/delProviderItem.do")
	@ResponseBody
	public ModelProviderItem delProviderItem(HttpServletRequest req)
			throws Exception {
		return null;
	}

	@RequestMapping("/moveProviderItem.do")
	@ResponseBody
	public ModelProviderItem moveProviderItem(HttpServletRequest req)
			throws Exception {
		return null;
	}

}
