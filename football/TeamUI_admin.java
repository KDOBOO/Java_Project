package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class TeamUI_admin {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private TeamDAO dao = new TeamDAOImpl();
	
	public void menu() {
		int ch;

		System.out.println("\n[팀]");
		
		while (true) {
			try {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("1.팀 추가 2.팀 수정 3.팀 삭제 4.팀 목록 5.뒤로가기");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("선택=> ");
				ch=Integer.parseInt(br.readLine());
				
				if(ch==5) {
					break;
				}
				switch(ch) {
				case 1: insertteam(); break;
				case 2: updateteam(); break;
				case 3: deleteteam();break;
				case 4 :listteam();break;
				}
				
			} catch (Exception e) {
			}
			
		}
	}
	public void insertteam() {
		System.out.println("\n[팀 등록]");
		System.out.println("-------------------------------------");
		
		TeamDTO dto=new TeamDTO();
		
		try {
			System.out.print("팀 번호 ➜");
			dto.setTeamCode(br.readLine());
			System.out.print("팀 이름 ➜");
			dto.setTeamName(br.readLine());
			System.out.print("팀 감독 ➜");
			dto.setHeadcoach(br.readLine());
			System.out.print("팀 주장 ➜");
			dto.setCaptain(br.readLine());
			System.out.print("팀 스타디움 ➜");
			dto.setStadiumName(br.readLine());
			System.out.print("팀 스타디움 위치 ➜");
			dto.setRegion(br.readLine());
			
			dao.insertTeam(dto);
			
			System.out.println("팀 등록 완료!!!");
		}catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode()==1) {
				System.out.println("등록된 팀입니다.");
			}else if(e.getErrorCode()==1400) { 
				System.out.println("필수입력 사항을 입력하지 않았습니다.");
			}else {
				System.out.println(e.toString());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateteam() {
		System.out.println("\n[팀 수정]");
		System.out.println("-------------------------------------");
		
		try {
			TeamDTO dto=new TeamDTO();
			
			System.out.print("수정할 팀 번호 ➜");
			dto.setTeamCode(br.readLine());
			System.out.print("수정할 팀 이름 ➜");
			dto.setTeamName(br.readLine());
			System.out.print("수정할 팀 감독 ➜");
			dto.setHeadcoach(br.readLine());
			System.out.print("수정할 팀 주장 ➜");
			dto.setCaptain(br.readLine());
			System.out.print("수정할 팀 스타디움 ➜");
			dto.setStadiumName(br.readLine());
			System.out.print("수정할 팀 스타디움 위치 ➜");
			dto.setRegion(br.readLine());
			
			int result =dao.updateTeam(dto);
			
			if(result>0) {
				System.out.println("데이터가 수정되었습니다.");
			}else {
				System.out.println("등록된 자료가 아닙니다.");
			} 
			
		} catch (SQLException e) {
			if(e.getErrorCode()==1407) {
				System.out.println("필수 사항을 입력하지 않았습니다");
			}else {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deleteteam() {
		System.out.println("\n[팀 삭제]");
		System.out.println("-------------------------------------");
		
		String teamCode;
		
		try {
			System.out.print("삭제할 팀 코드 : ");
			teamCode = br.readLine();
			
			int result=dao.deleteTeam(teamCode);
			
			if(result>0) {
				System.out.println("데이터가 삭제되었습니다.");
			}else {
				System.out.println("등록된 자료가 아닙니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}
	public void listteam() {
        System.out.println("\n[팀 전체 리스트]");
        
        List<TeamDTO> list = dao.listTeam();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-6s\t%-18s\t%-18s\t%-18s\t%-18s\t%s","팀코드","팀명","감독","주장","홈경기장","경기장지역\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for(TeamDTO dto : list) {
           printteam(dto);
        }
        System.out.println();
     }
     
     private void printteam(TeamDTO dto) {
        System.out.printf("%-6S",dto.getTeamCode());
        System.out.printf("%-18S\t",dto.getTeamName());
        System.out.printf("%-18S\t",dto.getHeadcoach());
        System.out.printf("%-18S\t",dto.getCaptain());
        System.out.printf("%-18S\t",dto.getStadiumName());
        System.out.println(dto.getRegion()+"\t");
     }
  }

