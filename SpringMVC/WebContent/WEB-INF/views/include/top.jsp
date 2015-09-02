<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>JEJOUP</title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Language" content="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<script language="javascript" type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script language="javascript" type="text/javascript" src="/js/jquery-ui-1.10.2.custom.js"></script>


<c:out value="${map.CSS}" escapeXml="false"/>




</head>

<!-- hide event  -->
	<div class="accessibility">
		<dl>
			<dt>�ٷΰ��� �޴�</dt>
			<dd>�������� ���� �ٷΰ��� ����Ű ������ ������ �����ϴ�.  ���ͳ� �ͽ��÷η�, ���� ũ��, ���� ���ĸ��� ��� Alt + AccessKey ��ȣ�̸�, ���̾������� Alt + Shift + Accesskey ��ȣ, ������ Shift + Esc + Accesskey ��ȣ�� ����Ű�� �̿��� �� �ֽ��ϴ�. </dd>
			<dd class="quick">
				<a accesskey="1" href="#accessibility1">���� �޴� �ٷΰ���</a>
				<a accesskey="2" href="#accessibility2">�������� �ٷΰ���</a>
				<a accesskey="3" href="#accessibility3">Ȩ������ ���� �ٷΰ���</a>
			</dd>
		</dl>
	</div>


	<!-- layer msg -->
	<div class="top-line"></div>

	<!-- <div id="top-notice" class="msg" style="display:none">
		<div class="msg-content">
			<p class="title">�������� ���� ��� �� ���� �� �ȳ�</p>
			<p class="note">
				���� ������û ��, �ڵ������� �ǹǷ� ���� ���� ��Ұ� �Ұ� �մϴ�.<br>
				���� ���� ������ ���� ���� ��Ҹ� �Ͻ� �е� ������ �Ʒ��� ����ó�� ���� �ֽñ� �ٶ��ϴ�.
			</p>
			<div class="close">
				<p class="todayclose"><a href="javascript:closeWin();">���� �Ϸ� ���� �ʱ�</a></p>
				<p><a href="javascript:closeAlert()"><img src="/templet/T001/images/common/icon_close.png" alt="�˸��� �ݽ��ϴ�."></a></p>
			</div>
		</div> -->
	</div>

