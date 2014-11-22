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
<script src="js/carmaintains.js" type="text/javascript"></script>
<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
</head>

<body>
	<div id="carmaintain">
		<div class="navBar">
			<a href="index.jsp" style="color: white; text-decoration: none;">&lt;保养</a>
		</div>
		<h3 class="h32">汽车常规保养</h3>
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

				<div class="cardLayer" style="padding-top: 10px;">
					<table width="100%" class="layoutTbl">
						<tr>
							<td>&nbsp;<span class="greyText">选择车型，查看可做的项目</span></td>
							<td width="60"><button class="greenBtn"
									onclick="javascript:switchdiv(1);">选车型</button></td>
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
									value="${modelService.serviceId}" />&serviceClassId=02"
							style="color: #000; text-decoration: none;"> <c:out
								value="${modelService.name}" />
							<table style="width: 100%;">
								<tr>
									<td><span class="greyText"><c:out
												value="${modelService.item1Txt}" /></span></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td><span class="greyText"><c:out
												value="${modelService.item2Txt}" /></span></td>
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
			<p>以独有的模式提供比目前更高品质的汽车保养服务</p>
			<p>1、集中采购统一配送油品和配件，省去中间环节，做到了品质高价格低;</p>
			<p>2、严谨的工序和监督反馈机制保证商家的每步工作都执行到位;</p>
			<p>3、根据官方保养手册推荐当前需要做的保养项目，车主省心又放心;</p>
		</div>

		<div class="m10">
			<div class="greyText">在车大邦平台上网，如出现任何问题，车大邦负责协调解决。</div>
		</div>
	</div>
	<!-- 以下为车型列表 -->
	<div id="carbrands" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择品牌</a>
		</div>

		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车型列表 -->
	<!-- 以下为车系列表  -->
	<div id="carseries" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择品牌</a>
		</div>

		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem">本田</div>
		<div class="brandItem">奥迪</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车系列表 -->
	<!-- 以下为车系列表  -->
	<div id="carseries" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择品牌</a>
		</div>

		<div class="brandItem">100</div>
		<div class="brandItem">200</div>
		<div class="brandItem">A4</div>
		<div class="brandItem">A6</div>
		<div class="brandItem">A6L</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车系列表 -->
	<!-- 以下为车型列表  -->
	<div id="carversions" style="display: none;">
		<div class="navBar">
			<a href="javascript:switchdiv(2);"
				style="color: white; text-decoration: none;">&lt;选择品牌</a>
		</div>
		<div class="brandItem">奥迪100 1.8L AT</div>
		<div class="brandItem">奥迪100 2.0L DCT</div>
		<div class="brandItem" style="background: none"></div>
		<div class="m10">没有您要的品牌？点击这里告诉我们</div>
	</div>
	<!-- 以上为车型列表 -->
</body>
</html>