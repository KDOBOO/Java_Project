package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class PlayerUI_User {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private PlayerDAO dao = new PlayerDAOImpl();

	public void menu() {
		int ch;
		System.out.println("\n[선수검색]");

		while (true) {
			try {
				do {
				System.out.println("-------------------------------------------------");
				System.out.println("1.이름 검색 2.국가별 선수 찾기 3.포지션 선수 찾기 4.종료 ");
				System.out.println("-------------------------------------------------");
				System.out.print("선택=> ");
				ch = Integer.parseInt(br.readLine());
				} while (ch<1 || ch>4);
				
				if (ch == 4) {
					return;
				}
				switch (ch) {
				case 1: findByName();break;
				case 2: findByNation();break;
				case 3: findByPosition();break;
				}

			} catch (Exception e) {
			}
		}
	}

	// 이름검색
	protected void findByName() {
		System.out.println("\n[선수이름 검색]");
		System.out.println("-------------------------------------");
		
		String playerName;

		try {
			
			System.out.print("검색할 선수이름 ? ");
			playerName = br.readLine();
			
			System.out.println("----------------------------------------------------------------------------------------------------------");
			System.out.printf("%-18s\t%-18s\t%-3s\t%-4s\t%-20s\t%-4s\t%s", "선수이름", "팀이름", "포지션", "선수번호", "생일","키","국가\n");
			System.out.println("----------------------------------------------------------------------------------------------------------");


			List<PlayerDTO> list = dao.listplayer(playerName);
			
			if (list.size() == 0) {
				System.out.println("등록된 선수가 아닙니다.\n");
				return;
			}
			for (PlayerDTO dto : list) {

				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-18s\t", dto.getTeamName());
				System.out.printf("%-3s\t",dto.getPosition());
				System.out.printf("%-4s\t",dto.getUniformNum());
				System.out.printf("%-20s\t",dto.getBirth());
				System.out.printf("%-4s\t",dto.getTall());
				System.out.println(dto.getPlayerNation());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 국가 검색
	protected void findByNation() {
		System.out.println("\n[국가 검색]");
		System.out.println("-------------------------------------");
	
		String playerNation;

		try {
			System.out.print("검색할 국가 ? ");
			playerNation = br.readLine();
			System.out.println("----------------------------------------------------------------------------------------------------------");
			System.out.printf("%-18s\t%-18s\t%-3s\t%-4s\t%-20s\t%-4s\t%s", "선수이름", "팀이름", "포지션", "선수번호", "생일","키","국가\n");
			System.out.println("----------------------------------------------------------------------------------------------------------");

			
			List<PlayerDTO> list = dao.listplayerC(playerNation);

			if (list.size() == 0) {
				System.out.println("등록된 국가가 아닙니다.\n");
				return;
			}

			for (PlayerDTO dto : list) {

				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-18s\t", dto.getTeamName());
				System.out.printf("%-3s\t",dto.getPosition());
				System.out.printf("%-4s\t",dto.getUniformNum());
				System.out.printf("%-20s\t",dto.getBirth());
				System.out.printf("%-4s\t",dto.getTall());
				System.out.println(dto.getPlayerNation());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	// 포지션
	protected void findByPosition() {
		System.out.println("\n[포지션 검색]");
		System.out.println("-------------------------------------");
	
		String position;
		

		try {
			System.out.print("검색할 포지션 ? [GK:골기퍼 DF:수비 MF:미드필더 FW:공격수] ");
			position = br.readLine();
			System.out.println("----------------------------------------------------------------------------------------------------------");
			System.out.printf("%-18s\t%-18s\t%-3s\t%-4s\t%-20s\t%-4s\t%s", "선수이름", "팀이름", "포지션", "선수번호", "생일","키","국가\n");
			System.out.println("----------------------------------------------------------------------------------------------------------");
			
			List<PlayerDTO> list = dao.listplayerP(position);

			if (list.size() == 0) {
				System.out.println("등록된 선수가 아닙니다.\n");
				return;
			}

			for (PlayerDTO dto : list) {

				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-18s\t", dto.getTeamName());
				System.out.printf("%-3s\t",dto.getPosition());
				System.out.printf("%-4s\t",dto.getUniformNum());
				System.out.printf("%-20s\t",dto.getBirth());
				System.out.printf("%-4s\t",dto.getTall());
				System.out.println(dto.getPlayerNation());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	

}
