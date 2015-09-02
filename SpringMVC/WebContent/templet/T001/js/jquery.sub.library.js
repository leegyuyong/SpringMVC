/* @Author lee jun */
$(function(){
	// tabmenu
	$(".tabmenu").find("button").click(function(){
		$(".view").not($(".view").eq($(this).index())).hide();
		$(".view").eq($(this).index()).show();
		$(".tabmenu").find("button").not($(this)).removeClass();
		$(this).addClass("on");
	});

	// step-tab
	$(".step-tab").find("button").click(function(){
		$(this).parent().parent().find(".step-view").not($(".step-tab").parent().parent().find(".step-view").eq($(this).index())).hide();
		$(this).parent().parent().find(".step-view").eq($(this).index()).show();
		$(this).parent().find("button").not($(this)).removeClass();
		$(this).addClass("on");
	});

	//mail for domain fix
	$(".mail").change(function(){
		if(!$(this).val()){
			$(this).parent().find(".mail-domain").val("");
			$(this).parent().find(".mail-domain").focus();
		} else {
			$(this).parent().find(".mail-domain").val($(this).val());
		}
	});

	//course checkbox for checked
	$(".totalcheck").click(function(){
		if($(this).is(":checked")){
			$(".course-list tbody").find("input[type='checkbox']").each(function(){
				$(this).attr("checked",true);
				/* 반전시
				if($(this).is(":checked")){
					$(this).attr("checked",false);
				} else {
					$(this).attr("checked",true);
				}
				*/
			});
		} else {
			$(".course-list tbody").find("input[type='checkbox']").each(function(){
				$(this).attr("checked",false);
				/* 반전시
				if($(this).is(":checked")){
					$(this).attr("checked",false);
				} else {
					$(this).attr("checked",true);
				}
				*/
			});
		}
	});

	//terms for agree
	$(".terms-box").find("label").click(function(){
		var thisCode = $(this).attr("code");

		if(!thisCode){
			$(this).attr("code","1");
			$(this).css({background:"url(/templet/T001/css/images/section/agree_check.png) no-repeat"});
		} else {
			$(this).removeAttr("code");
			$(this).css({background:"url(/templet/T001/css/images/section/agree.png) no-repeat"});
		}
	});

	//login for focus event
	$(".id, .password").bind({
		focus : function(){
			if(!$(this).val()){
				$(this).css({background:"white", border:"2px solid #3d8df0"});
			}
		}, blur : function(){
			if(!$(this).val()){
				$(this).removeAttr("style");
			}
		}
	});

	//id & pass search
	$(".selected-type").find("input[type='radio']").click(function(){
		var thisIndex = $(this).parent().index();

		$(".insert-search-field").hide();
		$(".insert-search-field").eq(thisIndex).show();
	});

	//other-link
	$(".other-link").change(function(){
		window.open = $(this).val();
	});

	$(".faq-list").find("dt").click(function(){
		if($(this).attr("code")) {
			$(this).removeAttr("code");
			$(this).parent().find("dd").slideUp(150);
			$(this).removeAttr("style");
		} else {
			$(this).attr("code","1");
			$(this).parent().find("dd").slideDown(150);
			$(this).css({
				"font-family":"nanumbold",
				"color":"#3d8df0",
				"background":"url(/templet/T001/css/images/section/icon_faq_up.png) no-repeat right 50%"
			});
		}
	});

	//download list view
	$("a.clip").click(function(){
		var thisList = $(this).parent().find("ul");

		$(".clip-view").each(function(){
			$(this).find("ul").hide();
			$(this).find(".clip").removeAttr("style");
		});
		$(this).css({background:"url(/templet/T001/css/images/section/icon_clip_on.png) no-repeat"});

		thisList.show(150);
		thisList.focus();
	});
	$("a.close").click(function(){
		$(this).parents("ul").hide(150);
		$(this).parents("div.clip-view").find("a.clip").removeAttr("style");
		$(this).parents("div.clip-view").find("a.clip").focus();
	});

	//append for file upload copy
	$(".add").click(function(){
		$(".fileform").eq(0).clone().appendTo($(".filelist"));
	});


	//book for tab
	$(".book-detail").eq(0).show();

	$(".book-case").find("button").bind({
		click : function(){
			$(".book-case").find("button").removeClass();
			$(this).addClass("on");

			$(".book-detail").hide();
			$(".book-detail").eq($(this).index()).show();
			$(".book-detail").eq($(this).index()).focus();
		}
	});

	//course for category & focus
	$(".mode-category").find("button").click(function(){
		$(".mode-category").find("button").removeClass();
		$(this).addClass("on");

		var upperclass = $(this).val();

		$(".category-layer").hide();
		if (upperclass != 'ALL'){
			$("#upperclass"+upperclass).show();
			$("#upperclass"+upperclass).focus();
		}
		//$(".category-layer").eq($(this).index()).show();
		//$(".category-layer").eq($(this).index()).focus();

		$.thisCategoryFocus = $(this).index();
	});

	$(".cate-close").click(function(){
		$(".category-layer").slideUp(200);
		$(".mode-category").find("button").eq($.thisCategoryFocus).focus();
	});

	$(".first-category").find("a").click(function(){
		$(".category-layer").slideUp(200);
		$(".focusing").focus();
	});

	//checkbox for all check/cancel
	$("#list-check").click(function(){
		var thisBody = $(this).parents(".learn-list").find("tbody");
		var thisCheckPut = thisBody.find("input[type='checkbox']");

		if(!$(this).is(":checked")){
			thisCheckPut.attr("checked",false);
		} else {
			thisCheckPut.attr("checked",true);
		}
	});

	//learning window for case
	$(".learn-content").eq(0).show();

	$(".learn-case").find("button").click(function(){
		$(".learn-case").find("button").removeClass();
		$(this).addClass("on");

		$(".learn-content").hide();
		$(".learn-content").eq($(this).index()).show();
	});

	//scrap list for modify action
	$(".scrap-modify").click(function(){
		if($(this).attr("code")){
			$(this).removeAttr("code");
			$(this).parents("table").find("a.scrap-modify").removeClass();
			$(this).parents("table").find("tr").removeAttr("bgcolor");
			$(this).addClass("scrap-modify");
			$(this).parents("table").find(".scrap-hide").hide();
		} else {
			$(this).parents("table").find("a.scrap-modify").removeAttr("code");
			$(this).attr("code","1");
			$(this).parents("table").find("a.scrap-modify").removeClass();
			$(this).parents("table").find("tr").removeAttr("bgcolor");
			$(this).parents("tr").attr("bgcolor","#fafafa");
			$(this).addClass("scrap-modify");
			$(this).addClass("on");
			$(this).parents("table").find(".scrap-hide").hide();
			$(this).parent().find(".scrap-hide").show();
		}
	});

	//support
	$(".support-list").find("a").click(function(){
		$(".outbox").hide();
		$(this).parent().find(".outbox").show();
		$(".support-list").find("a").removeAttr("style");
		$(this).css({"font-weight":"bold"});
	});

	$(".outboxclose").click(function(){
		$(".support-list").find("a").removeAttr("style");
		$(this).parents(".outbox").hide();
	});

});

//ilsunni 추가 지우지 말것
function faqAddFunction(){
	$(".faq-list").find("dt").click(function(){
		if($(this).attr("code")) {
			$(this).removeAttr("code");
			$(this).parent().find("dd").slideUp(150);
			$(this).removeAttr("style");
		} else {
			$(this).attr("code","1");
			$(this).parent().find("dd").slideDown(150);
			$(this).css({
				"font-family":"nanumbold",
				"color":"#3d8df0",
				"background":"url(/templet/T001/css/images/section/icon_faq_up.png) no-repeat right 50%"
			});
		}
	});
}