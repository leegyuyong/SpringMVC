
// 달력 스크립트 시작
var target;

function MiniCal(jucke) {
  target=jucke
  x = (document.layers) ? loc.pageX : event.clientX;
  y = (document.layers) ? loc.pageY : event.clientY;
  minical.style.pixelTop  = y+document.body.scrollTop;
  minical.style.pixelLeft  = x-50;
  minical.style.display = (minical.style.display == "block") ? "none" : "block";
  Show_cal(0,0,0)
}

var stime
function doOver() {
  var el = window.event.srcElement;
  cal_Day = el.title;

  if (cal_Day.length > 7) {
    el.style.borderTopColor = el.style.borderLeftColor = "buttonhighlight";
    el.style.borderRightColor = el.style.borderBottomColor = "buttonshadow";
  }
  window.clearTimeout(stime);
}

function doClick() {
  cal_Day = window.event.srcElement.title;
  window.event.srcElement.style.borderColor = "red";
  if (cal_Day.length > 7) {
    target.value=cal_Day
  }
}

function doOut() {
  var el = window.event.fromElement;
  cal_Day = el.title;

  if (cal_Day.length > 7) {
    el.style.borderColor = "white";
  }
  stime=window.setTimeout("minical.style.display='none';", 10);
}

// 달력 스크립트 끝

function SendMail(urlstr) {
  var popWnd = urlstr ;
  window.open(popWnd, "sendmail", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=no,copyhistory=yes,width=900,height=600");
}

function userinfo(url){
  window.open(url, "userinfo", "toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,width=562,height=285,top=0,left=0");
}

function imageOn(i)
{
  eval('document.img'+i+'.src=images['+i+'][1].src');
}

function imageOff(i)
{
  eval('document.img'+i+'.src=images['+i+'][0].src');
}

function num_max_chk(data, data1)
{
  for (var i = 0 ;i < data.value.length ;i++ )
  {
    if ((data.value.substring(i,i+1) < "0" || data.value.substring(i,i+1) > "9" ))
    {
      alert("숫자만 입력가능합니다.");
      data.focus();
      return;
    }
  }

  if (parseInt(data.value,10) > parseInt(data1,10))
  {
    alert("만점보다 적은값을 입력하십시요.");
    data.focus();
    return;
  }
}

function realsizeChk( value, maxleng, msg )
{
	var len = 0;
	var rtnmsg = "";
	if ( value == null ) {
		rtnmsg = "해당 Object 가 없습니다.";
		alert(rtnmsg);
		return false;
	}
	if (value.length> maxleng) {
		alert(msg + " 길이값이 " +  maxleng+ "Byte 보다 큽니다.");
		return false;
	}
	for(var i=0;i<value.length;i++){
	   var c = escape(value.charAt(i));
	   if ( c.length == 1 ) len ++;
	   else if ( c.indexOf("%u") != -1 ) len += 2;
	   else if ( c.indexOf("%") != -1 ) len += c.length/3;
	}

	if(len > maxleng) {
		alert(msg + " 길이값이 " +  maxleng+ "Byte 보다 큽니다.");
		return false;
	}
	return true;
}

function quotation_chk()
{
  var data;
  var sum_str = "";

  for ( var k = 0 ; k < document.forms.length ; k++ )
  {
    for ( var j = 0 ; j < document.forms[k].elements.length ;j++)
    {
      if ( document.forms[k].elements[j].type == "text" || document.forms[k].elements[j].type == "textarea" )
      {
        data = document.forms[k].elements[j].value;
        sum_str = "";

        for ( var i = 0 ; i < data.length ;i++)
        {
          if ( data.substring(i,i+1) == "'" )
          {
            sum_str = sum_str + "＇";
          }
          else if ( data.substring(i,i+1) == "\"" )
          {
            sum_str = sum_str + "＂";
          }
          else
          {
            sum_str = sum_str + data.substring(i,i+1);
          }
        }
        document.forms[k].elements[j].value = sum_str;
      }
    }
  }
}


function numeric_chk(data)
{
  for (var i = 0 ;i < data.value.length ;i++ )
  {
    if ((data.value.substring(i,i+1) < "0" || data.value.substring(i,i+1) > "9" ))
    {
      alert("숫자만 입력가능합니다.");
      data.value = "";
      data.focus();
      return false;
    }
  }
  return true;
}

function numeric_chk_noalert(value)
{
  for (var i = 0 ;i < value.length ;i++ )
  {
    if ((value.substring(i,i+1) < "0" || value.substring(i,i+1) > "9" )) {
      return false;
    }
  }
  return true;
}

function number_chk(data)
{
  if (isNaN(data.value))
  {
    alert("숫자만 입력가능합니다.");
    data.value = "0.0";
    data.focus();
    return false;
  }
  return true;
}

function number_chk_noalert(value)
{
  if (isNaN(value)) {
    return false;
  }
  return true;
}


/**
 * 입력값이 사용자가 정의한 포맷 형식인지 체크
 * 자세한 format 형식은 자바스크립트의 'regular expression'을 참조
 */
function isValidFormat(input,format) {
    if (input.value.search(format) != -1) {
        return true; //올바른 포맷 형식
    }
    return false;
}

/**
 * 입력값이 이메일 형식인지 체크
 * ex) if (!isValidEmail(form.email)) {
 *         alert("올바른 이메일 주소가 아닙니다.");
 *     }
 */
function isValidEmail(input) {
    var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
    if (blankCheck(input.value)) return true;
    return isValidFormat(input,format);
}

/**
 * 입력값이 전화번호 형식(숫자-숫자-숫자)인지 체크
 */
function isValidPhone(input) {
    var format = /^(\d+)-(\d+)-(\d+)$/;
    if (blankCheck(input.value)) return true;
    return isValidFormat(input,format);
}

/**
 * 선택된 라디오버튼이 있는지 체크
 */
function hasCheckedRadio(input) {
    if(input) {
        if (input.length > 1) {
            for (var inx = 0; inx < input.length; inx++) {
                if (input[inx].checked) return true;
            }
        }
        else {
            if (input.checked) return true;
        }
    }
    return false;
}

/**
 * 선택된 체크박스가 있는지 체크
 */
function hasCheckedBox(input) {
    return hasCheckedRadio(input);
}

function Insa_OnClick(urlstr)
{
  var popWnd = urlstr ;
  var popmnu = window.open(popWnd, "popmnu", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=yes,width=500,height=350");
}

function Excel(urlstr)
{
   excel_popup = window.open("/excel/" + urlstr + ".csv", "popup1","toolbar=no,location=no,directories=no,status=yes,menubar=yes,scrollbars=yes,resizable=yes,copyhistory=yes, width=800, height=530");
}

function PullWin(urlstr)
{
  var popWnd = urlstr ;
  var popup = window.open(popWnd, "popup", "toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,copyhistory=yes");
}

function select_YYYYMMDD_LeapYear(year)
{
  var flag = new Boolean(false);

  if (((year % 4 == 0) && !(year % 100 == 0))||(year % 400 ==0)) return (true);
  /*
  if ((parseInt(year,10) % 4) == 0)      // 4년마다 윤년이고
  {
    flag = true;
    if ((parseInt(year,10) % 100) == 0)   // 그중 100년마다 윤년이 아니고,
    {
      flag = false;
      if ((parseInt(year,10) % 400) == 0)  // 그중 400년마다 윤년이다
      {
        flag = true;
      }
    }
  }
  */
  return (false);
}

function select_YYYYMMDD_Change(syear,smon,sday)
{
  myform = document.form1;    // FORM 이름변경(script이동시)
  myform_s = "document.form1.";
  var end_day = new Array();
  end_day[0] = 31;
  end_day[1] = 28;
  end_day[2] = 31;
  end_day[3] = 30;
  end_day[4] = 31;
  end_day[5] = 30;
  end_day[6] = 31;
  end_day[7] = 31;
  end_day[8] = 30;
  end_day[9] = 31;
  end_day[10] = 30;
  end_day[11] = 31;
  var year = parseInt(this.eval(myform_s + syear + ".value"),10);
  var mon = parseInt(this.eval(myform_s + smon + ".value"),10) - 1;

  if (select_YYYYMMDD_LeapYear(year))  // true이면 윤년이므로 2월달은 28일이된다
  {
    end_day[1] = 29;
  }
  else
  {
    end_day[1] = 28;
  }

  var s_sday = myform_s + sday + ".options.length = end_day[mon]";
  this.eval(s_sday);

  for (var i=0; i<end_day[mon]; i++)
  {
    this.eval(myform_s + sday + ".options[i].value = i + 1");
    this.eval(myform_s + sday + ".options[i].text = i + 1");
  }
  if (this.eval(myform_s + sday + ".options.selectedIndex") == -1)
  {
    this.eval(myform_s + sday + ".options.selectedIndex = end_day[mon] - 1");
  }
}

function select_YYYYMMDD(syear,year,smon,mon,sday,day)
{   // s를 붙인 것은 html input name
  var select_check;
  var today = new Date();
  var now_year = today.getYear();

  if (now_year < 100)     // 2000년 이전에는 두자리 2000년이후는 4자리로 나온다..document참조
  {
    now_year = 1900 + now_year;
  }

  document.write("<select name=" + syear + " onChange=select_YYYYMMDD_Change('" + syear + "','" + smon + "','" + sday + "')>");
  for (var loop_year=now_year-1; loop_year<=now_year+10; loop_year++)
  {
    if (loop_year == year)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_year + select_check + ">" + loop_year + "</option>");
  }
  document.write("</select>/ ");

  document.write("<select name=" + smon + " onChange=select_YYYYMMDD_Change('" + syear + "','" + smon + "','" + sday + "')>");

  for (var loop_month=1; loop_month<=12; loop_month++)
  {
    if (loop_month == mon)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_month + select_check + ">" + loop_month + "</option>");
  }
  document.write("</select>/ ");

  document.write("<select name=" + sday + " onChange=select_YYYYMMDD_Change('" + syear + "','" + smon + "','" + sday + "')>");
  for (var loop_day=1; loop_day<=31; loop_day++)
  {
    if (loop_day == day)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_day + select_check + ">" + loop_day + "</option>");
  }
  document.write("</select>");
}

