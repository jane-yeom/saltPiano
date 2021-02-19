<%@ page language="java" contentType="application; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.io.*" %>
<%
/*
3개의 값 (파라미터로 받음)
- 경로, 실제파일명, 원본파일명
*/
String path = request.getParameter("path"); // 경로
String r_file = request.getParameter("r_file"); // 실제파일명
String o_file = java.net.URLEncoder.encode(request.getParameter("o_file"),"UTF-8"); // 원본파일명
//String o_file = request.getParameter("o_file"); // 원본파일명

// 웹상의 실제경로
path = request.getRealPath(path+r_file);
try {
	// File객체를 생성
	File f = new File(path);
	//f.delete();
	// /user/common/download.jsp?path=/upload/&r_file=${vo.filename}&o_file=${vo.filename_org}
	// 입력객체(서버의 디스크에 있는 파일)
	BufferedInputStream fin = new BufferedInputStream(new FileInputStream(f));
	// 출력객체(브라우저)
	BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
	System.out.println(o_file);
	System.out.println(o_file.replaceAll("\\+","%20"));
	//초기화
	response.reset();
	// 브라우저 헤더 설정(ie 이전버젼은 다르게 처리)
	response.setHeader("Content-Type","application/octet-stream");
	response.setHeader("Content-Disposition","attachment;filename="+o_file.replaceAll("\\+","%20")+";");
	response.setHeader("Content-Transfer-Encoding","binary");
	response.setHeader("Content-Length", f.length()+"");
	
	// 스트림 출력
	byte b[] = new byte[1024];
	for (int i=0; (i = fin.read(b)) != -1;) {
		// 쓰기
		fout.write(b, 0, i);
		fout.flush();
	}
	fin.close();
	fout.close();
	
} catch (Exception e) {
	System.out.println(e.toString());
}
%>