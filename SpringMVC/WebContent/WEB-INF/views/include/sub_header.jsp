<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- header -->
	<c:out value="${map.SUB_HEADER}" escapeXml="false"/>

	<a name="accessibility2"><!-- 접근성 : 본문 위치 --></a>
	<!-- location -->

	<div class="location">
		<u class="home-icon"><!-- home icon --></u><span onclick="javascript:location.href='/';">HOME</span>
		<!-- 0번 메인 메뉴만 뺀다. 2015 08 25-->
		<!--<c:if test="${dtInfoSession.upperMenuName !=null}"></c:if>-->
		<c:if test="${dtInfoSession.upperMenu !='0'}">
			<u class="route-icon"><!-- route icon --></u><span> <a href="javascript:goMenu('${dtInfoSession.upperDtCode}','${dtInfoSession.upperMenu}');">${dtInfoSession.upperMenuName}</a></span>
		</c:if>
		<u class="route-icon"><!-- route icon --></u><b>${dtInfoSession.middleMenuName}</b>
	</div>