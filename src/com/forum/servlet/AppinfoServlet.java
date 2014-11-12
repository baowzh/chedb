package com.forum.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.daoimpl.AppinfoDaoImpl;
import com.forum.daoimpl.UserDaoImpl;
import com.forum.model.ModelPublicItem;
import com.forum.model.ModelVersionInfo;
import com.forum.util.GsonUtil;

//import com.common.util.SystemUtil;

public class AppinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;

	static int apkVersion = 0;
	static String apkVersionName;
	static String apkSize;
	static String apkPath;
	
	private void getVersionInfo(HttpServletRequest req) {
		
		Properties ppt = new Properties();
		try {
//		     ppt.load(AppinfoServlet.class.getResourceAsStream("/apk/apkVersion.properties"));
			String s=req.getSession().getServletContext().getRealPath("/");
			String proPath = s+"apk/apkVersion.properties"; 
			
//			AppinfoServlet.class.getRealPath("/");

			FileInputStream fin = new FileInputStream(proPath);
//		     InputStream i = AppinfoServlet.class.getClassLoader().g.getResourceAsStream("apk/apkVersion.properties");
		     ppt.load(fin);
		     apkVersion = Integer.parseInt(ppt.getProperty("apkVersion"));
		     apkVersionName = ppt.getProperty("apkVersionName");
		     apkSize = ppt.getProperty("apkSize");
		     apkPath = ppt.getProperty("apkPath"); 
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
//	
//	private void printNextVersion(PrintWriter out) {
//		out.println("  <h4>��һ�汾Ҫ�ӵĹ��ܣ�</h4>");
//		out.println("  1��Υ����Ѳ�ѯ���ܣ�<br/>");
//		out.println("  2������Ԥ����<br/>");
//		out.println("  3���������̼ҿ���ͳ���Լ��Ŀ�����ҵ��<br/>");
//		out.println("  4���̼ҿ��Ը��������ͷ�����Ϣ��طã�<br/>");
//		out.println("  <br/>");
//		out.println("  �����ڴ���<br/>");
//		out.println("  <h4>����ʲô�������ʹ�õĹ��ܣ����ڡ�����������и������ǡ�</h4>");
//	}
//	private void printNowVersion(PrintWriter out) {
//		out.println("<h4>���°汾Ϊ��"+apkVersionName+"������汾���������¹��ܣ�</h4>");
//		out.println("1��XXX��<br/>");
//		out.println("2��YYY��<br/>");
//		out.println("<br/>");
//		out.println("����ֻ��"+apkSize+"�����������¡�<br/>");
//		out.println("<h4>����ʲô�������ʹ�õĹ��ܣ����ڡ�����������и������ǡ�</h4>");
//	}
//	
//	private void downloadApk(HttpServletRequest req, HttpServletResponse response) 
//			throws ServletException, IOException {
////		URL downUrl = new URL("/APPDown/android/HGT.apk");
////        URLConnection conn = downUrl.openConnection();
//		File f = new File("D:\\androidinstall\\eclipse\\workspace\\CheDBServlet\\apk\\cardb.apk");
//        InputStream is = new FileInputStream(f);//conn.getInputStream();
//        BufferedInputStream in = new BufferedInputStream(is); 
//        response.addHeader("Content-Disposition", "attachment;filename=" + "cardb.apk");
//        //response.setContentType("application/vnd.android.package-archive");
//        response.addHeader("Content-Type", "application/vnd.android.package-archive");
//        OutputStream outputStream = response.getOutputStream();
//        byte[] data = new byte[1024];
//        int size = 0;
//        int l = 0;
//        while ((l = in.read(data)) > 0) {
//            size += l;
//            outputStream.write(data, 0, l);
//        }
//        response.addHeader("Content-Length", size + "");
//        outputStream.flush();
//        outputStream.close();
//        in.close();
//        is.close();
//
//	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		myGet(req, resp);
//		test(req, resp);
	}
	private void myGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		resp.setContentType("text/html;charset=UTF-8");
//		String jsonStr = "ϵͳ��Ϣ��";
		String type = req.getParameter("infoType");
		AppinfoDaoImpl daoImpl = new AppinfoDaoImpl();
		if (type != null) {
			System.out.println("AppinfoServlet : type:" + type);
			if (type.equals("function")) {
				resp.sendRedirect("html/function.HTML");
			} else if (type.equals("about")) {
				resp.sendRedirect("html/about.HTML");
			} else if (type.equals("NoBuynoteProviderUser")) {
				PrintWriter out = resp.getWriter();
				out.println("<h3>�㻹û��ҵ���¼��</h3>");
				out.println("���ǽ������ÿ��ҵ�񶼼�¼��������������Է���Ĳ�ѯ��ͳ�ƣ�����Ҳ���𲽿���һЩͳ�ƹ��ܣ�����Ծ�Ӫ״������ָ�ơ�<br/>");

				out.println("<h4>����ͨ�����ַ������ҵ���¼��</h4>");
				out.println("1���ó�������Ӧ������Ŀ���Ϲ���<br/>");
				out.println("2�����Լ�������ӣ������ϽǵĲ˵���<br/>");
				out.close();
			} else if (type.equals("NoBuynoteCarUser")) {
				PrintWriter out = resp.getWriter();
				out.println("<h3>�㻹û�з����¼��</h3>");
				out.println("���ǽ�����Ѱ�����ÿ�ʻ��Ѷ���¼��������������Է���Ĳ�ѯ��ͳ�ƣ�����Ҳ���𲽿���һЩͳ�ƹ��ܣ�����԰�����֧��״������ָ�ơ�<br/>");
				out.println("<h4>����ͨ�����ַ�����Ӱ��������¼��</h4>");
				out.println("1�������̼ҵ���Ӧ������Ŀ���Ϲ���<br/>");
				out.println("2�����Լ�������ӣ������ϽǵĲ˵���<br/>");
				out.close();
			} else if (type.equals("question")) {
				String id = req.getParameter("id");
				System.out.println("AppinfoServlet : question id:" + id);
//				String c = daoImpl.getAppinfoContentById(id);
				resp.sendRedirect("html/"+id+".HTML");
			} else if (type.equals("down")) {
//				String id = req.getParameter("id");
//				System.out.println("AppinfoServlet : question id:" + id);
//				String c = daoImpl.getAppinfoContentById(id);
//				resp.sendRedirect("html/d2.mht");
//				downloadApk(req, resp);
				resp.sendRedirect("html/down.HTML");
//				PrintWriter out = resp.getWriter();
//				out.println("<br/>");
//				out.println("<h3>�������ֻ��˳�������</h3>");
//				out.println("<br/>");
//				out.println("<a href='http://"+DaoUtil.ipaddr+":8080/CheDBServlet/apk/cardb.apk'>Androidƽ̨</a>");
//				out.println("<br/>");
//				
//				out.println("iOSƽ̨�������ڽ��ſ�����");
//				out.close();
				//"http://192.168.1.101:8080/CheDBServlet/"
			} else if (type.equals("version")) {
//				PrintWriter out = resp.getWriter();
				String localVer = req.getParameter("localVer");
				System.out.println("AppinfoServlet : localVer:" + localVer);

				if (apkVersion==0) {
					getVersionInfo(req);
				}
				
				if (Integer.parseInt(localVer) < apkVersion) {
//					printNowVersion(out);
					resp.sendRedirect("html/nowversion.HTML");
				} else {
//					printNextVersion(out);
					resp.sendRedirect("html/nextversion.HTML");
				}
//				out.close();
			}
		} else {			
			System.out.println("AppinfoServlet : type is null");
		}
		
//		ServletUtil.doResponse(resp, jsonStr);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// test(req, resp);
		myPost(req, resp);
	}

	private void myPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String jsonStr = "failed";
		String function = req.getParameter("function");

		System.out.println("AppinfoServlet:" + function);
		List<ModelPublicItem> list = null;
		
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		AppinfoDaoImpl daoImpl = new AppinfoDaoImpl();
		if (function.equals("queryAppinfoListByType")) {
			String type = req.getParameter("type");
			System.out.println("AppinfoServlet: type=" + type);
//			list = NoDbData.queryStringList(Integer.parseInt(type));
			list = daoImpl.getAppinfoListByType(type);
			if (list != null) {
				jsonStr = GsonUtil.getGson().toJson(list);
			}
		} else if (function.equals("getNowVersion")) {
			String appId = req.getParameter("appId");
			userDaoImpl.addUserConfig("1001", appId, "");
			
			if (apkVersion==0) {
				getVersionInfo(req);
			}
			
			ModelVersionInfo info = new ModelVersionInfo();
			info.setSerVersionCode(apkVersion);
			info.setSerVersionName(apkVersionName);
			info.setSerFilePath(apkPath);
			jsonStr = GsonUtil.getGson().toJson(info);
		} else if (function.equals("commitComplain")) {
			String providerId = req.getParameter("providerId");
			String strSingleTitleList = req.getParameter("strSingleTitleList");
			String userId = req.getParameter("userId");
			String text = req.getParameter("text");
			
			System.out.println("AppinfoServlet:" + "providerId=" + providerId + ",strSingleTitleList="
					+ strSingleTitleList + ",userId=" + userId);
			
//			list = NoDbData.queryStringList(Integer.parseInt(type));
			if (daoImpl.userCommit("complain", strSingleTitleList, text, userId) == true) {
				jsonStr = "success";// failed
			}
		} else if (function.equals("commitFreeback")) {
			String strSingleTitleList = req.getParameter("strSingleTitleList");
			String userId = req.getParameter("userId");
			String text = req.getParameter("text");
			
			System.out.println("AppinfoServlet:" + "strSingleTitleList="
					+ strSingleTitleList + ",userId=" + userId);
			
//			list = NoDbData.queryStringList(Integer.parseInt(type));
			if (daoImpl.userCommit("freeback", strSingleTitleList, text, userId) == true) {
				jsonStr = "success";// failed
			}
		} else {
			System.out
					.println("AppinfoServlet : Error, unown:" + function);
			return;
		}

		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ServletUtil.doResponse(resp, "Hello, AppinfoServlet Servlet !!!!");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
