package com.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.ProviderItemDaoImpl;
import com.forum.model.ModelSysItem;
import com.forum.model.ModelProviderItem;
import com.forum.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class ProviderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		test(req, resp);
		myGet(req, resp);
	}
//	private void myGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//				
//		resp.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = resp.getWriter();
////		String jsonStr = "系统信息。";
//		String type = req.getParameter("infoType");
//		if (type != null) {
//			System.out.println("AppinfoServlet : type:" + type);
//			if (type.equals("summary")) {
//				out.println("商家说明信息，可以填写以下内容：");		
//				out.println("\r\n");
//				out.println("1、本店的规模，占地面积，营业时间；");
//				out.println("2、具有哪些行业资质；");
//				out.println("3、员工人数，技术特点；");
//				out.println("4、服务宗旨等；");
//			} else if (type.equals("phone")) {
//				out.println("商家电话，一定要填，车主需要电话咨询。");	
//			} else if (type.equals("remark")) {
//				out.println("商家说明，可以填写背景信息，服务内容等，可以给车主以信任。");	
//			} 
//		} else {			
//			System.out.println("AppinfoServlet : type is null");
//		}
//		out.close();
//	}
	private void myGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String type = req.getParameter("infoType");
		
		if (type != null) {
			System.out.println("AppinfoServlet : type:" + type);
			if (type.equals("summary")) {
				out.println("项目简单信息，可以填写以下内容：<br/>");	
				out.println("1、项目简要说明；<br/>");
				out.println("2、所使用的原材料说明；<br/>");
				out.println("3、大概所需时间说明；<br/>");
			} else if (type.equals("remark")) {
				out.println("项目说明，可以填写以下信息。<br/>");	
				out.println("1、操作工序；<br/>");
				out.println("2、使用的设备；<br/>");
				out.println("3、不解决存在哪些隐患等；<br/>");
			} else if (type.equals("ItemProcesses")) {
				String itemId = req.getParameter("itemId");
				System.out.println("AppinfoServlet : itemId:" + itemId);
				out.println("项目" + itemId + "的标准操作流程");		
				out.println("\r\n");
			} else if (type.equals("whatBuy")) {// 什么是“认购”
				String itemId = req.getParameter("itemId");
				System.out.println("AppinfoServlet : itemId:" + itemId);
				out.println("当前版本只是一次服务的记录，最后生成您的养车费用报告，不进行实际的支付。");		
				out.println("\r\n");
			} 
		} else {			
			System.out.println("AppinfoServlet : type is null");
		}
		out.close();
