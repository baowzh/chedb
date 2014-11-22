<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>保养首页</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">
		<a href="index.jsp" style="color: white; text-decoration: none;">&lt;检测</a>
	</div>
	<h3 class="h32">汽车中立检测</h3>
	<div class="card1">
		<div class="cardTitle tact">选择车型</div>
		<div class=" m10">
			<c:forEach items="${cars}" var="modelcar" varStatus="status">
				<div class="cardLayer">
					<c:if test="${modelcar.selected==1}">
						<input type="checkbox" checked="checked" />
					</c:if>
					<c:if test="${modelcar.selected==0}">
						<input type="checkbox" />
					</c:if>
					<c:out value="${modelcar.name}" />
					<br />
				</div>
			</c:forEach>
			<div class="cardLayer">
				<table width="100%" class="layoutTbl">
					<tr>
						<td>&nbsp;<span class="greyText">选择车型，查看可做的项目</span></td>
						<td width="60"><button class="greenBtn">选车型</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="card1">
		<div class="cardTitle tact">可选项目</div>
		<c:if test="${empty servicelist}">
			<div class="cardLayer">
				<div class="m10">
					<span class="greyText">该车型没有可选项目</span>
				</div>
			</div>
		</c:if>
		<c:forEach items="${servicelist}" var="modelService"
			varStatus="status">
			<div class="cardLayer">
				<div class="m10">
					<a
						href="serviceItemDetail.do?serviceId=<c:out
									value="${modelService.serviceId}" />&serviceClassId=03"
						style="color: #000; text-decoration: none;"> <c:out
							value="${modelService.name}" />
						<table style="width: 100%;">
							<tr>
								<td><span class="greyText">奉还发动机机油</span></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><span class="greyText">奉还发动机机油</span></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td>$<c:out value="${modelService.price}" />元
								</td>
							</tr>
						</table>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="listBox">
		<p>以中立机构的身份来为车辆做检查，并提供一份客观的检测报告，不在忽悠车主过度消费，真正让你了解什么需要换，什么不需要换</p>
		<p>目前我们正在研制详细检测项目，不久开通服务。</p>
	</div>

	<div class="m10">
		<div class="greyText">在车大邦平台上网，如出现任何问题，车大邦负责协调解决。</div>
	</div>
</body>
</html>