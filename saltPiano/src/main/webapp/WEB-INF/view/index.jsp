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
<title>Salt Piano</title>
<%@ include file="/WEB-INF/view/include/headHtml.jsp" %>
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
</head>
<body>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>
    
    <div class="main">
        <div class="visual animate">
            <div class="swiper1 swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide bg1">
                    </div>
                    <div class="swiper-slide bg2">
                    </div>
                    <div class="swiper-slide bg3">
                    </div>
                    <div class="swiper-slide bg4">
                    </div>
                </div>
    
                <div class="swiper-pagination"></div>
                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>
            </div>
        </div>
        <div class="movie">
        	<div class="poster">
        		<div class="tit"><h4>인기 악보</h4></div>
        		<img src="/saltpiano/img/daynnight.JPG">
        	</div>
        	<div class="bxoffice">
        		<ul>
        			<li><span class="rank">1</span><a href="">정승환 - Day & Night [스타트업 OST] </a></li>
        			<li><span class="rank">2</span><a href="">용주 - Sweet Home [스위트홈 OST]</a></li>
        			<li><span class="rank">3</span><a href="">테일즈위버 OST - Reminiscence</a></li>
        			<li><span class="rank">4</span><a href="">아이유 (IU) - Celebrity Piano Cover</a></li>
        			<li><span class="rank">5</span><a href="">가호 - Running [스타트업 OST]</a></li>
        			<li><span class="rank">6</span><a href="">백지영 - My Light [Run On OST] </a></li>
        			<li><span class="rank">7</span><a href="">다비치 - My Love [스타트업 OST]</a></li>
        			<li><span class="rank">8</span><a href="">The Whole Nine Yards - 냉정과 열정 사이 OST</a></li>
        			<li><span class="rank">9</span><a href="">김필 - 어느 날 우리 [스타트업 OST]</a></li>
        			<li><span class="rank">10</span><a href="">바이준 - 지금 아무도 사랑하지 않는다</a></li>
        		</ul>
        	</div>
        	<div class="youtube">
        		<div class="video_container">
					<iframe src="https://www.youtube.com/embed/PMw64QaHASQ" frameborder="0"  wmode="Opaque" width="100%" height="315"></iframe>
				</div>
        	</div>
        </div>
    </div>
    
    <%@ include file="/WEB-INF/view/include/footer.jsp" %>
     
</body>
</html>