function select_YYYYMMDD2(syear,year,smon,mon,sday,day,shh,hh,smm,mm)
{  // s를 붙인 것은 html input name 시간,분까지가져옴
  var select_check;
  var today = new Date();
  var now_year = today.getYear();

  if (now_year < 100)                  // 2000년 이전에는 두자리 2000년이후는 4자리로 나온다..document참조
  {
    now_year = 1900 + now_year;
  }

  document.write("<select name=" + syear + ">");
  for (var loop_year=now_year-1; loop_year<=now_year+10; loop_year++)
  {
    if (loop_year == year)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_year + select_check + ">" + loop_year + "</option>");
  }
  document.write("</select>/ ");

  document.write("<select name=" + smon + " onChange=select_YYYYMMDD_Change('" + syear + "','" + smon + "','" + sday + "')>");

  for (var loop_month=1; loop_month<=12; loop_month++)
  {
    if (loop_month == mon)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_month + select_check + ">" + loop_month + "</option>");
  }
  document.write("</select>/ ");

  document.write("<select name=" + sday + ">");
  for (var loop_day=1; loop_day<=31; loop_day++)
  {
    if (loop_day == day)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_day + select_check + ">" + loop_day + "</option>");
  }
  document.write("</select>&nbsp;&nbsp;");

  //시간
  document.write("<select name=" + shh + ">");
  for (var loop_hh=0; loop_hh<=23; loop_hh++)
  {
    if (loop_hh == hh)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_hh + select_check + ">" + loop_hh + "</option>");
  }
  document.write("</select>:");
  //분
  document.write("<select name=" + smm + ">");
  for (var loop_mm=0; loop_mm<=59; loop_mm++)
  {
    if (loop_mm == mm)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_mm + select_check + ">" + loop_mm + "</option>");
  }
  document.write("</select>");

}


function select_YYYYMMDD3(syear,year,smon,mon,sday,day)
{  // s를 붙인 것은 html input name
  var select_check;
  var today = new Date();
  var now_year = today.getYear();

  if (now_year < 100)                // 2000년 이전에는 두자리 2000년이후는 4자리로 나온다..document참조
  {
    now_year = 1900 + now_year;
  }

  document.write("<select name=" + syear + ">");
  for (var loop_year=1950; loop_year<=now_year+10; loop_year++)
  {
    if (loop_year == year)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_year + select_check + ">" + loop_year + "</option>");
  }
  document.write("</select>/ ");

  document.write("<select name=" + smon + " onChange=select_YYYYMMDD_Change('" + syear + "','" + smon + "','" + sday + "')>");

  for (var loop_month=1; loop_month<=12; loop_month++)
  {
    if (loop_month == mon)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_month + select_check + ">" + loop_month + "</option>");
  }
  document.write("</select>/ ");

  document.write("<select name=" + sday + ">");
  for (var loop_day=1; loop_day<=31; loop_day++)
  {
    if (loop_day == day)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    document.write("<option value=" + loop_day + select_check + ">" + loop_day + "</option>");
  }
  document.write("</select>");
}


