package com.chedb.controller;

import javax.servlet.http.HttpServletRequest;

public class ProviderController {
	
	public String summary(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ŀ����Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����Ŀ��Ҫ˵����<br/>");
		buffer.append("2����ʹ�õ�ԭ����˵����<br/>");
		buffer.append("3���������ʱ��˵����<br/>");
		return buffer.toString();
	}
	public String phone(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ŀ����Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����Ŀ��Ҫ˵����<br/>");
		buffer.append("2����ʹ�õ�ԭ����˵����<br/>");
		buffer.append("3���������ʱ��˵����<br/>");
		return buffer.toString();
	}
	public String remark(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ŀ����Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����Ŀ��Ҫ˵����<br/>");
		buffer.append("2����ʹ�õ�ԭ����˵����<br/>");
		buffer.append("3���������ʱ��˵����<br/>");
		return buffer.toString();
	}
	public String addrNotDw(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ŀ����Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����Ŀ��Ҫ˵����<br/>");
		buffer.append("2����ʹ�õ�ԭ����˵����<br/>");
		buffer.append("3���������ʱ��˵����<br/>");
		return buffer.toString();
	}
	public String getImage(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ŀ����Ϣ��������д�������ݣ�<br/>");
		buffer.append("1����Ŀ��Ҫ˵����<br/>");
		buffer.append("2����ʹ�õ�ԭ����˵����<br/>");
		buffer.append("3���������ʱ��˵����<br/>");
		return buffer.toString();
	}
	queryProviderList
	queryProviderListBySearch
	queryProviderImg
	unloadProviderImg
	editProviderImg
	queryProviderByUserId
	queryProviderById
	appendProviderBrowse
	modifyProviderInfo
	
	
}
