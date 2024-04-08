package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class PlayerUI_Admin {

	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private PlayerDAO dao = new PlayerDAOImpl();

	public void menu() {
		int ch;
		System.out.println("\n[선수관리]");

		while (true) {
			try {
					System.out.println("----------------------------------------------");
					System.out.println("1.선수등록 2.선수수정 3.선수삭제 4.전체리스트 5.뒤로가기");
					System.out.println("----------------------------------------------");
					System.out.print("선택=> ");

					ch = Integer.parseInt(br.readLine());
			
				if (ch == 5) {
					return;
				}
				switch (ch) {
				case 1:insert(); break;
				case 2:update(); break;
				case 3:delete(); break;
				case 4:listAll();break;

				}

			} catch (Exception e) {

			}
		}
	}

// 선수등록
	protected void insert() {
		System.out.println("\n[선수등록]");
		System.out.println("-------------------------------------");

		try {
			PlayerDTO dto = new PlayerDTO();
			
			System.out.print("선수번호 ? ");
			dto.setplayerId(Integer.parseInt(br.readLine()));

			System.out.print("이름 ? ");
			dto.setPlayerName(br.readLine());

			System.out.print("\n팀 번호?");
			System.out.println("\n--------------------------------------------");
			System.out.print("1.리버풀 2.아스날 3.멘시티 4.빌라 5.토트넘\n");
			System.out.print("6.맨유 7.뉴캐슬 8.웨스턴햄 9.브라이튼 10.울버햄튼\n");
			System.out.println("--------------------------------------------");
			dto.setTeamCode(br.readLine());

			System.out.print("포지션 ? ");
			dto.setPosition(br.readLine());

			System.out.print("등번호 ? ");
			dto.setUniformNum(Integer.parseInt(br.readLine()));

			System.out.print("생년월일 [YYYY-MM-DD] ? ");
			dto.setBirth(br.readLine());

			System.out.print("키 ? ");
			dto.setTall(Integer.parseInt(br.readLine()));

			System.out.print("국가 ? ");
			dto.setPlayerNation(br.readLine());

			dao.insertplayer(dto);

			System.out.println("등록 완료!!!");

		} catch (NumberFormatException e) {
			System.out.println("\n숫자만 입력 가능합니다.");
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode()==1) {
				System.out.println("에러 - 등록된 선수입니다!"); // 에러코드1번: 기본키 중복 에러.
			}else {
				System.out.println(e.toString());
			}
		} catch (SQLDataException e) {
			if(e.getErrorCode()==1840||e.getErrorCode()==1861) {
				System.out.println("에러 - 생년월일 형식이 올바르지 않습니다!");
			} else {
				System.out.println(e.toString());
			}
		} catch (SQLException e) {
			if(e.getErrorCode()==1400) { 
			System.out.println("에러 - 필수사항을 입력하지 않았습니다!");
			}else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//수정
	protected void update() {
		System.out.println("\n[선수수정]");
		System.out.println("-------------------------------------");
		
		try {
			PlayerDTO dto = new PlayerDTO();

			System.out.print("수정할 선수번호 ? ");
			dto.setplayerId(Integer.parseInt(br.readLine()));

			System.out.print("이름 ? ");
			dto.setPlayerName(br.readLine());

			System.out.println("\n--------------------------------------------");
			System.out.print("1.리버풀 2.아스날 3.멘시티 4.빌라 5.토트넘\n");
			System.out.print("6.맨유 7.뉴캐슬 8.웨스턴햄 9.브라이튼 10.울버햄튼\n");
			System.out.println("--------------------------------------------");
			System.out.print("\n팀 번호 ?");
			dto.setTeamCode(br.readLine());

			System.out.print("포지션 ? ");
			dto.setPosition(br.readLine());

			System.out.print("등번호 ? ");
			dto.setUniformNum(Integer.parseInt(br.readLine()));

			System.out.print("생년월일 [YYYY-MM-DD] ? ");
			dto.setBirth(br.readLine());

			System.out.print("키 ? ");
			dto.setTall(Integer.parseInt(br.readLine()));

			System.out.print("국가 ? ");
			dto.setPlayerNation(br.readLine());

			int result = dao.updateplayer(dto);

			if (result > 0) {
				System.out.println("선수데이터 수정 되었습니다.");
			} else {
				System.out.println("등록된 선수가 아닙니다.");
			}
			
		} catch (SQLDataException e) {
			if(e.getErrorCode()==1840|| e.getErrorCode()==1864) {
				System.out.println("에러 - 날짜 형식이 올바르지 않습니다!");
			}else {
				System.out.println(e.toString());
			}
			
		} catch (SQLException e) {
			if(e.getErrorCode()==1407) { //UPDATE-NOT NULL 제약 위반
			System.out.println("에러- 필수사항을 입력하지 않았습니다!");
			}else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// 선수삭제
	protected void delete() {
		System.out.println("\n[선수삭제]");
		System.out.println("-------------------------------------");

		int playerId;

		try {
			System.out.print("삭제할 아이디 ? ");
			playerId = Integer.parseInt(br.readLine());

			int result = dao.deleteplayer(playerId);

			if (result > 0) {
				System.out.println("선수데이터가 삭제되었습니다.");
			} else {
				System.out.println("등록된 선수가 아닙니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

	}

// 전체리스트
	protected void listAll() {
		System.out.println("\n[전체 리스트]");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-4s\t%-18s\t%-18s\t%-3s\t%-4s\t%-20s\t%-4s\t%s", "선수아이디", "선수이름", "팀이름", "포지션", "선수번호", "생일","키","국가\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");

		List<PlayerDTO> list = dao.listplayer();

		for (PlayerDTO dto : list) {

			System.out.printf("%-4s\t", dto.getplayerId());
			System.out.printf("%-18s\t", dto.getPlayerName());
			System.out.printf("%-18s\t", dto.getTeamName());
			System.out.printf("%-3s\t",dto.getPosition());
			System.out.printf("%-4s\t",dto.getUniformNum());
			System.out.printf("%-20s\t",dto.getBirth());
			System.out.printf("%-4s\t",dto.getTall());
			System.out.println(dto.getPlayerNation());
		}
		System.out.println();
	}
}
