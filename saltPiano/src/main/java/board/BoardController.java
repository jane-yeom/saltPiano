package board;

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
import board.BoardService;
import board.BoardVo;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/board/index.do")
	public String notice(HttpServletRequest req, BoardVo vo) {
		//db에서 조회 req에 담기
		int[] rowPageCount = boardService.getRowPageCount(vo);
		List<BoardVo> list = boardService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("list", list);
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
		
		
		return "/board/index";
	}
	
	@RequestMapping("/board/detail.do")
	public String detail(HttpServletRequest req, BoardVo vo) {
		
		boardService.update_hits(vo.getNo());
		BoardVo uv = boardService.selectOne(vo);
		
		List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "board/detail";
	}
	
	@GetMapping("/board/write.do")
	public String write() {
		return "board/write";
	}
	

	@RequestMapping("/board/edit.do")
	public String edit(HttpServletRequest req, BoardVo vo) {
		
		BoardVo uv = boardService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public void update(BoardVo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (boardService.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/piano/board/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/board/insert.do")
	public void insert(BoardVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
				
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (boardService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/piano/board/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/board/delete.do")
	public void delete(BoardVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(boardService.delete(vo));
	}	
}
