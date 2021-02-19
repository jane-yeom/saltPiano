<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${empty authUser}">
<!-- 현재 URL 가져오는 방법 request.getAttribute("javax.servlet.forward.request_uri") -->
<input type="button" value="로그인" onclick="location.href='/user/user/login.do?url=/board/index.do'">
</c:if>
<c:if test="${!empty authUser}">
<div id="header">
	<div class="header_inner">
		<h2>
			Salt Piano Music Sheet
		</h2>
		<div id="menu">
  			<ul class="menu">
  				<li><a href="/piano/index.do" class="parent"><span class='s1'>악보</span></a></li>
				<li><a href="/piano/video/index.do" class="parent"><span class='s2'>동영상</span></a></li>
				<li><a href="/piano/event/index.do" class="parent"><span class='s3'>이벤트</span></a>
				<li><a href="/piano/notice/index.do" class="parent"><span class='s4'>공지사항</span></a>
				<li><a href="/piano/board/index.do" class="parent"><span class='s4'>커뮤니티</span></a>
				</li>
		</div>
		<!--//gnb-->
	</div>
	<!-- //header_inner -->
</div>
${authUser.name }님 반갑습니다.
<input type="button" value="로그아웃" onclick="location.href='/user/user/logout.do'">
</c:if>
