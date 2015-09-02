<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<script >
//���
function boardInsert() {

	var frm = document.getElementById("form1");
	if (blankCheck(frm.title.value)) {
		alert("������ �Է��ϼ���!");
		frm.p_title.focus();
		return;
	}
	if (realsize(frm.title.value) > 200) {
		alert("������ �ѱ۱��� 100�ڸ� �ʰ����� ���մϴ�.");
		frm.p_title.focus();
		return;
	}

	if (blankCheck(frm.content.value)) {
		alert("������ �Է��ϼ���!");
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

	//���� Ȯ���� ���͸�
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
	<!-- �Խ��Ǿ��� -->
	<div class="boardWr">
		<table summary="${boardInfo.LDESC}">
			<caption>${boardInfo.SDESC}</caption>
			<colgroup>
				<col width="15%"/>
				<col />
			</colgroup>
			<tr>
				<th scope="row">�ۼ���</th>
				<td>�����̸�</td>
			</tr>
			<tr>
				<th scope="row"><label for="title">����</label></th>
				<td><input type="text" id="title" class="input" name="title" style="width:98%" value="" /></td>
			</tr>
			<tr>
				<th scope="row"><label for="content">����</label></th>
				<td><textarea id="content" name="content" rows="7" cols="50"></textarea></td>
			</tr>
			<tr>
				<th scope="row"><label for="files">÷������</label></th>
				<td>
					<input type="file" id="files" name="files" style="IME-MODE: disabled; width:98%;"/>
				</td>
			</tr>
		</table>
	</div>
	<!-- //�Խ��Ǿ��� -->
	<div class="ac">
		<a href="#none" onclick="javascript:boardInsert();"><img src="http://cylearn.co.kr/images/2014/btn_done.gif" alt="�ۼ��Ϸ�" /></a>
		<a href="#none" onclick="javascript:boardList();"><img src="http://cylearn.co.kr/images/2014/btn_goList.gif" alt="��Ϻ���" /></a>
	</div>
</form>
</div><!--//contentBody-->