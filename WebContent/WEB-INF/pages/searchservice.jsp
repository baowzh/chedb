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
<title>��ҳ</title>
</head>
<body>

	<!-- ���̼���ҳ -->
	<div id="searchindex" style="display: block;">
		<!-- 		<div class="navBar">&lt;���̼�</div> -->
		<div class="navBar">
			<table class="layoutTbl" width="100%">
				<tr>
					<td class="tart">&lt;���̼�</td>
					<td width="60"><input class="plain" style="background: #390" /></td>
					<td width="20"><img src="../img/search.png" width="20"
						height="20" /></td>
					<td width="20"><img src="../img/switch.png" width="20"
						height="20" /></td>
					<td><button class="greenBtn2">����</button></td>
				</tr>
			</table>
		</div>

		<div class="searchBar" style="font-size: 14px;">
			<a href="javascript:showsearchitems(1);"
				style="color: white; text-decoration: none;">�۸���,����̥,����,��ˮ,�ϳ�/����.����..</a>
		</div>
		<div id="map" style="height: 600px; padding: 0 0.5em;"></div>
	</div>
	<!-- ��ѯ���� ����-->
	<div id="searcheitems" style="display: none;">
		<div class="navBar">
			<table width="100%">
				<tr>
					<td width="80%"><div style="width: 90px;">&lt;ѡ����Ŀ</div></td>
					<td style="width: 20%; text-align: right;"><a
						href="javascript:showsearchitems(2);"
						style="text-decoration: none; color: white;"><div
								style="background: #6c0; border-radius: 2px; text-align: center;">ȷ��</div></a></td>
				</tr>
			</table>
		</div>
		<table width="100%">
			<tr>
				<td class="tart">�۸�:</td>
				<td width="60"><input type="text" class="plain" /></td>
				<td width="10">-</td>
				<td width="60"><input type="text" class="plain" /></td>
				<td><button class="greenBtn">����</button></td>
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
		<div class="bluLbl">����/��Ԯ</div>
		<div class="iconWrapper">
			<span class="textIcon">�����</span> <span class="textIcon">����̥</span>
			<span class="textIcon">������ˮ</span> <span class="textIcon">�ϳ�/����</span>
			<span class="textIcon">����</span> <span class="textIcon">��·</span> <span
				class="textIcon">������Ԯ</span> <span class="textIcon">�⳵</span> <span
				class="textIcon">�⳵</span> <span class="textIcon">�ֳ������ų�</span> <span
				class="textIcon">����</span> <span class="textIcon">����</span> <span
				class="textIcon">ͣ����</span> <span class="textIcon">����</span>
		</div>
		<div class="bluLbl">��̥/���</div>
		<div class="iconWrapper">
			<span class="textIcon">��̥</span> <span class="textIcon">������̥</span> <span
				class="textIcon">����̥</span> <span class="textIcon">�����</span> <span
				class="textIcon">���ֶ�λ</span> <span class="textIcon">��ƽ��</span> <span
				class="textIcon">�䵪��</span>
		</div>
		<div class="bluLbl">����/װ��</div>
		<div class="iconWrapper">
			<span class="textIcon">��ͨϴ��</span> <span class="textIcon">��Ʒϴ��</span>
			<span class="textIcon">����ϴ��</span> <span class="textIcon">��Ĥ</span> <span
				class="textIcon">��������</span> <span class="textIcon">����/�׹�/����</span>
			<span class="textIcon">����Ƥ</span> <span class="textIcon">������ϴ</span>
			<span class="textIcon">װ��װ��</span>
		</div>
		<div class="bluLbl">ά��/�ӽ�</div>
		<div class="iconWrapper">
			<span class="textIcon">������ά��</span> <span class="textIcon">������ά��</span>
			<span class="textIcon">�ӽ�����</span> <span class="textIcon">�����޸�</span>
			<span class="textIcon">����</span> <span class="textIcon">�޻�ˮ��</span> <span
				class="textIcon">����</span> <span class="textIconActive">����ϵͳ</span>
			<span class="textIcon">ת��ϵͳ</span> <span class="textIcon">����/��·</span>
			<span class="textIcon">����������</span> <span class="textIcon">���</span>
			<span class="textIcon">���Ӿ�</span> <span class="textIcon">���ո�</span> <span
				class="textIcon">����</span> <span class="textIcon">����/����</span> <span
				class="textIcon">����</span>
		</div>
		<div class="bluLbl">����/���</div>
		<div class="iconWrapper">
			<span class="textIcon">С����</span> <span class="textIcon">����</span> <span
				class="textIcon">����</span> <span class="textIcon">ɲ��Ƭ</span> <span
				class="textIcon">���Խ���</span> <span class="textIcon">��·��ϴ</span> <span
				class="textIcon">ɲ��ϵͳ</span>
		</div>
		<div class="bluLbl">�յ�/����</div>
		<div class="iconWrapper">
			<span class="textIcon">�յ���·</span> <span class="textIcon">�յ���ϴ</span>
			<span class="textIcon">�յ�ά��</span> <span class="textIcon">�ӷ�</span>
		</div>
		<div class="bluLbl">��װ/����</div>
		<div class="iconWrapper">
			<span class="textIcon">��Ȼ����װ</span> <span class="textIcon">������</span>
			<span class="textIcon">���</span> <span class="textIcon">�����״�</span> <span
				class="textIcon">����</span> <span class="textIcon">�ؽ�</span> <span
				class="textIcon">̥ѹ�����</span> <span class="textIcon">����</span> <span
				class="textIcon">�г���¼��</span> <span class="textIcon">���</span> <span
				class="textIcon">β��</span> <span class="textIcon">�����</span> <span
				class="textIcon">�촰</span> <span class="textIcon">��̤��</span> <span
				class="textIcon">����</span> <span class="textIcon">����װ��</span>
		</div>
		<div class="bluLbl">�������</div>
		<div class="iconWrapper">
			<span class="textIcon">����</span> <span class="textIcon">�¹�����</span> <span
				class="textIcon">Υ�´���</span> <span class="textIcon">�����������</span> <span
				class="textIcon">�ϻ�/����</span> <span class="textIcon">������ʻ֤/����</span>

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
	//   script.src = "http://api.map.baidu.com/api?v=1.5&ak=������Կ&callback=initialize";//��Ϊv1.5�汾�����÷�ʽ  
	//   // http://api.map.baidu.com/api?v=1.5&ak=������Կ&callback=initialize"; //��Ϊv1.4�汾����ǰ�汾�����÷�ʽ  
	//   document.body.appendChild(script);  
	// }  

	window.onload = initialize;
</script>
</html>