function select_hhmm(shh,hh,smm,mm){
    var select_check;
   //시간
  document.write("<select name=" + shh + ">");
  for (var loop_hh=0; loop_hh<=23; loop_hh++)
  {
    if (loop_hh == hh)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }

    if ( loop_hh < 10 )
    {
      loop_hh = "0" + loop_hh;
    }
    document.write("<option value=" + loop_hh + select_check + ">" + loop_hh + "</option>");
  }
  document.write("</select>:");
   //분
  document.write("<select name=" + smm + ">");
  for (var loop_mm=0; loop_mm<=59; loop_mm++)
  {
    if (loop_mm == mm)
    {
      select_check=" SELECTED ";
    }
    else
    {
      select_check=" ";
    }
    if ( loop_mm < 10 )
    {
      loop_mm = "0" + loop_mm;
    }
    document.write("<option value=" + loop_mm + select_check + ">" + loop_mm + "</option>");
  }
  document.write("</select>");
}

// 2001-06-22에서 숫자(20010622)가져오는 함수.
function make_date(str){
    var yyyy = str.substring(0,4);
    var mm   = str.substring(5,7);
    var dd   = str.substring(8,10);

    var date_val = yyyy+mm+dd;
    return date_val;
}

/*
function make_date2(data1, data2, data3){
	var zero_val = "00";
	var date_val = "";
	date_val = data1 + zero_val.substring(0,2-data2.length) + data2;
	date_val = date_val + zero_val.substring(0,2-data3.length) + data3;

	return date_val;
}
*/
function blankCheck( msg )
{
	var mleng = msg.length;
	chk=0;

	for (i=0; i<mleng; i++)
	{
		if ( msg.substring(i,i+1)!=' ' && msg.substring(i,i+1)!='\n' && msg.substring(i,i+1)!='\r') chk++;
	}
	if ( chk == 0 ) return (true);

	return (false);
}

function blankFind( msg )
{
	var mleng = msg.length;
	chk=0;

	for (i=0; i<mleng; i++)
	{
		if ( msg.substring(i,i+1)==' ' || msg.substring(i,i+1)=='\n' || msg.substring(i,i+1)=='\r') return (false);
	}

	return (true);
}

function realsize( value )
{
	var len = 0;
	if ( value == null ) return 0;
	for(var i=0;i<value.length;i++){
	   var c = escape(value.charAt(i));
	   if ( c.length == 1 ) len ++;
	   else if ( c.indexOf("%u") != -1 ) len += 2;
	   else if ( c.indexOf("%") != -1 ) len += c.length/3;
	}
	return len;
}

function compnumChk( code )
{
	var sum = 0;
	var getlist =new Array(10);
	var chkvalue =new Array("1","3","7","1","3","7","1","3","5");

	for (var i=0; i<10; i++) {
		getlist[i] = code.substring(i,i+1);
	}

	for (var i=0; i<9; i++) {
		sum += getlist[i]*chkvalue[i];
	}
	sum = sum +parseInt((getlist[8]*5)/10) ;
	sidliy = sum%10;
	sidchk = 0;

	if ( sidliy != 0 ) {
		sidchk = 10 - sidliy;
	}
	else {
		sidchk = 0;
	}

	if ( sidchk != getlist[9] ) {
		return (false);
	}

	return (true);
}

function useridCheck( userid )
{
  //id 영문 check
	var check = "y";
	var newid = userid;

	for(i=0; i<newid.length; i++){
		if (newid.charAt(i) == " "){
			check = "n";
		}
		else if (
				((newid.charAt(i) < "A") || (newid.charAt(i) > "Z"))
		&&		((newid.charAt(i) < "a") || (newid.charAt(i) > "z"))
		&&		((newid.charAt(i) < "0") || (newid.charAt(i) > "9"))
		&&		((newid.charAt(i) != "_") )
		){
			check = "n";
		}
	}

	if (check =="n") {
		return (false);
	}

	return (true);
}

function passwordCheck( passwd )
{
  //비밀번호 영문,숫자 혼용 check
	var cpasschk=0;
	var npasschk=0;
	var check = "y";
	var newpasswd = passwd;

	for(i=0; i<newpasswd.length; i++)
	{
		if (newpasswd.charAt(i) == " ") {
			check = "n";
		}
		else if (((newpasswd.charAt(i) < "A") || (newpasswd.charAt(i) > "Z"))
				 && ((newpasswd.charAt(i) < "a") || (newpasswd.charAt(i) > "z"))
		     && ((newpasswd.charAt(i) < "0") || (newpasswd.charAt(i) > "9"))){

			check = "n";
		}
		if ( "A" <= newpasswd.charAt(i) && newpasswd.charAt(i) <= "Z" ) cpasschk++;
		if ( "a" <= newpasswd.charAt(i) && newpasswd.charAt(i) <= "z" ) cpasschk++;
		if ( "0" <= newpasswd.charAt(i) && newpasswd.charAt(i) <= "9" ) npasschk++;
	}

	if (check == "n" || cpasschk == 0 || npasschk == 0) {
		return (false);
	}

	return (true);
  //check end
}

//입력폼에서 숫자만 입력
function currency(obj)
{
  if (event.keyCode >= 48 && event.keyCode <= 57) {
  } else {
  	event.returnValue = false
  }
}


// 전체 선택
function allcheck(theform)
{
    for( i=0; i<theform.elements.length; i++) {
        ele = theform.elements[i];
            ele.checked = true;
    }
    return;
}

// 전체해제
function discheck(theform)
{
    for( i=0; i<theform.elements.length; i++) {
        ele = theform.elements[i];
            ele.checked = false;
    }
    return;
}

