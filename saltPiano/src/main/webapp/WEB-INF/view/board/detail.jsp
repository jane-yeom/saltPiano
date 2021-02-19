<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
<meta name="format-detection" content="telephone=no, address=no, email=no">
<meta name="keywords" content="">
<meta name="description" content="">
<title>NOTICE</title>
<%@ include file="/WEB-INF/view/include/headHtml.jsp" %>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>
    <div class="sub">
		<div class="size">
			<h3 class="sub_title">공지사항</h3>
			<div class="bbs">
				<div class="view">
					<div class="title">
						<dl>
							<dt>${vo.title} </dt>
							<dd class="date">작성일 : ${vo.regdate } </dd>
						</dl>
					</div>
					<div class="cont"><p><img title="물고기.png" src="http://localhost:8080/saltpiano/upload/editor/1547434662941_477.png" alt=""><br style="clear:both;">${vo.contents}</p> </div>
					<div class="btnSet clear">
						<div class="fl_l"><a href="index.do?" class="btn">목록으로</a></div>
					</div>
			
				</div>
			</div>
		</div>
    </div>

    <%@ include file="/WEB-INF/view/include/footer.jsp" %>
<input type="button" value="수정" onclick='location.href="edit.do?no=${vo.no}";'>
<input type="button" value="삭제" onclick="del();">
<input type="button" value="목록" onclick='location.href="index.do";'>
<script>
function del() {
	/*
	if (confirm("정말 삭제하시겠습니까?")) {
		location.href="delete.do?no=${vo.no}";
	}
	*/
	if (confirm('정말 삭제하시겠습니까?')) {
		$.ajax({
			url:'delete.do',
			data:{no:${vo.no}},
			type:'HTML',
			method:'GET',
			cache:false,
			async:false,
			success:function(res) {
				//console.log(data);
				if (res == 'true') {
					alert('정상적으로 삭제되었습니다.');
					location.href='index.do';
				} else {
					alert('삭제 오류');
				}
			}
		});
	}
}
</script>
<form action="commentInsert.do" method=post">
<input type="hidden" name="board_no" value="${vo.no }">
<input type="hidden" name="user_no" value="${authUser.no }">
<table border="1">
	<tr>
		<td>
			<textarea name="description"></textarea>
		</td>
	</tr>
</table>
<input type="submit" value="댓글저장">
</form>
<table border="1">
<c:if test="${empty clist }">
	<tr>
		<td>등록된 댓글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="vo" items="${clist}">
	<tr>
		<td>
		
			${vo.content }
			<c:if test="${vo.user_no==authUser.no}">
			<input type="button" value="삭제" onclick="goDel(${vo.no});">
			</c:if>
			&nbsp;&nbsp;${vo.regdate }
		</td>
	</tr>

</c:forEach>
</table>
<script>
function goDel(no) {
	if (confirm('삭제하시겠습니까?')){
		location.href='/user/board/commentDelete.do?no='+no+'&board_no=${vo.no}';
	}
}
</script>
</body>
</html>

