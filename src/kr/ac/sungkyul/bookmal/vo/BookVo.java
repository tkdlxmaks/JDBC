package kr.ac.sungkyul.bookmal.vo;

public class BookVo {
	
	private Long no;
	private String title;
	private Long rate;
	private Long authorNo;
	
	
	// getter & setter
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setName(String title) {
		this.title = title;
	}
	public Long getRate() {
		return rate;
	}
	public void setRate(Long rate) {
		this.rate = rate;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long authorNo) {
		this.authorNo = authorNo;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", rate=" + rate + ", authorNo=" + authorNo + "]";
	}
	
}
