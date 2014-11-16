package com.chedb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chedb.service.UserService;
import com.forum.daoimpl.ProviderDaoImpl;
import com.forum.model.ModelCar;
import com.forum.model.ModelProvider;
import com.forum.model.ModelUser;
import com.forum.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

@Controller
public class UserController {
	@Resource(name = "userServiceimpl")
	private UserService userService;

	@RequestMapping("/Login.do")
	@ResponseBody
	public ModelUser Login(HttpServletRequest req) throws Exception {
		boolean ok = false;
		String userNo = req.getParameter("userNo");
		String password = req.getParameter("password");
		System.out.println("UserServlet : userNo=" + userNo + ", password="
				+ password);
		String jsonStr = "failed";
		String appId = req.getParameter("appId");
		userService.addUserConfig("2002", appId, userNo);
		ModelUser user = userService.getUser(userNo);
		if (user == null) {
			jsonStr = "nouser";
		} else {
			if (user.getPasswd() == null) {
				if (password == null || password.equals("")) {
					ok = true;
				} else {
					jsonStr = "errorpasswd";
				}
			} else {
				if (user.getPasswd().equals(password) == false) {
					jsonStr = "errorpasswd";
				} else {
					ok = true;
				}
			}
		}
		return user;
	}

	@RequestMapping("/modifyUserInfo.do")
	@ResponseBody
	public String modifyUserInfo(HttpServletRequest req) throws Exception {
		String userId = req.getParameter("userId");
		String infoType = req.getParameter("infoType");
		String content = req.getParameter("content");
		String jsonStr = "failed";
		System.out.println("UserServlet: userId=" + userId + ", infoType="
				+ infoType + ", content=" + content);

		if (userService.update(userId, infoType, content) == true) {
			jsonStr = "success";
		}
		return jsonStr;

	}

	@RequestMapping("/existApp.do")
	@ResponseBody
	public String existApp(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String appId = req.getParameter("appId");
		userService.addUserConfig("1002", appId, "");
		jsonStr = "success";
		return jsonStr;
	}

	@RequestMapping("/registerUser.do")
	@ResponseBody
	public String registerUser(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String userInfo = req.getParameter("userInfo");
		ModelUser user = GsonUtil.getGson().fromJson(userInfo,
				new TypeToken<ModelUser>() {
				}.getType());

		System.out.println("UserServlet: userId=" + user.getNo());

		String appId = req.getParameter("appId");
		userService.addUserConfig("2001", appId, user.getNo());

		if (userService.register(user) == true) {
			jsonStr = "success";
		}
		if (user.getLevel() == 2) {
			// 如果是商家用户，则在商家表中添加一条记录
			ModelProvider t = new ModelProvider();
			t.setProviderId(user.getNo());
			t.setTitle("汽修服务店");
			ProviderDaoImpl pdao = new ProviderDaoImpl();
			pdao.add(t);
		}
		return jsonStr;
	}

	@RequestMapping("/getUserNewNo.do")
	@ResponseBody
	public String getUserNewNo(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String type = req.getParameter("type");
		String newId = userService.getNewId(Integer.parseInt(type));
		if (newId != null) {
			jsonStr = newId;
		}
		return jsonStr;
	}

	@RequestMapping("/getUserInfoByPhone.do")
	@ResponseBody
	public String getUserInfoByPhone(HttpServletRequest req) throws Exception {
		String jsonStr = "failed";
		String phone = req.getParameter("phone");
		ModelUser user = userService.getUserByPhone(phone);
		if (user != null) {
			jsonStr = "与 " + phone + " 绑定的用户登录账号为：" + user.getNo() + "，密码："
					+ user.getPasswd() + "。谢谢使用。【车大帮】";
		} else {
			jsonStr = "nouser";
		}
		return jsonStr;
	}

	@RequestMapping("/chooseCar.do")
	@ResponseBody
	public String chooseCar(HttpServletRequest req) throws Exception {
		String appId = req.getParameter("appId");
		String carId = req.getParameter("carId");
		String jsonStr = "failed";
		userService.addUserConfig("3001", appId, carId);

		if (userService.addChoosedCarByAppId(carId, appId) == true) {
			jsonStr = "success";
		}
		return jsonStr;
	}

	@RequestMapping("/queryChoosedCarByUser.do")
	@ResponseBody
	public String queryChoosedCarByUser(HttpServletRequest req)
			throws Exception {
		String userId = req.getParameter("userId");
		String jsonStr = "failed";
		List<ModelCar> listCar = userService.getChoosedCarByUser(userId);//
		if (listCar != null) {
			jsonStr = GsonUtil.getGson().toJson(listCar);
		}
		return jsonStr;
	}

}
