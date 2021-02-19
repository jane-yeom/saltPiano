<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<table border="1">
	<tr>
		<td>회원번호</td>
		<td>${vo.no }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${vo.user_name }</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>${vo.id }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${vo.email }</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td>${vo.tel }</td>
	</tr>
	<tr>
		<td>가입일</td>
		<td>${vo.regdate }</td>
	</tr>
</table>
<input type="button" value="수정" onclick='location.href="edit.do?no=${vo.no}";'>
<input type="button" value="삭제" onclick="del();">
<script>
function del() {
	/*
	if (confirm("정말 삭제하시겠습니까?")) {
		location.href="delete.do?no=${vo.no}";
	}
	*/
	if (confirm('정말 삭제하시겠습니까?')) {
		$.ajax({
			url:'/user/user/delete.do',
			data:{no:${vo.no}},
			type:'HTML',
			method:'GET',
			cache:false,
			async:false,
			success:function(res) {
				//console.log(data);
				if (res == 'true') {
					alert('정상적으로 삭제되었습니다.');
					location.href='/user/user/index.do';
				} else {
					alert('삭제 오류');
				}
			}
		});
	}
}
</script>
</body>
</html>