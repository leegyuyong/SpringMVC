<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:insertAttribute name="top" />

<body>
	<tiles:insertAttribute name="sub_header" />

	<!-- wrapper -->
	<div id="wrapper" class="main_wrapper">
		<tiles:insertAttribute name="contents" />
	</div>
	<!-- //wrapper -->


	<tiles:insertAttribute name="footer" />
	<tiles:insertAttribute name="analytics" />
</body>
</html>