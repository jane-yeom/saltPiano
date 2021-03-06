<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<div id="footer">
		<div class="size">
			<div class="inner">					
				<div class="address">
					<div class="f_logo">
						<a href="#;"><img class="footer_logo" src="/saltpiano/img/salt.png"></a>
					</div>
					<ul>
						<li>
							<span class="c_name">솔트피아노</span>
						</li>
						<li>
							<span>경기도 고양시 일산동구 더조은컴퓨터학원</span>
							<span class="width100">Tel  :  031-111-2222</span>
						</li>
						<li>
							<span>대표이사 : 서민구</span>
							<span>사업자등록번호 : 123-45-67890</span>
						</li>
					</ul>
					<div class="copy">
						Copyright 2019 movie. All Rights Reserved
					</div>
					<div class="util">
						<a href="#;">개인정보취급방침</a>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
function showDialogue(movie_no) {
	//alert("회원가입후 예매해 주세요");

	$.ajax({
		url: "/ticket/ticket_form.do",
		data : {"no": movie_no},
		success : function(data) {
			$("#ticket_dialogue").html(data);
			cal();
			$("#tk_count").change(function() {
				cal();
			});
			$("input[name='format']").click(function(){
				cal();
			})
		}
	})
	
	var maskHeight = $(document).height(); 
	var maskWidth = $(window).width();

	$('#mask').css({'width':maskWidth,'height':maskHeight}); 
	$('#mask').fadeTo("fast",0.8);

	var t_dialog = $("#ticket_dialogue");
	t_dialog.css({
		'position': 'fixed',
		'left': '50%',
		'top': '50%'
		});
	t_dialog.css({
		'margin-left': -t_dialog.outerWidth() / 2 + 'px',
		'margin-top': -t_dialog.outerHeight() / 2 + 'px'
	});
	t_dialog.show();
}

function hideDialogue() {
	$("#ticket_dialogue").hide();
	$("#mask").hide();
}

function cal() {
	var tkcount = $("#tk_count").val();
	var format = $("input[name='format']:checked").val();
	//console.log(tkcount);
	//console.log(format);
	
	var formatPrice = 0;
	if (format == "1") {
		formatPrice = 10000;
	} else if (format == "2") {
		formatPrice = 12000;
	} else if (format == "3") {
		formatPrice = 14000;
	} else if (format == "4") {
		formatPrice = 16000;
	}
	
	var totalPrice = formatPrice * tkcount;
	console.log(totalPrice);
	$(".totalPrice").html(numberWithCommas(totalPrice)+"원");
	$("#price").val(totalPrice);
}
</script>
<div id="ticket_dialogue" class="dialogue_wr popupContent">
</div>
<div id="mask"></div>