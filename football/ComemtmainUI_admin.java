package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ComemtmainUI_admin {
	BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
	
	CommentsUI_admin ui = new CommentsUI_admin();
	//메인메뉴
	public void main_menu() {
		
		int ch;
		
		while (true) {
			
			try {
				System.out.println("\n[코멘트 관리(관리자용)]");
				System.out.println("-----------------------");
				System.out.println("1.선수 2.팀 3.경기 4.종료 ");
				System.out.println("-----------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch == 4) {
					return;
				}
				switch (ch) {
				case 1: ui.player_menu(); break; 
				case 2: ui.team_menu(); break; 
				case 3: ui.match_menu(); break; 
				}
				
			} catch (Exception e) {
				
			}
		}
	}
}
