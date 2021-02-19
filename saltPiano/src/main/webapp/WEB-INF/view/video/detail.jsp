<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
<meta name="format-detection" content="telephone=no, address=no, email=no">
<meta name="keywords" content="">
<meta name="description" content="">
<title>VIDEO</title>
<%@ include file="/WEB-INF/view/include/headHtml.jsp" %>
</head>
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
<body>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>
    <div class="sub">
		<div class="size">
			<h3 class="sub_title">동영상</h3>
			<div class="bbs">
				<div class="view">
					<div class="title">
						<dl>
							<dt>${vo.title} </dt>
							<dd class="date">작성일 : ${vo.regdate } </dd>
						</dl>
					</div>
					<div class="cont"><p><img title="물고기.png" src="http://localhost:8080/saltpiano/upload/editor/1547434662941_477.png" alt=""><br style="clear:both;">${vo.contents}</p></div><br>
					<br>
					<c:if test="${!empty vo.url }">
						<iframe width="600" height="360" src="https://www.youtube.com/embed/${vo.url2 }" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
					</c:if>
					<div class="btnSet clear">
						<div class="fl_l"><a href="index.do" class="btn">목록</a></div>
					</div>
			
				</div>
			</div>
		</div>
    </div>

    <%@ include file="/WEB-INF/view/include/footer.jsp" %>

</body>
<script>
function goDel(no) {
	if (confirm('삭제하시겠습니까?')){
		location.href='/user/board/commentDelete.do?no='+no+'&board_no=${vo.no}';
	}
}
</script>
</html>
