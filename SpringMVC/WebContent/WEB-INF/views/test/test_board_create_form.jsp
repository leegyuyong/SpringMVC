<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<script >
//등록
function boardInsert() {

	var frm = document.getElementById("form1");
	if (blankCheck(frm.title.value)) {
		alert("제목을 입력하세요!");
		frm.p_title.focus();
		return;
	}
	if (realsize(frm.title.value) > 200) {
		alert("제목은 한글기준 100자를 초과하지 못합니다.");
		frm.p_title.focus();
		return;
	}

	if (blankCheck(frm.content.value)) {
		alert("내용을 입력하세요!");
		frm.content.focus();
		return;
	}

	if (check_word(frm.content.value, "script")) {
		return;
	}
	if (check_word(frm.content.value, "applet")) {
		return;
	}
	if (check_word(frm.content.value, "object")) {
		return;
	}

	//파일 확장자 필터링
	var islimit = true;
	/* for ( var i = 1; i <= 1; i++) {
		var file = eval("frm.p_file" + i + ".value");

		if (file != "") {
			islimit = limitFile(file);

			if (!islimit)
				break;
		}
	} */

	if (islimit) {
		frm.search.value = "";
		frm.searchtext.value = "";
		frm.action = "/test/board_insert";
		frm.submit();
	} else {
		return;
	}

}

function boardList() {
	var frm = document.getElementById("form1");
	frm.action = "/test/board_list";
	frm.submit();
}

</script>
<div class="contentBody">
<form id="form1" method="post" action="/test/board_insert" enctype="multipart/form-data">
	<input type="hidden" name="tabseq"     value="${tabseq}"/>
	<input type="hidden" name="seq"        value="${seq}" />
	<input type="hidden" name="search"     value="${search}" />
	<input type="hidden" name="searchtext" value="${searchtext}" />
	<%--
	<input type="hidden" name="subj"     value = "${subj}" />
	<input type="hidden" name="year"     value = "${year}" />
	<input type="hidden" name="subjseq"  value = "${subjseq }" />
	 --%>
	<div class="topTitle clearfix">
		<h3>${boardInfo.SDESC}</h3>
		<span>${boardInfo.LDESC}</span> <span class="nowPage"> Home&nbsp;>&nbsp;${boardInfo.SDESC}</span>
	</div>
	<!-- 게시판쓰기 -->
	<div class="boardWr">
		<table summary="${boardInfo.LDESC}">
			<caption>${boardInfo.SDESC}</caption>
			<colgroup>
				<col width="15%"/>
				<col />
			</colgroup>
			<tr>
				<th scope="row">작성자</th>
				<td>세션이름</td>
			</tr>
			<tr>
				<th scope="row"><label for="title">제목</label></th>
				<td><input type="text" id="title" class="input" name="title" style="width:98%" value="" /></td>
			</tr>
			<tr>
				<th scope="row"><label for="content">내용</label></th>
				<td><textarea id="content" name="content" rows="7" cols="50"></textarea></td>
			</tr>
			<tr>
				<th scope="row"><label for="files">첨부파일</label></th>
				<td>
					<input type="file" id="files" name="files" style="IME-MODE: disabled; width:98%;"/>
				</td>
			</tr>
		</table>
	</div>
	<!-- //게시판쓰기 -->
	<div class="ac">
		<a href="#none" onclick="javascript:boardInsert();"><img src="http://cylearn.co.kr/images/2014/btn_done.gif" alt="작성완료" /></a>
		<a href="#none" onclick="javascript:boardList();"><img src="http://cylearn.co.kr/images/2014/btn_goList.gif" alt="목록보기" /></a>
	</div>
</form>
</div><!--//contentBody-->