package com.chedb.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forum.model.ModelPublicItem;
import com.forum.model.ModelVersionInfo;

@Controller
public class AppinfoController {
	static int apkVersion = 0;
	static String apkVersionName;
	static String apkSize;
	static String apkPath;

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
		bufer.append("<h3>你还没有业务记录。</h3>");
		bufer.append("我们建议你把每笔业务都记录进来，这样你可以方便的查询和统计，我们也会逐步开放一些统计功能，让你对经营状况了如指掌。<br/>");

		bufer.append("<h4>可以通过两种方法添加业务记录：</h4>");
		bufer.append("1、让车主在相应服务项目中认购；<br/>");
		bufer.append("2、你自己进行添加，见右上角的菜单；<br/>");
		return bufer.toString();
	}

	@RequestMapping("/NoBuynoteCarUser.do")
	@ResponseBody
	public String NoBuynoteCarUser(HttpServletRequest request, ModelMap map) {
		StringBuffer bufer = new StringBuffer();
		bufer.append("<h3>你还没有服务记录。</h3>");
		bufer.append("我们建议你把爱车的每笔花费都记录进来，这样你可以方便的查询和统计，我们也会逐步开放一些统计功能，让你对爱车的支出状况了如指掌。<br/>");
		bufer.append("<h4>可以通过两种方法添加爱车服务记录：</h4>");
		bufer.append("1、你在商家的相应服务项目中认购；<br/>");
		bufer.append("2、你自己进行添加，见右上角的菜单；<br/>");
		return bufer.toString();
	}

	@RequestMapping("/question.do")
	public ModelAndView question(HttpServletRequest request, ModelMap map) {
		String id = request.getParameter("id");
		System.out.println("AppinfoServlet : question id:" + id);
		return new ModelAndView("redirect:html/function.HTML");
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
	public List<ModelPublicItem> queryAppinfoListByType() {
		return new ArrayList<ModelPublicItem>();
	}

	@RequestMapping("/getNowVersion.do")
	@ResponseBody
	public ModelVersionInfo getNowVersion() {
		return new ModelVersionInfo();
	}
//	commitComplain
//	commitFreeback

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
