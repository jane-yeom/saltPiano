<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>VIDEO</title>
<%@ include file="/WEB-INF/view/include/headHtml.jsp" %>
<script>
function goSearch() {
	$("#searchForm").submit();
}
</script>
</head>
<body>
    <%@ include file="/WEB-INF/view/include/header.jsp" %>

    <div class="sub">
		<div class="size">
			<h3 class="sub_title">공지사항</h3>

			<div class="bbs">
				<table class="list">
				<p><span><strong>총 ${totalCount }개</strong>  |  ${reqPage }/${totalPage }페이지</span></p>
					<caption>목록</caption>
					<colgroup>
						<col width="80px" />
						<col width="*" />
						<col width="100px" />
						<col width="100px" />
						<col width="80px" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
							
						</tr>
					</thead>
					<tbody>
					<!-- 
									<tr>
										<td class="first" colspan="8">등록된 글이 없습니다.</td>
									</tr>
					 -->
					 <c:forEach var="vo" items="${list}">
						<tr style='cursor:pointer;' onclick="location.href='detail.do?no=${vo.no }'">
							<td>${vo.no }</td>
							<td class="txt_l"><a href="detail.do?no=${vo.no }">${vo.title } </a></td>
							<td class="writer">${vo.user_name }</td>
							<td class="date">${vo.regdate }</td>
							<td class="hit" >${vo.hits }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			
				<div class="pagenate clear">
					<c:if test="${startPage > 5}">
					<ul class='paging'> <li><a href="/saltpiano/video/index.do?reqPage=${startPage-1 }&searchWord=${param.searchWord}" class='prepage'>이전</a></li> </ul> 
					</c:if>
					<c:forEach var="rp" begin="${startPage }" end="${endPage }">
					<ul class='paging'> <li><a href="/piano/video/index.do?reqPage=${rp }&searchWord=${param.searchWord}" class='current'>${rp }</a></li> </ul> 
					</c:forEach>
					<c:if test="${totalPage > endPage }">
					<ul class='paging'> <li><a href="/piano/video/index.do?reqPage=${endPage+1 }&searchWord=${param.searchWord}" class='afpage'>다음</a></li> </ul> 
					</c:if>
				</div>
				<!-- 페이지처리 -->
				<div class="bbsSearch">
					<form method="get" name="searchForm" id="searchForm" action="index.do">
						<span class="srchSelect">
							<select id="stype" name="stype" class="dSelect" title="검색분류 선택">
								<option value="all"  >전체</option>
								<option value="title" >제목</option>
								<option value="contents" >내용</option>
							</select>
						</span>
						<span class="searchWord">
							<input type="text" id="sval" name="sval" value="${param.searchWord }"  title="검색어 입력" onKeypress="">
							<input type="button" id="" value="검색" title="검색" onclick="goSearch();">					
							
						</span>
					</form>
				</div>
			</div>
		</div>
    </div>

    <%@ include file="/WEB-INF/view/include/footer.jsp" %>

</body>
</html>
