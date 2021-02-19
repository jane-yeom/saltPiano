<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/view/admin/include/headHtml.jsp" %>
<script>
var oEditors = [];
$(function() {
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "contents", // textarea ID
		sSkinURI: "<%=request.getContextPath()%>/smarteditor/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			fOnBeforeUnload : function(){
				
			}
		}, //boolean
		fOnAppLoad : function(){
			//예제 코드
			//oEditors.getById["contents"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator: "createSEditor2"
	});
});

function save() {
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
	var html = oEditors.getById["contents"].getIR(); // 값 가져오기
	if (html == '') {
		alert('내용을 입력하세요');
	}
	oEditors.getById['contents'].exec("UPDATE_CONTENTS_FIELD",[]); // 에디터있던 내용을 textarea에 담기
	$("#frm").submit();
}


</script>
</head>
<body> 
<div id="wrap">
	<!-- canvas -->
	<div id="canvas">
		<!-- S T A R T :: headerArea-->
		<%@ include file="/WEB-INF/view/admin/include/top.jsp" %>
		<!-- E N D :: headerArea--> 
		<!-- S T A R T :: containerArea-->
		<div id="container">
			<div id="content">
				<div class="con_tit">
					<h2>회원등록 - [쓰기]</h2>
				</div>
				<!-- //con_tit -->
				<div class="con">
					<!-- 내용 : s -->
					<div id="bbs">
						<div id="bread">
							<form method="post" name="frm" id="frm" action="insert.do" enctype="multipart/form-data">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="관리자 관리 기본내용입니다.">
								<colgroup>
									<col width="10%" />
									<col width="10%" />
									<col width="10%" />
									<col width="10%" />
									<col width="10%" />
									<col width="10%" />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label for="">*아이디</label></th>
										<td colspan="10">
											<input type="text" id="id" name="id" class="w50" title="아이디를 입력해주세요" />	
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">*비밀번호</label></th>
										<td colspan="10">
											<input type="text" id="password" name="password" class="w50" title="비밀번호를 입력해주세요" />	
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">*이름</label></th>
										<td colspan="10">
											<input type="text" id="name" name="name" class="w50" title="이름을 입력해주세요" />	
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">*이메일</label></th>
										<td colspan="5">
											<input type="text" id="email" name="email" class="w50" title="이메일을 입력해주세요" />	
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="">*연락처</label></th>
										<td colspan="5">
											<input type="text" id="tel" name="tel" class="w50" title="연락처를 입력해주세요" />	
										</td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" name="cmd" value="write" />
							</form>
							<div class="btn">
								<div class="btnLeft">
									<a class="btns" href="index.do"><strong>목록</strong></a>
								</div>
								<div class="btnRight">
									<a class="btns" style="cursor:pointer;" onclick="save()"><strong>저장</strong></a>
								</div>
							</div>
							<!--//btn-->
						</div>
						<!-- //bread -->
					</div>
					<!-- //bbs --> 
					<!-- 내용 : e -->
				</div>
				<!--//con -->
			</div>
			<!--//content -->
		</div>
		<!--//container --> 
		<!-- E N D :: containerArea-->
	</div>
	<!--//canvas -->
</div>
<!--//wrap -->

</body>
</html>