// 윈도우 팝업
function open_window(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable) {

    toolbar_str   = ((toolbar   == 'yes') ? 'yes' : 'no') ;
    menubar_str   = ((menubar   == 'yes') ? 'yes' : 'no') ;
    statusbar_str = ((statusbar == 'yes') ? 'yes' : 'no') ;
    scrollbar_str = ((scrollbar == 'yes') ? 'yes' : 'no') ;
    resizable_str = ((resizable == 'yes') ? 'yes' : 'no') ;

    //alert('left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);

    window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str).focus();
}


 // null,자릿수 체크 ( 필드명,메세지,길이 ) /*완료
function checkLength ( theField, s, Length)
{
    if (theField.value == "" || theField.value == null )
    {
        theField.value="";  //폼창에 NULL값 리턴
        alert( s + "를 입력하십시요.");
        return(false);
    }

    if (theField.value.length < Length)
    {
        theField.value="";  //폼창에 NULL값 리턴
        alert( Length+"글자 이상 이어야 합니다.");
        return(false);
    }

    return(true);
}

    // null 값 체크 /*완료
function isNull ( theField, s )
{
    if (theField.value == "" || theField.value == null )
    {
        theField.value="";  //폼창에 NULL값 리턴
        theField.focus();
        //theField.select();
        alert( s + "을(를) 입력하십시요.");
        return(false);
    }

    return(true);
}

// ?????? ???? ???? ????
function rnocheck(input)
{
    var inputStr;
    var i = 0;
    var j = 0;
    var sum =0;
    var weight = new Array(1,3,7,1,3,7,1,3);
    inputStr= input.toString();

    for (i=0;i<inputStr.length-2;i++) {
        sum = sum + parseInt(inputStr.charAt(i)) * weight[i];
    }

    j = parseInt(inputStr.charAt(8)) * 5;

    sum = sum + ((j - (j % 10)) / 10) + ( j % 10 );
    sum = sum % 10;
    if (sum!=0)
        sum = 10 - sum;
    if (sum != inputStr.charAt(9) )
        return false;
     else
        return true;
}

function enterKey_Event(func_name, e){
	  if(e.keyCode == 13){
	    eval(func_name);
	  }
}

// 단어 체크
function check_word(value, word) {
	if(value.toLowerCase().indexOf(word) >=0 ) {
		alert("내용에 " + word + "라는 단어가 들어갈 수 없습니다.");
		return true;
	}
}

// 업로드 파일 제한
function validFilename( file_name ){
	var name = file_name;//.trim();
	var slashIdx = name.lastIndexOf('\\');   // 경로까지 포함된 파일명
	if ( slashIdx<0 )
		slashIdx = name.lastIndexOf('/')

	temp = name.substring( slashIdx+1 )

	var path = temp;
	var index = path.indexOf(".");
	var ext = path.substr(index,4);

	if ( ext.toLowerCase()==".html" || ext.toLowerCase()==".asp" || ext.toLowerCase()==".jsp"){
		alert("html, asp, jsp 파일은 제한합니다.");
		return false;
	}

	return true;
}

// 업로드 파일 제한
function validFilename2( file_name, file_type, s ){
	var name = file_name;//.trim();
	var slashIdx = name.lastIndexOf('\\');   // 경로까지 포함된 파일명
	if ( slashIdx<0 )
		slashIdx = name.lastIndexOf('/')

	temp = name.substring( slashIdx+1 )

	var path = temp;
	var index = path.indexOf(".");
	var ext = path.substr(index + 1, 4);

	if(file_type.indexOf(ext.toLowerCase()) < 0){
		alert(s + "은(는) '" + file_type + "' 형태의 파일만 업로드 가능합니다.");
		return false;
	}
	return true;
}

//--------------------------------------------------------------------------------------------------
// 용    도 : 라디오 버튼 값을 가져오는 함수
// 파라미터 : x(라디오 버튼 객체명)
// 리    턴 : 라디오 버튼 값
// 이 벤 트 : 스크립트 내
//--------------------------------------------------------------------------------------------------
function getRadioValue(x) {
    for(var i=0; i<x.length; i++)
        if (x[i].checked==true) return x[i].value;
    return "";
}

//--------------------------------------------------------------------------------------------------
// 용    도 : 라디오 버튼 값을 지정하는 함수
// 파라미터 : x(라디오 버튼 객체명), set_value(지정할 값)
// 리    턴 : 없음
// 이 벤 트 : 스크립트 내
//--------------------------------------------------------------------------------------------------
function setRadioValue(x, set_value) {
    for(var i=0; i<x.length; i++)
        if (x[i].value==set_value) x[i].checked=true;
}

