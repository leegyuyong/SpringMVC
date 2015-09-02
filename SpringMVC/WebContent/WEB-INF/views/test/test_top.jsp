<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="kr.co.cylearn.model.ResultHashMap"%>
<%@ page import="org.springframework.web.util.WebUtils"%>
<%@ page import="kr.co.cylearn.model.UserSession"%>
<%
	ResultHashMap resultModel = (ResultHashMap)request.getAttribute("resultModel");

	// get script
	String[] scripts = null;

	if(resultModel != null)
		scripts = (String[])resultModel.get("scripts");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="euc-kr" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="http://cylearn.co.kr/css/common.css" />
<!-- <link rel="stylesheet" type="text/css" href="http://cylearn.co.kr/common/css/page.css" /> -->
<link rel="stylesheet" type="text/css" href="http://cylearn.co.kr/common/css/subPage.css" />
<link rel="stylesheet" type="text/css" href="/css/test_common.css" />
<link rel="stylesheet" type="text/css" href="/css/test_sub.css" />
<script type="text/javascript" src="/js/cylearn_lib.js"></script>
<script type="text/javascript" src="/js/jquery-1.8.1.min.js"></script>
<%
	if(scripts != null){
		for(String script : scripts){

				if("jquery.mobile-1.4.2.min".equals(script)){
%>
<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<%
				}else{
%>
<script type="text/javascript" src="/js/<%=script%>.js"></script>
<%
				}
		}
	}
%>

<script>
</script>
<script type="text/javascript" src="http://wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">
//네이버 애널리틱스 2014-10-24 jdh
if(!wcs_add) var wcs_add = {};
wcs_add["wa"] = "1cec9079049788";
wcs_do();
</script>
sfdasdfafsdf
</head>