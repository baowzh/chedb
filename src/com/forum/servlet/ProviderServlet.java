package com.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.ProviderDaoImpl;
import com.forum.daoimpl.UserDaoImpl;
import com.forum.dbdata.FileData;
import com.forum.model.ModelProvider;
import com.forum.util.GsonUtil;

public class ProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;
	
	private static final String JPG = "image/jpeg;charset=GB2312";   


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		test(req, resp);
		myGet(req, resp);
	}

	private void sendImgFile(HttpServletResponse resp, String imgfile) 
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String con = "<html><body style='background-color: transparent;'>"
				+ "<div style='text-align:center'><img src='"+imgfile+"'></div>"+
		"</body></html>";
		out.println(con);	
		out.close();
	}
	private void myGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		resp.setContentType("text/html;charset=UTF-8");
//		String jsonStr = "系统信息。";
		String type = req.getParameter("infoType");
		if (type != null) {
			System.out.println("AppinfoServlet : type:" + type);
			if (type.equals("summary")) {
				PrintWriter out = resp.getWriter();
				out.println("商家摘要信息，可以填写以下内容：<br/>");
				out.println("1、行业资质，如类别；<br/>");
				out.println("2、占地面积，营业时间；<br/>");
				out.println("3、创建时间，注册资金；<br/>");
				out.println("4、服务宗旨等；<br/>");
				out.close();
			} else if (type.equals("phone")) {
				PrintWriter out = resp.getWriter();
				out.println("商家电话，一定要填，车主需要电话咨询。");	
				out.close();
			} else if (type.equals("remark")) {
				PrintWriter out = resp.getWriter();
				out.println("商家说明，可以填写背景信息，服务内容等，如：<br/>");	
				out.println("1、员工人数，各级技工人数；<br/>");
				out.println("2、主要服务品牌，如大众、别克；<br/>");
				out.println("3、硬件设施介绍，如休息室、停车场等；<br/>");
				out.println("4、维修设备介绍等；<br/>");
				out.close();
			} else if (type.equals("addrNotDw")) {
				PrintWriter out = resp.getWriter();
				out.println("你还没有为自己的店定位，这样车主在地图上看不到你，根据距离查询时也查不到你。<br/>");	
				out.println("1、你首先要在自己的店里；<br/>");
				out.println("2、点击“在地图上定位”按钮；<br/>");
				out.println("3、点击“保存”就可以了；<br/>");
				out.println("<br/>");
				out.println("软件会自动根据你所在的位置在地图上做标记；<br/>");
				out.println("以上操作只需要做一次，除非你的店搬家了；<br/>");
				out.close();
			} else if (type.equals("getImage")) {
				String providerId = req.getParameter("providerId");
				String imgId = req.getParameter("imgId");
				String ss = req.getContextPath();
				if (imgId.equals("-1")) {
					PrintWriter out = resp.getWriter();
					out.println("当前没有任何商家图片。<br/>");	
					out.println("上传图片可以提高商家形象。可以上传店面外观、车间、员工合影、员工工作照、先进设备、行业资质等照片。<br/>");	
					out.println("最多上传8张图片。<br/>");	
					out.println("<br/>点击右上角的“+”按钮添加照片。<br/>");	
					out.close();
					return;
				}
				// 得到图片的真实路径   
//				String imagePath1 = "D:\\pic\\102"+imgId+".jpg"; 
				String imagePath = ss+"/img/provider/"+providerId+"/"+imgId+".jpg"; 
				System.out.println("商家图片："+imagePath);	
				sendImgFile(resp, imagePath);
//				OutputStream output = resp.getOutputStream();// 得到输出流 
//				resp.setContentType(JPG);// 设定输出的类型   
//				// 得到图片的文件流   
//				InputStream imageIn = new FileInputStream(new File(imagePath));   
//				// 得到输入的编码器，将文件流进行jpg格式编码   
//				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);   
//				// 得到编码后的图片对象   
//				BufferedImage image = decoder.decodeAsBufferedImage();   
//				// 得到输出的编码器   
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);   
//				encoder.encode(image);// 对图片进行输出编码   
//				imageIn.close();// 关闭文件流  
//				output.close();
			}
		} else {			
			System.out.println("AppinfoServlet : type is null");
		}
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
		System.out.println("ProviderServlet:" + function);
		
		List<ModelProvider> list = null;
		ProviderDaoImpl daoImpl = new ProviderDaoImpl();
		if (function.equals("queryProviderList")) {
			String level = req.getParameter("level");
			String sort = req.getParameter("sort");
			String priceStart = req.getParameter("priceStart");
			String priceEnd = req.getParameter("priceEnd");
			String latitude = req.getParameter("latitude");
			String longitude = req.getParameter("longitude");
			String strItemIdList = req.getParameter("strItemIdList");
//			List<String> sysItemIdList = null;
//			if (strItemIdList != null) {
//				sysItemIdList = GsonUtil.getGson().fromJson(strItemIdList,
//						new TypeToken<List<String>>() {
//						}.getType());
//			}
//			System.out.println("ProviderServlet:" + "level=" + level + ",sort="
//					+ sort + ",strItemIdList=" + strItemIdList);
//			list = NoDbData.queryProviderList(Integer.parseInt(level),
//					sysItemIdList, Integer.parseInt(sort));
//			String strSysItemList = "";
			list = daoImpl.getProviderList(level, strItemIdList, sort, priceStart, priceEnd, latitude, longitude);
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryProviderListBySearch")) {
			String searchStr = req.getParameter("searchStr");
			System.out.println("ProviderServlet:" + "searchStr=" + searchStr);
//			list = NoDbData.queryProviderListBySearch(searchStr);
			list = daoImpl.getProviderListBySearch(searchStr);
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("queryProviderImg")) {
			String providerId = req.getParameter("providerId");
			String imgId = req.getParameter("imgId");
			System.out.println("ProviderServlet:" + "imgId=" + imgId);
//			list = NoDbData.queryProviderListBySearch(searchStr);
			
//			String ss = req.getContextPath();
//			request.getContextPath()
			// 得到图片的真实路径   
//			String imagePath1 = "D:\\pic\\102"+imgId+".jpg"; 
			String s=req.getSession().getServletContext().getRealPath("/");
			String imagePath = s+"img/provider/"+providerId+"/"+imgId + ".jpg"; 
			
			jsonStr = FileData.SendFileImg(imagePath, 120, true); 
		} else if (function.equals("unloadProviderImg")) {
			String providerId = req.getParameter("providerId");
			String imgString = req.getParameter("imgString");
			System.out.println("ProviderServlet:unloadProviderImg(), providerId=" + providerId);

//			jsonStr = "success";
//			if (false) {
			// 得到图片的真实路径   
			String s=req.getSession().getServletContext().getRealPath("/");
			String providerPath = s+"img/provider/"+providerId+"/";
			
			FileData.dirCreate(providerPath);// 创建图片所在的目录
			
			String  imgId = daoImpl.getImgIdAndUpdateImgList(providerId);//访问数据库得到 
			String imagePath = providerPath + imgId + ".jpg"; 
			boolean ret = FileData.writeFileImg(imagePath, imgString); 
			if (ret == true) {
				jsonStr = imgId;
			}
		} else if (function.equals("editProviderImg")) {
			String providerId = req.getParameter("providerId");
			String oldImgIdList = req.getParameter("oldImgIdList");
			String imgId = req.getParameter("imgId");
			String edittype = req.getParameter("edittype");
			
			System.out.println("editProviderImg(), providerId=" + providerId);

			String ret = daoImpl.editImgSpace(providerId, oldImgIdList, imgId, edittype);//访问数据库得到 
			if (ret!=null && edittype.equals("0")) {// 删除图片
				// 得到图片的真实路径   
				String s=req.getSession().getServletContext().getRealPath("/");
				String imagePath = s+"img/provider/"+providerId+"/"+imgId + ".jpg"; 
			
				FileData.delFileImg(imagePath); 
			}
			if (ret!=null) {				
				jsonStr = ret;
			}
		} else if (function.equals("queryProviderByUserId")) {
			String userId = req.getParameter("userId");
//			ProviderModel provider = NoDbData.queryProviderByUserId(userId);
			ModelProvider provider = daoImpl.queryProviderById(userId);
			if (provider != null) {
				jsonStr = GsonUtil.getGson().toJson(provider);
			}
		} else if (function.equals("queryProviderById")) {
			String providerId = req.getParameter("providerId");
//			ProviderModel provider = NoDbData.queryProviderById(providerId);
			ModelProvider provider = daoImpl.queryProviderById(providerId);
			
			String appId = req.getParameter("appId");
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			userDaoImpl.addUserConfig("3002", appId, providerId);
			
			daoImpl.appendBrowseCount(providerId);
			if (provider != null) {
				jsonStr = GsonUtil.getGson().toJson(provider);
			}
		} else if (function.equals("appendProviderBrowse")) {
			String providerId = req.getParameter("providerId");
			System.out.println("ProviderServlet: providerId=" + providerId);

			if (daoImpl.appendBrowseCount(providerId) == true) {
				jsonStr = "success";
			}
		} else if (function.equals("modifyProviderInfo")) {
			String providerId = req.getParameter("providerId");
			String infoType = req.getParameter("infoType");
			String content = req.getParameter("content");
			System.out.println("ProviderServlet: providerId=" + providerId + ", infoType=" + infoType +", content="+content);

			if (daoImpl.update(providerId, infoType, content) == true) {
				jsonStr = "success";
			}
		} else {
			System.out.println("ProviderServlet:Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, Provider Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
