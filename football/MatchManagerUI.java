package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class MatchManagerUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private MatchManagerDAO mao = new MatchManagerDAO();
	int homeid, awayid, id;

	public void matchSchedule() {
		int ch = 0;

		System.out.println("\n[경기 일정]");
		while (true) {

			try {
				System.out.println("-----------------------------------------------------------------");
				System.out.println("1.경기일정 추가 2.경기일정 수정 3.경기일정 삭제 4.모든 경기 조회 5.뒤로");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("선택=> ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 5) {
					return;
				}

				switch (ch) {
				case 1:Insert();break;
				case 2:Update();break;
				case 3:Delete();break;
				case 4:listAll();break;
				}
			} catch (Exception e) {
			}
		}
	}

	public void listAll() {

		List<MatchScheduleDTO> list = mao.allMatch();
	      
	      System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
	      System.out.printf("%-6s\t%-18s\t%-20s\t%-6s\t%-20s\t%-6s\t%-18s\t%s","경기번호","경기일자","홈팀명","홈점수","어웨이팀명","어웨이점수","경기장명","주심\n");
	      System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
	      for (MatchScheduleDTO dto : list) {
	         System.out.printf("%-6s\t",dto.getMatchNumber());
	         System.out.printf("%-18s\t",dto.getMatchDate());
	         System.out.printf("%-20s\t",dto.getHomeName());
	         System.out.printf("%-6s\t",dto.getHomeScore());
	         System.out.printf("%-20s\t",dto.getAwayName());
	         System.out.printf("%-6s\t",dto.getAwayScore());
	         System.out.printf("%-18s\t",dto.getStadium());
	         System.out.println(dto.getMainReferee() + "\t");
	      }
	      System.out.println();
	}

	public void Delete() {
		System.out.println("\n경기일정 삭제");
		System.out.println("-------------------------------------");
		
		int matchnumber;
		
		try {
			System.out.print("삭제할 경기번호 ?");
			matchnumber = Integer.parseInt(br.readLine());
			
			int result = mao.deleteMatch(matchnumber);
			
			if (result > 0) {
				System.out.println("데이터가 삭제 되었습니다.");
			} else {
				System.out.println("등록된 자료가 아닙니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	public void Update() {
		System.out.println("\n[경기일정 수정]");
		System.out.println("-------------------------------------");

		try {
			MatchScheduleDTO dto = new MatchScheduleDTO();

			System.out.print("수정할 경기번호 ?");
			dto.setMatchNumber(Integer.parseInt(br.readLine()));

			System.out.print("수정할 경기일정 ?");
			dto.setMatchDate(br.readLine());

			System.out.print("수정할 홈팀 이름 ?");
			dto.setHomeName(br.readLine());
			dto.setHomenum(teamCode(dto.getHomeName()));

			System.out.print("수정할 원정팀 이름 ?");
			dto.setAwayName(br.readLine());
			dto.setAwaynum(teamCode(dto.getAwayName()));

			int result = mao.updateMatch(dto);

			if (result > 0) {
				System.out.println("데이터가 수정되었습니다.");
			} else {
				System.out.println("등록된 자료가 아닙니다.");
			}

		} catch (SQLException e) {
			if (e.getErrorCode() == 1407) {
				System.out.println("에러 - 필수사항을 입력하지 않습니다");
			} else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	public void Insert() {
		MatchScheduleDTO dto = new MatchScheduleDTO();
		try {
			System.out.println("경기 날짜?");
			dto.setMatchDate(br.readLine());

			System.out.println("팀 이름 종류 :리버풀 아스날 맨시티 애스턴 빌라 토트넘 맨유 웨스트햄 뉴캐슬 울버햄튼 브라이턴");
			System.out.print("홈팀 이름 ?");
			dto.setHomeName(br.readLine());
			dto.setHomenum(teamCode(dto.getHomeName()));

			
			System.out.println("팀 이름 종류 :리버풀 아스날 맨시티 애스턴 빌라 토트넘 맨유 웨스트햄 뉴캐슬 울버햄튼 브라이턴");
			System.out.print("원정팀 이름 ?");
			dto.setAwayName(br.readLine());
			dto.setAwaynum(teamCode(dto.getAwayName()));

			mao.insertMatch(dto);
			
			System.out.println("등록 완료!!!");

		} catch (SQLIntegrityConstraintViolationException e) {
			// 기본키 중복, NOT NULL 등의 제약조건을 위반할 때 발생하는 예외 - 무결성 제약조건 위반
			if (e.getErrorCode() == 1) {
				System.out.println("에러 - 등록된 경기번호입니다.");
			} else if (e.getErrorCode() == 1400) { // NOT NULL 제약 위반
				System.out.println("에러 - 필수입력 사항을 입력하지 않았습니다.");
			} else {
				System.out.println(e.toString());
			}
		} catch (SQLDataException e) {
			// 날짜등의 형식 잘못으로 인한 예외
			if (e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("에러 - 날짜 형식이 올바르지 않습니다.");
			} else {
				System.out.println(e.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int teamCode(String teamname) {
		id = 0;
		switch (teamname) {
		case "리버풀":id = 1;break;
		case "아스날":id = 2;break;
		case "맨시티":id = 3;break;
		case "애스턴 빌라":id = 4;break;
		case "토트넘":id = 5;break;
		case "맨유":id = 6;break;
		case "웨스트햄":id = 7;break;
		case "뉴캐슬":id = 8;break;
		case "브라이턴":id = 9;break;
		case "울버햄튼":id = 10;break;
		}
		return id;
	}
}
