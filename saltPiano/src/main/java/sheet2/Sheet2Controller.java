package sheet2;

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
public class Sheet2Controller {
	
	@Autowired
	private Sheet2Service sheet2Service;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/sheet2/index.do")
	public String sheet(HttpServletRequest req, Sheet2Vo vo) {
		//db에서 조회 req에 담기
		int[] rowPageCount = sheet2Service.getRowPageCount(vo);
		List<Sheet2Vo> list = sheet2Service.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("list", list);
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
	
		return "/sheet2/index";
	}
	
	@RequestMapping("/sheet2/detail.do")
	public String detail(HttpServletRequest req, Sheet2Vo vo) {
		
		sheet2Service.update_hits(vo.getNo());
		Sheet2Vo uv = sheet2Service.selectOne(vo);
		
		//List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		//req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "sheet2/detail";
	}
	
	@GetMapping("/sheet2/write.do")
	public String write() {
		return "sheet2/write";
	}
	
	
	@RequestMapping("/sheet2/edit.do")
	public String edit(HttpServletRequest req, Sheet2Vo vo) {
		
		Sheet2Vo uv = sheet2Service.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "sheet2/edit";
	}
	
	@PostMapping("/sheet2/update.do")
	public void update(Sheet2Vo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
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
		if (sheet2Service.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/piano/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/sheet2/insert.do")
	public void insert(Sheet2Vo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
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
		if (sheet2Service.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/piano/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/sheet2/delete.do")
	public void delete(Sheet2Vo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(sheet2Service.delete(vo));
	}
	
}
