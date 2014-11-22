<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商家介绍页</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">
		<table class="layoutTbl" width="100%">
			<tr>
				<td class="">&lt;商家：<c:out value="${modelProvider.providerId}" />
				</td>
				<td width="20"><img src="../img/contextMenu.png" width="20"
					height="20" /></td>
			</tr>
		</table>
	</div>
	<h3 class="h31">
		<c:out value="${modelProvider.title}" />
		<!-- 	全诚汽车修理厂 -->
	</h3>
	<div>
		<!-- 	吉实颉在要要，快修，保养 -->
		<c:out value="${modelProvider.summary}" />
	</div>
	<div>
		<table class="layoutTbl" width="100%">
			<tr>
				<td class="">
					<!-- 				内蒙古自治区 呼和浩特 赛罕区 --> <c:out value="${modelProvider.addr}" />
				</td>
				<td width="80"><button class="greenBtn">地图导航</button></td>
			</tr>
			<tr>
				<td class="">
					<!-- 				电话：0471-1230000 --> <c:out value="${modelProvider.phone}" />
				</td>
				<td width="80"><button class="greenBtn">电话咨询</button></td>
			</tr>
		</table>
	</div>
	<div class="listBox" style="margin-top: 10px; line-height: 200%">
		综合打分：<span class="redText"><c:out
				value="${modelProvider.score}" /> <!-- 		未有人打分或评价 --> </span><br /> 商家人气：<span
			class=" redText"><c:out value="${modelProvider.business}" />次成效/<c:out
				value="${modelProvider.browse}" />次浏览</span>
	</div>
	<div class="m10">
		<img src="../img/th.jpg" width="300" height="256" />
	</div>
	<hr class="seperator" />
	<h3 class="h31">服务项目</h3>
	<c:forEach items="${items}" var="modelProviderItem" varStatus="status">
		<div class="listBox">
			<span class="textIcon"> <c:out
					value="${modelProviderItem.title}" /> <!-- 		补胎 -->
			</span> 服务项目
			<table class="layoutTbl" width="100%">
				<tr>
					<td class=" greenText">成交0次</td>
					<td width="80" class="greenText">还未定价</td>
				</tr>
			</table>
		</div>
	</c:forEach>
	<!--  
	<div class="listBox">
		<span class="textIcon">补胎</span>服务项目
		<table class="layoutTbl" width="100%">
			<tr>
				<td class=" greenText">成交0次</td>
				<td width="80" class="greenText">还未定价</td>
			</tr>
		</table>
	</div>
	<div class="listBox">
		<span class="textIcon">补胎</span>服务项目
		<table class="layoutTbl" width="100%">
			<tr>
				<td class=" greenText">成交0次</td>
				<td width="80" class="greenText">还未定价</td>
			</tr>
		</table>
	</div>
	<div class="listBox">
		<span class="textIcon">补胎</span>服务项目
		<table class="layoutTbl" width="100%">
			<tr>
				<td class=" greenText">成交0次</td>
				<td width="80" class="greenText">还未定价</td>
			</tr>
		</table>
	</div>
	-->
</body>
</html>
