package member;

import java.util.ArrayList;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import property.SiteProperty;

@Controller
public class UserController {

	@Autowired
	private MemberService userService;
	
	@RequestMapping("/login.do")
	public String login(Model model, @RequestParam(value="login_url", required=false) String login_url, @RequestParam(value="login_param", required=false) String login_param, MemberVO vo, HttpSession session) throws Exception {
		if (userService.loginCheck(vo)) {
			
			MemberVO userInfo = userService.getLoginSessionInfo(vo);
			session.setAttribute("userInfo", userInfo);	// 세션 저장
			String redirectUrl = SiteProperty.SALTPIANO_INDEX_PAGE; // 시작페이지
			
			// 로그인 이전페이지 존재하는 경우
			if(login_url != null && !"".equals(login_url)) {
				redirectUrl = login_url;
				
				if(login_param != null && !"".equals(login_param)) {
					redirectUrl = redirectUrl + "?" + login_param;					
				}
			}
			return "redirect:"+redirectUrl;
		} else {
			model.addAttribute("code", "alertMessageBack");
			model.addAttribute("message", "아이디, 비밀번호가 올바르지 않습니다.");
			return "include/alert";
		}
	}
	
	/**
	 * 로그아웃 처리
	 * @param model
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(Model model, HttpSession session) {
		
		model.addAttribute("code", "alertMessageUrl");
		model.addAttribute("message", "정상적으로 로그아웃 되었습니다.");
		model.addAttribute("url", "index.do");
		session.invalidate();
		return "include/alert";		
	}
	
	//회원가입 페이지 리턴
	@RequestMapping("/user/join.do")
	public String join(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "user/join";
	}
	
	//마이페이지 리턴
	@RequestMapping("/mypage/index.do")
	public String mypage(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "mypage/reserve/index";
	}
	
	//아이디 찾기 페이지 리턴
	@RequestMapping("/user/emailsearch.do")
	public String emailsearch(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "user/emailsearch";
	}
	
	//비밀번호 찾기 페이지 리턴
	@RequestMapping("/user/pwsearch.do")
	public String pwsearch(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "user/pwsearch";
	}
	
	//회원탈퇴 페이지 리턴
	@RequestMapping("/user/secession.do")
	public String secession(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "user/secession";
	}
	
	/**
	 * 회원 탈퇴 기능
	 * @param model
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/secessionState.do")
	public String secessionState(Model model, MemberVO param) throws Exception{
		int cnt = userService.secession(param);
		if(cnt==1) {
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", "정상적으로 탈퇴되었습니다.");
			model.addAttribute("url", "/index.do");
		} else {
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", "존재하지않는 이메일이거나 이메일과 비밀번호가 같지않습니다.");
			model.addAttribute("url", "secession.do");
		}
		return "include/alert";
	}
	
	//비밀번호 찾아 이메일 보내기
	@RequestMapping("/user/searchpw.do")
	public String searchpw(Model model, MemberVO param) throws Exception{
		boolean success = userService.searchpw(param);
		
		if (success) {
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", "임시비밀번호가 메일로 발송되었습니다.");
			model.addAttribute("url", "pwsearch.do");
		} else {
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", "회원정보가 존재하지 않습니다.");
			model.addAttribute("url", "pwsearch.do");
		}
		
		return "include/alert";	
	}
	
	//회원정보 수정 페이지
	@RequestMapping("/user/edit.do")
	public String userEdit(Model model, HttpServletRequest request) throws Exception {
		MemberVO userInfo = (MemberVO)request.getSession().getAttribute("userInfo"); 	//로그인된 session정보 찾아서 get해오기
		MemberVO data = userService.read(userInfo.getNo());			//가져온 session의 no를 read 메소드에 파라미터로 넣고 data에 대입해준다.
		model.addAttribute("data", data);
		
		return "user/edit";
	}
	
	
	@RequestMapping("/admin/user/index.do")
	public String index(Model model, MemberVO param) throws Exception {
		int[] rowPageCount = userService.count(param);
		ArrayList<MemberVO> list = userService.list(param);
		
		model.addAttribute("totCount", rowPageCount[0]);
		model.addAttribute("totPage", rowPageCount[1]);
		model.addAttribute("list", list);
		model.addAttribute("vo", param);
		
		return "user/user/index";
	}
	
	@RequestMapping("/admin/user/read.do")
	public String read(Model model, MemberVO param) throws Exception {
		MemberVO data = userService.read(param.getNo());
		model.addAttribute("data", data);
		model.addAttribute("vo", param);
		
		return "user/user/read";
	}
	

	
	//회원 개인정보 페이지 리턴
	@RequestMapping("/user/read.do")
	public String userRead(Model model, MemberVO param) throws Exception {
		MemberVO data = userService.read(param.getNo());
		model.addAttribute("data", data);
		model.addAttribute("vo", param);
		
		return "user/read";
	}
	
	
	@RequestMapping("/admin/user/write.do")
	public String write(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "user/user/write";
	}
	
	@RequestMapping("/user/write.do")
	public String userwrite(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		
		return "user/write";
	}
	
	
	
	@RequestMapping("/admin/user/edit.do")
	public String edit(Model model, MemberVO param) throws Exception {
		MemberVO data = userService.read(param.getNo());
		model.addAttribute("data", data);
		model.addAttribute("vo", param);
		
		return "user/user/edit";
	}
	

	
	

	
	
	/**
	 * 회원 아이디 중복체크
	 * 사용자에서 저장시 ajax로 체크
	 * @param model
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/emailcheck.do")
	public String emailcheck(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		int value = userService.emailcheck(param);
		model.addAttribute("value", value);
		
		return "include/return";
	}
	
	@RequestMapping("user/searchemail.do")
	public String searcheamil(Model model, MemberVO param) throws Exception {
		MemberVO data = userService.searchemail(param);
		String email = "";
		if (data != null) {email = data.getEmail();}
		model.addAttribute("value", email);
		
		return "include/return";
	}
	
	@RequestMapping("/admin/user/pwcheck.do")
	public String pwcheck(Model model, MemberVO param) throws Exception {
		model.addAttribute("vo", param);
		int value = userService.emailcheck(param);
		
		model.addAttribute("value", value);
		
		return "include/return";
	}
	
	
	/**
	 * 관리자 회원등록, 수정, 삭제 cmd값으로 구분해서 처리
	 * @param model
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/process.do")
	public String userProcess(Model model, MemberVO param, HttpServletRequest request) throws Exception {
		model.addAttribute("vo", param);
		
		if ("write.do".equals(param.getCmd())) {
			int r = userService.insert(param, request);
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "정상적으로 등록되었습니다.", "등록실패"));
			model.addAttribute("url", "index.do");
		} else if ("edit.do".equals(param.getCmd())) {
			int r = userService.update(param, request);
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "정상적으로 수정되었습니다.", "수정실패"));
			model.addAttribute("url", param.getTargetURLParam("index.do", param, 0));
		} else if ("groupDelete.do".equals(param.getCmd())) {
			int r = userService.groupDelete(request);
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "총 "+r+"건이 삭제되었습니다.", "삭제실패"));
			model.addAttribute("url", param.getTargetURLParam("index.do", param, 0));
		} else if ("delete.do".equals(param.getCmd())) {
			int r = userService.delete(param.getNo());
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "정상적으로 삭제되었습니다.", "삭제실패"));
			model.addAttribute("url", param.getTargetURLParam("index.do", param, 0));
		}
		
		return "include/alert";
	}
	
	/**
	 * 회원가입, 수정, 삭제
	 * @param model
	 * @param param
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/process.do")
	public String userProcess(Model model, MemberVO param, HttpServletRequest request) throws Exception {
		model.addAttribute("vo", param);
		
		if ("write.do".equals(param.getCmd())) {
			int r = userService.insert(param, request);
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "정상적으로 등록되었습니다.", "등록실패"));
			model.addAttribute("url", "/index.do");
		} else if ("edit.do".equals(param.getCmd())) {
			int r = userService.update(param, request);
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "정상적으로 수정되었습니다.", "수정실패"));
			model.addAttribute("url", param.getTargetURLParam("/index.do", param, 0));
		} else if ("groupDelete.do".equals(param.getCmd())) {
			int r = userService.groupDelete(request);
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "총 "+r+"건이 삭제되었습니다.", "삭제실패"));
			model.addAttribute("url", param.getTargetURLParam("index.do", param, 0));
		} else if ("delete.do".equals(param.getCmd())) {
			int r = userService.delete(param.getNo());
			model.addAttribute("code", "alertMessageUrl");
			model.addAttribute("message", Function.message(r, "정상적으로 삭제되었습니다.", "삭제실패"));
			model.addAttribute("url", param.getTargetURLParam("index.do", param, 0));
		}
		return "include/alert";
	}
	
	
	
//	@RequestMapping("/admin/user/loginHistory")
//	public String loginHistory(Model model, MemberVO param) throws Exception {
//		int[] rowPageCount = userService.countLoginHistory(param);
//		ArrayList<MemberVO> list = userService.listLoginHistory(param);
//		
//		model.addAttribute("totCount", rowPageCount[0]);
//		model.addAttribute("totPage", rowPageCount[1]);
//		model.addAttribute("list", list);
//		model.addAttribute("vo", param);
//		
//		return "user/user/loginHistory";
//	}
}