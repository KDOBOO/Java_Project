package subject;

//도서에서 임포트
//이름
public class RentalMngVO {
	//도서 객체 찍기
	private String rentalday;//대여날짜 v
	private String returnday;//예정반납일v
	private String rentname; // 대여하는 사람..
	private String rentbook; // 대여도서이름v
	private String booknumber;
	private String username; 
	private String lastday; //반납하기로 한 날짜
	private String isTrue;//연체인지 아닌지
	
	public String getLastday() {
		return lastday;
	}
	public void setLastday(String lastday) {
		this.lastday = lastday;
	}
	public String getRentname() {
		return rentname;
	}
	public void setRentname(String rentname) {
		this.rentname = rentname;
	}
	public String getRentbook() {
		return rentbook;
	}
	public void setRentbook(String rentbook) {
		this.rentbook = rentbook;
	}


	
	public String getRentalday() {
		return rentalday;
	}
	public void setRentalday(String rentalday) {
		this.rentalday = rentalday;
	}
	public String getReturnday() {
		return returnday;
	}
	public void setReturnday(String returnday) {
		this.returnday = returnday;
	}
	public String getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(String booknumber) {
		this.booknumber = booknumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(String isTrue) {
		this.isTrue = isTrue;
	}

}
