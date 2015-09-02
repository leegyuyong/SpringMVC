<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
	<!-- 본문시작 -->

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
				<div class="name">작성자</div>
				<div class="insert">${userSession.name}(${userSession.userid})</div>
			</div>
			<div class="box">
				<div class="name">제목</div>
				<div class="insert"><input type="text" style="width:97%" name="title" value="[RE]${map.TITLE}"></div>
			</div>
			<div class="box">
				<div class="name">내용</div>
				<div class="insert"><textarea title="내용을 입력해주세요." name="content"></textarea></div>
			</div>
			<div class="box">
				<div class="name">첨부파일</div>
				<div class="insert">
					<div class="file-upload">
						<div class="filelist">
							<input type="file" class="fileform" style="width:99%" name="files"><!--파칠추가 버튼 클릭시 해당 file input을 복사하여 추가시켜줌. jquery.sub.library.js 함수 참조 -->
						</div>
						<div class="addfile">
							<a href="#none" class="add" title="파일추가"></a>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="mt30 ac">
			<button type="button" class="active" onclick="create();">작성완료</button>
			<button type="button" class="normal" onclick="selectList();">목록으로</button>
		</div>
	</form>
	</div>

	<!-- 본문 끝 -->



<script >
	//등록
	function create() {

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