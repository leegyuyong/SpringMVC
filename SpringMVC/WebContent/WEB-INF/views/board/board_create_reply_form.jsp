<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
	<!-- �������� -->

	<!-- section -->
	<div class="section">
	<form id="form1" method="post" action="/board/insert" enctype="multipart/form-data">
		<input type="hidden" name="tabseq"     value="${tabseq}"/>
		<input type="hidden" name="seq"        value="${seq}" />
		<input type="hidden" name="search"     value="${search}" />
		<input type="hidden" name="searchtext" value="${searchtext}" />

		<input type="hidden" name="refseq"   value="${map.REFSEQ }" />
		<input type="hidden" name="levels"   value="${map.LEVELS }" />
		<input type="hidden" name="position" value="${map.POSITION }" />

		<h1>${bds.SDESC}</h1>
		<div class="head-tip mt10">
			<p>${bds.LDESC}</p>
		</div>
		<div class="board-write">
			<div class="box">
				<div class="name">�ۼ���</div>
				<div class="insert">${userSession.name}(${userSession.userid})</div>
			</div>
			<div class="box">
				<div class="name">����</div>
				<div class="insert"><input type="text" style="width:97%" name="title" value="[RE]${map.TITLE}"></div>
			</div>
			<div class="box">
				<div class="name">����</div>
				<div class="insert"><textarea title="������ �Է����ּ���." name="content"></textarea></div>
			</div>
			<div class="box">
				<div class="name">÷������</div>
				<div class="insert">
					<div class="file-upload">
						<div class="filelist">
							<input type="file" class="fileform" style="width:99%" name="files"><!--��ĥ�߰� ��ư Ŭ���� �ش� file input�� �����Ͽ� �߰�������. jquery.sub.library.js �Լ� ���� -->
						</div>
						<div class="addfile">
							<a href="#none" class="add" title="�����߰�"></a>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="mt30 ac">
			<button type="button" class="active" onclick="create();">�ۼ��Ϸ�</button>
			<button type="button" class="normal" onclick="selectList();">�������</button>
		</div>
	</form>
	</div>

	<!-- ���� �� -->



<script >
	//���
	function create() {

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
			frm.action = "/board/create_reply";
			frm.submit();
		} else {
			return;
		}

	}

	function selectList() {
		var frm = document.getElementById("form1");
		frm.action = "/board/list/"+ frm.tabseq.value;
		frm.submit();
	}

</script>