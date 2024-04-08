<<<<<<< HEAD
package subject;

// 회원 정보
public class MemberVO {
	private String name; // 이름
	private String address; // 주소
	private String tel; // 전화번호
	private String regDate; // 회원 등록일
	private String id; // 회원번호

	
	public String getName() {
		return name;
	}
	public void setName(String name) throws PatternException{
		// name을 한글 또는 영문자로 입력하지 않은 경우 예외처리
		if (!name.matches("^[가-힣A-Za-z]+$")) {
			throw new PatternException("잘못 입력하셨습니다.");
		} 
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
=======
package subject;

// 회원 정보
public class MemberVO {
	private String name; // 이름
	private String address; // 주소
	private String tel; // 전화번호
	private String regDate; // 회원 등록일
	private String id; // 회원번호

	
	public String getName() {
		return name;
	}
	public void setName(String name) throws PatternException{
		// name을 한글 또는 영문자로 입력하지 않은 경우 예외처리
		if (!name.matches("^[가-힣A-Za-z]+$")) {
			throw new PatternException("잘못 입력하셨습니다.");
		} 
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
>>>>>>> c1dce160f423e1166a16664b95b883140ea323f5
