<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

%>
<form name="plusform" method="get">
	<input type="hidden" name="board"		value="<%=request.getParameter("board") %>" />
	<input type="hidden" name="p"		value="<%=request.getParameter("p") %>" />
	<input type="hidden" name="pg"		value="<%=request.getParameter("pg") %>" />
	<input type="hidden" name="mode"		value="<%=request.getParameter("mode") %>" />
</form>
		<div id="container">

			<c:if test="${fn:length(list) > 0}">
			<div id="sub03_content">
				<!-- list -->
				<div id="moreTypeList">
					<c:forEach items="${list}" var="item" varStatus="stat">
					<article class="list">
						<table summary="과정" class="book_info" cellpadding="0" cellspacing="0">
							<caption>과정</caption>
							<colgroup>
								<col width="87px" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<td>
										<h2>${item.BOOKNAME}</h2>
										<div class="info">
											<dl>
												<dt>가 격 :</dt>
												<dd>${item.BOOKPRICE}</dd>
											</dl>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</article>
					</c:forEach>
				</div>
				<!-- //list -->
			</div>
			</c:if>
			<c:if test="${fn:length(list)==0}">
			<div id="sub_common_content">
				<!-- 검색결과없음 -->
				<div class="search_result">
					<ul>
						<li class="txt">검색된 내용이 없습니다</li>
					</ul>
				</div>
				<!-- //검색결과없음 -->
			</div>
			</c:if>
			<div><button onclick="javascript:location.href='/test/form';">등록</button></div>
		</div>
