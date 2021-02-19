<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/user/js/common.js"></script>
<script>
function formCheck() {
	if ($("#title").val().trim() == '') {
		alert('제목을 입력하세요');
		$("#title").focus();
		return false;
	}
	if ($("#description").val().trim() == '') {
		alert('내용을 입력하세요');
		$("#description").focus();
		return false;
	}
	$("#frm").submit();
}

</script>
</head>
<body>
<form action="insert.do" method="post" enctype="multipart/form-data" id="frm" onsubmit="return formCheck()">
<input type="hidden" name="user_no" value="${authUser.no }">
<table border="1" style="width:800px">
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="title" id="title">
		</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>
			<input type="text" name="user_name" id="user_name">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="description" id="description" style="width:100%"></textarea></td>
	</tr>
</table>
<input type="submit" value="등록">
</form>
</body>
</html>