package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class ScoreUI_Admin {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ScoreDAO dao = new ScoreDAO();

	public void menu() {
		int ch = 0;

		while (true) { // 무한루프 사용

			try {
				System.out.println("\n[득점 관리]");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.printf("%-8s\t%-10s\t%-10s\t%-8s\t%s", "1.득점등록", "2.득점수정", "3.득점삭제", "4.선수정보조회", "5.경기정보조회\n");
				System.out.printf("%-8s\t%-10s\t%-10s\t%-8s\t%s", "6.득점조회(이름)", "7.득점조회(경기일자)", "8.전체리스트", "9.득점순위리스트", "10.이전\n");
				System.out.println("------------------------------------------------------------------------------");
				System.out.print("선택 => ");
				
				ch = Integer.parseInt(br.readLine());

				if (ch == 10) {
					return;
				}

				switch (ch) {
				case 1: insert(); break;
				case 2: update(); break;
				case 3: delete(); break;
				case 4: viewPlayer(); break; 	//선수정보보기
				case 5: viewMatch(); break; 	//경기정보보기
				case 6: findByPname(); break;	
				case 7: findByMDate(); break;
				case 8: listAll(); break;
				case 9: rankList(); break;
				}
			} catch (Exception e) {
			}
		}
	}
	
	// 득점등록
	protected void insert() {
		System.out.println("\n[득점등록]");
		System.out.println("-------------------------------------");

		try {
			ScoreDTO dto = new ScoreDTO();

			System.out.print("선수번호 ? ");
			dto.setPlayerId(Integer.parseInt(br.readLine()));

			System.out.print("득점시간[45분=>45 혹은 95=>90+5] ? ");
			dto.setScoreTime(br.readLine());

											// 분 + 추가분 형식. = "90", "120+10"  1~120분까지 쓸수있고, 추가시간도 넣을 수 있습니다.
			if (!dto.getScoreTime().matches("^((\\d{1,2}|1[01]\\d|120)(\\+\\d{1,2})?)$")) {
				System.out.println("\n득점시간을 잘못 입력하였습니다.\n");
				return;
			}

			System.out.print("경기번호 ? ");
			dto.setMatchNumber(Integer.parseInt(br.readLine()));

			dao.insert(dto);

			System.out.println("\n등록 성공\n");

		} catch (NumberFormatException e) { // 숫자만 입력 가능한 예외 // parseInt 때문에 발생 할 수 있다.
			System.out.println("\n(선수번호,경기번호) 숫자만 입력 가능합니다.");
		} catch (SQLException e) {
			if (e.getErrorCode() == 2291) { //부모키 없을때 예외.
				System.out.println("\n선수번호 혹은 경기번호가 존재하지 않습니다.");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("\n필수 사항을 입력하지 않았습니다.");
			} else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	
	// 득점수정
	protected void update() {
		System.out.println("\n[득점수정]");
		System.out.println("-------------------------------------");

		try {
			ScoreDTO dto = new ScoreDTO();

			System.out.print("수정할 득점번호 ? ");
			dto.setScoreId(br.readLine());

			System.out.print("득점시간 ? ");
			dto.setScoreTime(br.readLine());
											// 분 + 추가분 형식. =  1~120분까지 쓸수있고, 추가시간도 넣을 수 있습니다.
			if (!dto.getScoreTime().matches("^((\\d{1,2}|1[01]\\d|120)(\\+\\d{1,2})?)$")) {
				System.out.println("\n득점시간을 잘못 입력하였습니다.\n");
				return;
			}

			System.out.print("선수번호 ? ");
			dto.setPlayerId(Integer.parseInt(br.readLine()));

			System.out.print("경기번호 ? ");
			dto.setMatchNumber(Integer.parseInt(br.readLine()));

			int result = dao.updateScore(dto);

			if (result == 0) {
				System.out.println("\n득점번호가 존재하지 않습니다.\n");
			} else {
				System.out.println("\n수정 성공\n");
			}

		} catch (NumberFormatException e) {
			System.out.println("\n(선수번호,경기번호) 숫자만 입력 가능합니다.\n");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1407) {
				System.out.println("\n필수 사항을 입력하지 않았습니다.\n");
			} else if (e.getErrorCode() == 2291) {
				System.out.println("\n선수번호 혹은 경기코드가 존재하지 않습니다.\n");
			} else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	
	// 득점삭제
	protected void delete() {
		System.out.println("\n[득점삭제]");
		System.out.println("-------------------------------------");

		String scoreId;

		try {
			System.out.print("삭제할 득점번호 ? ");
			scoreId = br.readLine();

			int result = dao.deleteScore(scoreId);

			if (result > 0) {
				System.out.println("\n삭제 성공!!\n");
			} else {
				System.out.println("\n득점번호가 존재하지 않습니다.\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	
	// 선수정보조회
	protected void viewPlayer() {
		System.out.println("\n[선수정보조회]");
		System.out.println("-----------------------------------------------------------");

		String name;

		try {
			System.out.print("검색할 이름 ? ");
			name = br.readLine();

			List<ScoreDTO> list = dao.viewPlayer(name);

			if (list.size() == 0) {
				System.out.println("\n존재하지 않는 이름입니다.\n");
				return;
			}

			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-4s\t%-18s\t%-5s\t%s", "선수번호", "선수이름", "팀코드", "팀명\n");
			System.out.println("-----------------------------------------------------------");

			for (ScoreDTO dto : list) {
				System.out.printf("%-4s\t", dto.getPlayerId());
				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-5s\t", dto.getTeamCode());
				System.out.println(dto.getTeamName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	
	// 경기정보조회
	protected void viewMatch() {
		System.out.println("\n[경기정보조회]");
		System.out.println("-------------------------------------");

		String matchDate;

		try {
			System.out.print("검색할 경기일자[YYYY-MM-DD] ? ");
			matchDate = br.readLine();
									// YYYY-MM-DD 형식의 날짜를 입력. ex. 2024-04-07 
									// ^: 문자열 시작
									// [\\d]{4} : 4자리  
									// 0[1-9]|1[012] : 01~09까지의 월 또는(|) 1[012] 10부터12월 까지의 일.
									// 0[1-9]|[12][0-9]|3[01])$ : 1~9 , 10~29, 30~31 까지 $: 문자열 끝
			if (!matchDate.matches("^[\\d]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
				System.out.println("\n옳지않은 날짜 혹은 형식입니다. 예:[YYYY-MM-DD]\n");
				return;
			}

			List<ScoreDTO> list = dao.viewMatch(matchDate);

			if (list.size() == 0) {
				System.out.println("\n존재하지 않는 경기일자입니다.\n");
				return;
			}

			System.out.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-4s\t%-13s\t%-5s\t%-20s%-15s\t%-7s\t%-15s\t%s", 
					"경기번호", "경기일자", "홈팀코드", "홈팀명", "홈팀점수", "원정팀점수", "원정팀명", "원정팀코드\n");
			System.out.println("------------------------------------------------------------------------------------------------------------------------");

			for (ScoreDTO dto : list) {
				System.out.printf("%-4s\t", dto.getMatchNumber());
				System.out.printf("%-12s\t", dto.getMatchDate() + "  |");
				System.out.printf("%-5s\t", dto.getHomeCode());
				System.out.printf("%-16s\t", dto.getHomeName());
				System.out.printf("%3s\t", dto.getHomeScore());
				System.out.printf(" :\t%-5s\t\t", dto.getAwayScore());
				System.out.printf("%-15s\t", dto.getAwayName());
				System.out.println(dto.getAwayCode());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	
	// 득점검색(이름)
	protected void findByPname() {
		System.out.println("\n[득점검색(이름)]");
		System.out.println("-------------------------------------");

		String playerName;

		try {
			System.out.print("검색할 이름 ? ");
			playerName = br.readLine();

			List<ScoreDTO> list = dao.listScoreP(playerName);

			if (list.size() == 0) {
				System.out.println("\n존재하지 않는 이름입니다.\n");
				return;
			}

			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.printf("%-3s\t%-5s\t%-18s\t%-5s\t%-18s\t", "득점번호", "선수번호", "선수명", "팀코드", "팀명");
			System.out.println("경기번호\t득점시간\t경기일자");
			System.out.println("----------------------------------------------------------------------------------------------------");

			for (ScoreDTO dto : list) {
				System.out.printf("%-3s\t", dto.getScoreId());
				System.out.printf("%-5s\t", dto.getPlayerId());
				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-5s\t", dto.getTeamCode());
				System.out.printf("%-18s\t", dto.getTeamName());
				System.out.print(dto.getMatchNumber() + "\t");
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

			if (!matchDate.matches("^[\\d]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
				System.out.println("\n옳지않은 날짜 혹은 형식입니다. 예:[YYYY-MM-DD]\n");
				return;
			}

			List<ScoreDTO> list = dao.listScoreM(matchDate);

			if (list.size() == 0) {
				System.out.println("\n존재하지 않는 경기일자입니다.\n");
				return;
			}

			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.printf("%-3s\t%-5s\t%-18s\t%-5s\t%-15s\t", "득점번호", "선수번호", "선수명", "팀코드", "팀명");
			System.out.println("\t경기번호\t득점시간\t경기일자");
			System.out.println("----------------------------------------------------------------------------------------------------");

			for (ScoreDTO dto : list) {
				System.out.printf("%-3s\t", dto.getScoreId());
				System.out.printf("%-5s\t", dto.getPlayerId());
				System.out.printf("%-18s\t", dto.getPlayerName());
				System.out.printf("%-5s\t", dto.getTeamCode());
				System.out.printf("%-15s\t", dto.getTeamName());
				System.out.print(dto.getMatchNumber() + "\t");
				System.out.print(dto.getScoreTime() + "'\t");
				System.out.println(dto.getMatchDate());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	
	// 전체리스트
	protected void listAll() {
		System.out.println("\n[전체리스트]");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.printf("%-3s\t%-5s\t%-18s\t%-5s\t%-19s\t", "득점번호", "선수번호", "선수명", "팀코드", "팀명");
		System.out.println("경기번호\t득점시간\t경기일자");
		System.out.println("----------------------------------------------------------------------------------------------------");

		List<ScoreDTO> list = dao.listScore();
		
		for (ScoreDTO dto : list) {
			System.out.printf("%-3s\t", dto.getScoreId());
			System.out.printf("%-5s\t", dto.getPlayerId());
			System.out.printf("%-18s\t", dto.getPlayerName());
			System.out.printf("%-5s\t", dto.getTeamCode());
			System.out.printf("%-19s\t", dto.getTeamName());
			System.out.print(dto.getMatchNumber() + "\t");
			System.out.print(dto.getScoreTime() + "'\t");
			System.out.println(dto.getMatchDate());
		}
		System.out.println();
	}

	// 득점순위리스트
	protected void rankList() {
		System.out.println("\n[득점순위리스트]");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%-2s\t%-4s\t%-18s\t%-3s\t%-18s\t%-3s", "순위", "선수번호", "선수명", "팀코드", "팀명", "득점수\n");
		System.out.println("-----------------------------------------------------------------------------");

		List<ScoreDTO> list = dao.rankList();
		
		for (ScoreDTO dto : list) {
			System.out.printf("%-2s\t", dto.getRank());
			System.out.printf("%-4s\t", dto.getPlayerId());
			System.out.printf("%-18s\t", dto.getPlayerName());
			System.out.printf("%-3s\t", dto.getTeamCode());
			System.out.printf("%-18s\t", dto.getTeamName());
			System.out.printf("%-3s\t", dto.getGoals());
			System.out.println();
		}
		System.out.println();
	}
}