/******************************************************************
 * EditAmt  숫자 입력시 천 단위로 콤마를 자동으로 입력한다
 *          숫자만 입력가능 하다. onKeyup="EditAmt(this);"
 * @param   object
 * @return  콤마추가한 데이타
******************************************************************/
function EditAmt(obj) {
    /*------------------------------------------------------------------------*/
    /* 변수선언 및 초기화.                                                    */
    /*------------------------------------------------------------------------*/
    var i = 0, j = 0, k = 0;                               /* Index           */
    /*------------------------------------------------------------------------*/
    /* 숫자만 입력가능하게 처리.                                              */
    /*------------------------------------------------------------------------*/
    for(i = 0; i < obj.value.length; i++) {
        /*--------------------------------------------------------------------*/
        /* 숫자가 아닌것은 제거.                                              */
        /*--------------------------------------------------------------------*/
        if (isNaN(obj.value.charAt(i)))
        {
            obj.value = obj.value.substring(0, i)
                        + obj.value.substring(i + 1, obj.value.length);
        }
    }
    switch (obj.value.length) {
        /*--------------------------------------------------------------------*/
        /* 4자리인 경우 구분자 ","를 추가.                                    */
        /*--------------------------------------------------------------------*/
        case 4:
             obj.value = obj.value.substring(0, 1) + ","
                         + obj.value.substring(1, 4);
             break;
        /*--------------------------------------------------------------------*/
        /* 5자리인 경우 년도 구분자 ","를 추가.                               */
        /*--------------------------------------------------------------------*/
        case 5:
             obj.value = obj.value.substring(0, 2) + ","
                         + obj.value.substring(2, 5);
             break;
        /*--------------------------------------------------------------------*/
        /* 6자리인 경우 구분자 ","를 추가.                                    */
        /*--------------------------------------------------------------------*/
        case 6:
             obj.value = obj.value.substring(0, 3) + ","
                         + obj.value.substring(3, 6);
             break;
        /*--------------------------------------------------------------------*/
        /* 7자리인 경우 구분자 ","를 추가.                                    */
        /*--------------------------------------------------------------------*/
        case 7:
             obj.value = obj.value.substring(0, 1) + ","
                         + obj.value.substring(1, 4) + ","
                         + obj.value.substring(4, 7);
             break;
        /*--------------------------------------------------------------------*/
        /* 8자리인 경우 구분자 ","를 추가.                                    */
        /*--------------------------------------------------------------------*/
        case 8:
             obj.value = obj.value.substring(0, 2) + ","
                         + obj.value.substring(2, 5) + ","
                         + obj.value.substring(5, 8);
             break;
        /*--------------------------------------------------------------------*/
        /* 9자리인 경우 구분자 ","를 추가.                                    */
        /*--------------------------------------------------------------------*/
        case 9:
             obj.value = obj.value.substring(0, 3) + ","
                         + obj.value.substring(3, 6) + ","
                         + obj.value.substring(6, 9);
             break;
        /*--------------------------------------------------------------------*/
        /* 10자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 10:
             obj.value = obj.value.substring(0, 1) + ","
                         + obj.value.substring(1, 4) + ","
                         + obj.value.substring(4, 7) + ","
                         + obj.value.substring(7,10);
             break;
        /*--------------------------------------------------------------------*/
        /* 11자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 11:
             obj.value = obj.value.substring(0, 2) + ","
                         + obj.value.substring(2, 5) + ","
                         + obj.value.substring(5, 8) + ","
                         + obj.value.substring(8,11);
             break;
        /*--------------------------------------------------------------------*/
        /* 12자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 12:
             obj.value = obj.value.substring(0, 3) + ","
                         + obj.value.substring(3, 6) + ","
                         + obj.value.substring(6, 9) + ","
                         + obj.value.substring(9,12);
             break;
        /*--------------------------------------------------------------------*/
        /* 13자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 13:
             obj.value = obj.value.substring( 0, 1) + ","
                         + obj.value.substring( 1, 4) + ","
                         + obj.value.substring( 4, 7) + ","
                         + obj.value.substring( 7,10) + ","
                         + obj.value.substring(10,13);
             break;
        /*--------------------------------------------------------------------*/
        /* 14자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 14:
             obj.value = obj.value.substring( 0, 2) + ","
                         + obj.value.substring( 2, 5) + ","
                         + obj.value.substring( 5, 8) + ","
                         + obj.value.substring( 8,11) + ","
                         + obj.value.substring(11,14);
             break;
        /*--------------------------------------------------------------------*/
        /* 15자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 15:
             obj.value = obj.value.substring( 0, 3) + ","
                         + obj.value.substring( 3, 6) + ","
                         + obj.value.substring( 6, 9) + ","
                         + obj.value.substring( 9,12) + ","
                         + obj.value.substring(12,15);
             break;
        /*--------------------------------------------------------------------*/
        /* 16자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 16:
             obj.value = obj.value.substring( 0, 1) + ","
                         + obj.value.substring( 1, 4) + ","
                         + obj.value.substring( 4, 7) + ","
                         + obj.value.substring( 7,10) + ","
                         + obj.value.substring(10,13) + ","
                         + obj.value.substring(13,16);
             break;
        /*--------------------------------------------------------------------*/
        /* 17자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 17:
             obj.value = obj.value.substring( 0, 2) + ","
                         + obj.value.substring( 2, 5) + ","
                         + obj.value.substring( 5, 8) + ","
                         + obj.value.substring( 8,11) + ","
                         + obj.value.substring(11,14) + ","
                         + obj.value.substring(14,17);
             break;
        /*--------------------------------------------------------------------*/
        /* 18자리인 경우 구분자 ","를 추가.                                   */
        /*--------------------------------------------------------------------*/
        case 18:
             obj.value = obj.value.substring( 0, 3) + ","
                         + obj.value.substring( 3, 6) + ","
                         + obj.value.substring( 6, 9) + ","
                         + obj.value.substring( 9,12) + ","
                         + obj.value.substring(12,15) + ","
                         + obj.value.substring(15,18);
             break;
    }
    return;
}

// 달력으로 선택 하지 않고 텍스트박스에 직접 입력했을 때 형식에 어긋나나 체크
function calendarCheck(caldate) {
	    var str = caldate.value.replace(/\-/g, "");
		var origin_len = caldate.value.length;
		var len = str.length;
		var cal = caldate.value.split("-");

	    var validation = false;
		if(isNaN(str)) {
			alert("-와 숫자만 입력 가능합니다.");
			caldate.focus();
			return true;
		} else {
			if(len > 8) { // 숫자만 길이를 따졌을 때 8자를 넘으면
				validation = true;
			} else {
				if(cal.length == 3) {
					if(!(cal[0].length == 4 && cal[1].length == 2 && cal[2].length == 2) ) {	// 2008.08.08 포맷
						validation = true;
					}
				} else if(cal.length == 1) {	// 20080808 포맷
					if(len != 8) {
						validation == true;
					}
				} else {	// 그 외
					validation = true;
				}
			}

			if(validation) {	// 형식에 안맞는게 있으면
				alert("수동입력 시 날짜 입력 형식은 2008-12-01 혹은 2008-12-01로 입력해 주세요.");
				caldate.focus();
				return true;
			}
		}
		return false;
	}

	// 이미지 보기 창 팝업 띄우기
	function viewImage( imgSrc ) {
		var w = window.open( '', '_blank', 'scrollbars=yes,width=400,height=300,top=20,left=20' )
		var html = '<html>'
		    + '<head>'
			+ '<style>'
			+ '.gallery {font-family:돋움,돋움체; font-size:12px; color:#868383;text-decoration:none;list-style-type:none;}'
			+ '</style>'
			+ '<script>'
			+ '    function body_onLoad() {'
			+ '        var o = document.all["myImg"];'
			+ '        var width = o.width>1024 ? 1024 : o.width;'
			+ '        var height = o.height>768 ? 768 : o.height;'
			+ '        resizeBy( width-document.body.clientWidth +8, height-document.body.clientHeight + 6 );'
			+ '        document.body.onclick = function() { window.close() }'
			+ '    }'
			+ '</script>'
			+ '</head>'
			+ '<body style="cursor:hand; margin:0; border:none" onLoad="body_onLoad()" oncontextmenu="return false">'
			+ '<table>'
			+ '<tr>'
			+ '<td>'
			+ '<img id=myImg src="' + imgSrc + '">'
			+ '</td>'
			+ '</tr>'
			+ '</table>'
			+ '</body></html>'

		w.document.open();
		w.document.write( html );
		w.document.close();
		w.focus();
	}

