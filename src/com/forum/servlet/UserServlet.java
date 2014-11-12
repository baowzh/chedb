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
import com.forum.model.ModelCar;
import com.forum.model.ModelProvider;
import com.forum.model.ModelUser;
import com.forum.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 314719472293387358L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		test(req, resp);
		myGet(req, resp);
	}
	private void myGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String type = req.getParameter("infoType");
		if (type != null) {
			System.out.println("AppinfoServlet : type:" + type);
			if (type.equals("sendPasswd")) {
				out.println("ͨ���󶨵��ֻ����һ����������룺<br/>");
				out.println("1��������˺Ű󶨵��ֻ����룻<br/>");
				out.println("2��������Ͻǵġ����͡���ť��<br/>");
				out.println("3���յ����ֻ������а�����¼�����Լ����룻<br/>");
				out.println("<br/>�ɶ�ε�����͡�");
			} 
		} else {			
			System.out.println("AppinfoServlet : type is null");
		}
		out.close();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// test(req, resp);
		myPost(req, resp);
	}

	private void myPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String function = req.getParameter("function");
		System.out.println("UserServlet:function=" + function);
		if (function == null) {
			return;
		}
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		String jsonStr = "failed";
		// ��½�ɹ���־
		boolean ok = false;
		if (function.equals("Login")) {
			// ��ÿͻ����ύ�û�������
			String userNo = req.getParameter("userNo");
			String password = req.getParameter("password");

			System.out.println("UserServlet : userNo=" + userNo
					+ ", password=" + password);
			
			String appId = req.getParameter("appId");
			userDaoImpl.addUserConfig("2002", appId, userNo);
			
			ModelUser user = userDaoImpl.getUser(userNo);
			if (user == null) {
				jsonStr = "nouser";
			} else {
				if (user.getPasswd() == null) {
					if (password==null || password.equals("")) {
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
			if (ok == true) {
				jsonStr = GsonUtil.getGson().toJson(user);
			}
		} else if (function.equals("modifyUserInfo")) {
			String userId = req.getParameter("userId");
			String infoType = req.getParameter("infoType");
			String content = req.getParameter("content");
			System.out.println("UserServlet: userId=" + userId + ", infoType=" + infoType +", content="+content);
			
			if (userDaoImpl.update(userId, infoType, content) == true) {
				jsonStr = "success";
			}
		} else if (function.equals("existApp")) {
			String appId = req.getParameter("appId");
			userDaoImpl.addUserConfig("1002", appId, "");
			jsonStr = "success";
		} else if (function.equals("registerUser")) {
			String userInfo = req.getParameter("userInfo");
			ModelUser user = GsonUtil.getGson().fromJson(userInfo,
					new TypeToken<ModelUser>() {}.getType());
			
			System.out.println("UserServlet: userId=" + user.getNo());
			
			String appId = req.getParameter("appId");
			userDaoImpl.addUserConfig("2001", appId, user.getNo());
			
			if (userDaoImpl.register(user) == true) {
				jsonStr = "success";
			}
			if (user.getLevel()==2){
				// ������̼��û��������̼ұ������һ����¼
				ModelProvider t = new ModelProvider();
				t.setProviderId(user.getNo());
				t.setTitle("���޷����");
				ProviderDaoImpl pdao = new ProviderDaoImpl();
				pdao.add(t);
			}
		} else if (function.equals("unregisterUser")) {
			String userId = req.getParameter("userId");
			System.out.println("UserServlet: userId=" + userId);

			jsonStr = "success";
		} else if (function.equals("getUserNewNo")) {
			String type = req.getParameter("type");
			String newId = userDaoImpl.getNewId(Integer.parseInt(type));
			if (newId != null) {
				jsonStr = newId;
			}
		} else if (function.equals("getUserInfoByPhone")) {
			String phone = req.getParameter("phone");
			ModelUser user = userDaoImpl.getUserByPhone(phone);
			if (user != null) {
				jsonStr = "�� "+phone+" �󶨵��û���¼�˺�Ϊ��"+user.getNo()+"�����룺"+user.getPasswd()+"��ллʹ�á�������";
			} else {
				jsonStr = "nouser";
			}
		} else if (function.equals("chooseCar")) {
			// ����appid��ѯ��ѡ����ĳ���
			String appId = req.getParameter("appId");
			String carId = req.getParameter("carId");
			
			userDaoImpl.addUserConfig("3001", appId, carId);

			if (userDaoImpl.addChoosedCarByAppId(carId, appId) == true) {
				jsonStr = "success";
			}
		} else if (function.equals("queryChoosedCarByUser")) {
			// �����û�id��ѯ��ѡ����ĳ���
			String userId = req.getParameter("userId");

			List<ModelCar> listCar = userDaoImpl.getChoosedCarByUser(userId);//
			if (listCar != null) {
				jsonStr = GsonUtil.getGson().toJson(listCar);
			}
		}

		System.out.println("UserServlet: function=" + function+", jsonStr="+jsonStr);
		ServletUtil.doResponse(resp, jsonStr);
	}

	private void test(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("Hello, User Servlet !!!!");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
