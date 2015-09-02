/**
 * 학습창 Javascript
 *
 * Copyright 2008 ziaan Co,. LTD. All rights reserved.
 *
 * @version		1.0, 2008/12/10
 * @author		Chung Jin-pil
 */

// 학습창 Javascript 사용시, 다음 form 정보를 포함 시킬 것
/*
<form id="studyForm" name="studyForm" method="post">
  <input type="hidden" name="p_process">
  <input type="hidden" name="p_contenttype">
  <input type="hidden" name="p_subj">
  <input type="hidden" name="p_subjseq">
  <input type="hidden" name="p_year">
  <input type="hidden" name="p_studytype">
  <input type="hidden" name="p_next_process">
</form>
*/

/**
 * 학습하기 : 수강승인 된 학습자들만 학습 가능
 */
function doStudy( contentType, subj, year, subjseq, height, width ) {
	var studyType = "";
	goStudy( contentType, subj, year, subjseq, studyType , height, width);
}

/**
 * 복습하기 : 해당 과정(기수)을 수료완료하고 복습기간이내인 학습자
 */
function doReview( contentType, subj, year, subjseq ,height, width) {
	var studyType = "review";
	goStudy( contentType, subj, year, subjseq, studyType, height, width );
}

/**
 * 복습하기 : 해당 과정(기수)을 수료완료하고 복습기간이내인 학습자
 */
function doReview2013( contentType, subj, year, subjseq ,height, width) {
	var studyType = "review";
	goStudy2013( contentType, subj, year, subjseq, studyType, height, width );
}

/**
 * 미리보기 : p_year="2000" 일 경우 & 운영자 및 특정 권한일 경우
 */
function doPreview( contentType, subj , height, width) {
	var studyType = "preview";

	if ( contentType == "S" ) {	// SCORM2004 일 때만 예외처리
		studyType = "preview";
	}
	goPreview( contentType, subj, studyType, height, width );
}
/**
 * 미리보기 : p_year="2000" 일 경우 & 운영자 및 특정 권한일 경우
 */
function doPreview2013( contentType, subj , height, width) {
	var studyType = "preview";

	if ( contentType == "S" ) {	// SCORM2004 일 때만 예외처리
		studyType = "preview";
	}
	goPreview2013( contentType, subj, studyType, height, width );
}

/**
 * 열린과정 : 열린과정일 경우만 학습가능
 */
function doOpenedu( contentType, subj ) {
	var studyType = "openedu";
	goPreview( contentType, subj, studyType );
}

/**
 * 맛보기 : 맛보기 페이지 및 맛보기로 설정된 차시(Object)를 보여준다
 */
function doSample( contentType, subj, prevurl, width, height ) {

	switch( contentType ) {
		case "N":
		case "H":
		case "C":
			url = prevurl;
			//url = "/content/lcms/" + subj + "/guest/guest.html";
			break;
		case "L":

			url = prevurl;
			//url = "/content/lcms/" + subj + "/guest/guest.html";
			break;
		case "O":
			url = "/servlet/controller.lcms.EduStart?p_subj=" + subj + "&p_year=PREV";
			break;
		case "OA":
			url = "/servlet/controller.lcms.NewEduStart?p_subj=" + subj + "&p_year=PREV";
			break;
		case "S":
			url = "/servlet/controller.scorm2004.ScormStudyServlet?p_process=previewMain&p_subj=" + subj;
			break;
		case "K":
			url = "/preview/" + subj.substring(0,4) + "/index.html";
			break;
	}

	if (width == "99999" || width == undefined || width == ''){
		studyPop = window.open(url,"studyPopup","toolbar=no,status=yes,menubar=no,scrollbars=no,resizable=yes,width=1014,height=756,left=0,top=0");
	} else {
		studyPop = window.open(url,"studyPopup","toolbar=no,status=yes,menubar=no,scrollbars=no,resizable=yes,width="+width+",height="+height+",left=0,top=0");
	}
	studyPop.focus();
}

/**
 * 학습하기
 */
function goStudy( contentType, subj, year, subjseq, studyType, height, width ) {
	var form = document.getElementById("studyForm");

	form["p_contenttype"].value = contentType;
	form["p_subj"].value = subj;
	form["p_year"].value = year;
	form["p_subjseq"].value = subjseq;
	form["p_studytype"].value = studyType;
	form["p_next_process"].value = "";

	switch( contentType ) {
		case "N":
		case "H":
		case "O":
		case "C":
			form["p_process"].value = "main";
			form.action = "/servlet/controller.lcms.EduStart";
			break;
		case "OA":
			form["p_process"].value = "main";
			form.action = "/servlet/controller.lcms.NewEduStart";
			break;
		case "S":
			form["p_process"].value = "mappingInfoList";
			if (studyType == "betatest")
				form["p_next_process"].value = "betatestMain";
			else
				form["p_next_process"].value = "studyMain";
			form.action = "/servlet/controller.scorm2004.ScormStudyServlet";
			break;
	}


	if(height == 99999 && width == 99999){
		//window.open("","studyPopup","channelmode");
		studyPop = window.open("","studyPopup","channelmode=yes,resizable=yes");
		//alert('test');
 	}
	else if(height != 0 && width != 0){
 		studyPop = window.open("","studyPopup","toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width="+width+",height="+height+",left=0,top=0");
 	}
	else{
		studyPop = window.open("","studyPopup","toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=685,height=400,left=0,top=0");
	}
    studyPop.focus();

    form.target = "studyPopup";
	form.submit();

	form.target = window.self.name;
}

