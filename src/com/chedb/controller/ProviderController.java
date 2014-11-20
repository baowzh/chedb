package com.chedb.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.service.ProviderService;
import com.chedb.service.SystemItemService;
import com.forum.daoimpl.UserDaoImpl;
import com.forum.dbdata.FileData;
import com.forum.model.ModelProvider;
import com.forum.model.ModelSysItem;
import com.forum.util.GsonUtil;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Resource(name = "providerServiceImpl")
	private ProviderService providerService;
	@Resource(name = "systemItemServiceimpl")
	private SystemItemService systemItemService;

	@RequestMapping(value = { "/summary.do" })
	@ResponseBody
	public String summary(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("�̼�ժҪ��Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����ҵ���ʣ������<br/>");
		buffer.append("2��ռ�������Ӫҵʱ�䣻<br/>");
		buffer.append("3������ʱ�䣬ע���ʽ�<br/>");
		buffer.append("4��������ּ�ȣ�<br/>");
		return buffer.toString();
	}

	@RequestMapping(value = { "/phone.do" })
	@ResponseBody
	public String phone(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("�̼ҵ绰��һ��Ҫ�������Ҫ�绰��ѯ��");
		return buffer.toString();
	}

	@RequestMapping(value = { "/remark.do" })
	@ResponseBody
	public String remark(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("�̼�˵����������д������Ϣ���������ݵȣ��磺<br/>");
		buffer.append("1��Ա����������������������<br/>");
		buffer.append("2����Ҫ����Ʒ�ƣ�����ڡ���ˣ�<br/>");
		buffer.append("3��Ӳ����ʩ���ܣ�����Ϣ�ҡ�ͣ�����ȣ�<br/>");
		buffer.append("4��ά���豸���ܵȣ�<br/>");
		return buffer.toString();
	}

	@RequestMapping(value = { "/addrNotDw.do" })
	@ResponseBody
	public String addrNotDw(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("�㻹û��Ϊ�Լ��ĵ궨λ�����������ڵ�ͼ�Ͽ������㣬���ݾ����ѯʱҲ�鲻���㡣<br/>");
		buffer.append("1��������Ҫ���Լ��ĵ��<br/>");
		buffer.append("2��������ڵ�ͼ�϶�λ����ť��<br/>");
		buffer.append("3����������桱�Ϳ����ˣ�<br/>");
		buffer.append("<br/>");
		buffer.append("������Զ����������ڵ�λ���ڵ�ͼ������ǣ�<br/>");
		buffer.append("���ϲ���ֻ��Ҫ��һ�Σ�������ĵ����ˣ�<br/>");
		return buffer.toString();
	}

	@RequestMapping(value = { "/getImage.do" })
	@ResponseBody
	public String getImage(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String imgId = req.getParameter("imgId");
		String ss = req.getContextPath();
		StringBuffer buffer = new StringBuffer();
		if (imgId.equals("-1")) {
			buffer.append("��ǰû���κ��̼�ͼƬ��<br/>");
			buffer.append("�ϴ�ͼƬ��������̼����󡣿����ϴ�������ۡ����䡢Ա����Ӱ��Ա�������ա��Ƚ��豸����ҵ���ʵ���Ƭ��<br/>");
			buffer.append("����ϴ�8��ͼƬ��<br/>");
			buffer.append("<br/>������Ͻǵġ�+����ť�����Ƭ��<br/>");
			return "";
		}
		// �õ�ͼƬ����ʵ·��
		// String imagePath1 = "D:\\pic\\102"+imgId+".jpg";
		String imagePath = ss + "/img/provider/" + providerId + "/" + imgId
				+ ".jpg";
		System.out.println("�̼�ͼƬ��" + imagePath);
		return sendImgFile(imagePath);
	}

	@RequestMapping(value = { "/queryProviderList.do" })
	@ResponseBody
	public List<ModelProvider> queryProviderList(HttpServletRequest req)
			throws Exception {
		String level = req.getParameter("level");
		String sort = req.getParameter("sort");
		String priceStart = req.getParameter("priceStart");
		String priceEnd = req.getParameter("priceEnd");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String strItemIdList = req.getParameter("strItemIdList");
		return providerService.getProviderList(level, strItemIdList, sort,
				priceStart, priceEnd, latitude, longitude);
	}

	@RequestMapping(value = { "/queryProviderListBySearch.do" })
	@ResponseBody
	public List<ModelProvider> queryProviderListBySearch(HttpServletRequest req)
			throws Exception {
		String searchStr = req.getParameter("searchStr");
		return providerService.getProviderListBySearch(searchStr);
	}

	@RequestMapping(value = { "/queryProviderImg.do" })
	@ResponseBody
	public String queryProviderImg(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String imgId = req.getParameter("imgId");
		String s = req.getSession().getServletContext().getRealPath("/");
		String imagePath = s + "img/provider/" + providerId + "/" + imgId
				+ ".jpg";
		String jsonStr = FileData.SendFileImg(imagePath, 120, true);
		return jsonStr;
	}

	@RequestMapping(value = { "/unloadProviderImg.do" })
	@ResponseBody
	public String unloadProviderImg(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String imgString = req.getParameter("imgString");
		String s = req.getSession().getServletContext().getRealPath("/");
		String providerPath = s + "img/provider/" + providerId + "/";
		FileData.dirCreate(providerPath);// ����ͼƬ���ڵ�Ŀ¼
		String imgId = this.providerService
				.getImgIdAndUpdateImgList(providerId);// �������ݿ�õ�
		String imagePath = providerPath + imgId + ".jpg";
		boolean ret = FileData.writeFileImg(imagePath, imgString);
		String jsonStr = "failed";
		if (ret == true) {
			jsonStr = imgId;
		}
		return jsonStr;
	}

	@RequestMapping(value = { "/editProviderImg.do" })
	@ResponseBody
	public String editProviderImg(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String oldImgIdList = req.getParameter("oldImgIdList");
		String imgId = req.getParameter("imgId");
		String edittype = req.getParameter("edittype");
		String jsonStr = "failed";
		String ret = this.providerService.editImgSpace(providerId,
				oldImgIdList, imgId, edittype);// �������ݿ�õ�
		if (ret != null && edittype.equals("0")) {// ɾ��ͼƬ
			// �õ�ͼƬ����ʵ·��
			String s = req.getSession().getServletContext().getRealPath("/");
			String imagePath = s + "img/provider/" + providerId + "/" + imgId
					+ ".jpg";
			FileData.delFileImg(imagePath);
		}
		if (ret != null) {
			jsonStr = ret;
		}
		return jsonStr;
	}

	@RequestMapping(value = { "/queryProviderByUserId.do" })
	@ResponseBody
	public ModelProvider queryProviderByUserId(HttpServletRequest req)
			throws Exception {
		String userId = req.getParameter("userId");
		ModelProvider provider = this.providerService.queryProviderById(userId);
		return provider;
	}

	@RequestMapping(value = { "/queryProviderById.do" })
	@ResponseBody
	public String queryProviderById(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String jsonStr = "failed";
		ModelProvider provider = this.providerService
				.queryProviderById(providerId);
		String appId = req.getParameter("appId");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.addUserConfig("3002", appId, providerId);

		this.providerService.appendBrowseCount(providerId);
		if (provider != null) {
			jsonStr = GsonUtil.getGson().toJson(provider);
		}
		return jsonStr;
	}

	@RequestMapping(value = { "/appendProviderBrowse.do" })
	@ResponseBody
	public String appendProviderBrowse(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		System.out.println("ProviderServlet: providerId=" + providerId);
		String jsonStr = "failed";
		if (providerService.appendBrowseCount(providerId) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping(value = { "/modifyProviderInfo.do" })
	@ResponseBody
	public String modifyProviderInfo(HttpServletRequest req) throws Exception {
		String providerId = req.getParameter("providerId");
		String infoType = req.getParameter("infoType");
		String content = req.getParameter("content");
		String jsonStr = "failed";
		if (providerService.update(providerId, infoType, content) == true) {
			jsonStr = "success";
		}

		return jsonStr;
	}

	private String sendImgFile(String imgfile) throws ServletException,
			IOException {
		// ����gitͬ��
		String con = "<html><body style='background-color: transparent;'>"
				+ "<div style='text-align:center'><img src='" + imgfile
				+ "'></div>" + "</body></html>";
		return con;
	}

	@RequestMapping("/providerindex.do")
	public ModelAndView providerindex(HttpServletRequest req) throws Exception {
		List<ModelSysItem> items = systemItemService.getSysItemClass("1");
		// ��ȡ��ÿ�������Ӧ����ϸ
		for (ModelSysItem modelSysItem : items) {
			List<ModelSysItem> childitems = this.systemItemService
					.getSysItemByClassId(modelSysItem.getLabelId());
		}
		return new ModelAndView("searchservice");
	}
	@RequestMapping("/providerdetail.do")
	public ModelAndView providerdetail(HttpServletRequest req) throws Exception {
//		List<ModelSysItem> items = systemItemService.getSysItemClass("1");
//		// ��ȡ��ÿ�������Ӧ����ϸ
//		for (ModelSysItem modelSysItem : items) {
//			List<ModelSysItem> childitems = this.systemItemService
//					.getSysItemByClassId(modelSysItem.getLabelId());
//		}
		return new ModelAndView("servicedetail");
	}
	
}