//		ServletUtil.doResponse(resp, jsonStr);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// test(req, resp);
		myPost(req, resp);
	}

	private void myPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String jsonStr = "failed";
		req.setCharacterEncoding("UTF-8");
		String function = req.getParameter("function");

		System.out.println("ProviderItemServlet:" + function);
		List<ModelProviderItem> list = null;

		ProviderItemDaoImpl daoImpl = new ProviderItemDaoImpl();
		
		if (function.equals("queryProviderItemByProviderId")) {
			int mode = Integer.parseInt(req.getParameter("mode"));
			String providerId = req.getParameter("providerId");
			String strItemIdList = req.getParameter("strItemIdList");
			String priceStart = req.getParameter("priceStart");
			String priceEnd = req.getParameter("priceEnd");
//			List<String> sysItemIdList = null;
//			if (strItemIdList != null) {
//				sysItemIdList = GsonUtil.getGson().fromJson(strItemIdList,
//						new TypeToken<List<String>>() {
//						}.getType());
//			}
//			list = NoDbData.queryProviderItemByProviderId(providerId,
//					sysItemIdList);
			list = daoImpl.getProviderItemByProviderId(mode,providerId, strItemIdList, priceStart, priceEnd);

			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryProviderItemBySysItemId")) {
			String providerId = req.getParameter("providerId");
			String sysItemId = req.getParameter("sysItemId");
			System.out.println("ProviderItemServlet:" + "providerId="
					+ providerId + ", sysItemId=" + sysItemId);
//			List<ModelSysItem> list2 = NoDbData.queryProviderItemBySysItemId(
//					providerId, sysItemId);
			List<ModelSysItem> list2 = daoImpl.getProviderItemBySysItemId(providerId, sysItemId);
			if (list2 != null) {
				jsonStr = GsonUtil.getGson().toJson(list2);
			}
		} else	if (function.equals("queryProviderItemById")) {
			String itemId = req.getParameter("itemId");
//			ProviderItemModel providerItem = NoDbData
//					.queryProviderItemById(itemId);
			ModelProviderItem providerItem = daoImpl.getProviderItemById(itemId);
			if (providerItem != null) {
				jsonStr = GsonUtil.getGson().toJson(providerItem);
			}
		} else	if (function.equals("queryProviderItemDetailById")) {
			String itemId = req.getParameter("itemId");
//			ProviderItemModel providerItem = NoDbData
//					.queryProviderItemDetailById(itemId);
			ModelProviderItem providerItem = daoImpl.getProviderItemDetailById(itemId);
			if (providerItem != null) {
				jsonStr = GsonUtil.getGson().toJson(providerItem);
//				System.out.println("ProviderItemServlet :queryProviderItemDetailById(), jsonStr=" + jsonStr);
			}
//		} else	if (function.equals("buyItem")) {
//			String type = req.getParameter("type");
//			String itemId = req.getParameter("itemId");
//			String userId = req.getParameter("userId");
//			
//			System.out.println("ProviderItemServlet : type=" + type+ ",itemId=" + itemId + ",userId=" + userId);
//			// 进行“认购”的数据操作
//			jsonStr = "success";// failed
		} else if (function.equals("modifyProviderItemInfo")) {
			String itemId = req.getParameter("itemId");
			String infoType = req.getParameter("infoType");
			String content = req.getParameter("content");
			System.out.println("ProviderItemServlet: itemId=" + itemId + ", infoType=" + infoType +", content="+content);
			
			if (daoImpl.update(itemId, infoType, content) == true) {
				jsonStr = "success";
			}
//			jsonStr = "success";
		} else if (function.equals("modifyProviderItemPrice")) {
			String itemId = req.getParameter("itemId");
			String price = req.getParameter("price");
			String priceOld = req.getParameter("priceOld");
			System.out.println("ProviderItemServlet: itemId=" + itemId + ", price=" + price +", priceOld="+priceOld);

			if (daoImpl.updatePrice(itemId, price, priceOld) == true) {
				jsonStr = "success";
			}
//			jsonStr = "success";
		} else	if (function.equals("addProviderItem")) {
			String itemInfo = req.getParameter("itemInfo");
			ModelProviderItem providerItem = GsonUtil.getGson().fromJson(itemInfo,
					new TypeToken<ModelProviderItem>() {}.getType());
					
			providerItem.setRemark("");
			providerItem.setSummary("");
			
			if (daoImpl.add(providerItem) == true) {
				jsonStr = GsonUtil.getGson().toJson(providerItem);
			}
		} else if (function.equals("delProviderItem")) {
			String itemId = req.getParameter("itemId");
			System.out.println("ProviderItemServlet: itemId=" + itemId);

			if (daoImpl.delete(itemId) == true) {
				jsonStr = "success";
			}
		} else if (function.equals("moveProviderItem")) {
			String itemId = req.getParameter("itemId");
			String targetItemId = req.getParameter("targetItemId");
			System.out.println("ProviderItemServlet: itemId=" + itemId + ", targetItemId=" + targetItemId);

			if (daoImpl.moveItem(itemId, targetItemId) == true) {
				jsonStr = "success";
			}
		} else {
			System.out
					.println("ProviderItemServlet : Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, ProviderItem Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
