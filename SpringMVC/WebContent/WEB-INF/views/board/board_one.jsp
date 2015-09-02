<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!-- �������� -->

	<!-- section -->
	<div class="section">
	<form name="form1" id="form1" method="post">
		<input type="hidden" name="tabseq"     value="${tabseq}"/>
		<input type="hidden" name="seq"        value="${seq}" />
		<input type="hidden" name="search"     value="${search}" />
		<input type="hidden" name="searchtext" value="${searchtext}" />
		<input type="hidden" name="pageNo"     value="${pageNo}" />

		<h1>${bds.SDESC}</h1>
		<div class="head-tip mt10">
			<p>${bds.LDESC}</p>
		</div>
		<div class="board-view">
			<div class="view-title">
				<p>${map.TITLE }</p>
			</div>
			<div class="write-about">
				<p class="nobg"><b>�ۼ���</b> :
				<c:if test="${bds.ISBLIND == 'N'}">
				${map.NAME }(${map.USERID })
				</c:if>
				<c:if test="${bds.ISBLIND == 'Y'}">
				***
				</c:if>
				</p>
				<p><b>�����</b> : ${map.INDATE }</p>
				<p><b>��ȸ</b> : ${map.CNT }</p>
			</div>
			<div class="memo">${map.CONTENT_HTML}</div>

			<c:if test="${map.UPFILECNT > 0}">
			<!-- ÷������ ����� -->
			<div class="addition">
				<div class="add-name"><span>÷������ : </span></div>
				<div class="add-list">
					<c:forEach items="${fileList}" var="item" varStatus="stat">
					<a href="/common/download_file?savefile=${item.SAVEFILE}&realfile=${item.REALFILE}" title="�ٿ�ε�" >${item.REALFILE}</a>
					</c:forEach>
					<div class="cl"><!-- clear fix --></div>
				</div>
			</div>
			</c:if>

		</div>

		<div class="mt30 al">
			<c:if test="${bds.ISREPLY == 'Y'}">
			<button type="button" class="active" onclick="createReplyForm()">�亯�ϱ�</button>
			</c:if>
			<c:if test="${userSession.userid == map.USERID}">
			<button type="button" class="active" onclick="modifySeq()">�����ϱ�</button>
			</c:if>
			<c:if test="${userSession.userid == map.USERID}">
			<button type="button" class="active" onclick="removeSeq('${map.DELYN}')">�����ϱ�</button>
			</c:if>
			<button type="button" class="normal" onclick="selectList()">�������</button>
		</div>

		<c:if test="${bds.ISCOMMENT == 'Y'}">
		<%--
		<!-- ���� -->
		<div class="replay-write">
			<dl>
				<dt><textarea></textarea></dt>
				<dd><button type="button">�����Է�</button></dd>
			</dl>
		</div>
		<div class="reply-length">
			<p><b>3</b>���� ������ �ֽ��ϴ�.</p>
		</div>
		<div class="reply">
			<dl>
				<dt>
					<p class="name">KOANMI-����͸�</p>
					<p class="date">2015.07.03 09:52</p>
				</dt>
				<dd>
					�����̿�ٶ��ϴ�. <br> ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�.
					<span class="ml5">
						<a href="#" class="reply" title="��۾���"><!-- reply + --></a>
						<a href="#"  class="modify" title="���ۼ���"><!-- edit --></a>
						<a href="#"  class="delete" title="���ۻ���"><!-- delete --></a>
					</span>
				</dd>
			</dl>

			<dl>
				<dt>
					<p class="name">KOANMI-����͸�</p>
					<p class="date">2015.07.03 09:52</p>
				</dt>
				<dd>
					�����̿�ٶ��ϴ�. <br> ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�.
					<span class="ml5">
						<a href="#"  class="reply" title="��۾���"><!-- reply + --></a>
						<a href="#"  class="modify" title="���ۼ���"><!-- edit --></a>
						<a href="#"  class="delete" title="���ۻ���"><!-- delete --></a>
					</span>
				</dd>
			</dl>

			<dl>
				<dt>
					<p class="name">KOANMI-����͸�</p>
					<p class="date">2015.07.03 09:52</p>
				</dt>
				<dd>
					�����̿�ٶ��ϴ�. <br> ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�. ���� �̿�ٶ��ϴ�.
					<span class="ml5">
						<a href="#"  class="reply" title="��۾���"><!-- reply + --></a>
						<a href="#"  class="modify" title="���ۼ���"><!-- edit --></a>
						<a href="#"  class="delete" title="���ۻ���"><!-- delete --></a>
					</span>
				</dd>
			</dl>
		</div>
		 --%>
		</c:if>
	</form>
	</div>

	<!-- ���� �� -->




<script>
	function toggleFileDownBox(){
		$("#fileDownBox").toggle();
	}

	function selectList() {
		var frm = document.getElementById("form1");
		frm.action = "/board/list/"+ frm.tabseq.value;
		frm.submit();
	}

	function removeSeq(delyn) {
		if (delyn == "Y") {
			if (confirm("������ �����Ͻðڽ��ϱ�?")) {
				var frm = document.getElementById("form1");
				frm.action = "/board/remove";
				frm.submit();
			}
		} else {
			alert("�亯���� �־� ������ �� �����ϴ�.");
			return;
		}
	}

	function modifySeq() {
		var frm = document.getElementById("form1");
		frm.action = "/board/modify_form";
		frm.submit();
	}

	function createReplyForm() {
		var frm = document.getElementById("form1");
		frm.action = "/board/create_reply_form";
		frm.submit();
	}

</script>
