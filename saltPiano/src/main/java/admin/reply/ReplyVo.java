package admin.reply;

import java.sql.Timestamp;

import util.CommonVo;

public class ReplyVo extends CommonVo {

	private int no;					// 글번호
	private String title;			// 제목
	private String content;			// 내용
	private Timestamp regdate;		// 등록일
	private String filename; 		// 서버에 저장될 파일명
	private String filename_org; 	// 사용자가 업로드한 원본파일명
	private int user_no;
	private int commentCount;
	private String username;
	private int readcount;
	private String[] nos;
	private int ref;
	private int seq;
	private int lev;
	
	public ReplyVo() {
		// 부모에서 초기값으로 10을 대입했지만, 자식에서 5로 변경
		// 부모객체가 먼저 생성되고 나서, 자식 객체가 생성되기 때문에
		//this.setPageRow(5);
	}
	
	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public String[] getNos() {
		return nos;
	}

	public void setNos(String[] nos) {
		this.nos = nos;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getFilename_org() {
		return filename_org;
	}

	public void setFilename_org(String filename_org) {
		this.filename_org = filename_org;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	// contentLine -> getContentLine()
	public String getContentLine() {
		return content.replace("\n", "<br>");
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		String date = String.valueOf(regdate);
		return date.substring(0,10);
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
	
}
