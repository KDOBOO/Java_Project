package football;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class MatchScheduleUI {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private MatchSchedulDAO dao = new MatchSchedulDAO();

	public void matchSchedul() {
		int ch = 0;

		System.out.println("\n[경기 일정]");
		while (true) {

			try {
				System.out.println("-----------------------------------------------");
				System.out.println("1.이전 경기기록 2.예정 경기일정 3.리그 순위 4.뒤로");
				System.out.println("-----------------------------------------------");
				System.out.print("선택=> ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 4) {
					return;
				}

				switch (ch) {
				case 1:preMatch();break;
				case 2:nextMatch();break;
				case 3:leagueRank();break;
				}
			} catch (Exception e) {
			}
		}
	}

	private void month() {
		String matchdate;
		System.out.print("\n검색할 경기년월[yyyy-mm]");
		System.out.println();
		
		try {

			matchdate = br.readLine();
			List<MatchScheduleDTO> list = dao.listMatch(matchdate);
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.printf("%-15s\t", "[경기날짜]");
			System.out.printf("%-25s\t", "[홈팀]");
			System.out.printf("%-25s\t", "[원정팀]");
			System.out.printf("%-20s\t", "[경기장]");
			System.out.println();
			for (MatchScheduleDTO dto : list) {
				System.out.printf("%-15s\t", dto.getMatchDate() + "\t");
				System.out.printf("%-20s\t", dto.getHomeName() + "\t");
				System.out.printf("%-20s\t", dto.getAwayName() + "\t");
				System.out.println(dto.getStadium());
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	public void leagueRank() {
		System.out.print("\n[리그 순위]\n");
		System.out.println("-------------------------------------");
		
		List<Map.Entry<String, Integer>> ranking = dao.Ranking();
		int i = 0;
		for (Map.Entry<String, Integer> entry : ranking) {
			String teamName = entry.getKey();
			int points = entry.getValue();
			i++;
			System.out.println(i + "등  " + teamName + "  승점 :" + points);
		}
		System.out.println();
	}

	public void nextMatch() {
		System.out.println("\n[다음 경기 일정]");
		System.out.println("-------------------------------------");

		List<MatchScheduleDTO> list = dao.nextMatch();
		System.out.printf("%-15s\t", "[경기날짜]");
		System.out.printf("%-25s\t", "[홈팀]");
		System.out.printf("%-25s\t", "[원정팀]");
		System.out.printf("%-20s\t", "[경기장]");
		System.out.println();
		for (MatchScheduleDTO dto : list) {
			System.out.printf("%-15s\t", dto.getMatchDate() + "\t");
			System.out.printf("%-20s\t", dto.getHomeName() + "\t");
			System.out.printf("%-20s\t", dto.getAwayName() + "\t");
			System.out.println(dto.getStadium());
		}
		System.out.println();
	}

	public void preMatch() {
		int ch=0;
		while (true) {

			try {
				System.out.println("----------------------------");
				System.out.println("1.년도별 2.년월별 3.전체보기 4.뒤로");
				System.out.println("----------------------------");
				System.out.print("선택=> ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 4) {
					return;
				}

				switch (ch) {
				case 1:year();break;
				case 2:month();break;
				case 3:Allmatch();break;
				case 4:month();break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void year() {
		String matchyear;
		System.out.print("\n검색할 경기년도[yyyy] ➜ \n");
		try {

			matchyear= br.readLine();
			List<MatchScheduleDTO> list = dao.listYear(matchyear);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-15s\t", "[경기날짜]");
			System.out.printf("%-25s\t", "[홈팀]");
			System.out.printf("%-15s\t", "[홈팀 득점]");
			System.out.printf("%-25s\t", "[원정팀]");
			System.out.printf("%-15s\t", "[원정팀 득점]");
			System.out.printf("%-25s\t", "[경기장]");
			System.out.printf("%-25s\t", "[주심]\n");
			System.out.println();
			for (MatchScheduleDTO dto : list) {
				System.out.printf("%-15s\t", dto.getMatchDate() + "\t");
				System.out.printf("%-20s\t", dto.getHomeName() + "\t");
				System.out.printf("%-15s\t", dto.getHomeScore() + "\t");
				System.out.printf("%-20s\t", dto.getAwayName() + "\t");
				System.out.printf("%-15s\t", dto.getAwayScore() + "\t");
				System.out.printf("%-23s\t", dto.getStadium() + "\t");
				System.out.printf("%-15s\t", dto.getMainReferee() + "\t");
				System.out.println();
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	private void Allmatch() {
		System.out.println("[이전 경기 결과]");

		List<MatchScheduleDTO> list = dao.preResult();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-15s\t", "[경기날짜]");
		System.out.printf("%-25s\t", "[홈팀]");
		System.out.printf("%-15s\t", "[홈팀 득점]");
		System.out.printf("%-25s\t", "[원정팀]");
		System.out.printf("%-15s\t", "[원정팀 득점]");
		System.out.printf("%-25s\t", "[경기장]");
		System.out.printf("%-25s\t", "[주심]\n");
		System.out.println();
		for (MatchScheduleDTO dto : list) {
			System.out.printf("%-15s\t", dto.getMatchDate() + "\t");
			System.out.printf("%-20s\t", dto.getHomeName() + "\t");
			System.out.printf("%-15s\t", dto.getHomeScore() + "\t");
			System.out.printf("%-20s\t", dto.getAwayName() + "\t");
			System.out.printf("%-15s\t", dto.getAwayScore() + "\t");
			System.out.printf("%-23s\t", dto.getStadium() + "\t");
			System.out.printf("%-15s\t", dto.getMainReferee() + "\t");
			System.out.println();
		}
		System.out.println();
	}
}