/* SCRIPT 메소드 주석
 * <!--
 * 메소드명     : checkMaxLength
 * 설명         : 최대길이를 체크한다
 *                onblur()시 발생한다
 * 메소드인수1  :
 * 메소드인수2  :
 * 메소드인수3 :
 * 메소드리턴값 :
 * 예외처리1    :
 * 예외처리2    :
 * //-->
 */
function CheckMaxLength(obj, maxLen, name)
{
	var len = getByteLength(obj.value);
	if (maxLen < len) {
		alert(name + "에 입력된 글자가 최대 길이(" + maxLen + ")보다 큽니다");
		return false;
	}
    return true;
}



/* SCRIPT 메소드 주석
 * <!--
 * 메소드명     : getByteLength
 * 설명         : Byte를 계산한다
 * 메소드인수1  : s 문자열
 * 메소드인수2  :
 * 메소드인수3 :
 * 메소드리턴값 : 문자열의 Byte수 (한글은 2Byte로 계산)
 * 예외처리1    :
 * 예외처리2    :
 * //-->
 */
function getByteLength(s)
{
   var len = 0;
   if ( s == null ) return 0;
   for(var i=0;i<s.length;i++)
	 {
      var c = escape(s.charAt(i) );
      if ( c.length == 1 ) len ++;
      else if ( c.indexOf("%u") != -1 ) len += 2;
      else if ( c.indexOf("%") != -1 ) len += c.length/3;
   }

   return len;
}

var reNumericBlank = /^([0-9]|[\ ])+$/;     //  isNumericBlank

/**
 *  숫자형 체크 ,  공백포함
 *  @param  String
 *  @return boolean
 */
function isNumericBlank(str) {
    return reNumericBlank.test(str);
}


/* SCRIPT 메소드 주석
 * <!--
 * 메소드명     : dragchkSetting
 * 설명         : 드래그 & checked
 *                body onload시 호출해서 사용한다.
 * 메소드인수1  : 드래그 & checked를 사용할 객체
 * 메소드인수2  :
 * 메소드인수3  :
 * 메소드리턴값 :
 * 예외처리1    :
 * 예외처리2    :
 * //-->
 */
var dragchkstat = "off";
function dragchkNOOP() { return false; }
function dragchkOnMouseDown() {
	if (this.checked) {
		dragchkstat = "uncheck"; this.checked = false;
	} else {
		dragchkstat = "check"; this.checked = true;
	}
	return false;
}
function dragchkOnMouseOver() {
	switch (dragchkstat) {
		case "off":
			break;
		case "check":
			this.focus(); this.checked = true; break;
		case "uncheck":
		this.focus(); this.checked = false; break;
	}
	return false;
}
function dragchkOnMouseUp() { dragchkstat = "off"; return true; }

function dragchkSetting(obj) {
	if ( obj != null ) {
		for (var i = 0; i < obj.length; i++) {
			obj[i].onclick = dragchkNOOP;
			obj[i].onmousedown = dragchkOnMouseDown;
			obj[i].onmouseover = dragchkOnMouseOver;
			document.onmouseup = dragchkOnMouseUp;
		}
	}
}

// 개인정보 수정
function modMemberInfo() {
    var frm = document.getElementById("frmLogin");
    document.getElementById("i_process").value = "updatePage";
    frm.action = "/servlet/controller.homepage.MemberServlet";
    frm.submit();
}

// 관련 사이트 링크 셀렉트박스
function goSiteSelect() {
	var url = document.linkform.link.value;
	if(url=="") {
		alert("이동하시고자 하시는 Family Site를 선택해 주세요.");
		return;
	}

	window.open(url).focus();

}

function goSite(url) {
	if(url != "?"){
		//location.href=url;
    	window.open(url).focus();
	}else{
		alert('잘못된 url 입니다');
		self.close();
	}
}

// 문자길이 제한(영문길이기준)
function strLenchk(obj1, len) {
	var obj = obj1;
	var hlen = len/2;
	if (getByteLength(obj.value) > len) {
		alert("글자수는 영문"+len+", 한글"+hlen+"자로 제한됩니다!");
		obj.value = cutStr(obj.value,len);
		obj.focus();
		return false;
	}
	return true;
}

// 문자열 길이 자르기(한글-2Byte계산)
function cutStr(str, limit) {
	var tmpStr = str;
	var byte_count = 0;
	var len = str.length;
	var dot = "";
	for (i = 0; i < len; i++) {
		byte_count += chr_byte(str.charAt(i));
		if (byte_count == limit - 1) {
			if (chr_byte(str.charAt(i + 1)) == 2) {
				tmpStr = str.substring(0, i + 1);
				//dot = "...";
			} else {
				if (i + 2 != len)
					//dot = "...";
				tmpStr = str.substring(0, i + 2);
			}
			break;
		} else if (byte_count == limit) {
			if (i + 1 != len)
				//dot = "...";
			tmpStr = str.substring(0, i + 1);
			break;
		}
	}
	return tmpStr;
}

//문자열 길이 자르기(한글-2Byte계산)+"..."
function cutStrDot(str, limit) {
	var tmpStr = str;
	var byte_count = 0;
	var len = str.length;
	var dot = "";
	for (i = 0; i < len; i++) {
		byte_count += chr_byte(str.charAt(i));
		if (byte_count == limit - 1) {
			if (chr_byte(str.charAt(i + 1)) == 2) {
				tmpStr = str.substring(0, i + 1);
				dot = "...";
			} else {
				if (i + 2 != len)
					dot = "...";
				tmpStr = str.substring(0, i + 2);
			}
			break;
		} else if (byte_count == limit) {
			if (i + 1 != len)
				dot = "...";
			tmpStr = str.substring(0, i + 1);
			break;
		}
	}
	return tmpStr+dot;
}

//한글 영문 byte길이 구분
function chr_byte(chr) {
	if (escape(chr).length > 4)
		return 2;
	else
		return 1;
}



