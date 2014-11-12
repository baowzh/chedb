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
//		out.println("  <h4>下一版本要加的功能：</h4>");
//		out.println("  1、违章免费查询功能；<br/>");
//		out.println("  2、天气预报；<br/>");
//		out.println("  3、车主或商家可以统计自己的开销或业务；<br/>");
//		out.println("  4、商家可以给车主发送服务信息或回访；<br/>");
//		out.println("  <br/>");
//		out.println("  敬请期待。<br/>");
//		out.println("  <h4>你有什么建议或想使用的功能，可在“意见或反馈”中告诉我们。</h4>");
//	}
//	private void printNowVersion(PrintWriter out) {
//		out.println("<h4>最新版本为："+apkVersionName+"，这个版本增加了以下功能：</h4>");
//		out.println("1、XXX；<br/>");
//		out.println("2、YYY；<br/>");
//		out.println("<br/>");
//		out.println("程序只有"+apkSize+"，建议您更新。<br/>");
//		out.println("<h4>你有什么建议或想使用的功能，可在“意见或反馈”中告诉我们。</h4>");
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
//		String jsonStr = "系统信息。";
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
				out.println("<h3>你还没有业务记录。</h3>");
				out.println("我们建议你把每笔业务都记录进来，这样你可以方便的查询和统计，我们也会逐步开放一些统计功能，让你对经营状况了如指掌。<br/>");

				out.println("<h4>可以通过两种方法添加业务记录：</h4>");
				out.println("1、让车主在相应服务项目中认购；<br/>");
				out.println("2、你自己进行添加，见右上角的菜单；<br/>");
				out.close();
			} else if (type.equals("NoBuynoteCarUser")) {
				PrintWriter out = resp.getWriter();
				out.println("<h3>你还没有服务记录。</h3>");
				out.println("我们建议你把爱车的每笔花费都记录进来，这样你可以方便的查询和统计，我们也会逐步开放一些统计功能，让你对爱车的支出状况了如指掌。<br/>");
				out.println("<h4>可以通过两种方法添加爱车服务记录：</h4>");
				out.println("1、你在商家的相应服务项目中认购；<br/>");
				out.println("2、你自己进行添加，见右上角的菜单；<br/>");
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
//				out.println("<h3>“车大邦”手机端程序下载</h3>");
//				out.println("<br/>");
//				out.println("<a href='http://"+DaoUtil.ipaddr+":8080/CheDBServlet/apk/cardb.apk'>Android平台</a>");
//				out.println("<br/>");
//				
//				out.println("iOS平台我们正在紧张开发中");
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
