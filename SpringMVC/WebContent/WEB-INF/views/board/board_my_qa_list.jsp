<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<!-- �������� -->

	<!-- section -->
	<div class="section">
	<form name="form1" id="form1" method="post" onSubmit="return false;">
		<input type="hidden" name="pageNo" value="${pageNo}" />
		<h1>${dtInfoSession.menuName}</h1>
		<div class="head-tip mt10">
			<p>�н�����, ��������, ��Ÿ ���ǵ��� ������ Ȯ���� �� �ֽ��ϴ�.</p>
		</div>


		<div class="board">
			<div class="option">
				<div class="com-search">
					<select name="search">
						<option value="title" ${search == 'title' ? 'selected':''}>����</option>
						<option value="content" ${search == 'content' ? 'selected':''}>����</option>
						<option value="name" ${search == 'name' ? 'selected':''}>�ۼ���</option>
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
					<col width="60">
					<col width="60">
					<col width="100">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>����</th>
						<th>����</th>
						<th>����</th>
						<th>÷��</th>
						<th>�����</th>
						<th>��ȸ</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(list) > 0}">
					<c:forEach items="${list}" var="item" varStatus="stat">
					<tr>
						<td>${item.RNUM}</td>
						<td class="left"><a href="#none" onclick="selectOne('${item.TABSEQ}','${item.SEQ}');">${item.TITLE}</a></td>
						<td>
						<c:choose>
							<c:when test="${item.ATITLE ne null && item.ATITLE != ''}"><span class="red">�Ϸ�</span></c:when>
							<c:otherwise>ó����</c:otherwise>
						</c:choose>
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
						<td>${item.INDATE}</td>
						<td>${item.CNT}</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(list)==0}">
					<!-- ����� ���� ��� -->
					<tr>
						<td colspan="6">��ϵ� ���ǰ� �����ϴ�.</td>
					</tr>
				</c:if>
				</tbody>
			</table>

			<!-- paging -->
			${pagingStr}

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
		frm.action = "/board/my_qa_list";
		frm.pageNo.value = "1";
		frm.submit();
	}

	//�󼼺���
	function selectOne(tabseq, seq) {
		var frm = document.getElementById("form1");
		frm.action = "/board/my_qa_one/"+ tabseq +"/"+ seq;
		frm.submit();
	}

	//������ �̵�
	function goPage(pageNo) {
		var frm = document.getElementById("form1");
		frm.action = "/board/my_qa_list";
		frm.pageNo.value = pageNo;
		frm.submit();
	}

</script>