// cylearn 패스워드 유효성 검사 by dogbag 2014.08.27
function chkPassWord(password){
	var str = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var chr = '~!@#$%^&*()-_=+';

	var res_1 = 0;
	var res_2 = 0;

	if( password.length < 8 ) {
		alert("비밀번호는 최소 8자리 이상 입력해주세요.");
		return (false);
	}


	for(var i=0; i<=password.length-3; i++) {
		if( password.charAt(i)==password.charAt(i+1) && password.charAt(i)==password.charAt(i+2) ) {
			alert("비밀번호는 같은 문자열을 3개 이상 연속으로 사용할 수 없습니다.");
			return (false);
		}
	}

	for(var x=0; x<password.length; x++) {
		for(var y=0; y<str.length; y++) {
			if(password.charAt(x)==str.charAt(y)) res_1++;
		}

		for(var z=0; z<chr.length; z++) {
			if(password.charAt(x)==chr.charAt(z)) res_2++;
		}
	}
	if( res_1 < 2) {
		alert("비밀번호 문자는 최소 2개 이상 입력하세요.");
		return (false);
	}

	return (true);
}

/*
' ------------------------------------------------------------------
' Function    :
' Description : 문자열 관련 함수
' Argument    :
' Return      :
' ------------------------------------------------------------------
*/
function lpad(str, length, charstr)
{
	if (str == '' || str == null)
	{
		return '';
	}

	str = str.toString();

	for(i=0;i<length;i++)
	{
		if(str.length < length)
		{
			str = charstr + str;
		}
	}
	return str;
}

function remove_str(in_str,str) {
	return str_replace(in_str,str,"");
}

function trim( str ) {
	var str = str.replace(/(^\s*)|(\s*$)/g, "");
	return str;
}

function rtrim(in_str)
{
    var r_str = in_str;
    while(in_str.substring(r_str.length-1,r_str.length) == ' ')
        r_str = r_str.substring(0, r_str.length-1);
    return r_str;
}

function str_replace(in_str,str1,str2) {
	var r_str = "/"+str1+"/gi" ;
    return in_str.replace(eval(r_str),str2);
}


/*
' ------------------------------------------------------------------
' Function    :
' Description : 날짜/시간 관련 함수
' Argument    :
' Return      :
' ------------------------------------------------------------------
*/
function getCurDate()
{

	var ret_str = "";

	var vDate = new Date();
	var yyyy = vDate.getFullYear();
	var mm = vDate.getMonth() + 1;
	var dd = vDate.getDate();
	ret_str = yyyy + "-" + lpad(mm,2,"0") + "-" + lpad(dd,2,"0");

	return ret_str;

}

function getCurDatebyFormat(format)
{
	var ret_str = "";

	var vDate = new Date();
	var yyyy = vDate.getFullYear();
	var mm = vDate.getMonth() + 1;
	var dd = vDate.getDate();


	switch(format.toUpperCase())
	{
		case "YYYY":
			ret_str = yyyy;
			break;
		case "MM":
			ret_str = lpad(mm,2,"0");
			break;
		case "DD":
			ret_str = lpad(dd,2,"0");
			break;
		case "YYYYMM":
			ret_str = yyyy + lpad(mm,2,"0");
			break;
		case "YYYY/MM":
			ret_str = yyyy + "/" + lpad(mm,2,"0");
			break;
		case "YYYYMMDD":
			ret_str = yyyy + lpad(mm,2,"0") + lpad(dd,2,"0");
			break;
		case "YYYY/MM/DD":
			ret_str = yyyy + "/" + lpad(mm,2,"0") + "/" + lpad(dd,2,"0");
			break;
		case "YYYY-MM-DD":
			ret_str = yyyy + "-" + lpad(mm,2,"0") + "-" + lpad(dd,2,"0");
			break;
		case "YYYY.MM.DD":
			ret_str = yyyy + "." + lpad(mm,2,"0") + "." + lpad(dd,2,"0");
			break;
		default:
			ret_str = yyyy + "-" + lpad(mm,2,"0") + "-" + lpad(dd,2,"0");
			break;

	}

	return ret_str;

}

function getDate(yyyy,mm,dd)
{
	var ret_str = "";

	var vDate = new Date(parseInt(yyyy), parseInt(mm)-1, parseInt(dd));
	var yyyy = vDate.getFullYear();
	var mm = vDate.getMonth() + 1;
	var dd = vDate.getDate();

	ret_str = yyyy + "-" + lpad(mm,2,"0") + "-" + lpad(dd,2,"0");

	return ret_str;

}

function addMonth(currentDate,month,pattren) {
	var Ty,Tm,Td;
	currentDate = (currentDate.replace("/","-")).replace("/","-");
	var arr_cd = currentDate.split('-');
	var TDate  = new Date(arr_cd[0],arr_cd[1]-1,arr_cd[2]);

	Ty=TDate.getFullYear()+"";
	Tm=TDate.getMonth()+1+month+"";
	if((TDate.getMonth()+1+month) < 0){
		Ty = (TDate.getFullYear()-1)+"";
		Tm = (12+(TDate.getMonth()+1+month))+"";
	}
	Td=TDate.getDate()+"";
	if(Tm.length<2) Tm="0"+Tm;
	if(Td.length<2) Td="0"+Td;
	return Ty + pattren + Tm + pattren + Td;
}

function addDate(currentDate,day,pattren) {
	var days = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

	currentDate = remove_str(currentDate,"-");
	currentDate = (currentDate.replace("/","")).replace("/","");
	Year  = eval(currentDate.substring(0,4));
	Month = eval(currentDate.substring(4,6));
	Day   = eval(currentDate.substring(6)) + eval(day);

	if(Month == 2){
		if ((((eval(Year) % 4) == 0) && (((eval(Year) % 100) != 0) || ((eval(Year % 400) == 0))) ||
			((eval(Year) % 1000) == 0))) {
			days[1]=29;
		}
	}

	if(Day > days[Month - 1]) {
		Day = Day - (days[Month - 1]);
		Month = Month + 1;

		if(Month > 12) {
			Month = 1;
			Year = Year + 1;
		}
	}

	varYear = Year;
	varMonth = Month;
	varDay = Day;

	if(Month < 10) {
		varMonth = "0" + Month;
	}

	if(Day < 10) {
		varDay = "0" + Day;
	}

	return varYear + pattren + varMonth + pattren + varDay;
}


function getAddDay(day, addend) {
    var tdate = day.getDate() + addend;
    var nextDay = new Date(day.getFullYear(), day.getMonth(), tdate);
    return nextDay;
}

