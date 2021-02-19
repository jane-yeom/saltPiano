package tutorial;

import java.sql.Timestamp;

import util.CommonVo;

public class TutorialVo extends CommonVo{

	private int no;
	private String title;
	private String user_name;
	private int hits;
	private String genre;
	private String contents;
	private Timestamp regdate;
	private String url;
	private int commentCount;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getUrl() {
		return url;
	}
	public String getUrl2() {
		return url.split("v=")[1];
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
}
