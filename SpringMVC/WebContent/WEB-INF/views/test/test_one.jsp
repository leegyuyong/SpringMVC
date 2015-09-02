<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%

%>
<form name="plusform" method="get">
	<input type="hidden" name="board"		value="<%=request.getParameter("board") %>" />
	<input type="hidden" name="p"		value="<%=request.getParameter("p") %>" />
	<input type="hidden" name="pg"		value="<%=request.getParameter("pg") %>" />
	<input type="hidden" name="mode"		value="<%=request.getParameter("mode") %>" />
</form>
		<div id="container">
			<div id="sub03_content">
				<!-- book_view -->
				<div class="book_view">
					<div class="subject">
						<h3>${map.BOOKNAME}</h3>
					</div>
					<div class="desc">
						<dl>
							<dt>АЁ Ан |</dt>
							<dd>${map.BOOKPRICE}</dd>
						</dl>
					</div>
				</div>
				<!-- //book_view -->
			</div>
		</div>
