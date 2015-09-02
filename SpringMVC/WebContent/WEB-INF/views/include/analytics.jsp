<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<script>
	var userid='${userSession.userid}';
	var username='${userSession.name}';
	var grcodenm='${grcodeSession.grcodenm}';
	var grcode='${grcodeSession.grcode}';


	var googleUserId = "auto";

	if(userid==''){
		googleUserId = "auto";
	}else{
		googleUserId = userid;
	}

	var googleGrCodeNm = "";

	if(grcodenm==''){
	}else{
		googleGrCodeNm = grcodenm;
	}

	var googleGrCode = "";
	if(grcode==''){
	}else{
		googleGrCode = grcode;
	}


	//구글 애널리틱스 2014-10-24 jdh
	var dimensionValue1 = googleUserId;
	var dimensionValue2 = googleGrCodeNm+"("+googleGrCode+")";

	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

	ga('create', 'UA-56020325-1', { 'userId': googleUserId });
	ga('require', 'displayfeatures');
	ga('set', 'dimension1', dimensionValue1);
	ga('set', 'dimension2', dimensionValue2);
	ga('send', 'pageview');
	</script>
	<script type="text/javascript" src="http://wcs.naver.net/wcslog.js"></script>
	<script type="text/javascript">
	//네이버 애널리틱스 2014-10-24 jdh
	if(!wcs_add) var wcs_add = {};
	wcs_add["wa"] = "1cec9079049788";
	wcs_do();

</script>