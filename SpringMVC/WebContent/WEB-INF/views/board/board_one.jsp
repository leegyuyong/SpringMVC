<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!-- 본문시작 -->

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
				<p class="nobg"><b>작성자</b> :
				<c:if test="${bds.ISBLIND == 'N'}">
				${map.NAME }(${map.USERID })
				</c:if>
				<c:if test="${bds.ISBLIND == 'Y'}">
				***
				</c:if>
				</p>
				<p><b>등록일</b> : ${map.INDATE }</p>
				<p><b>조회</b> : ${map.CNT }</p>
			</div>
			<div class="memo">${map.CONTENT_HTML}</div>

			<c:if test="${map.UPFILECNT > 0}">
			<!-- 첨부파일 존재시 -->
			<div class="addition">
				<div class="add-name"><span>첨부파일 : </span></div>
				<div class="add-list">
					<c:forEach items="${fileList}" var="item" varStatus="stat">
					<a href="/common/download_file?savefile=${item.SAVEFILE}&realfile=${item.REALFILE}" title="다운로드" >${item.REALFILE}</a>
					</c:forEach>
					<div class="cl"><!-- clear fix --></div>
				</div>
			</div>
			</c:if>

		</div>

		<div class="mt30 al">
			<c:if test="${bds.ISREPLY == 'Y'}">
			<button type="button" class="active" onclick="createReplyForm()">답변하기</button>
			</c:if>
			<c:if test="${userSession.userid == map.USERID}">
			<button type="button" class="active" onclick="modifySeq()">수정하기</button>
			</c:if>
			<c:if test="${userSession.userid == map.USERID}">
			<button type="button" class="active" onclick="removeSeq('${map.DELYN}')">삭제하기</button>
			</c:if>
			<button type="button" class="normal" onclick="selectList()">목록으로</button>
		</div>

		<c:if test="${bds.ISCOMMENT == 'Y'}">
		<%--
		<!-- 덧글 -->
		<div class="replay-write">
			<dl>
				<dt><textarea></textarea></dt>
				<dd><button type="button">덧글입력</button></dd>
			</dl>
		</div>
		<div class="reply-length">
			<p><b>3</b>개의 덧글이 있습니다.</p>
		</div>
		<div class="reply">
			<dl>
				<dt>
					<p class="name">KOANMI-모니터링</p>
					<p class="date">2015.07.03 09:52</p>
				</dt>
				<dd>
					많은이용바랍니다. <br> 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다.
					<span class="ml5">
						<a href="#" class="reply" title="답글쓰기"><!-- reply + --></a>
						<a href="#"  class="modify" title="덧글수정"><!-- edit --></a>
						<a href="#"  class="delete" title="덧글삭제"><!-- delete --></a>
					</span>
				</dd>
			</dl>

			<dl>
				<dt>
					<p class="name">KOANMI-모니터링</p>
					<p class="date">2015.07.03 09:52</p>
				</dt>
				<dd>
					많은이용바랍니다. <br> 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다.
					<span class="ml5">
						<a href="#"  class="reply" title="답글쓰기"><!-- reply + --></a>
						<a href="#"  class="modify" title="덧글수정"><!-- edit --></a>
						<a href="#"  class="delete" title="덧글삭제"><!-- delete --></a>
					</span>
				</dd>
			</dl>

			<dl>
				<dt>
					<p class="name">KOANMI-모니터링</p>
					<p class="date">2015.07.03 09:52</p>
				</dt>
				<dd>
					많은이용바랍니다. <br> 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다. 많은 이용바랍니다.
					<span class="ml5">
						<a href="#"  class="reply" title="답글쓰기"><!-- reply + --></a>
						<a href="#"  class="modify" title="덧글수정"><!-- edit --></a>
						<a href="#"  class="delete" title="덧글삭제"><!-- delete --></a>
					</span>
				</dd>
			</dl>
		</div>
		 --%>
		</c:if>
	</form>
	</div>

	<!-- 본문 끝 -->




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
			if (confirm("정말로 삭제하시겠습니까?")) {
				var frm = document.getElementById("form1");
				frm.action = "/board/remove";
				frm.submit();
			}
		} else {
			alert("답변글이 있어 삭제할 수 없습니다.");
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
