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
<title>������ҳ</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">
		<a href="index.jsp" style="color: white; text-decoration: none;">&lt;���</a>
	</div>
	<h3 class="h32">�����������</h3>
	<div class="card1">
		<div class="cardTitle tact">ѡ����</div>
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
						<td>&nbsp;<span class="greyText">ѡ���ͣ��鿴��������Ŀ</span></td>
						<td width="60"><button class="greenBtn">ѡ����</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="card1">
		<div class="cardTitle tact">��ѡ��Ŀ</div>
		<c:if test="${empty servicelist}">
			<div class="cardLayer">
				<div class="m10">
					<span class="greyText">�ó���û�п�ѡ��Ŀ</span>
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
								<td><span class="greyText">�����������</span></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><span class="greyText">�����������</span></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td>$<c:out value="${modelService.price}" />Ԫ
								</td>
							</tr>
						</table>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="listBox">
		<p>�����������������Ϊ��������飬���ṩһ�ݿ͹۵ļ�ⱨ�棬���ں��Ƴ����������ѣ����������˽�ʲô��Ҫ����ʲô����Ҫ��</p>
		<p>Ŀǰ��������������ϸ�����Ŀ�����ÿ�ͨ����</p>
	</div>

	<div class="m10">
		<div class="greyText">�ڳ����ƽ̨������������κ����⣬������Э�������</div>
	</div>
</body>
</html>