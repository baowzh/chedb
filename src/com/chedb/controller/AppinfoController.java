package com.chedb.controller;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chedb.service.AppInfoService;
import com.chedb.service.UserService;
import com.forum.model.ModelPublicItem;
import com.forum.model.ModelVersionInfo;

@Controller
public class AppinfoController {
	static int apkVersion = 0;
	static String apkVersionName;
	static String apkSize;
	static String apkPath;
	@Resource(name = "appInfoServiceImpl")
	private AppInfoService appInfoService;
	@Resource(name = "userServiceimpl")
	private UserService userService;

	@RequestMapping("/function.do")
	public ModelAndView function(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("redirect:html/function.HTML");
	}

	@RequestMapping("/about.do")
	public ModelAndView about(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("redirect:html/about.HTML");
	}

	@RequestMapping("/NoBuynoteProviderUser.do")
	@ResponseBody
	public String NoBuynoteProviderUser(HttpServletRequest request, ModelMap map) {
		// PrintWriter out = resp.getWriter();
		StringBuffer bufer = new StringBuffer();
		bufer.append("<h3>�㻹û��ҵ���¼��</h3>");
		bufer.append("���ǽ������ÿ��ҵ�񶼼�¼��������������Է���Ĳ�ѯ��ͳ�ƣ�����Ҳ���𲽿���һЩͳ�ƹ��ܣ�����Ծ�Ӫ״������ָ�ơ�<br/>");

		bufer.append("<h4>����ͨ�����ַ������ҵ���¼��</h4>");
		bufer.append("1���ó�������Ӧ������Ŀ���Ϲ���<br/>");
		bufer.append("2�����Լ�������ӣ������ϽǵĲ˵���<br/>");
		return bufer.toString();
	}

	@RequestMapping("/NoBuynoteCarUser.do")
	@ResponseBody
	public String NoBuynoteCarUser(HttpServletRequest request, ModelMap map) {
		StringBuffer bufer = new StringBuffer();
		bufer.append("<h3>�㻹û�з����¼��</h3>");
		bufer.append("���ǽ�����Ѱ�����ÿ�ʻ��Ѷ���¼��������������Է���Ĳ�ѯ��ͳ�ƣ�����Ҳ���𲽿���һЩͳ�ƹ��ܣ�����԰�����֧��״������ָ�ơ�<br/>");
		bufer.append("<h4>����ͨ�����ַ�����Ӱ��������¼��</h4>");
		bufer.append("1�������̼ҵ���Ӧ������Ŀ���Ϲ���<br/>");
		bufer.append("2�����Լ�������ӣ������ϽǵĲ˵���<br/>");
		return bufer.toString();
	}

	@RequestMapping("/question.do")
	public ModelAndView question(HttpServletRequest request, ModelMap map) {
		String id = request.getParameter("id");
		System.out.println("AppinfoServlet : question id:" + id);
		return new ModelAndView("redirect:html/" + id + ".html");
	}

	@RequestMapping("/down.do")
	public ModelAndView down(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("redirect:html/down.HTML");
	}

	@RequestMapping("/version.do")
	public ModelAndView version(HttpServletRequest request, ModelMap map) {
		String localVer = request.getParameter("localVer");
		if (apkVersion == 0) {
			this.getVersionInfo(request);
		}

		if (Integer.parseInt(localVer) < apkVersion) {

			return new ModelAndView("redirect:html/nowversion.HTML");
		} else {

			return new ModelAndView("redirect:html/nextversion.HTML");
		}
	}

	@RequestMapping("/queryAppinfoListByType.do")
	@ResponseBody
	public List<ModelPublicItem> queryAppinfoListByType(
			HttpServletRequest request) throws Exception {
		String type = request.getParameter("type");
		return appInfoService.getAppinfoListByType(type);
	}

	@RequestMapping("/getNowVersion.do")
	@ResponseBody
	public ModelVersionInfo getNowVersion(HttpServletRequest req)
			throws Exception {
		String appId = req.getParameter("appId");
		userService.addUserConfig("1001", appId, "");
		getVersionInfo(req);
		ModelVersionInfo info = new ModelVersionInfo();
		info.setSerVersionCode(apkVersion);
		info.setSerVersionName(apkVersionName);
		info.setSerFilePath(apkPath);
		return info;
	}

	@RequestMapping("/commitComplain.do")
	@ResponseBody
	public String commitComplain(HttpServletRequest req) {
		String jsonStr = "failed";
		// String providerId = req.getParameter("providerId");
		String strSingleTitleList = req.getParameter("strSingleTitleList");
		String userId = req.getParameter("userId");
		String text = req.getParameter("text");
		try {
			if (this.appInfoService.commitComplain("complain",
					strSingleTitleList, text, userId) == true) {
				jsonStr = "success";// failed
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			jsonStr = "failed";// failed
		}
		return jsonStr;
	}

	//
	@RequestMapping("/commitFreeback.do")
	@ResponseBody
	public String commitFreeback(HttpServletRequest req) {
		String jsonStr = "failed";
		String strSingleTitleList = req.getParameter("strSingleTitleList");
		String userId = req.getParameter("userId");
		String text = req.getParameter("text");
		System.out.println("AppinfoServlet:" + "strSingleTitleList="
				+ strSingleTitleList + ",userId=" + userId);
		// list = NoDbData.queryStringList(Integer.parseInt(type));
		try {
			if (appInfoService.commitFreeback("freeback", strSingleTitleList,
					text, userId) == true) {
				jsonStr = "success";// failed
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonStr;
	}

	private void getVersionInfo(HttpServletRequest req) {

		Properties ppt = new Properties();
		try {
			String s = req.getSession().getServletContext().getRealPath("/");
			String proPath = s + "/apk/apkVersion.properties";
			FileInputStream fin = new FileInputStream(proPath);
			ppt.load(fin);
			apkVersion = Integer.parseInt(ppt.getProperty("apkVersion"));
			apkVersionName = ppt.getProperty("apkVersionName");
			apkSize = ppt.getProperty("apkSize");
			apkPath = ppt.getProperty("apkPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
