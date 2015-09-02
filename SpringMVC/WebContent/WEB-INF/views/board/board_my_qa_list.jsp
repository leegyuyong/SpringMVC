<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<!-- 본문시작 -->

	<!-- section -->
	<div class="section">
	<form name="form1" id="form1" method="post" onSubmit="return false;">
		<input type="hidden" name="pageNo" value="${pageNo}" />
		<h1>${dtInfoSession.menuName}</h1>
		<div class="head-tip mt10">
			<p>학습진행, 과정관련, 기타 문의등의 내역을 확인할 수 있습니다.</p>
		</div>


		<div class="board">
			<div class="option">
				<div class="com-search">
					<select name="search">
						<option value="title" ${search == 'title' ? 'selected':''}>제목</option>
						<option value="content" ${search == 'content' ? 'selected':''}>내용</option>
						<option value="name" ${search == 'name' ? 'selected':''}>작성자</option>
					</select>
					<input type="text" title="검색어를 입력해주세요." name="searchtext" value="${searchtext}" onKeyDown="pressEnter(event)">
					<button type="button" title="검색" onclick="selectList()"></button>
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
						<th>순차</th>
						<th>제목</th>
						<th>상태</th>
						<th>첨부</th>
						<th>등록일</th>
						<th>조회</th>
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
							<c:when test="${item.ATITLE ne null && item.ATITLE != ''}"><span class="red">완료</span></c:when>
							<c:otherwise>처리중</c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:if test="${item.UPFILECNT > 0}">
							<div class="clip-view">
							<a href="#none" class="clip" title="첨부파일 목록 창"></a>
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
					<!-- 목록이 없을 경우 -->
					<tr>
						<td colspan="6">등록된 문의가 없습니다.</td>
					</tr>
				</c:if>
				</tbody>
			</table>

			<!-- paging -->
			${pagingStr}

		</div>
	</form>
	</div>

	<!-- 본문 끝 -->


<script language="JavaScript" type="text/JavaScript">
	//검색창에서 enter key쳤을때
	function pressEnter(e) {
		if (e.keyCode =='13'){  selectList();  }
	}

	//리스트
	function selectList() {
		var frm = document.getElementById("form1");
		frm.action = "/board/my_qa_list";
		frm.pageNo.value = "1";
		frm.submit();
	}

	//상세보기
	function selectOne(tabseq, seq) {
		var frm = document.getElementById("form1");
		frm.action = "/board/my_qa_one/"+ tabseq +"/"+ seq;
		frm.submit();
	}

	//페이지 이동
	function goPage(pageNo) {
		var frm = document.getElementById("form1");
		frm.action = "/board/my_qa_list";
		frm.pageNo.value = pageNo;
		frm.submit();
	}

</script>