<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes"> 
<meta name="format-detection" content="telephone=no, address=no, email=no">
<meta name="keywords" content="">
<meta name="description" content="">
<title>MOVIE</title>
<%@ include file="/WEB-INF/view/include/headHtml.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>
    
     <div class="sub">
		<div class="size">
			<h3 class="sub_title">곡 정보</h3>
			<div class="bbs">
				<div class="movie">
					<div class="info">
						<div class="movie_img">
							<img src="/saltpiano/upload/movie/image1.JPG" />
						</div>
						<div class="movie_info">
							<dl>
								<dt class="title">
									정승환 - Day & Night [스타트업OST]
									
								</dt>
								<dt class="info_detail">
									<strong>난이도</strong>&nbsp; ★★★☆☆
								</dt>
								<dt class="info_detail">
									<strong>악보 종류</strong>&nbsp; 피아노 2단 / Eb Major
								</dt>
								<dt class="info_detail">
									<strong>가격</strong>&nbsp; ${vo.price }
								</dt>
								<dt class="info_detail">
									<strong>장르</strong>&nbsp; ${vo.genre }
								</dt>
								<dt class="info_detail">
									<strong>Released On</strong>&nbsp; 2020-10-18
								</dt>
								<dt class="synop">
									<strong>곡 설명</strong> <br/>
									<div class="synop_contents">
										<br><p>tvN 토일드라마 "스타트업" OST PART. 2 ‘정승환'의 ‘Day & Night' 발매!!</p><p>해와 달 그리고 낮과 밤처럼 로맨틱하면서도 먹먹한 감정이 교차되어 내재된 곡으로, &nbsp;</p><p>당신의 지친 하루를 따뜻함으로 채워주고 싶은 바람을 담은 곡이다.&nbsp;</p><p>곡 전반에 잔잔히 깔린 피아노 선율과 스트링 퀄텟의 조화가 따스한 분위기를 이끄는 한편, &nbsp;</p><p>섬세한 표현력이 돋보이는 정승환의 감미로운 허밍이 귀를 사로잡는다.&nbsp;</p>
									</div>
								</dt>
								<dt class="reser_btn">
									<input type="button" class="btn" value="구매" onclick="showDialogue('29');"/>
								</dt>
							</dl>
						</div>
					</div>
					<h5 class="movie_title"><strong>스틸컷</strong></h5>
					<div class="stillcut_area">
							<div class="swiper_stillcut swiper-container">
				                <div class="swiper-wrapper">
				                
				                    <div class="swiper-slide" >
				                    	<img class="stillcut_img" src="/saltpiano/upload/movie/김선호.png"/>
				                    </div>
				                    
				                    
				                    <div class="swiper-slide">
				                    	<img class="stillcut_img" src="/saltpiano/upload/movie/수지.png"/>
				                    </div>
				                    
				                    
				                    <div class="swiper-slide">
				                    	<img class="stillcut_img" src="/saltpiano/upload/movie/stillCut_1548057324405._V1_SX1777_CR0,0,1777,736_AL_.jpg"/>
				                    </div>
				                    
				                    
				                    <div class="swiper-slide">
				                    	<img class="stillcut_img" src="/saltpiano/upload/movie/stillCut_1548057324427._V1_SX1777_CR0,0,1777,736_AL_.jpg"/>
				                    </div>
				                </div>
				    
				                <div class="swiper-pagination"></div>
				                <div class="swiper-button-prev"></div>
				                <div class="swiper-button-next"></div>
				            </div>
					</div>
					<h5 class="movie_title"><strong>트레일러</strong></h5>
					<div class="trailler_area">
						<ul>
							<li>
								<div class="video_container">
									<iframe src="https://www.youtube.com/embed/2dEeiY_w3xE" frameborder="0"  wmode="Opaque" width="100%" height="315"></iframe>
								</div>
							</li>
						
						 
							<li>
								<div class="video_container">
									<iframe src="https://www.youtube.com/embed/NbLJBWY6UCA" frameborder="0"  wmode="Opaque" width="100%" height="315"></iframe>
								</div>
							</li>
							
						
							<li>
								<div class="video_container">
									<iframe src="https://www.youtube.com/embed/PMw64QaHASQ" frameborder="0"  wmode="Opaque" width="100%" height="315"></iframe>
								</div>
							</li>
						</ul>
					</div>
					
					<form method="post" name="frm" id="frm" action="" >
					<input type="hidden" name="movie_pk" value="0"/>
					<h5 class="movie_title">리뷰</h5>
					<div class="review_area">
						<div class="review_write">
							<div class="input">
								<div class="rate">
									<select name="score">
									<option value='1'selected>5.0</option><option value='2'>4.5</option><option value='3'>4.0</option><option value='4'>3.5</option><option value='5'>3.0</option><option value='6'>2.5</option><option value='7'>2.0</option><option value='8'>1.5</option><option value='9'>1.0</option><option value='10'>0.5</option>
									</select>
								</div>
								
								<div class="textarea">
									<textarea name="contents" id="contents"></textarea>
								</div>
								<div class="btn_area">
									<input type="button" class="btn" onclick="save();" value="등록">
								</div>
							</div>
						</div>
					
					</form>
						
<script>
$(function() {
	getList();
});

function getList() {
	$.ajax({
		url : "reviewList.do",
		dataType : "html",
		data : {"smovie_pk" : 0},  
		async : true,
		success : function(data) {
			$(".review_list").html(data);
		},
		error : function(msg) {
			console.log(msg);
		}
	});
}

function goDelete(no) {
	if (confirm("삭제하시겠습니까?")) {
		$.ajax({
			url : "/movie/review/delete.do",
			data : {"no" : no},
			async : true,
			success : function(data) {
				var d = data.trim();
				if (d>0) {
					getList();
					alert("정상적으로 삭제되었습니다.");
				} else {
					alert("삭제실패");
				}
			},
			error : function(msg) {
				console.log(msg);
			}
		});
	}
}

function save() {
	//alert("로그인후 작성가능합니다.");
	var data = $("#frm").serialize();
	$.ajax({
		type : "POST",
		url :"/movie/review/insert.do",
		async : true,
		data : data,
		success : function (data) {
			var r = data.trim();
			if (r>0) {
				getList();
				alert("정상적으로 등록되었습니다.");
				$("#contents").val("");
			} else {
				alert("등록실패");
			}
		}
	})
}
</script>
						<div class="review_list">
							
						</div>
					</div>
				</div>
			</div>
		</div>
    
    <%@ include file="/WEB-INF/view/include/footer.jsp" %>

<script>
$(function(){
	var swiper = new Swiper('.swiper-container', {
		loop: true,
		autoplay: {
		    delay: 5000,
		  },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        pagination: {
            el: '.swiper-pagination',
       	},
	});
});
</script>
</body>
</html>