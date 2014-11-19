<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>保养首页</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">&lt;检测</div>
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
		<c:forEach items="${servicelist}" var="modelService"
			varStatus="status">
			<div class="cardLayer">
				<div class="m10">
					<c:out value="${modelService.name}" />
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
				</div>
			</div>
		</c:forEach>
		<!-- 		<div class="cardLayer"> -->
		<!-- 			<div class="m10"> -->
		<!-- 				<span class="greyText">该车型没有可选项目</span> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
	</div>


	<div class="listBox">
		<p>以猢的模式提供比目前更高品质的汽车保养服务</p>
		<p>1、集中采购统一配送洞口和配件</p>
		<p>2、</p>
	</div>

	<div class="m10">
		<div class="greyText">在车大邦平台上网，如出现任何问题，车大邦负责协调解决。</div>
	</div>
</body>
</html>