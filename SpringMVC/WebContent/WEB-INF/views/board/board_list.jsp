<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<!-- �������� -->

	<!-- section -->
	<div class="section">
	<form name="form1" id="form1" method="post" onSubmit="return false;">
		<input type="hidden" name="tabseq" value="${tabseq}" />
		<input type="hidden" name="pageNo" value="${pageUtil.pageNo}" />
		<h1>${bds.SDESC}</h1>
		<div class="head-tip mt10">
			<p>${bds.LDESC}</p>
		</div>
		<div class="board">
			<div class="option">
				<div class="com-search">
					<select name="search">
						<option value="title" ${search == 'title' ? 'selected':''}>����</option>
						<option value="content" ${search == 'content' ? 'selected':''}>����</option>
						<c:if test="${bds.ISBLIND == 'N'}">
						<option value="name" ${search == 'name' ? 'selected':''}>�ۼ���</option>
						</c:if>
					</select>
					<input type="text" title="�˻�� �Է����ּ���." name="searchtext" value="${searchtext}" onKeyDown="pressEnter(event)">
					<button type="button" title="�˻�" onclick="selectList()"></button>
					<div class="cl"><!-- clear fix --></div>
				</div>
			</div>
			<table>
				<colgroup>
					<col width="50">
					<col width="*">
					<col width="100">
					<col width="100">
					<col width="100">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>����</th>
						<th>����</th>
						<th>÷��</th>
						<th>�ۼ���</th>
						<th>�����</th>
						<th>��ȸ</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="item" varStatus="stat">
					<tr>
						<td>${item.RNUM}</td>
						<td class="left">
						<c:if test="${item.LEVELS > 1}">
							<u class="answer"><!-- answer --></u>Re :
						</c:if>
							<a href="#none" onclick="selectOne('${item.SEQ}');">${item.TITLE}</a>
						<c:if test="${bds.ISCOMMENT == 'Y'}">
							<c:if test="${item.COMCNT > 0}">
								<span class="blue">(${item.COMCNT})</span>
							</c:if>
						</c:if>
						<c:if test="${item.DAY_CNT < 7}">
							<u class="new-icon"><!-- new icon --></u>
						</c:if>
						</td>
						<td>
						<c:if test="${item.UPFILECNT > 0}">
							<div class="clip-view">
							<a href="#none" class="clip" title="÷������ ��� â"></a>
							</div>
						</c:if>
						<c:if test="${item.UPFILECNT == 0}">
							-
						</c:if>
						</td>
						<td>
						<c:if test="${bds.ISBLIND == 'Y'}">
							***
						</c:if>
						<c:if test="${bds.ISBLIND == 'N'}">
							${item.NAME}
						</c:if>
						</td>
						<td>${item.INDATE}</td>
						<td>${item.CNT}</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(list)==0}">
					<tr>
						<td colspan="6">��ȸ�� ������ �����ϴ�.</td>
					</tr>
				</c:if>
				</tbody>
			</table>

			<!-- paging -->
			${pagingStr}

			<c:if test="${bds.ISUSER_REGIST == 'Y'}">
			<div class="com-write">
				<button type="button" onclick="createForm()">�۾���</button>
			</div>
			</c:if>
		</div>
	</form>
	</div>
	<!-- ���� �� -->




<script language="JavaScript" type="text/JavaScript">
	//�˻�â���� enter key������
	function pressEnter(e) {
		if (e.keyCode =='13'){  selectList();  }
	}

	//����Ʈ
	function selectList() {
		var frm = document.getElementById("form1");
		frm.action = "/board/list/"+ frm.tabseq.value;
		frm.pageNo.value = "1";
		frm.submit();
	}

	//�󼼺���
	function selectOne(seq) {
		var frm = document.getElementById("form1");
		frm.action = "/board/one/"+ frm.tabseq.value +"/"+ seq;
		frm.submit();
	}

	//������ �̵�
	function goPage(pageNo) {
		var frm = document.getElementById("form1");
		frm.action = "/board/list/"+ frm.tabseq.value;
		frm.pageNo.value = pageNo;
		frm.submit();
	}


	// ���
	function createForm() {
		var frm = document.getElementById("form1");
		frm.action = "/board/create_form";
		frm.submit();
	}


</script>