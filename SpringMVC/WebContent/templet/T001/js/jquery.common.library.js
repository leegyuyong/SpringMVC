/* @Author lee jun */

function onpop(urls,w,h){
	//popup setting application
	sw = (screen.width);
	sh = (screen.height);
	size = "width="+w+",height="+h+",scrollbars=yes";
	var pop_view = window.open(urls,'',size);
	pop_view.focus();
}

$(function(){
	//	nav open
	$(".nav").find("dt").bind({
		focusin : function(){
			$(".extend").slideDown(300);
		}, mouseenter : function(){
			$(".extend").slideDown(300);
		}
	});

	//nav focus out
	$(".close-menu").focusout(function(){
		$(".extend").slideUp(700,"easeOutExpo");
	});

	$(".accessibility").find("a").bind({
		focusin : function() {
			$(this).css({top:"500px"});
		}, focusout : function() {
			$(this).removeAttr("style");
		}
	});

	//top notice for cookie
	cookiedata = document.cookie;
	if(cookiedata.indexOf("popupdiv=done") < 0){
		$("#top-notice").show();
	} else {
		$("#top-notice").hide();
	}
});

function closeNav(){	//nav close
	$(function(){
		$(".extend").slideUp(700,"easeOutExpo");
	});
}

function closeAlert(){ //top notice close
	$(function(){
		$("#top-notice").slideUp(1);
	});
}

function setCookie(name,value,expiredays) { //top notice cookie
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

function closeWin(){
	$(function(){
		setCookie( "popupdiv", "done" , 1 );
		$("#top-notice").slideUp(300);
	});
}

function corpRoll(eventName, time){
	var eventEl = "#"+eventName;
	var eventElLink = $(eventEl).find("a");
	var eventElLength = eventElLink.length;
	var eventTime = time;
	var hideNum = 0;
	var motionSpeed = 300;

	function rollStart(){
		if(hideNum == eventElLength){
			hideNum = 1;
		} else {
			hideNum++;
		}

		eventElLink.eq(hideNum-1).hide(motionSpeed,deleteEl);
		function deleteEl(){
			$(".corp-list").append(eventElLink.eq(hideNum-1));
		}
		eventElLink.eq(hideNum-1).show(motionSpeed);
	}

	var corpTimeSet = setInterval(rollStart,eventTime);

	eventElLink.bind({
		focusin : function(){
			clearInterval(corpTimeSet);
		}, focusout : function(){
			corpTimeSet = setInterval(rollStart,eventTime);
		}
	});
}

/* layer pop function */
function outerScreen(returnPosition, filename, wsize, hsize) {
	$(function(){
		var hiddenSet = $(".accessibility, .location, .section, .footer, #top-notice");
		//var hiddenSet = $("*");
		hiddenSet.attr("aria-hidden","true");

		//$("html,body").css({"overflow":"hidden"});
		$(".transparent-content").before(' <div class="transparent-window"></div> ');

		var appendClass = $(".transparent-window");
		var dataClass = $(".transparent-content");

		appendClass.css({width:"100%", height:$(document).height()});
		appendClass.fadeTo(100,0.5);

		dataClass.fadeIn(200);
		//dataClass.load(filename);
		dataClass.append($("#"+filename));
		$("#"+filename).show();

		dataClass.css({width:wsize, height:hsize});
		dataClass.focus();

		//var returnSet = $("head, body");
		//returnSet.children().attr("aria-hidden","true");

		//dataClass.attr("aria-hidden","false");
		//dataClass.children().attr("aria-hidden","false");

		var wSizeFix = ($(window).width()-dataClass.outerWidth())/2;
		var hSizeFix = ($(window).height()-dataClass.outerHeight())/2;

		dataClass.css({left:wSizeFix, top:hSizeFix});

		$(".layer-close").click(function(){	//닫기 버튼 클릭시 본래 상태 복귀
			//$("html,body").removeAttr("style");
			hiddenSet.attr("aria-hidden","false");
			appendClass.remove();
			dataClass.hide();
			$("#"+returnPosition).focus();
		});

		$(".return").bind({
			focusin : function(){
				dataClass.focus();
			}
		});
	});
}