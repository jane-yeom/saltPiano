package admin.board;

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

import board.BoardService;
import board.BoardVo;
import sheet.CommentService;
import sheet.CommentVo;

@Controller
public class AdminBoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/admin/board/notice/index.do")
	public String index(HttpServletRequest req, BoardVo vo) {
		// 서비스(로직) 처리(호출)
		int[] rowPageCount = boardService.getRowPageCount(vo);
		List<BoardVo> list = boardService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("totalCount", rowPageCount[0]); // 총갯수
		req.setAttribute("list", list);
		// /board/index.do?reqPage=2 -> BoardVo에 reqPage 필드에 바인딩 (커맨드객체)
		// /board/index.do
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
		
		// jsp 포워딩
		return "admin/board/notice/index";
	}
	
	@RequestMapping("/admin/board/notice/detail.do")
	public String detail(HttpServletRequest req, BoardVo vo) {
		
		BoardVo uv = boardService.selectOne(vo);
		List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "admin/board/notice/detail";
	}
	
	@GetMapping("/admin/board/notice/write.do")
	public String write() {
		return "admin/board/notice/write";
	}
	
	@RequestMapping("/admin/board/notice/insert.do")
	public void insert(BoardVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		// 등록처리
		//res.getWriter().print(boardService.insert(vo));
		
		// 파일을 저장
		if (!file.isEmpty()) { // 사용자가 첨부한 파일이 있으면
			try {
				String ext = "";
				if (file.getOriginalFilename().indexOf(".") > -1 ) { // 파일명에 . 이 포함되어있는 경우
					ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					System.out.println(ext);
				}
				String filename = new Date().getTime()+ext;
				
				
				// request.getRealPath() -> 실제 경로를 리턴
				String path = req.getRealPath("/upload/");
				System.out.println(path);
				//path = "D:\\AI\\workspace\\user\\src\\main\\webapp\\upload\\";
				file.transferTo(new File(path+filename));
				// 파일명을 vo에 저장
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (boardService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/saltpiano/admin/board/notice/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/admin/board/notice/edit.do")
	public String edit(HttpServletRequest req, BoardVo vo) {
		
		BoardVo uv = boardService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "admin/board/notice/edit";
	}
	
	@PostMapping("/admin/board/notice/update.do")
	public void update(BoardVo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
		//res.getWriter().print(boardService.update(vo));
		// 파일을 저장
		if (!file.isEmpty()) { // 사용자가 첨부한 파일이 있으면
			try {
				String ext = "";
				if (file.getOriginalFilename().indexOf(".") > -1 ) { // 파일명에 . 이 포함되어있는 경우
					ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					System.out.println(ext);
				}
				String filename = new Date().getTime()+ext;
				
				
				// request.getRealPath() -> 실제 경로를 리턴
				String path = req.getRealPath("/upload/");
				System.out.println(path);
				//path = "D:\\AI\\workspace\\user\\src\\main\\webapp\\upload\\";
				file.transferTo(new File(path+filename));
				// 파일명을 vo에 저장
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (boardService.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/saltpiano/admin/board/notice/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/admin/board/notice/delete.do")
	public void delete(BoardVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(boardService.delete(vo));
	}
	
	@RequestMapping("/admin/board/notice/commentInsert.do")
	public void commentInsert(CommentVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (cService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/saltpiano/admin/board/notice/detail.do?no="+vo.getBoard_no()+"';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/admin/board/notice/commentDelete.do")
	public void commentDelete(CommentVo vo, HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (cService.delete(vo.getNo())) {
			out.print("alert('정상적으로 삭제되었습니다.');");
			out.print("location.href='/saltpiano/admin/board/notice/detail.do?no="+vo.getBoard_no()+"';");
		} else {
			out.print("alert('삭제실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/admin/board/notice/groupDelete.do")
	public void groupDelete(BoardVo vo, HttpServletResponse res, HttpServletRequest req) throws IOException {
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
			if (boardService.delete(vo)) delCount++;
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (delCount > 0) {
			out.print("alert('총 "+nos.length+"건중에 "+delCount+"건이 삭제되었습니다.');");
			out.print("location.href='/saltpiano/admin/board/notice/index.do';");
		} else {
			out.print("alert('삭제실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
//	@RequestMapping("/admin/board/groupDelete2.do")
//	public void groupDelete2(BoardVo vo, HttpServletResponse res, HttpServletRequest req) throws IOException {
//		/*
//		 컨트롤러에서 파라미터를 받는 3가지 방법
//		 1. request 객체
//		 2. @RequestParam
//		 3. 커맨드 객체 (스프링이 커맨드객체의 필드명과 클라이언트에서 전송되어 오는 파라미터명이 같으면 저장, setXXX())
//		 */
//		int delCount = 0;
//		for (int i=0; i<vo.getNos().length; i++) {
//			vo.setNo(Integer.parseInt(vo.getNos()[i]));
//			if (boardService.delete(vo)) delCount++;
//		}
//		
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out = res.getWriter();
//		out.print("<script>");
//		if (delCount > 0) {
//			out.print("alert('총 "+vo.getNos().length+"건중에 "+delCount+"건이 삭제되었습니다.');");
//			out.print("location.href='/user/admin/board/index.do';");
//		} else {
//			out.print("alert('삭제실패.');");
//			out.print("history.back();");
//		}
//		out.print("</script>");
//		out.flush();
//	}
	
	
}
