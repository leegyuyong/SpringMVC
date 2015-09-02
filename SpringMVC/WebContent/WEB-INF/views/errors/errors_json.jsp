<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.HashMap"%>
<%
	HashMap errorModel = (HashMap) request.getAttribute("errorModel");
	
	String errorCode = (String) errorModel.get("errorCode");
	String errorMsg = (String) errorModel.get("errorMsg");
%>
<%="[" + errorCode + "]" + errorMsg%>