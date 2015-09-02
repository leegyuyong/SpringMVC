<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

%>
<script language="JavaScript" type="text/JavaScript">
//검색창에서 enter key쳤을때
function press_enter(e) {
	if (e.keyCode =='13'){  selectList();  }
}

//리스트
function selectList() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_list";
	frm.pageno.value = "1";
	frm.submit();
}

//상세보기
function boardOne(seq) {
	var frm = document.getElementById("form1");
	frm.seq.value = seq;
	frm.action = "/test/board_one";
	frm.submit();
}

//조회
function go(index) {
	var frm = document.getElementById("form1");
	frm.p_pageno.value = index;
	frm.action = "/servlet/controller.homepage.TotalBoardServlet";
	frm.p_process.value = "selectList";
	frm.submit();
}

//페이지 이동
function goPage(pageNum) {
	var frm = document.getElementById("form1");
	frm.p_pageno.value = pageNum;
	frm.action = "/servlet/controller.homepage.TotalBoardServlet";
	frm.p_process.value = "selectList";
	frm.submit();
}


// 등록
function boardForm() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_create_form";
	frm.submit();
}


</script>
<div class="contentBody">
	<div class="topTitle clearfix">
		<h3>테스트 게시판</h3>
		<span>게시판 안내</span> <span class="nowPage"> Home&nbsp;>&nbsp;고객센터&nbsp;>&nbsp;공지사항 </span>
	</div>
	<div class="boardSch">
		<!-- s: search -->
		<form class="g_search" name= "form1" id="form1" method="post">
			<input type="hidden" name="tabseq" value="${tabseq}" />
			<input type="hidden" name="seq" value="" />
			<input type="hidden" name="pageno" value="1" />
			<select name="search">
				<option value="title" ${search == 'title' ? 'selected':''}>제목</option>
				<option value="content" ${search == 'content' ? 'selected':''}>내용</option>
				<option value="name" ${search == 'name' ? 'selected':''}>작성자</option>
			</select>
			<div class="txtBox">
				<input type="text" name="searchtext" class="white" value="${searchtext}" onkeypress="press_enter(event)" />
				<span><a href="#" onclick="whenSelection()"><img src="http://cylearn.co.kr/images/2014/btn_boardSch.gif" alt="검색" /></a></span>
			</div>
			<div class="meChk">
				<input type="checkbox" name="meWrite" />
				<span>내가 쓴 글 보기</span>
			</div>
		</form>
		<!-- e: search -->
	</div>
	<!--//게시글 검색 -->
	<!-- 게시판 -->
	<div class="boardList">
		<table summary="교육 관련된 공지사항을 확인한다">
			<caption>공지사항</caption>
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
					<th scope="col">제목</th>
					<th scope="col">첨부</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
					<th class="thEnd" scope="col">조회</th>
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
						<img class="board_new_icon" src="http://cylearn.co.kr/images/2014/ico_board_new.png" alt="새 게시물" />
						</c:if>
					</td>
					<td>
						<c:if test="${item.UPFILECNT > 0}">
						<img src="http://cylearn.co.kr/images/2014/ico_disk.png" alt="첨부파일" />
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
					<td class="tdSubject" colspan="6">조회된 내용이 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div><!-- //boardList -->
	<div class="ar"><a href="#none" onclick="boardForm()"><img src="http://cylearn.co.kr/images/2014/btn_write.gif" alt="글쓰기" /></a></div>
	<!-- 페이지 네비게이션 -->
	<div class="paging"><a href="#none" ><img src="http://cylearn.co.kr/images/2014/btn_1st.gif" alt="처음" /></a>&nbsp;<a href="#none" ><img src="http://cylearn.co.kr/images/2014/btn_pre.gif" alt="이전" /></a><span>&nbsp;<a href="#none" ><em>1</em></a>&nbsp;<a href="#none" onclick="javascript:goPage('2')">2</a>&nbsp;<a href="#none" onclick="javascript:goPage('3')">3</a>&nbsp;<a href="#none" onclick="javascript:goPage('4')">4</a>&nbsp;<a href="#none" onclick="javascript:goPage('5')">5</a>&nbsp;<a href="#none" onclick="javascript:goPage('6')">6</a>&nbsp;<a href="#none" onclick="javascript:goPage('7')">7</a>&nbsp;<a href="#none" onclick="javascript:goPage('8')">8</a>&nbsp;<a href="#none" onclick="javascript:goPage('9')">9</a>&nbsp;<a href="#none" onclick="javascript:goPage('10')">10</a></span>&nbsp;<a href="#none" onclick="javascript:goPage('11')"><img src="http://cylearn.co.kr/images/2014/btn_next.gif" alt="다음" /></a>&nbsp;<a href="#none" onclick="javascript:goPage('111') "><img src="http://cylearn.co.kr/images/2014/btn_last.gif" alt="마지막" /></a></div>
	<!-- //페이지 네비게이션 -->
</div><!--//contentBody-->
