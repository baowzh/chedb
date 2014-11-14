package com.chedb.controller;

import javax.servlet.http.HttpServletRequest;

public class ProviderController {
	
	public String summary(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目简单信息，可以填写以下内容：<br/>");
		buffer.append("1、项目简要说明；<br/>");
		buffer.append("2、所使用的原材料说明；<br/>");
		buffer.append("3、大概所需时间说明；<br/>");
		return buffer.toString();
	}
	public String phone(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目简单信息，可以填写以下内容：<br/>");
		buffer.append("1、项目简要说明；<br/>");
		buffer.append("2、所使用的原材料说明；<br/>");
		buffer.append("3、大概所需时间说明；<br/>");
		return buffer.toString();
	}
	public String remark(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目简单信息，可以填写以下内容：<br/>");
		buffer.append("1、项目简要说明；<br/>");
		buffer.append("2、所使用的原材料说明；<br/>");
		buffer.append("3、大概所需时间说明；<br/>");
		return buffer.toString();
	}
	public String addrNotDw(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目简单信息，可以填写以下内容：<br/>");
		buffer.append("1、项目简要说明；<br/>");
		buffer.append("2、所使用的原材料说明；<br/>");
		buffer.append("3、大概所需时间说明；<br/>");
		return buffer.toString();
	}
	public String getImage(HttpServletRequest req) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("项目简单信息，可以填写以下内容：<br/>");
		buffer.append("1、项目简要说明；<br/>");
		buffer.append("2、所使用的原材料说明；<br/>");
		buffer.append("3、大概所需时间说明；<br/>");
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
