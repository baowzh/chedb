<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>洗车主页</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="navBar">&lt;洗车</div>
	<div class="card1">
		<div class="cardTitle tact">可选项目</div>
		<c:forEach items="${servicelist}" var="modelService"
			varStatus="status">
			<div class="cardLayer">
				<table class="layoutTbl" width="100%">

					<tr>
						<td><a href="javascript:alert('1');"
							style="color: #000; text-decoration: none;"> <c:out
									value="${modelService.name}" />
						</a></td>
						<td width="80" class="tart">></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td class="redText tart">￥<c:out
								value="${modelService.price}" /></td>
					</tr>
				</table>
			</div>

		</c:forEach>
	</div>

	<div class="listBox">
		提供比目前更高品质的洗车服务；<br /> 1.集中配送车大邦认证的洗车液，不伤车漆 2.更严谨的工序保证每步工作都等待到位
	</div>
	<div class="m10 greyText">在车大顾平台上购买服务，如出现任何问题，车大邦负责协调解决。</div>
</body>
</html>

