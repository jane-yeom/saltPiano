<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

function formCheck() {
	if ($("#id").val().trim() == '') {
		alert('아이디를 입력하세요');
		$("#id").focus();
		return false;
	}
	var con = true;
	if ($("#id").val().length >= 3) { // 아이디 값이 3자 이상인 경우
		$.ajax({
			url:'/piano/user/isDuplicateId.do',
			data:{id:$("#id").val()},
			type:'HTML',
			method:'GET',
			cache:false,
			async:false,
			success:function(data) {
				//console.log(data);
				if (data == 'true') {
					alert('아이디가 중복되었습니다.');
					$("#id").focus();
					con = false;
				}
			}
		});
	} else {
		alert('아이디는 3자 이상 입력해 주세요');
		$("#id").focus();
		return false;
	}
	if (con == false) return false;
	if ($("#pwd").val().trim() == '') {
		alert('비밀번호를 입력하세요');
		$("#pwd").focus();
		return false;
	}
	if ($("#name").val().trim() == '') {
		alert('이름을 입력하세요');
		$("#name").focus();
		return false;
	}
	// 폼에 있는 모든 데이터를 한번에 읽어오는 방법
	var data = $("#frm").serialize();
	//console.log(data);
	$.ajax({
		url:'/piano/user/insert.do',
		data:data,
		type:'HTML',
		method:'POST',
		cache:false,
		success:function(data) {
			//console.log(data);
			if (data == 'true') {
				alert("정상적으로 등록되었습니다.");
				// 모든 입력란을 초기화
				//$("input[type='text'], input[type='password']").val("");
				//$("#frm")[0].reset();
				location.href="index.do";
			} else {
				alert("등록 실패");
			}
		}
	});
}
// 아이디에 keyup이벤트를 걸어서 ajax로 중복여부 체크
$(function() {
	$("#submitBtn").click(function() {
		formCheck();
	});
	$("#id").keyup(function() {
		//console.log($(this).val());
		//console.log($(this).val().length);
		if ($(this).val().length >= 3) { // 아이디 값이 3자 이상인 경우
			$.ajax({
				url:'/piano/user/isDuplicateId.do',
				data:{id:$(this).val()},
				type:'HTML',
				method:'GET',
				cache:false,
				success:function(data) {
					//console.log(data);
					if (data == 'true') {
						$("#idMsg").text('아이디 중복');
					} else {
						$("#idMsg").text('아이디 사용 가능');
					}
				}
			});
		} else {
			$("#idMsg").text('아이디를 입력하세요');
		}
	});
});
</script>
</head>
<body>
<form action="insert.do" method="post" id="frm" onsubmit="return false;">
<table border="1">
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name="id" id="id"><br>
			<span id="idMsg">아이디를 입력하세요</span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd" id="pwd"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="user_name" id="name"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"></td>
	</tr>
	<tr>
		<td>연락처</td>
		<td><input type="text" name="tel"></td>
	</tr>
</table>
<input type="submit" value="등록" id="submitBtn">
</form>
</body>
</html>