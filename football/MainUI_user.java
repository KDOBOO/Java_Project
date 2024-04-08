package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainUI_user {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	PlayerUI_User pu = new PlayerUI_User();
	TeamUI_user tu= new TeamUI_user();
	MatchScheduleUI mu =new MatchScheduleUI();
	ScoreUI_User su = new ScoreUI_User();
	ComentmainUI cu = new ComentmainUI();
	public void menu() {
		
		int ch;
		System.out.println("\n[사용자 메뉴]");
		
		
		while (true) {

			try {
			
			
				do {
				System.out.println("----------------------------------------------------");
				System.out.println("1.선수 2.팀 3.경기기록 4.득점 5.코멘트 6.뒤로가기");
				System.out.println("----------------------------------------------------");
		
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch < 1 || ch > 6) {
					System.out.println("1~6번중에서만 선택하여 주세요.");
				}
				} while (ch < 1 || ch > 6) ;
				
				if (ch == 6) {
					return;
				}

				switch (ch) {
				case 1:pu.menu();break;
				case 2:tu.menu();break;
				case 3:mu.matchSchedul();break;
				case 4:su.menu();break;
				case 5:cu.main_menu();
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}
