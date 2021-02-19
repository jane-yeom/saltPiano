package admin.video;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import sheet.CommentService;
import sheet.CommentVo;
import tutorial.TutorialService;
import tutorial.TutorialVo;

@Controller
public class AdminTutorialController {

	@Autowired
	private TutorialService tutorialService;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/admin/tutorial/index.do")
	public String index(HttpServletRequest req, TutorialVo vo) {
		// 서비스(로직) 처리(호출)
		int[] rowPageCount = tutorialService.getRowPageCount(vo);
		List<TutorialVo> list = tutorialService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("totalCount", rowPageCount[0]); // 총갯수
		req.setAttribute("list", list);
		// /tutorial/index.do?reqPage=2 -> TutorialVo에 reqPage 필드에 바인딩 (커맨드객체)
		// /tutorial/index.do
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
		
		// jsp 포워딩
		return "admin/tutorial/index";
	}
	
	@RequestMapping("/admin/tutorial/detail.do")
	public String detail(HttpServletRequest req, TutorialVo vo) {
		
		TutorialVo uv = tutorialService.selectOne(vo);
		List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "admin/tutorial/detail";
	}
	
	@GetMapping("/admin/tutorial/write.do")
	public String write() {
		return "admin/tutorial/write";
	}
	
	@RequestMapping("/admin/tutorial/insert.do")
	public void insert(TutorialVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		// 등록처리
		//res.getWriter().print(tutorialService.insert(vo));
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (tutorialService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/saltpiano/admin/tutorial/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/admin/tutorial/edit.do")
	public String edit(HttpServletRequest req, TutorialVo vo) {
		
		TutorialVo uv = tutorialService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "admin/tutorial/edit";
	}
	
	@PostMapping("/admin/tutorial/update.do")
	public void update(TutorialVo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
		//res.getWriter().print(tutorialService.update(vo));
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (tutorialService.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/saltpiano/admin/tutorial/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/admin/tutorial/delete.do")
	public void delete(TutorialVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(tutorialService.delete(vo));
	}
	
	@RequestMapping("/admin/tutorial/commentInsert.do")
	public void commentInsert(CommentVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (cService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/saltpiano/admin/tutorial/detail.do?no="+vo.getBoard_no()+"';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/admin/tutorial/commentDelete.do")
	public void commentDelete(CommentVo vo, HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (cService.delete(vo.getNo())) {
			out.print("alert('정상적으로 삭제되었습니다.');");
			out.print("location.href='/saltpiano/admin/tutorial/detail.do?no="+vo.getBoard_no()+"';");
		} else {
			out.print("alert('삭제실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/admin/tutorial/groupDelete.do")
	public void groupDelete(TutorialVo vo, HttpServletResponse res, HttpServletRequest req) throws IOException {
		/*
		 컨트롤러에서 파라미터를 받는 3가지 방법
		 1. request 객체
		 2. @RequestParam
		 3. 커맨드 객체 (스프링이 커맨드객체의 필드명과 클라이언트에서 전송되어 오는 파라미터명이 같으면 저장, setXXX())
		 */
		String[] nos = req.getParameterValues("nos");
		int delCount = 0;
		for (int i=0; i<nos.length; i++) {
			vo.setNo(Integer.parseInt(nos[i]));
			if (tutorialService.delete(vo)) delCount++;
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (delCount > 0) {
			out.print("alert('총 "+nos.length+"건중에 "+delCount+"건이 삭제되었습니다.');");
			out.print("location.href='/saltpiano/admin/tutorial/index.do';");
		} else {
			out.print("alert('삭제실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
//	@RequestMapping("/admin/tutorial/groupDelete2.do")
//	public void groupDelete2(TutorialVo vo, HttpServletResponse res, HttpServletRequest req) throws IOException {
//		/*
//		 컨트롤러에서 파라미터를 받는 3가지 방법
//		 1. request 객체
//		 2. @RequestParam
//		 3. 커맨드 객체 (스프링이 커맨드객체의 필드명과 클라이언트에서 전송되어 오는 파라미터명이 같으면 저장, setXXX())
//		 */
//		int delCount = 0;
//		for (int i=0; i<vo.getNos().length; i++) {
//			vo.setNo(Integer.parseInt(vo.getNos()[i]));
//			if (tutorialService.delete(vo)) delCount++;
//		}
//		
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out = res.getWriter();
//		out.print("<script>");
//		if (delCount > 0) {
//			out.print("alert('총 "+vo.getNos().length+"건중에 "+delCount+"건이 삭제되었습니다.');");
//			out.print("location.href='/user/admin/tutorial/index.do';");
//		} else {
//			out.print("alert('삭제실패.');");
//			out.print("history.back();");
//		}
//		out.print("</script>");
//		out.flush();
//	}
	
	
}
