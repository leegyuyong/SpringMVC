<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

%>
<script language="JavaScript" type="text/JavaScript">
//�˻�â���� enter key������
function press_enter(e) {
	if (e.keyCode =='13'){  selectList();  }
}

//����Ʈ
function selectList() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_list";
	frm.pageno.value = "1";
	frm.submit();
}

//�󼼺���
function boardOne(seq) {
	var frm = document.getElementById("form1");
	frm.seq.value = seq;
	frm.action = "/test/board_one";
	frm.submit();
}

//��ȸ
function go(index) {
	var frm = document.getElementById("form1");
	frm.p_pageno.value = index;
	frm.action = "/servlet/controller.homepage.TotalBoardServlet";
	frm.p_process.value = "selectList";
	frm.submit();
}

//������ �̵�
function goPage(pageNum) {
	var frm = document.getElementById("form1");
	frm.p_pageno.value = pageNum;
	frm.action = "/servlet/controller.homepage.TotalBoardServlet";
	frm.p_process.value = "selectList";
	frm.submit();
}


// ���
function boardForm() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_create_form";
	frm.submit();
}


</script>
<div class="contentBody">
	<div class="topTitle clearfix">
		<h3>�׽�Ʈ �Խ���</h3>
		<span>�Խ��� �ȳ�</span> <span class="nowPage"> Home&nbsp;>&nbsp;������&nbsp;>&nbsp;�������� </span>
	</div>
	<div class="boardSch">
		<!-- s: search -->
		<form class="g_search" name= "form1" id="form1" method="post">
			<input type="hidden" name="tabseq" value="${tabseq}" />
			<input type="hidden" name="seq" value="" />
			<input type="hidden" name="pageno" value="1" />
			<select name="search">
				<option value="title" ${search == 'title' ? 'selected':''}>����</option>
				<option value="content" ${search == 'content' ? 'selected':''}>����</option>
				<option value="name" ${search == 'name' ? 'selected':''}>�ۼ���</option>
			</select>
			<div class="txtBox">
				<input type="text" name="searchtext" class="white" value="${searchtext}" onkeypress="press_enter(event)" />
				<span><a href="#" onclick="whenSelection()"><img src="http://cylearn.co.kr/images/2014/btn_boardSch.gif" alt="�˻�" /></a></span>
			</div>
			<div class="meChk">
				<input type="checkbox" name="meWrite" />
				<span>���� �� �� ����</span>
			</div>
		</form>
		<!-- e: search -->
	</div>
	<!--//�Խñ� �˻� -->
	<!-- �Խ��� -->
	<div class="boardList">
		<table summary="���� ���õ� ���������� Ȯ���Ѵ�">
			<caption>��������</caption>
			<colgroup>
			<col width="6%"/>
			<col />
			<col width="8%"/>
			<col width="9%"/>
			<col width="10%"/>
			<col width="8%"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">����</th>
					<th scope="col">÷��</th>
					<th scope="col">�ۼ���</th>
					<th scope="col">�����</th>
					<th class="thEnd" scope="col">��ȸ</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(list) > 0}">
				<c:forEach items="${list}" var="item" varStatus="stat">
				<tr>
					<td>${stat.index+1}</td>
					<td class="tdSubject">
						<img src="http://cylearn.co.kr/images/user/study/board/space.gif" width="${item.LEVELS * 15 - 30}" height ="2" border="0" />
						<c:if test="${item.LEVELS > 1}">
							<span class="tdSubject_re">&nbsp;</span>
						</c:if>
						<a href="#none" onclick="javascript:boardOne('${item.SEQ}');">${item.TITLE}</a>
						<c:if test="${item.COMCNT > 0}">
						<span class="list_comm_num">${item.COMCNT}</span>
						</c:if>
						<c:if test="${item.COMCNT == 0}">
						</c:if>
						<c:if test="${item.DAY_CNT < 7}">
						<img class="board_new_icon" src="http://cylearn.co.kr/images/2014/ico_board_new.png" alt="�� �Խù�" />
						</c:if>
					</td>
					<td>
						<c:if test="${item.UPFILECNT > 0}">
						<img src="http://cylearn.co.kr/images/2014/ico_disk.png" alt="÷������" />
						</c:if>
						<c:if test="${item.UPFILECNT == 0}">
						-
						</c:if>
					</td>
					<td>${item.NAME}</td>
					<td>${item.INDATE}</td>
					<td>${item.CNT}</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(list)==0}">
				<tr>
					<td class="tdSubject" colspan="6">��ȸ�� ������ �����ϴ�.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div><!-- //boardList -->
	<div class="ar"><a href="#none" onclick="boardForm()"><img src="http://cylearn.co.kr/images/2014/btn_write.gif" alt="�۾���" /></a></div>
	<!-- ������ �׺���̼� -->
	<div class="paging"><a href="#none" ><img src="http://cylearn.co.kr/images/2014/btn_1st.gif" alt="ó��" /></a>&nbsp;<a href="#none" ><img src="http://cylearn.co.kr/images/2014/btn_pre.gif" alt="����" /></a><span>&nbsp;<a href="#none" ><em>1</em></a>&nbsp;<a href="#none" onclick="javascript:goPage('2')">2</a>&nbsp;<a href="#none" onclick="javascript:goPage('3')">3</a>&nbsp;<a href="#none" onclick="javascript:goPage('4')">4</a>&nbsp;<a href="#none" onclick="javascript:goPage('5')">5</a>&nbsp;<a href="#none" onclick="javascript:goPage('6')">6</a>&nbsp;<a href="#none" onclick="javascript:goPage('7')">7</a>&nbsp;<a href="#none" onclick="javascript:goPage('8')">8</a>&nbsp;<a href="#none" onclick="javascript:goPage('9')">9</a>&nbsp;<a href="#none" onclick="javascript:goPage('10')">10</a></span>&nbsp;<a href="#none" onclick="javascript:goPage('11')"><img src="http://cylearn.co.kr/images/2014/btn_next.gif" alt="����" /></a>&nbsp;<a href="#none" onclick="javascript:goPage('111') "><img src="http://cylearn.co.kr/images/2014/btn_last.gif" alt="������" /></a></div>
	<!-- //������ �׺���̼� -->
</div><!--//contentBody-->
