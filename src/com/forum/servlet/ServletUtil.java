package com.forum.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum.model.ModelUser;
import com.forum.util.GsonUtil;

public class ServletUtil {
	

	static public void doResponse(HttpServletResponse resp, String jsonStr)
			throws ServletException, IOException {		
		try {			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(jsonStr);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
