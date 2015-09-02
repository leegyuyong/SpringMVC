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


//��������,��������,�¶��� ���� ����.. ���������� �ٷ� �̵���
function goMenuDetail(dtCode,detailURL,pararm){
	location.href="/common/page/"+dtCode+"?detailYN=Y&detailURL="+detailURL+"&pararm="+pararm;
}


/**
 * SSL�� ���� HTTPS �� �������� �����´�.
 * @returns {String}
 */
function https_host(){
	var host;
	 host = location.href;

	ori_host = host.split("//");
	url = ori_host[1] ;

	domain = url.substr(0,url.indexOf("/"));
	if(domain.indexOf("cylearn.co.kr") > -1){ // cylearn �������� �ƴ� ��� SSL ó���� ���� �ʴ´�.
		return "https://"+domain;
	} else {
		return "http://"+domain;
	}
}


/**
 * �˾� ����
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
 * ���� : ���ɰ��� ���
 * @since : 2015. 8. 21.
 * @author : jdh
 * @param subj
 */
function addSubjConcern(subj) {

	if (!confirm("���ɰ������� ����Ͻðڽ��ϱ�?")){
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
				alert("���ɰ������� ��ϵǾ����ϴ�.");
				return;
			}else{
				var msg = data.msg;
				alert(msg);
				return;
			}
		},
		error : function(request, status, error) {
			//alert("code : " + request.status + "\r\nmessage : " + request.responseText + "\r\nerror : " + error);
			alert("������ �߻��߽��ϴ�. \n�����ڿ��� �����ϼ���");
			return;
		}
	});
}