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
<title>��������</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">&lt;����</div>
	<h3 class="h32">
		<c:out value="${modelService.name}" />
	</h3>
	<div class="m10">
		<table class="layoutTbl" width="100%">
			<tr>
				<td width="545" class="redText">��<c:out
						value="${modelService.price}" />Ԫ
				</td>
				<td width="196" class="tart">����<c:out
						value="${modelService.businessNum}" />
				</td>
			</tr>
			<tr>
				<td class="greyText">��<c:out
						value="${modelService.returnJifen}" />����
				</td>
				<td class="greyText">���ֵָ���Ҫ<c:out
						value="${modelService.priceJifen}" />����
				</td>
			</tr>
		</table>
	</div>
	<c:if test="${serviceClassId!=01}">
		<div class="card1">
			<div class="cardTitle tact">��Ʒ��ԭ��</div>
			<div class="m10">
				���������͡����ƷǷ�����������<br /> �����������������Դ�ר�û���������
			</div>
			<div class="brandItem greyText">���вɹ�ͳһ���ͣ�Ʒ�ʸ߼۸��</div>
		</div>
	</c:if>
	<div class="card1">
		<div class="cardTitle tact">�Ͻ��Ĺ���</div>
		<div class="m10">
			<c:out value="${modelService.workTitle}" />
		</div>
		<div class="brandItem greyText">���вɹ�ͳһ���ͣ�Ʒ�ʸ߼۸��</div>
	</div>
	<div class="card1">
		<div class="cardTitle tact">��֤�̼�</div>

		<c:forEach items="${providers}" var="modelProvider" varStatus="status">
			<div class="cardLayer">
				<div class=" m10">
					<table class="layoutTbl" width="100%">
						<tr>
							<td width="120" class="tact"><a
								href="provider/providerdetail.do?providerId=<c:out
										value="${modelProvider.providerId}" />"><img
									src="img/th.jpg" width="120" height="120" /> </a></td>
							<td rowspan="2" valign="top"><a
								href="provider/providerdetail.do"
								style="text-decoration: none; color: #999;"> <c:out
										value="${modelProvider.title}" />
							</a><br /> <span class="greyText"> <c:out
										value="${modelProvider.addr}" /> <br />
							</span><span class="redText">4.6��/ 247�˴��</span></td>
						</tr>
						<tr>
							<td class="tact"><input type="checkbox" /></td>
						</tr>
					</table>
				</div>
			</div>
		</c:forEach>
		<div class="cardLayer tart ">
			<div class="m10">
				<span class="greyText  ">2/��2��
					<button class="greenBtn">��һҳ</button>
				</span>
			</div>
		</div>
	</div>
	<div class="m10">
		<table class="layoutTbl" width="100%">
			<tr>
				<td width="50%" class="tart"><div class=" bigBtn">
						<h3>���ֵָ�</h3>
						<span style="font-size: small">����ǰ����ʱȡ��</span>
					</div></td>
				<td width="50%"><div class=" bigBtn">
						<h3>�ֽ�֧��</h3>
						<span style="font-size: small">�ֽ�֧��ͬ���л���</span>
					</div></td>
			</tr>
		</table>
	</div>
	<div class="card1">
		<div class="cardTitle tact">��������</div>
		<c:forEach items="${apprsises}" var="modelUserAppraise"
			varStatus="status">
			<div class="cardLayer">
				<div class="m10">
					<div class=" greenText">
						<c:out value="${modelUserAppraise.userName}" />
					</div>
					<div>
						<c:out value="${modelUserAppraise.appraise}" />
					</div>
					<div class=" tart greyText">
						<c:out value="${modelUserAppraise.dateStr}" />
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="cardLayer">
			<table class="layoutTbl" width="100%">
				<tr>
					<td width="50%"><button class="greenBtn">˵����</button></td>
					<td width="50%" class=" tart"><span class="greyText">5/��7ҳ</span>
						<button class="greenBtn">��һҳ</button></td>

				</tr>
			</table>
		</div>
	</div>

</body>
</html>
