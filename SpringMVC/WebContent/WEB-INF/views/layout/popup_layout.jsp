<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:insertAttribute name="popup_top" />

<body>
	<!-- wrapper -->
	<div id="wrapper" class="main_wrapper">
		<tiles:insertAttribute name="contents" />
	</div>
	<!-- //wrapper -->

	<tiles:insertAttribute name="analytics" />
</body>
</html>