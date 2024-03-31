package subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class MainUI {

	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	//각 UI에 접근할수있는 객체 생성
	private Book bkImpl = new BookImpl();
	
	private RentalMngUI rentalmngUI = new RentalMngUI(bkImpl);
	private RecommendUI recommendUI = new RecommendUI(bkImpl);
	private MemberUI memberUI = new MemberUI();
	private BookUI bookUI = new BookUI(bkImpl);
	
	private String bookId = "book";
	private String bookPwd = "book";

	
	// 초기 로그인 화면 UI
	public void Start() {
		String id, pwd;
		int cnt = 0;
		boolean b = false;
		
		
		//아이디, 패스워드 입력
		do {
			try {
				System.out.print("아이디 : ");
				id = br.readLine();
				System.out.print("패스워드 : ");
				pwd = br.readLine();
				b = bookId.equals(id) && bookPwd.equals(pwd);
				
				if(!b) {
					System.out.println("아이디 또는 패스워드가 틀렸습니다\n");
				}
				//입력시도 횟수
				cnt++;
			} catch (Exception e) {
				
			}
		} while (cnt < 5 && !b) ;

		if (!b) {
			System.out.println("5회 이상 틀렸음으로 프로그램을 종료합니다.");
			return;
		}

		menu();
	}
	

	// 도서관리프로그램 메뉴 UI
	public void menu() {
		int ch;

		while (true) {
		
				try {
					
				do {
				System.out.println("\t\t[도서 관리 프로그램]");
				System.out.println("------------------------------------------------");
				System.out.println("1.회원관리  2.도서관리  3.대여관리  4.추천도서  5.종료\n");

				System.out.println("선택 => ");
				
				ch = Integer.parseInt(br.readLine());
				
				} while (ch < 1 || ch > 5);

			if (ch == 5) {
				return;
			}

        	switch (ch) {
			case 1:
				memberUI.menu();
				break;
			case 2:
				bookUI.menu();
				break;
			case 3:
				rentalmngUI.menu();
				break;
			case 4:
				recommendUI.menu();
				break;
			} 
			} catch(Exception e) {
				
			}
		}
	}
}
