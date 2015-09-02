/* @Author lee jun */
function rolleventer(eventName,timeSetting){
	$(function(){
		var eventEl = "#"+eventName;
		eventEl = $(eventEl);
		var eventElbox = eventEl.find(".banner");
		var controlEl = eventEl.find(".event-control");
		var rollNumer = 1;
		var rollLength = eventElbox.find(".ban").length;
		var motionSpeed = 300;
		var timeSet = timeSetting;

		eventElbox.find(".choice").each(function(i){
			$(this).css({ left : 125+(18*(i+1)) });
		});

		eventElbox.find(".ban").eq(0).css({ left:0,"z-index":"2" });

		function rollevent(){
			if(rollNumer == rollLength){
				rollNumer = 1;
			} else {
				rollNumer++;
			}

			playRoll();
		}

		function playRoll(){
			eventElbox.find(".choice").attr("src","/templet/T001/images/main/icon_selected.png");
			eventElbox.find(".choice").eq(rollNumer-1).attr("src","/templet/T001/images/main/icon_selected_on.png");

			eventElbox.find(".ban").css({"z-index":"1"});
			eventElbox.find(".ban").eq(rollNumer-1).css({"z-index":"2"});
			eventElbox.find(".ban").eq(rollNumer-1).animate({left:0},motionSpeed,resetRoll);

			function resetRoll(){
				eventElbox.find(".ban").not(eventElbox.find(".ban").eq(rollNumer-1)).css({left:320});
			}
		}

		var timeroll = setInterval(rollevent, timeSet);
		var playcheck = 1;

		controlEl.bind({
			mousedown : function(){
				if(playcheck == 0){
					$(this).find("img").attr("src","/templet/T001/images/main/icon_stop.png");
					timeroll = setInterval(rollevent, timeSet);
					playcheck = 1;
				} else {
					$(this).find("img").attr("src","/templet/T001/images/main/icon_play.png");
					clearInterval(timeroll);
					playcheck = 0;
				}
			}, keydown : function(e){
				if(e.keyCode == 32){
					if(playcheck == 0){
					$(this).find("img").attr("src","/templet/T001/images/main/icon_stop.png");
						timeroll = setInterval(rollevent, timeSet);
						playcheck = 1;
					} else {
						$(this).find("img").attr("src","/templet/T001/images/main/icon_play.png");
						clearInterval(timeroll);
						playcheck = 0;
					}
				}
			}
		});

		eventElbox.find(".choice-link").bind({
			mousedown : function(){
				clearInterval(timeroll);
				if(playcheck == 1){
					timeroll = setInterval(rollevent, timeSet);
				}
				rollNumer = $(this).parent().index()+1;
				playRoll();
			}, focusin : function(){
				clearInterval(timeroll);
				if(playcheck == 1){
					timeroll = setInterval(rollevent, timeSet);
				}
				rollNumer = $(this).parent().index()+1;
				playRoll();
			}
		});


	});

}