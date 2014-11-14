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
		buffer.append("��Ŀ����Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����Ŀ��Ҫ˵����<br/>");
		buffer.append("2����ʹ�õ�ԭ����˵����<br/>");
		buffer.append("3���������ʱ��˵����<br/>");
		return buffer.toString();
	}

	@RequestMapping("/remark.do")
	@ResponseBody
	public String remark(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ŀ˵����������д������Ϣ��<br/>");
		buffer.append("1����������<br/>");
		buffer.append("2��ʹ�õ��豸��<br/>");
		buffer.append("3�������������Щ�����ȣ�<br/>");
		return buffer.toString();
	}

	@RequestMapping("/ItemProcesses.do")
	@ResponseBody
	public String ItemProcesses(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String itemId = req.getParameter("itemId");
		System.out.println("AppinfoServlet : itemId:" + itemId);
		buffer.append("��Ŀ" + itemId + "�ı�׼��������");
		buffer.append("\r\n");
		return buffer.toString();
	}

	@RequestMapping("/whatBuy.do")
	@ResponseBody
	public String whatBuy(HttpServletRequest req) throws Exception {
		String itemId = req.getParameter("itemId");
		System.out.println("AppinfoServlet : itemId:" + itemId);
		StringBuffer buffer = new StringBuffer();
		buffer.append("��ǰ�汾ֻ��һ�η���ļ�¼��������������������ñ��棬������ʵ�ʵ�֧����");
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
