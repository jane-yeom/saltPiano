package sheet;

import java.sql.Timestamp;
import util.CommonVo;

public class SheetVo extends CommonVo {

	private int no;
	private String title;
	private String user_name;
	private int price;
	private int hits;
	private String genre;
	private String contents;
	private Timestamp regdate;
	private String filename;
	private String filename_org;
	private String url;
	private int commentCount;
	private String filename_org_enc;
	private String level;
	private String kinds;
	private String release;
	
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getKinds() {
		return kinds;
	}
	public void setKinds(String kinds) {
		this.kinds = kinds;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename_org() {
		return filename_org;
	}
	public String getFilename_org_enc() throws Exception{
		return java.net.URLEncoder.encode(filename_org,"UTF-8").replaceAll("\\+", "%20");
	}
	public void setFilename_org(String filename_org) {
		this.filename_org = filename_org;
	}
	
	public void setFilename_org_enc(String filename_org_enc) {
		this.filename_org_enc = filename_org_enc;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	
	
}
