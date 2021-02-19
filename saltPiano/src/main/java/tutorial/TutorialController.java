package tutorial;

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

@Controller
public class TutorialController {
	
	@Autowired
	private TutorialService tutorialService;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/tutorial/index.do")
	public String video(HttpServletRequest req, TutorialVo vo) {
		//db에서 조회 req에 담기
		int[] rowPageCount = tutorialService.getRowPageCount(vo);
		List<TutorialVo> list = tutorialService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("list", list);
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
	
		return "/tutorial/index";
	}
	
	@RequestMapping("/tutorial/detail.do")
	public String detail(HttpServletRequest req, TutorialVo vo) {
		
		tutorialService.update_hits(vo.getNo());
		TutorialVo uv = tutorialService.selectOne(vo);
		
		List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "tutorial/detail";
	}
	
	@GetMapping("/tutorial/write.do")
	public String write() {
		return "tutorial/write";
	}
	
	
	@RequestMapping("/tutorial/edit.do")
	public String edit(HttpServletRequest req, TutorialVo vo) {
		
		TutorialVo uv = tutorialService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "tutorial/edit";
	}
	
	@PostMapping("/tutorial/update.do")
	public void update(TutorialVo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
		//res.getWriter().print(boardService.update(vo));
		// 파일을 저장
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (tutorialService.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/piano/tutorial/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/tutorial/insert.do")
	public void insert(TutorialVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		// 등록처리
		//res.getWriter().print(boardService.insert(vo));
		
		// 파일을 저장
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (tutorialService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/piano/tutorial/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/tutorial/delete.do")
	public void delete(TutorialVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(tutorialService.delete(vo));
	}
	
	
	
}	
	
