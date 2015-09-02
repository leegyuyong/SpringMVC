<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="top" />
<body>
	<!-- wrapper -->
	<div id="wrapper">
		<div id="top" class="sub">
			<tiles:insertAttribute name="sub_gnb" />
			<%--<tiles:insertAttribute name="sub_nav" /> --%>
		</div>
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
	<!-- //wrapper -->
</body>
</html>