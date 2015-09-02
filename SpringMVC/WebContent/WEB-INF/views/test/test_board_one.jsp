<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

%>
<script>
function toggleFileDownBox(){
	$("#fileDownBox").toggle();
}

function boardList() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_list";
	frm.submit();
}

function boardDelete() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_delete";
	frm.submit();
}

function boardUpdate() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_update_form";
	frm.submit();
}
</script>

<div class="contentBody">
	<div class="topTitle clearfix">
		<h3>공지사항</h3>
		<span>최근 소식 및 교육관련사항을 안내해드립니다</span> <span class="nowPage"> Home&nbsp;>&nbsp;고객센터&nbsp;>&nbsp;공지사항 </span> </div>
	<div class="boardRead">
		<!-- s: 공지사항 보기 -->
		<table summary="공지사항 상세보기">
			<caption>
			공지사항 상세보기
			</caption>
			<form name="form1" id="form1" method="post">
				<input type="hidden" name="tabseq" value="${tabseq}"/>
				<input type="hidden" name="seq" value="${seq}" />
				<tbody>
					<tr>
						<th scope="row">${one.TITLE }</th>
					</tr>
					<tr>
						<td>
							<span><strong>작성자&nbsp;:&nbsp;</strong>${one.NAME }</span><span>|</span>
							<span><strong>등록일&nbsp;:&nbsp;</strong>${one.INDATE }</span><span>|</span>
							<span><strong>조회&nbsp;:&nbsp;</strong>${one.CNT }</span>
							<c:if test="${one.UPFILECNT > 0}">
							<span>|</span>
							<div class="board_file_down">
								<strong>첨부파일&nbsp;:</strong>
								<a class="btn_file_down" href="javascript:toggleFileDownBox();">
									<img src="http://cylearn.co.kr/images/2014/ico_disk.png" alt="첨부파일" /><em class="file_count">(${one.UPFILECNT})</em>
								</a>
								<div class="layer_file_down" id="fileDownBox" style="display:none;">
									<ul>
									<c:forEach items="${fileList}" var="item" varStatus="stat">
										<li><a href="#">${item.REALFILE}</a></li>
									</c:forEach>
									</ul>
									<a class="close"><img onclick="javascript:toggleFileDownBox();" src="http://cylearn.co.kr/images/2014/btn_file_close.png" alt="닫기" /></a>
								</div>
							</div>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="contItem">
							<div class="article">
							${one.CONTENT}
							</div>
						</td>
					</tr>
				</tbody>
			</form>
		</table>
		<div class="ar mt10">
			<a href="#none" onclick="javascript:return false;"><img src="http://cylearn.co.kr/images/2014/btn_board_reply.gif" alt="답변" /></a>
			<a href="#none" onclick="boardUpdate();"><img src="http://cylearn.co.kr/images/2014/btn_board_modify.gif" alt="수정" /></a>
			<a href="#none" onclick="boardDelete();"><img src="http://cylearn.co.kr/images/2014/btn_board_del.gif" alt="삭제" /></a>
			<a href="#none" onclick="boardList();"><img src="http://cylearn.co.kr/images/2014/btn_board_list.gif" alt="목록보기" /></a>
		</div>
	</div>
	<!--boardRead-->
</div>
<!--//contentBody-->
