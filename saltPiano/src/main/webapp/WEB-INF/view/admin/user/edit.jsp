<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
// submit(image) 버튼인 경우
// form에 onsubmit 속성 부여
$(function() {
	$("#submitBtn").click(function() {
		if ($("#pwd").val().trim() == '') {
			alert('비밀번호를 입력하세요');
			return false;
		}
		if ($("#name").val().trim() == '') {
			alert('이름을 입력하세요');
			return false;
		}
		var data = $("#frm").serialize();
		$.ajax({
			url:'/user/user/update.do',
			data:data,
			type:'HTML',
			method:'POST',
			cache:false,
			success:function(data) {
				//console.log(data);
				if (data == 'true') {
					alert("정상적으로 수정되었습니다.");
					// 모든 입력란을 초기화
					//$("input[type='text'], input[type='password']").val("");
					//$("#frm")[0].reset();
					location.href="index.do";
				} else {
					alert("등록 실패");
				}
			}
		});
	});
});
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function zip_api() {
        new daum.Postcode({
            oncomplete: function(data) {
            	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //document.getElementById("sample6_extraAddress").value = extraAddr;
                    addr += ' '+extraAddr;
                
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                //document.getElementById('zipcode').value = data.zonecode;
                $("#zipcode").val(data.zonecode);
                //document.getElementById("addr1").value = addr;
                $("#addr1").val(addr);
                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById("addr2").focus();
                $("#addr2").focus();
            }
        }).open();
    }
</script>
</head>
<body>
<form action="update.do" method="post" id="frm" onsubmit="return false;">
<input type="hidden" name="no" value="${vo.no }">
<table border="1">
	<tr>
		<td>아이디</td>
		<td>
			${vo.id }
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd" id="pwd" value=""></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="user_name" id="name" value="${vo.user_name }"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" value="${vo.email }"></td>
	</tr>
	<tr>
		<td>연락처</td>
		<td><input type="text" name="tel" value="${vo.tel }"></td>
	</tr>
</table>
<input type="submit" value="수정" id="submitBtn">
</form>
</body>
</html>