function goMenu(dtCode,menuUpper,menuMiddle,menuLower,tab){
	if(menuUpper==undefined){
		menuUpper="1";
	}
	if(menuMiddle==undefined){
		menuMiddle="1";
	}
	if(menuLower==undefined){
		menuLower="1";
	}
	if(tab==undefined){
		tab="1";
	}
	location.href="/common/page/"+dtCode+"?menuUpper="+menuUpper+"&menuMiddle="+menuMiddle+"&menuLower="+menuLower+"&tab="+tab;
}


//공지사항,오프라인,온라인 과정 정보.. 상세페이지로 바로 이동시
function goMenuDetail(dtCode,detailURL,pararm){
	location.href="/common/page/"+dtCode+"?detailYN=Y&detailURL="+detailURL+"&pararm="+pararm;
}


/**
 * SSL을 위해 HTTPS 용 도메인을 가져온다.
 * @returns {String}
 */
function https_host(){
	var host;
	 host = location.href;

	ori_host = host.split("//");
	url = ori_host[1] ;

	domain = url.substr(0,url.indexOf("/"));
	if(domain.indexOf("cylearn.co.kr") > -1){ // cylearn 도메인이 아닌 경우 SSL 처리를 하지 않는다.
		return "https://"+domain;
	} else {
		return "http://"+domain;
	}
}


/**
 * 팝업 띄우기
 */
function onpop(urls,w,h){
	//popup setting application
	sw = (screen.width);
	sh = (screen.height);
	size = "width="+w+",height="+h+",scrollbars=yes";
	var pop_view = window.open(urls,'',size);
	pop_view.focus();
}


/**
 * 설명 : 관심과정 등록
 * @since : 2015. 8. 21.
 * @author : jdh
 * @param subj
 */
function addSubjConcern(subj) {

	if (!confirm("관심과정으로 등록하시겠습니까?")){
		return;
	}

	$.ajax({
		type : "post",
		data : "subj="+subj,
		dataType : "json",
		url : "/subjconcern/create_json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			console.log(data);
			if(data.result == 'OK'){
				alert("관심과정으로 등록되었습니다.");
				return;
			}else{
				var msg = data.msg;
				alert(msg);
				return;
			}
		},
		error : function(request, status, error) {
			//alert("code : " + request.status + "\r\nmessage : " + request.responseText + "\r\nerror : " + error);
			alert("오류가 발생했습니다. \n관리자에게 문의하세요");
			return;
		}
	});
}