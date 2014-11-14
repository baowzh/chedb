package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.service.ProviderItemService;
import com.forum.model.ModelProviderItem;
import com.forum.model.ModelSysItem;
import com.forum.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

@Controller
public class ProviderItemController {
	@Resource(name = "providerItemServiceImpl")
	private ProviderItemService providerItemService;

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
		String providerId = req.getParameter("providerId");
		String strItemIdList = req.getParameter("strItemIdList");
		String priceStart = req.getParameter("priceStart");
		String priceEnd = req.getParameter("priceEnd");
		int mode = Integer.parseInt(req.getParameter("mode"));
		return this.providerItemService.getProviderItemByProviderId(mode,
				providerId, strItemIdList, priceStart, priceEnd);

	}

	@RequestMapping("/queryProviderItemBySysItemId.do")
	@ResponseBody
	public List<ModelSysItem> queryProviderItemBySysItemId(
			HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String sysItemId = req.getParameter("sysItemId");
		System.out.println("ProviderItemServlet:" + "providerId=" + providerId
				+ ", sysItemId=" + sysItemId);
		List<ModelSysItem> list2 = this.providerItemService
				.getProviderItemBySysItemId(providerId, sysItemId);
		return list2;
	}

	@RequestMapping("/queryProviderItemById.do")
	@ResponseBody
	public ModelProviderItem queryProviderItemById(HttpServletRequest req)
			throws Exception {
		String itemId = req.getParameter("itemId");
		ModelProviderItem providerItem = this.providerItemService
				.getProviderItemById(itemId);
		return providerItem;
	}

	@RequestMapping("/queryProviderItemDetailById.do")
	@ResponseBody
	public ModelProviderItem queryProviderItemDetailById(HttpServletRequest req)
			throws Exception {
		String itemId = req.getParameter("itemId");
		ModelProviderItem providerItem = this.providerItemService
				.getProviderItemDetailById(itemId);
		return providerItem;
	}

	@RequestMapping("/modifyProviderItemInfo.do")
	@ResponseBody
	public String modifyProviderItemInfo(HttpServletRequest req)
			throws Exception {
		String jsonStr = "failed";
		String itemId = req.getParameter("itemId");
		String infoType = req.getParameter("infoType");
		String content = req.getParameter("content");
		if (this.providerItemService.update(itemId, infoType, content) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping("/modifyProviderItemPrice.do")
	@ResponseBody
	public String modifyProviderItemPrice(HttpServletRequest req)
			throws Exception {
		String jsonStr = "failed";
		String itemId = req.getParameter("itemId");
		String price = req.getParameter("price");
		String priceOld = req.getParameter("priceOld");
		if (this.providerItemService.updatePrice(itemId, price, priceOld) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping("/addProviderItem.do")
	@ResponseBody
	public String addProviderItem(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String itemInfo = req.getParameter("itemInfo");
		ModelProviderItem providerItem = GsonUtil.getGson().fromJson(itemInfo,
				new TypeToken<ModelProviderItem>() {
				}.getType());
		providerItem.setRemark("");
		providerItem.setSummary("");
		if (this.providerItemService.add(providerItem) == true) {
			jsonStr = GsonUtil.getGson().toJson(providerItem);
		}
		return jsonStr;
	}

	@RequestMapping("/delProviderItem.do")
	@ResponseBody
	public String delProviderItem(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String itemId = req.getParameter("itemId");
		if (this.providerItemService.delete(itemId) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping("/moveProviderItem.do")
	@ResponseBody
	public String moveProviderItem(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String itemId = req.getParameter("itemId");
		String targetItemId = req.getParameter("targetItemId");
		if (this.providerItemService.moveItem(itemId, targetItemId) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

}
