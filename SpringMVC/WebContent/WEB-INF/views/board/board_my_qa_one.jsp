<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!-- 본문시작 -->

	<!-- section -->
	<div class="section">
	<form name="form1" id="form1" method="post">
		<input type="hidden" name="tabseq" value="${tabseq}"/>
		<input type="hidden" name="seq"    value="${seq}" />
		<input type="hidden" name="pageNo" value="${pageNo}" />
		<h1>${dtInfoSession.menuName}</h1>
		<div class="head-tip mt10">
			<p>학습진행, 과정관련, 기타 문의등의 내역을 확인할 수 있습니다.</p>
		</div>
		<div class="board-view">
			<div class="view-title">
				<p>${map.TITLE }</p>
			</div>
			<div class="write-about">
				<p class="nobg"><b>작성자</b> : ${map.NAME }(${map.USERID })</p>
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

			<c:if test="${map.ATITLE ne null && map.ATITLE != ''}">
			<!-- 답변 -->
			<div class="answer">
				<div class="re-title"><u class="answer-icon"><!-- answer icon --></u></div>
				<div class="re-memo">${map.ACONTENT}
				</div>
			</div>
			</c:if>


		</div>

		<div class="mt30 al">
			<button type="button" class="normal" onclick="selectList()">목록으로</button>
		</div>

	</form>
	</div>

	<!-- 본문 끝 -->

<script>

	function selectList() {
		var frm = document.getElementById("form1");
		frm.action = "/board/my_qa_list";
		frm.submit();
	}
</script>
