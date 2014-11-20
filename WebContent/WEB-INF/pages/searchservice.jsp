<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
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
		<!-- 		<div class="navBar">&lt;找商家</div> -->
		<div class="navBar">
			<table class="layoutTbl" width="100%">
				<tr>
					<td class="tart">&lt;找商家</td>
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
				style="color: white; text-decoration: none;">价格不限,换备胎,送油,送水,拖车/运输.困境..</a>
		</div>
		<div id="map" style="height: 600px; padding: 0 0.5em;"></div>
	</div>
	<!-- 查询条件 界面-->
	<div id="searcheitems" style="display: none;">
		<div class="navBar">
			<table width="100%">
				<tr>
					<td width="80%"><div style="width: 90px;">&lt;选择项目</div></td>
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
				<td width="60"><input type="text" class="plain" /></td>
				<td width="10">-</td>
				<td width="60"><input type="text" class="plain" /></td>
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
		<div class="bluLbl">服务/救援</div>
		<div class="iconWrapper">
			<span class="textIcon">搭电打火</span> <span class="textIcon">换备胎</span>
			<span class="textIcon">送油送水</span> <span class="textIcon">拖车/运输</span>
			<span class="textIcon">代驾</span> <span class="textIcon">引路</span> <span
				class="textIcon">困境救援</span> <span class="textIcon">租车</span> <span
				class="textIcon">租车</span> <span class="textIcon">现场故障排除</span> <span
				class="textIcon">加油</span> <span class="textIcon">加气</span> <span
				class="textIcon">停车场</span> <span class="textIcon">定损</span>
		</div>
		<div class="bluLbl">轮胎/轮毂</div>
		<div class="iconWrapper">
			<span class="textIcon">补胎</span> <span class="textIcon">调换轮胎</span> <span
				class="textIcon">换轮胎</span> <span class="textIcon">换轮毂</span> <span
				class="textIcon">四轮定位</span> <span class="textIcon">动平衡</span> <span
				class="textIcon">充氮气</span>
		</div>
		<div class="bluLbl">美容/装饰</div>
		<div class="iconWrapper">
			<span class="textIcon">普通洗车</span> <span class="textIcon">精品洗车</span>
			<span class="textIcon">电脑洗车</span> <span class="textIcon">贴膜</span> <span
				class="textIcon">臭氧消毒</span> <span class="textIcon">打蜡/抛光/封釉</span>
			<span class="textIcon">包真皮</span> <span class="textIcon">内饰清洗</span>
			<span class="textIcon">装饰装具</span>
		</div>
		<div class="bluLbl">维修/钣金</div>
		<div class="iconWrapper">
			<span class="textIcon">发动机维修</span> <span class="textIcon">变速箱维修</span>
			<span class="textIcon">钣金喷漆</span> <span class="textIcon">划痕修复</span>
			<span class="textIcon">车灯</span> <span class="textIcon">修换水箱</span> <span
				class="textIcon">底盘</span> <span class="textIconActive">减震系统</span>
			<span class="textIcon">转向系统</span> <span class="textIcon">电视/电路</span>
			<span class="textIcon">玻璃升降器</span> <span class="textIcon">雨刮</span>
			<span class="textIcon">后视镜</span> <span class="textIcon">保险杠</span> <span
				class="textIcon">玻璃</span> <span class="textIcon">门锁/拉手</span> <span
				class="textIcon">翻新</span>
		</div>
		<div class="bluLbl">保养/检测</div>
		<div class="iconWrapper">
			<span class="textIcon">小保养</span> <span class="textIcon">大保养</span> <span
				class="textIcon">火花赛</span> <span class="textIcon">刹车片</span> <span
				class="textIcon">电脑解码</span> <span class="textIcon">沿路清洗</span> <span
				class="textIcon">刹车系统</span>
		</div>
		<div class="bluLbl">空调/电器</div>
		<div class="iconWrapper">
			<span class="textIcon">空调电路</span> <span class="textIcon">空调清洗</span>
			<span class="textIcon">空调维修</span> <span class="textIcon">加氟</span>
		</div>
		<div class="bluLbl">改装/加配</div>
		<div class="iconWrapper">
			<span class="textIcon">天然气改装</span> <span class="textIcon">发动机</span>
			<span class="textIcon">轮毂</span> <span class="textIcon">倒车雷达</span> <span
				class="textIcon">导航</span> <span class="textIcon">地胶</span> <span
				class="textIcon">胎压监测器</span> <span class="textIcon">音响</span> <span
				class="textIcon">行车记录仪</span> <span class="textIcon">大灯</span> <span
				class="textIcon">尾翼</span> <span class="textIcon">行李架</span> <span
				class="textIcon">天窗</span> <span class="textIcon">脚踏板</span> <span
				class="textIcon">座椅</span> <span class="textIcon">底盘装甲</span>
		</div>
		<div class="bluLbl">车务代办</div>
		<div class="iconWrapper">
			<span class="textIcon">车险</span> <span class="textIcon">事故理赔</span> <span
				class="textIcon">违章处理</span> <span class="textIcon">车辆驾照年检</span> <span
				class="textIcon">上户/过户</span> <span class="textIcon">补办行驶证/号牌</span>

		</div>
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
</script>
</html>
