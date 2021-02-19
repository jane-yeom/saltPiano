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
<title>SHEET</title>
<%@ include file="/WEB-INF/view/include/headHtml.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>
    
     <div class="sub">
		<div class="size">
			<h3 class="sub_title">인기 악보</h3>
			<div class="bbs">
				<div class="gallery">
					<ul>
						
						<li>
							<a href="#;">
							<dl>
								<dt class="img">
										<img src="/saltpiano/upload/movie/image1.JPG" alt=""/>
								</dt>
								<dd class="date">${vo.level }</dd>
								
								<dd class="title">
									${vo.title }
								</dd>
								<dd class="btnArea">
									<input type="button" class="btn" value="자세히" onclick="location.href='detail.do?no=${vo.no}';"/>
									<input type="button" class="btn" value="다운로드" onclick="/saltpiano/common/download.jsp?path=/upload/&r_file=${vo.filename}" target="_blank">${vo.filename_org}
								</dd>
							</dl>
							</a>
						
						</li>							
					</ul>
				</div>
			
				<div class="pagenate clear">
					<ul class='paging'> <li><a href='javascript:;' class='current'>1</a></li> </ul> 
				</div>
			</div>
		</div>
	</div>
    
    <%@ include file="/WEB-INF/view/include/footer.jsp" %>
     
</body>
</html>