<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript" src="/saltpiano/js/swiper.min.js"></script>
<script>

$(function() {
	$(".depth1 > li").mouseover(function(){
		$(this).find(".depth2").stop().slideDown(300);
	});
	$(".depth1 > li").mouseleave(function(){
		$(this).find(".depth2").stop().slideUp(300);
	});
	$("#login_click").click(function() {
		$(".login_info").toggle();
		useremail_chk();
	});
	$(".login_info > .top_area > img").click(function() {
		$(".login_info").hide();
	});
	
});

//로그인, 이메일 체크
function loginCheck(){
	if ( $("#loginEmail").val().length < 1 ) {
		alert("이메일을 입력해주세요.");
		$("#loginEmail").focus();
		return false;
	}
	if ( $("#loginPw").val().length < 1 ) {
		alert("비밀번호를 입력해주세요.");
		$("#loginPw").focus();
		return false;
	}
	var f = document.loginFrm;
	if (f.reg.checked) {
	   document.cookie = "cookie_useremail=" + $("#loginEmail").val() + ";path=/;expires=Sat, 31 Dec 2050 23:59:59 GMT;";
	} else {
	   var now = new Date();	
	   document.cookie = "cookie_useremail=null;path=/;expires="+now;
	}
	return true;
}

function useremail_chk() {
	var f=document.loginFrm;
	var useremail = CookieVal("cookie_useremail");
	
	if (useremail=="null"){	
		f.loginEmail.focus();
		f.loginEmail.value="";
	} else {
		f.loginPw.focus();
		f.loginEmail.value=useremail;
		f.reg.checked=true;
		$("#loginPw").focus();
	}
}

function CookieVal(cookieName) {
	thisCookie = document.cookie.split("; ");
	for (var i = 0; i < thisCookie.length;i++) {
		if (cookieName == thisCookie[i].split("=")[0]) {
			return thisCookie[i].split("=")[1];
		}
	}
	return "null" ;
}	



</script>
	<div id="header">
        <div class="head_top">
            <div class="size">
                <div class="header_call">
                    <div class="txt">
                        <a href=""></a>
                    </div>
                </div>
                <h1 class="logo"><a href="/saltpiano/index.do"><img src="/saltpiano/img/salt.png"/></a></h1>
                <div class="util clear">
                    <a href="#;" id="login_click">Login</a>
                    <a href="/saltpiano/user/join.do">Join us</a>
                    <!-- 
                	<a href="/logout.do">로그아웃</a>
                    <a href="/mypage/index.do">마이페이지</a>
                     -->
                </div>
                <form action="/login.do" id="loginFrm" name="loginFrm" method="post" onsubmit="return loginCheck();">
                <div class="login_info">
                	<div class="top_area"><img src="/img/btn_del.gif"/></div>
                	<div class="title_area"><span>Salt Piano 로그인</span></div>
                	<div class="middle_area">
                		<div class="input_area">
                			<input type="text" id="loginEmail" name="email" value="" placeholder="이메일"/>
                			<input type="password" id="loginPw" name="pw" value="" placeholder="비밀번호"/>
                		</div>
                		<div class="login_btn">
                			<input type="submit" value="로그인"/>
                		</div>
                	</div>
                	<div class="bottom_area">
                		<input type="checkbox" id="reg" name="reg"/><label for="reg">이메일 저장</label>&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;
                		<a href="/saltpiano/member/emailsearch.do">이메일찾기</a>&nbsp;/&nbsp;<a href="/saltpiano/member/pwsearch.do">비밀번호 찾기</a>
                	</div>
                </div>
                </form>
            </div>
        </div>
        <div class="head_bot">
            <div class="size">
                <div class="gnb">
                    <ul class="depth1 clear">
                        <li>
                            <a href="/saltpiano/sheet/index.do" >SHEET</a>
                            <ul class="depth2">
                                <li><a href="/saltpiano/sheet/index.do" >인기 악보</a></li>
                                <li><a href="/saltpiano/sheet2/index.do" >무료 악보</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="/saltpiano/video/index.do" >VIDEO</a>
                            <ul class="depth2">
							<li><a href="/saltpiano/video/index.do" >동영상</a></li>
							<li><a href="/saltpiano/tutorial/index.do" >Tutorial</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="/saltpiano/board/index.do" >Notice</a>
                            <ul class="depth2">
                                <li><a href="/saltpiano/board/index.do">공지사항</a></li>
                                <li><a href="/saltpiano/board/qna/index.do" >Q&A</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
