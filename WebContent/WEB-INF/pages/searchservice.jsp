<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="../css/main.css" type="text/css" rel="stylesheet" />
<script src="../js/searchservice.js" type="text/javascript"></script>
<script src="../js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
<title>首页</title>
</head>
<body>

	<!-- 找商家主页 -->
	<div id="searchindex" style="display: block;">
		<div class="navBar">
			<table class="layoutTbl" width="100%">
				<tr>
					<td class="tart"><a href="../index.jsp"
						style="color: white; text-decoration: none;">&lt;找商家</a></td>
					<td width="60"><input class="plain" style="background: #390" /></td>
					<td width="20"><img src="../img/search.png" width="20"
						height="20" /></td>
					<td width="20"><img src="../img/switch.png" width="20"
						height="20" /></td>
					<td><button class="greenBtn2">距离</button></td>
				</tr>
			</table>
		</div>

		<div class="searchBar" style="font-size: 14px;">
			<a href="javascript:showsearchitems(1);"
				style="color: white; text-decoration: none;" id="selectedItems">
				价格不限,项目不限(点击设置)</a>
		</div>
		<div id="map" style="height: 600px; padding: 0 0.5em;"></div>
	</div>
	<!-- 查询条件 界面-->
	<div id="searcheitems" style="display: none;">
		<div class="navBar">
			<table width="100%">
				<tr>
					<td width="80%"><div style="width: 90px;">
							<a href="javascript:showsearchitems(3);"
								style="text-decoration: none; color: white;">&lt;选择项目</a>
						</div></td>
					<td style="width: 20%; text-align: right;"><a
						href="javascript:showsearchitems(2);"
						style="text-decoration: none; color: white;"><div
								style="background: #6c0; border-radius: 2px; text-align: center;">确定</div></a></td>
				</tr>
			</table>
		</div>
		<table width="100%">
			<tr>
				<td class="tart">价格:</td>
				<td width="60"><input type="text" class="plain" id="lowerprice" /></td>
				<td width="10">-</td>
				<td width="60"><input type="text" class="plain" id="upperprice" /></td>
				<td><button class="greenBtn">清零</button></td>
			</tr>
			<tr>
				<td height="5"></td>
				<td class="androidInputBtnBorder"></td>
				<td></td>
				<td class="androidInputBtnBorder"></td>
				<td></td>

			</tr>
		</table>
		<div>&nbsp;</div>
		<c:forEach items="${items}" var="modelSysItem" varStatus="status">
			<div class="bluLbl"
				id="<c:out
									value="${modelSysItem.labelId}" />">
				<c:out value="${modelSysItem.title}" />
			</div>
			<div class="iconWrapper">
				<c:forEach items="${modelSysItem.childitems}" var="modelSysItemi"
					varStatus="status">
					<a
						href="javascript:switchstatus('<c:out
								value="${modelSysItemi.labelId}" />');"><span
						class="textIcon"
						id="<c:out
								value="${modelSysItemi.labelId}" />"><c:out
								value="${modelSysItemi.title}" /></span></a>
					<input type="hidden"
						id="item<c:out
								value="${modelSysItemi.labelId}" />"
						value="<c:out
								value="${modelSysItemi.labelId}" />"></input>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
</body>
<script language="javascript" type="text/javascript">
	function initialize() {
		var mp = new BMap.Map('map');
		mp.centerAndZoom(new BMap.Point(121.491, 31.233), 11);
	}

	// function loadScript() {  
	//   var script = document.createElement("script");  
	//   script.src = "http://api.map.baidu.com/api?v=1.5&ak=您的密钥&callback=initialize";//此为v1.5版本的引用方式  
	//   // http://api.map.baidu.com/api?v=1.5&ak=您的密钥&callback=initialize"; //此为v1.4版本及以前版本的引用方式  
	//   document.body.appendChild(script);  
	// }  
	window.onload = initialize;
	var serviceItems = <c:out
	value="${serviceItems}" escapeXml="false"  />
</script>
</html>
