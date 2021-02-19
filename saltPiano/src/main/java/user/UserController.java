package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
//	@GetMapping("/")
//	public String index(HttpSession sess) {
//		UserVo uv = new UserVo();
//		uv.setNo(104);
//		uv.setUser_name("test30");
//		uv.setId("test30");
//		sess.setAttribute("authUser", uv);
//		return "redirect:/user/index.do";
//	}
	
	@RequestMapping("/user/index.do")
	public String index(HttpServletRequest req, UserVo vo) {
		// 서비스(로직) 처리(호출)
		int[] rowPageCount = userService.getRowPageCount(vo);
		List<UserVo> list = userService.getList(vo);
		
		// 값 저장
		// totalPage, list, reqPage
		req.setAttribute("totalPage", rowPageCount[1]);
		req.setAttribute("startPage", rowPageCount[2]); // 시작페이지
		req.setAttribute("endPage", rowPageCount[3]); // 마지막페이지
		req.setAttribute("list", list);
		// /user/index.do?reqPage=2 -> UserVo에 reqPage 필드에 바인딩 (커맨드객체)
		// /user/index.do
		req.setAttribute("reqPage", vo.getReqPage());
		req.setAttribute("vo", vo);
		
		// jsp 포워딩
		return "saltpiano/user/index";
	}
	
	@RequestMapping("/user/detail.do")
	public String detail(HttpServletRequest req, UserVo vo) {
		
		UserVo uv = userService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "user/detail";
	}
	
	@GetMapping("/user/write.do")
	/*
	public String write(HttpSession sess) {
		//
		UserVo uv = (UserVo)sess.getAttribute("authUser");
		String page = "";
		if (uv == null) {
			page = "redirect:index.do";
		} else {
			page = "user/write";
		}
		return page;
	}
	*/
	public String write() {
		System.out.println("여기는 write.do");
		return "user/write";
	}
	
	@RequestMapping("/user/insert.do")
	public void insert(UserVo vo, HttpServletResponse res) throws Exception {
		// 등록처리
		res.getWriter().print(userService.insert(vo));
	}
	
	//@RequestMapping("/user/isDuplicateId.do")
	//@GetMapping("/user/isDuplicateId.do")
	@RequestMapping(value="/user/isDuplicateId.do", method=RequestMethod.GET)
	public void isDuplicateId(HttpServletRequest req, HttpServletResponse res, @RequestParam("id") String userid) throws IOException{
		boolean r = userService.isDuplicateId(userid);
		res.getWriter().print(r);
	}
	
	@RequestMapping("/user/edit.do")
	public String edit(HttpServletRequest req, UserVo vo) {
		
		UserVo uv = userService.selectOne(vo);
		
		req.setAttribute("vo", uv);
		
		// jsp 포워딩
		return "user/edit";
	}
	
	@PostMapping("/user/update.do")
	public void update(UserVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(userService.update(vo));
	}
	
	@GetMapping("/user/delete.do")
	public void delete(UserVo vo, HttpServletResponse res) throws IOException {
		res.getWriter().print(userService.delete(vo));
	}
	
	// 로그인폼
	@GetMapping("/user/login.do")
	public String login() {
		return "user/login";
	}
	
	// 로그인처리
	@PostMapping("/user/login.do")
	public String loginProcess(UserVo vo, HttpServletRequest req) {
		/*
		 세션(session) : 브라우저 단위로 저장되는 저장소		 
		 
		 DB에서 아이디 비밀번호로 조회한 결과를 가져오고
		 결과가 있으면(로그인 성공)
		 - 세션에 결과객체를 저장
		 - 목록페이지로 이동
		 결과값 없으면(로그인 실패)
		 - 메시지 처리
		 - 로그인폼으로 이동
		 */
		// 사용자가 입력한 아이디와 비밀번호로 DB에서 조회한 결과
		UserVo uv = userService.login(vo);
		// 결과 확인
		if (uv != null) { // 로그인 성공
			// 세션객체 가져오기
			HttpSession sess = req.getSession();
			// 세션객체에 로그인정보 저장
			sess.setAttribute("authUser", uv);
			
			// 위 코드와 동일하게
			//req.getSession().setAttribute("authUser", uv);
			String url = "/saltpiano/user/board/index.do";
			if (req.getParameter("url") != null && !"".equals(req.getParameter("url"))) {
				url = req.getParameter("url"); // /user/board/index.do
			}
			return "redirect: "+url;
			
		} else { // 로그인 실패
			req.setAttribute("msg", "아이디와 비밀번호가 올바르지 않습니다.");
			return "redirect:/saltpiano/user/login.do";
		}
	}
	
	@RequestMapping("/user/logout.do")
	public void logout(HttpServletResponse res, HttpSession sess) throws IOException {
		
		sess.invalidate(); // 세션초기화(모든 세션)
		// authUser만 세션 제거
		//sess.removeAttribute("authUser");
		
		res.setContentType("text/html; charset=utf-8"); // 한글처리
		PrintWriter out = res.getWriter();
		out.print("<script>");
		out.print("alert('로그아웃되었습니다.');");
		out.print("location.href='/saltpiano/user/index.do';");
		out.print("</script>");
		out.flush();
	}
	
	//회원가입 페이지 리턴
		@RequestMapping("/user/join.do")
		public String join(Model model, UserVo param) throws Exception {
			model.addAttribute("vo", param);
			
			return "user/join";
		}
	
	
	
	
	
	
	
	
	
}
