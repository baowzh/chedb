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
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>ϴ����ҳ</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">&lt;ϴ��</div>
	<div class="card1">
		<div class="cardTitle tact">��ѡ��Ŀ</div>
		<c:forEach items="${servicelist}" var="modelService"
			varStatus="status">
			<div class="cardLayer">
				<table class="layoutTbl" width="100%">

					<tr>
						<td><a href="serviceItemDetail.do"
							style="color: #000; text-decoration: none;"> <c:out
									value="${modelService.name}" />
						</a></td>
						<td width="80" class="tart">></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td class="redText tart">��<c:out
								value="${modelService.price}" /></td>
					</tr>
				</table>
			</div>

		</c:forEach>
	</div>

	<div class="listBox">
		�ṩ��Ŀǰ����Ʒ�ʵ�ϴ������<br /> 1.�������ͳ������֤��ϴ��Һ�����˳��� 2.���Ͻ��Ĺ���֤ÿ���������ȴ���λ
	</div>
	<div class="m10 greyText">�ڳ����ƽ̨�Ϲ������������κ����⣬������Э�������</div>
</body>
</html>

