package sheet;

import java.sql.Timestamp;

public class CommentVo {

	private int no;
	private int board_no;
	private String content;
	private Timestamp regdate;
	private int user_no;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return String.valueOf(regdate).substring(0, 10);
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	
}
