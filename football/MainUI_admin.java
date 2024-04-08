package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainUI_admin {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	PlayerUI_Admin pa = new PlayerUI_Admin();
	TeamUI_admin ta = new TeamUI_admin();
	MatchManagerUI ma =new MatchManagerUI();
	ScoreUI_Admin sa = new ScoreUI_Admin();
	ComemtmainUI_admin ca= new ComemtmainUI_admin();
	
	public void menu() {
		int ch;
		System.out.println("\n[관리자 메뉴]");
		
		while (true) {

			try {
			
				do {
				System.out.println("-------------------------------------------------------------------");
				System.out.println("1.선수관리 2.팀관리 3.경기기록관리 4.득점관리 5.코멘트관리 6.뒤로가기");
				System.out.println("-------------------------------------------------------------------");
		
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
				case 1:pa.menu();break;
				case 2:ta.menu();break;
				case 3:ma.matchSchedule();break;
				case 4:sa.menu();break;
				case 5:ca.main_menu();
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}
