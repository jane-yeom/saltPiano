package sheet;

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
import sheet.SheetService;
import sheet.SheetVo;


@Controller
public class SheetController {
	
	@Autowired
	private SheetService sheetService;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/sheet/index.do")
	public String sheet(HttpServletRequest req, SheetVo vo) {
		//db에서 조회 req에 담기
		int[] rowPageCount = sheetService.getRowPageCount(vo);
		List<SheetVo> list = sheetService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("list", list);
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
	
		return "/sheet/index";
	}
	
	@RequestMapping("/sheet/detail.do")
	public String detail(HttpServletRequest req, SheetVo vo) {
		
		sheetService.update_hits(vo.getNo());
		SheetVo uv = sheetService.selectOne(vo);
		
		//List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		//req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "sheet/detail";
	}
	
	@GetMapping("/sheet/write.do")
	public String write() {
		return "sheet/write";
	}
	
	@RequestMapping("/board/commentInsert.do")
	public void commentInsert(CommentVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		// 등록처리. 댓글 등록후 목록으로 돌아가는게 아니고 보던 상세페이지로 이동
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (cService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/piano/detail.do?no="+vo.getBoard_no()+"';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	 
	@GetMapping("/board/commentDelete.do")
	public void commentDelete(CommentVo vo, HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (cService.delete(vo.getNo())) {
			out.print("alert('정상적으로 삭제되었습니다.');");
			out.print("location.href='/piano/detail.do?no="+vo.getBoard_no()+"';");
		} else {
			out.print("alert('삭제실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest req, SheetVo vo) {
		
		SheetVo uv = sheetService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "sheet/edit";
	}
	
	@PostMapping("/update.do")
	public void update(SheetVo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
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
				vo.setFilename(filename);
				vo.setFilename_org(file.getOriginalFilename());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (sheetService.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/piano/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/insert.do")
	public void insert(SheetVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
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
				vo.setFilename(filename);
				vo.setFilename_org(file.getOriginalFilename());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (sheetService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/piano/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/delete.do")
	public void delete(SheetVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(sheetService.delete(vo));
	}
	
}
