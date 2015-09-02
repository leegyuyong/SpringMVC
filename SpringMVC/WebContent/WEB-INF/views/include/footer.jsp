<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a name="accessibility3"><!-- ���ټ� : Ȩ������ ���� ��ġ --></a>

<script language="javascript" type="text/javascript" src="/js/common.js"></script>
<script>
	//�޴� �κ� �� �������� START
	$(function() {
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "/layout/menu_json?mappingType=MENU&menuMiddle=0&menuLower=0",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				if (data == null) return;
				$('#menu_upper').empty();
				$('#menu_middle').empty();

				var list = data.list;
				for ( var i = 0; i < list.length; i++) {
				//for ( var i = 0; i < 2; i++) {
					//��з� �޴� �ߺз���  0�̸� ��޴�..
					if(list[i].MENU_MIDDLE==0 && list[i].MENU_LOWER==0){
						//ù ��° �޴���.. name='accessibility1' <- �ٿ��ش�.. ���ټ� ����..
						if(list[i].MENU_UPPER==1){
							$('#menu_upper').append('<dt><a href="javascript:goMenu(\''+list[i].DT_CODE+'\',\''+list[i].MENU_UPPER+'\');" name="accessibility1">'+list[i].MENU_NAME+'</a></dt>');
						}else{
							$('#menu_upper').append('<dt><a href="javascript:goMenu(\''+list[i].DT_CODE+'\',\''+list[i].MENU_UPPER+'\');">'+list[i].MENU_NAME+'</a></dt>');
						}
						goMiddleSet(list[i].MENU_UPPER);
					}
				}
			}
		});

		//login Menu Start
		var userid='${userSession.userid}';
		var username='${userSession.name}';
		if(userid!=''){
			//alert('���̵��� �ִ�.');
			$('#cylearn_login').css("display", "none");
			$('#cylearn_logout').css("display", "block");

			//���̵�,�̸��� �־��ֱ�
			$('#loginId').text(userid);
			$('#loginName').text(username);

		}else{
			//alert('���̵��� ����.');
			$('#cylearn_login').css("display", "block");
			$('#cylearn_logout').css("display", "none");
		}
		//login Menu  end

	});

	function goMiddleSet(menuUpper){
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "/layout/menu_json?mappingType=MENU&menuUpper="+menuUpper,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				if (data == null) return;
				var list = data.list;

				var htmlRow = '';

				for ( var i = 0; i < list.length; i++) {
					//ó���϶�..
					if(i==0){
						htmlRow += '<dl class="menu'+menuUpper+'">';
					}

					//��з� �޴� �ߺз���  0�̸� ��޴�..
					if(list[i].MENU_MIDDLE!=0 && list[i].MENU_LOWER==0){
						//�ߺз� �޴�
						htmlRow += '<dt><a href="javascript:goMenu(\''+list[i].DT_CODE+'\',\''+list[i].MENU_UPPER+'\',\''+list[i].MENU_MIDDLE+'\');" >'+list[i].MENU_NAME+'</a></dt>';
					}

					//�������϶�..
					if((i+1)==(list.length)){
						htmlRow += '</dl>';
					}
				}
				//console.log(htmlRow);
				$('#menu_middle').append(htmlRow);
			},
			error : function(request, status, error) {
				//alert("code : " + request.status + "\r\nmessage : " + request.responseText + "\r\nerror : " + error);
				alert("������ �߻��߽��ϴ�. \n�����ڿ��� �����ϼ���");
				return;
			}
		});
	}
	//�޴� �κ� �� �������� END


	//��й�ȣ ���� �˾�
<c:if test="${userSession.deferChgpwdDate!='' && userSession.deferChgpwdDate!=null}">
	onpop('/member/pw_change_popup',400,400);
</c:if>


</script>



<c:out value="${map.FOOTER}" escapeXml="false"/>