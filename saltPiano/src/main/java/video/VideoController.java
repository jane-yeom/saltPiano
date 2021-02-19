package video;

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
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private CommentService cService;
	
	@RequestMapping("/video/index.do")
	public String video(HttpServletRequest req, VideoVo vo) {
		//db에서 조회 req에 담기
		int[] rowPageCount = videoService.getRowPageCount(vo);
		List<VideoVo> list = videoService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("totalCount", rowPageCount[0]); // 총갯수
		req.setAttribute("list", list);
		// /video/index.do?reqPage=2 -> VideoVo에 reqPage 필드에 바인딩 (커맨드객체)
		// /video/index.do
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
	
		return "/video/index";
	}
	
	@RequestMapping("/video/detail.do")
	public String detail(HttpServletRequest req, VideoVo vo) {
		
		videoService.update_hits(vo.getNo());
		VideoVo uv = videoService.selectOne(vo);
		
		List<CommentVo> clist = cService.getList(uv.getNo());
		
		req.setAttribute("vo", uv);
		req.setAttribute("clist", clist);
		
		// jsp 포워딩
		return "video/detail";
	}
	
	@GetMapping("/video/write.do")
	public String write() {
		return "video/write";
	}
	
	
	@RequestMapping("/video/edit.do")
	public String edit(HttpServletRequest req, VideoVo vo) {
		
		VideoVo uv = videoService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "video/edit";
	}
	
	@PostMapping("/video/update.do")
	public void update(VideoVo vo, HttpServletResponse res, HttpServletRequest req, MultipartFile file) throws IOException {
		//res.getWriter().print(boardService.update(vo));
		// 파일을 저장
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (videoService.update(vo)) {
			out.print("alert('정상적으로 수정되었습니다.');");
			out.print("location.href='/piano/video/detail.do?no="+vo.getNo()+"';");
		} else {
			out.print("alert('수정실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@RequestMapping("/video/insert.do")
	public void insert(VideoVo vo, HttpServletRequest req, HttpServletResponse res, MultipartFile file) throws Exception {
		// 등록처리
		//res.getWriter().print(boardService.insert(vo));
		
		// 파일을 저장
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<script>");
		if (videoService.insert(vo)) {
			out.print("alert('정상적으로 등록되었습니다.');");
			out.print("location.href='/piano/video/index.do';");
		} else {
			out.print("alert('등록실패.');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.flush();
	}
	
	@GetMapping("/video/delete.do")
	public void delete(VideoVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(videoService.delete(vo));
	}
	
	
	
}	
	
