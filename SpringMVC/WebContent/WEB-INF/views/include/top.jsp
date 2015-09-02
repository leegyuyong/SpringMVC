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
			<dt>바로가기 메뉴</dt>
			<dd>브라우저에 따라 바로가기 단축키 사용법은 다음과 같습니다.  인터넷 익스플로러, 구글 크롬, 애플 사파리의 경우 Alt + AccessKey 번호이며, 파이어폭스는 Alt + Shift + Accesskey 번호, 오페라는 Shift + Esc + Accesskey 번호로 단축키를 이용할 수 있습니다. </dd>
			<dd class="quick">
				<a accesskey="1" href="#accessibility1">서비스 메뉴 바로가기</a>
				<a accesskey="2" href="#accessibility2">본문내용 바로가기</a>
				<a accesskey="3" href="#accessibility3">홈페이지 정보 바로가기</a>
			</dd>
		</dl>
	</div>


	<!-- layer msg -->
	<div class="top-line"></div>

	<!-- <div id="top-notice" class="msg" style="display:none">
		<div class="msg-content">
			<p class="title">오프라인 교육 취소 및 변경 시 안내</p>
			<p class="note">
				교육 수강신청 시, 자동승인이 되므로 직접 수강 취소가 불가 합니다.<br>
				만일 수강 변경을 위해 수강 취소를 하실 분들 께서는 아래의 연락처로 연락 주시기 바랍니다.
			</p>
			<div class="close">
				<p class="todayclose"><a href="javascript:closeWin();">오늘 하루 열지 않기</a></p>
				<p><a href="javascript:closeAlert()"><img src="/templet/T001/images/common/icon_close.png" alt="알림을 닫습니다."></a></p>
			</div>
		</div> -->
	</div>