function getAddDateStr(yyyy,mm,dd, cnt)
{
	var vDate = new Date(yyyy*1, mm*1-1, dd*1);

	vDate = getAddDay(vDate, cnt);

	var yyyy = vDate.getFullYear();
	var mm = vDate.getMonth() + 1;
	var dd = vDate.getDate();


	return yyyy + "-" + lpad(mm,2,"0") + "-" + lpad(dd,2,"0");

}

function getDateDiff(st_date, end_date)
{
	var temp_st = new Date(st_date.substring(0,4), st_date.substring(4,6), st_date.substring(6,8));
	var temp_end = new Date(end_date.substring(0,4), end_date.substring(4,6), end_date.substring(6,8));

	var result = (temp_st.getTime() - temp_end.getTime()) / (1000*60*60*24);

	return result;
}


//재외국인 번호 체크
function check_fgnno(fgnno) {
	var sum=0;
	var odd=0;

	buf = new Array(13);

	for(i=0; i<13; i++) {
		buf[i]=parseInt(fgnno.charAt(i));
	}

	odd = buf[7]*10 + buf[8];

	if(odd%2 != 0) { return false; }

	if( (buf[11]!=6) && (buf[11]!=7) && (buf[11]!=8) && (buf[11]!=9) ) {
		return false;
	}

	multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
	for(i=0, sum=0; i<12; i++) {
		sum += (buf[i] *= multipliers[i]);
	}
	sum = 11 - (sum%11);
	if(sum >= 10) {
		sum -= 10;
	}
	sum += 2;

	if(sum >= 10) { sum -= 10; }
	if(sum != buf[12]) { return false; }

	return true;
}

//주민번호 체크
function check_juminno(juminno) {
	if(juminno=="" || juminno==null || juminno.length!=13) {
		alert("주민등록번호를 적어주세요.");
		return false;
	}
	var jumin1 = juminno.substr(0,6);
	var jumin2 = juminno.substr(6,7);
	var yy = jumin1.substr(0,2); // 년도
	var mm = jumin1.substr(2,2); // 월
	var dd = jumin1.substr(4,2); // 일
	var genda = jumin2.substr(0,1); // 성별
	var msg, ss, cc;

	// 숫자가 아닌 것을 입력한 경우
	if (!isNumeric(jumin1)) {
		alert("주민등록번호 앞자리를 숫자로 입력하세요.");
		return false;
	}
	// 길이가 6이 아닌 경우
	if (jumin1.length != 6) {
		alert("주민등록번호 앞자리를 다시 입력하세요.");
		return false;
	}
	// 첫번째 자료에서 연월일(YYMMDD) 형식 중 기본 구성 검사
	if (yy < "00" || yy > "99" ||
		mm < "01" || mm > "12" ||
		dd < "01" || dd > "31") {
		alert("주민등록번호 앞자리를 다시 입력하세요.");
		return false;
	}
	// 숫자가 아닌 것을 입력한 경우
	if (!isNumeric(jumin2)) {
		alert("주민등록번호 뒷자리를 숫자로 입력하세요.");
		return false;
	}
	// 길이가 7이 아닌 경우
	if (jumin2.length != 7) {
		alert("주민등록번호 뒷자리를 다시 입력하세요.");
		return false;
	}
	// 성별부분이 1 ~ 4 가 아닌 경우
	if (genda < "1" || genda > "4") {
		alert("주민등록번호 뒷자리를 다시 입력하세요.");
		return false;
	}
	// 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
	cc = (genda == "1" || genda == "2") ? "19" : "20";
	// 첫번째 자료에서 연월일(YYMMDD) 형식 중 날짜 형식 검사
	if (isYYYYMMDD(parseInt(cc+yy), parseInt(mm), parseInt(dd)) == false) {
		alert("주민등록번호 앞자리를 다시 입력하세요.");
		return false;
	}
	// Check Digit 검사
	if (!isSSN(jumin1, jumin2)) {
		alert("입력한 주민등록번호를 확인한 후, 다시 입력하세요.");
		return false;
	}

	return true;
}

//사업자등록번호 체크
function check_busino(vencod) {
	var sum = 0;
	var getlist =new Array(10);
	var chkvalue =new Array("1","3","7","1","3","7","1","3","5");

	for(var i=0; i<10; i++) {
		getlist[i] = vencod.substring(i, i+1);
	}
	for(var i=0; i<9; i++) {
		sum += getlist[i]*chkvalue[i];
	}
	sum = sum + parseInt((getlist[8]*5)/10);
	sidliy = sum % 10;
	sidchk = 0;

	if(sidliy != 0) {
		sidchk = 10 - sidliy;
	}else {
		sidchk = 0;
	}

	if(sidchk != getlist[9]) { return false; }

	return true;
}

function isYYYYMMDD(y, m, d) {
	switch (m) {
		case 2: // 2월의 경우
			if (d > 29) return false;
			if (d == 29) {
				// 2월 29의 경우 당해가 윤년인지를 확인
				if ((y % 4 != 0) || (y % 100 == 0) && (y % 400 != 0))
				return false;
			}
			break;
		case 4: // 작은 달의 경우
		case 6:
		case 9:
		case 11:
			if (d == 31) return false;
	}

	// 큰 달의 경우
	return true;
}

function isNumeric(s) {
	for (i=0; i<s.length; i++) {
		c = s.substr(i, 1);
		if (c < "0" || c > "9") return false;
	}
	return true;
}

function isLeapYear(y) {
	if (y < 100)
		y = y + 1900;
	if ( (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0) ) {
		return true;
	} else {
		return false;
	}
}

function getNumberOfDate(yy, mm) {
	month = new Array(29,31,28,31,30,31,30,31,31,30,31,30,31);
	if (mm == 2 && isLeapYear(yy)) mm = 0;

	return month[mm];
}

function isSSN(s1, s2) {
	n = 2;
	sum = 0;

	for (i=0; i<s1.length; i++)
		sum += parseInt(s1.substr(i, 1)) * n++;

	for (i=0; i<s2.length-1; i++) {
		sum += parseInt(s2.substr(i, 1)) * n++;

		if (n == 10) n = 2;
	}

	c = 11 - sum % 11;

	if (c == 11) c = 1;
	if (c == 10) c = 0;

	if (c != parseInt(s2.substr(6, 1))) return false;
	else return true;
}
