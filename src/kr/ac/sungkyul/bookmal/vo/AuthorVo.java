package kr.ac.sungkyul.bookmal.vo;

/**
 * 프로그램과 DAO와 교환할 VO를 생성
 * resultSet에 VO에 담아 DAO를 통해 전달. 
 * @author WonHo
 */
public class AuthorVo {
	
	private Long no;  // Long으로 잡아주는게 좋다. 또한 Refference 값으로 자료형인 Long로
	private String name;
	private String description;
	
	
	/*Setter & Getter*/
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "AuthorVo [no=" + no + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
