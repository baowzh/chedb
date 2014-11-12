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
//		String jsonStr = "ϵͳ��Ϣ��";
		String type = req.getParameter("infoType");
		if (type != null) {
			System.out.println("AppinfoServlet : type:" + type);
			if (type.equals("summary")) {
				PrintWriter out = resp.getWriter();
				out.println("�̼�ժҪ��Ϣ��������д�������ݣ�<br/>");
				out.println("1����ҵ���ʣ������<br/>");
				out.println("2��ռ�������Ӫҵʱ�䣻<br/>");
				out.println("3������ʱ�䣬ע���ʽ�<br/>");
				out.println("4��������ּ�ȣ�<br/>");
				out.close();
			} else if (type.equals("phone")) {
				PrintWriter out = resp.getWriter();
				out.println("�̼ҵ绰��һ��Ҫ�������Ҫ�绰��ѯ��");	
				out.close();
			} else if (type.equals("remark")) {
				PrintWriter out = resp.getWriter();
				out.println("�̼�˵����������д������Ϣ���������ݵȣ��磺<br/>");	
				out.println("1��Ա����������������������<br/>");
				out.println("2����Ҫ����Ʒ�ƣ�����ڡ���ˣ�<br/>");
				out.println("3��Ӳ����ʩ���ܣ�����Ϣ�ҡ�ͣ�����ȣ�<br/>");
				out.println("4��ά���豸���ܵȣ�<br/>");
				out.close();
			} else if (type.equals("addrNotDw")) {
				PrintWriter out = resp.getWriter();
				out.println("�㻹û��Ϊ�Լ��ĵ궨λ�����������ڵ�ͼ�Ͽ������㣬���ݾ����ѯʱҲ�鲻���㡣<br/>");	
				out.println("1��������Ҫ���Լ��ĵ��<br/>");
				out.println("2��������ڵ�ͼ�϶�λ����ť��<br/>");
				out.println("3����������桱�Ϳ����ˣ�<br/>");
				out.println("<br/>");
				out.println("������Զ����������ڵ�λ���ڵ�ͼ������ǣ�<br/>");
				out.println("���ϲ���ֻ��Ҫ��һ�Σ�������ĵ����ˣ�<br/>");
				out.close();
			} else if (type.equals("getImage")) {
				String providerId = req.getParameter("providerId");
				String imgId = req.getParameter("imgId");
				String ss = req.getContextPath();
				if (imgId.equals("-1")) {
					PrintWriter out = resp.getWriter();
					out.println("��ǰû���κ��̼�ͼƬ��<br/>");	
					out.println("�ϴ�ͼƬ��������̼����󡣿����ϴ�������ۡ����䡢Ա����Ӱ��Ա�������ա��Ƚ��豸����ҵ���ʵ���Ƭ��<br/>");	
					out.println("����ϴ�8��ͼƬ��<br/>");	
					out.println("<br/>������Ͻǵġ�+����ť�����Ƭ��<br/>");	
					out.close();
					return;
				}
				// �õ�ͼƬ����ʵ·��   
//				String imagePath1 = "D:\\pic\\102"+imgId+".jpg"; 
				String imagePath = ss+"/img/provider/"+providerId+"/"+imgId+".jpg"; 
				System.out.println("�̼�ͼƬ��"+imagePath);	
				sendImgFile(resp, imagePath);
//				OutputStream output = resp.getOutputStream();// �õ������ 
//				resp.setContentType(JPG);// �趨���������   
//				// �õ�ͼƬ���ļ���   
//				InputStream imageIn = new FileInputStream(new File(imagePath));   
//				// �õ�����ı����������ļ�������jpg��ʽ����   
//				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);   
//				// �õ�������ͼƬ����   
//				BufferedImage image = decoder.decodeAsBufferedImage();   
//				// �õ�����ı�����   
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);   
//				encoder.encode(image);// ��ͼƬ�����������   
//				imageIn.close();// �ر��ļ���  
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
			// �õ�ͼƬ����ʵ·��   
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
			// �õ�ͼƬ����ʵ·��   
			String s=req.getSession().getServletContext().getRealPath("/");
			String providerPath = s+"img/provider/"+providerId+"/";
			
			FileData.dirCreate(providerPath);// ����ͼƬ���ڵ�Ŀ¼
			
			String  imgId = daoImpl.getImgIdAndUpdateImgList(providerId);//�������ݿ�õ� 
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

			String ret = daoImpl.editImgSpace(providerId, oldImgIdList, imgId, edittype);//�������ݿ�õ� 
			if (ret!=null && edittype.equals("0")) {// ɾ��ͼƬ
				// �õ�ͼƬ����ʵ·��   
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
