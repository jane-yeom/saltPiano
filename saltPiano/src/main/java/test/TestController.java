package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.TestVo;

public class TestController {
	
	@RequestMapping("/piano/test.do")
	public String test(@RequestParam(required=false) String test) {
	    return "test";
	}
	// 1. requestparam 
	@RequestMapping("/piano/submit1.do")
	public String submit1(@RequestParam(required=false) String submit) {
	    return "test/submit1";
	}
	// 2. request 객체
	@RequestMapping("/piano/submit2.do")
	public String submit2(HttpServletRequest req, TestVo vo) {
		String submit2 = req.getParameter("submit2");
		
		return "/piano/submit2";
	}
	// 3. 커맨드 객체
	@RequestMapping("/piano/submit3.do")
	public String submit3(HttpServletRequest req, TestVo vo) {
		
		
		return "/piano/submit3";
	}
}
