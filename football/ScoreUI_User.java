package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.util.DBConn;

public class ScoreUI_User {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ScoreDAO dao = new ScoreDAO();

	public void menu() {
		int ch = 0;

		while (true) {
			try {
				System.out.println("\n[득점]");
				System.out.println("----------------------------------------------------");
				System.out.println("1.득점조회(이름) 2.득점조회(경기일자) 3.득점순위리스트 4.이전");
				System.out.println("----------------------------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 4) {
					DBConn.close();
					return;
				}

				switch (ch) {
				case 1: findByPname(); break;
				case 2: findByMDate(); break;
				case 3: rankList(); break;
				}
			} catch (Exception e) {
			}
		}
	}

	
	// 득점검색(이름)
	protected void findByPname() {
		System.out.println("\n[득점검색(이름)]");
		System.out.println("-----------------------");

		String playerName;

		try {
			System.out.print("검색할 이름 ? ");
			playerName = br.readLine();

			List<ScoreDTO> list = dao.listScoreP(playerName);

			if (list.size() == 0) {
				System.out.println("\n존재하지 않는 이름입니다.\n");
				return;
			}

			System.out.println("-----------------------------------------------------------------------");
			System.out.printf("%-18s\t%-18s\t","선수명","팀명");
			System.out.println("득점시간\t경기일자");
			System.out.println("-----------------------------------------------------------------------");

			for (ScoreDTO dto : list) {
				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-18s\t", dto.getTeamName());
				System.out.print(dto.getScoreTime() + "'\t");
				System.out.println(dto.getMatchDate());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	// 득점검색(경기일자)
	protected void findByMDate() {
		System.out.println("\n[득점검색(경기일자)]");
		System.out.println("-------------------------------------");
		
		String matchDate;
		
		try {
			System.out.print("검색할 경기일자[YYYY-MM-DD] ? ");
			matchDate = br.readLine();
			
			if (! matchDate.matches("^[\\d]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
				System.out.println("\n옳지않은 날짜 혹은 형식입니다. 예:[YYYY-MM-DD]\n");
				return;
			}
			
			List<ScoreDTO> list = dao.listScoreM(matchDate);
			
			if(list.size() == 0) {
				System.out.println("\n존재하지 않는 경기일자입니다.\n");
				return;
			}
			
			System.out.println("------------------------------------------------------------------------");
			System.out.printf("%-18s\t%-18s\t","선수명","팀명");
			System.out.println("득점시간\t경기일자");
			System.out.println("------------------------------------------------------------------------");
			
			for(ScoreDTO dto : list) {
				System.out.printf("%-18s\t",dto.getPlayerName());
				System.out.printf("%-18s\t",dto.getTeamName());
				System.out.print(dto.getScoreTime() + "'\t");
				System.out.println(dto.getMatchDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	
	// 득점순위리스트
	protected void rankList() {
		System.out.println("\n[득점순위리스트]");
		System.out.println("--------------------------------------------------------------");
		System.out.printf("%-2s\t%-18s\t%-18s\t%-3s","순위","선수명","팀명","득점수\n");
		System.out.println("--------------------------------------------------------------");
		
		List<ScoreDTO> list = dao.rankList();
		for(ScoreDTO dto : list) {
			System.out.printf("%-2s\t",dto.getRank());
			System.out.printf("%-18s\t",dto.getPlayerName());
			System.out.printf("%-18s\t",dto.getTeamName());
			System.out.printf("%-3s\t",dto.getGoals());
			System.out.println();
		}
		System.out.println();
	}
}