function goStudy2013( contentType, subj, year, subjseq, studyType, height, width ) {
	var form = document.getElementById("studyForm");

	form["p_contenttype"].value = contentType;
	form["p_subj"].value = subj;
	form["p_year"].value = year;
	form["p_subjseq"].value = subjseq;
	form["p_studytype"].value = studyType;
	form["p_next_process"].value = "";

	switch( contentType ) {
		case "N":
		case "H":
		case "O":
		case "C":
			form["p_process"].value = "main";
			form.action = "/servlet/controller.lcms.EduStart2013";
			break;
		case "OA":
			form["p_process"].value = "main";
			form.action = "/servlet/controller.lcms.NewEduStart";
			break;
		case "S":
			form["p_process"].value = "mappingInfoList";
			if (studyType == "betatest")
				form["p_next_process"].value = "betatestMain";
			else
				form["p_next_process"].value = "studyMain";
			form.action = "/servlet/controller.scorm2004.ScormStudyServlet";
			break;
	}


	if(height == 99999 && width == 99999){
		//window.open("","studyPopup","channelmode");
		studyPop = window.open("","studyPopup","channelmode=yes,resizable=yes");
		//alert('test');
 	}
	else if(height != 0 && width != 0){
 		studyPop = window.open("","studyPopup","toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width="+width+",height="+height+",left=0,top=0");
 	}
	else{
		studyPop = window.open("","studyPopup","toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=685,height=400,left=0,top=0");
	}
    studyPop.focus();

    form.target = "studyPopup";
	form.submit();

	form.target = window.self.name;
}

/**
 * 미리보기
 */
function goPreview( contentType, subj, studyType, height, width ) {
	var form = document.getElementById("studyForm");

	form["p_contenttype"].value = contentType;
	form["p_subj"].value = subj;

	form["p_year"].value = "2000";
	form["p_subjseq"].value = "0001";
	form["p_studytype"].value = studyType;
	form["p_next_process"].value = "";

	switch( contentType ) {
		case "N":
		case "H":
		case "O":
		case "C":
			form["p_process"].value = "main";
			form.action = "/servlet/controller.lcms.EduStart";
			break;
		case "OA":
			form["p_process"].value = "main";
			form.action = "/servlet/controller.lcms.NewEduStart";
			break;
		case "S":
			form["p_process"].value = "mappingInfoList";
			form["p_next_process"].value = "testeduMain";
			form.action = "/servlet/controller.scorm2004.ScormStudyServlet";
			break;
		case "K":
			form["p_process"].value = "main";
    		form.action = "/servlet/controller.lcms.KTEduStart";
			break;
	}

	if(height == 99999 && width == 99999){
		studyPop = window.open("","studyPopup","channelmode=yes,resizable=yes");
 	}
	else if(height != 0 && width != 0){
 		studyPop = window.open("","studyPopup","toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width="+width+",height="+height+",left=0,top=0");
 	}
	else{
		studyPop = window.open("","studyPopup","toolbar=no,status=yes,menubar=no,scrollbars=no,resizable=yes,width=685,height=400,left=0,top=0");
	}

	studyPop.focus();

    form.target = "studyPopup";
	form.submit();

	form.target = window.self.name;
}
/**
 * 미리보기
 */
function goPreview2013( contentType, subj, studyType, height, width ) {
	var form = document.getElementById("studyForm");

	form["p_contenttype"].value = contentType;
	form["p_subj"].value = subj;

	form["p_year"].value = "2000";
	form["p_subjseq"].value = "0000";
	form["p_studytype"].value = studyType;
	form["p_next_process"].value = "";

	switch( contentType ) {
	case "N":
	case "H":
	case "O":
	case "C":
		form["p_process"].value = "main";
		form.action = "/servlet/controller.lcms.EduStart2013";
		break;
	case "OA":
		form["p_process"].value = "main";
		form.action = "/servlet/controller.lcms.NewEduStart";
		break;
	case "S":
		form["p_process"].value = "mappingInfoList";
		form["p_next_process"].value = "testeduMain";
		form.action = "/servlet/controller.scorm2004.ScormStudyServlet";
		break;
	case "K":
		form["p_process"].value = "main";
		form.action = "/servlet/controller.lcms.KTEduStart";
		break;
	}

	if(height == 99999 && width == 99999){
		studyPop = window.open("","studyPopup","channelmode=yes,resizable=yes");
	}
	else if(height != 0 && width != 0){
		studyPop = window.open("","studyPopup","toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width="+width+",height="+height+",left=0,top=0");
	}
	else{
		studyPop = window.open("","studyPopup","toolbar=no,status=yes,menubar=no,scrollbars=no,resizable=yes,width=685,height=400,left=0,top=0");
	}

	studyPop.focus();

	form.target = "studyPopup";
	form.submit();

	form.target = window.self.name;
}

/**
 * 독서통신 학습창
 */
function goStudyRC( subj, year, subjseq ) {
	var form = document.getElementById("studyForm");

	form["p_contenttype"].value = "K";//KT컨텐츠이미지를 동일하게 사용하기 위해
	form["p_subj"].value = subj;
	form["p_year"].value = year;
	form["p_subjseq"].value = subjseq;
	form["p_studytype"].value = "review";
	form["p_next_process"].value = "";

	form["p_process"].value = "main";
 	form.action = "/servlet/controller.lcms.RCEduStart";

    studyPop = window.open("","studyPopup","toolbar=no,status=yes,menubar=no,scrollbars=no,resizable=yes,width=685,height=400,left=0,top=0");
    studyPop.focus();

    form.target = "studyPopup";
	form.submit();

	form.target = window.self.name;
}