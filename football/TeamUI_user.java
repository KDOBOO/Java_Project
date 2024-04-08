package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class TeamUI_user {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private TeamDAO dao = new TeamDAOImpl();
	private MatchSchedulDAO dao1 = new MatchSchedulDAO();
	private PlayerDAO dao2 = new PlayerDAOImpl();
	
	public void menu() {
		int ch;
		
		System.out.println("\n[팀]");
		
		while (true) {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("1.팀정보 2.팀 기록 3.팀 선수 4.뒤로가기");
				System.out.println("---------------------------------------------");
				System.out.print("선택=> ");
				ch=Integer.parseInt(br.readLine());
				
				if(ch==4) {
					break;
				}
				switch(ch) {
				case 1: teaminfo(); break;
				case 2: teamrecode(); break;
				case 3 :teammember(); break;
				}
				
			} catch (Exception e) {
			}
		}
	}
	public void teaminfo() {
		System.out.println("\n[팀 정보]");
		
		String name;
		try {
			System.out.print("검색할 팀 이름 : ");
			name =br.readLine();
			
			List<TeamDTO> list = dao.listTeam(name);
			
			if(list.size() ==0) {
				System.out.println("등록된 팀이 아닙니다.\n");
				return;
			}
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("    [팀명]\t\t\t\t[감독]\t\t\t[주장]\t\t\t[홈경기장]\t\t[경기장지역]");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			for(TeamDTO dto : list ) {
				printteam(dto);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	public void teamrecode() {
		System.out.println("\n[팀 매치 기록]");
		
		List<TeamDTO> list =dao.listTeam();
		String code;
		
		for(TeamDTO dto : list) {
			System.out.println("------------------------");
			System.out.printf("%-4S\t%-27S","팀번호","팀이름");
			System.out.println();
			System.out.printf("%-4S\t",dto.getTeamCode());
		    System.out.printf("%-27S\t",dto.getTeamName());
		    System.out.println();
	      }
	      System.out.println();
		try {
		      System.out.print("팀 번호를 고르세요 ➜");
		      code=br.readLine();
		      List<MatchScheduleDTO> list2 =dao1.teammatchRecode(code);
		      
		      	System.out.println();
		      	System.out.printf("%-15s\t", "[경기날짜]");
				System.out.printf("%-25s\t", "[홈팀]");
				System.out.printf("%-15s\t", "[홈팀 득점]");
				System.out.printf("%-25s\t", "[원정팀]");
				System.out.printf("%-15s\t", "[원정팀 득점]");
				System.out.printf("%-25s\t", "[경기장]");
				System.out.printf("%-25s\t", "[주심]\n");
		
				System.out.println();
				
				for (MatchScheduleDTO dto : list2) {
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
		      
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	public void teammember() {
		System.out.println("\n[팀 선수 목록]");
		List<TeamDTO> list =dao.listTeam();
		String teamcode;
		
		for(TeamDTO dto : list) {
			 System.out.printf("%-4S",dto.getTeamCode());
		      System.out.printf("%-27S\n",dto.getTeamName());
		      
	      }
		
		try {
			System.out.print("팀 번호를 고르세요 ➜");
			teamcode =br.readLine();
			
			System.out.println();
			System.out.printf("%-5s", "ID");
			System.out.printf("%-22s\t", "선수이름");
			System.out.printf("%-20s\t", "팀이름");
			System.out.println("포지션\t선수번호\t생일\t\t키\t국가\n");
			
			List<PlayerDTO> list2 = dao2.teamlistplayer(teamcode);

			for (PlayerDTO dto : list2) {

				System.out.printf("%-5s", dto.getplayerId());
				System.out.printf("%-22s\t", dto.getPlayerName());
				System.out.printf("%-20s\t", dto.getTeamName());
				System.out.print(dto.getPosition() + "\t");
				System.out.print(dto.getUniformNum() + "\t");
				System.out.print(dto.getBirth() + "\t");
				System.out.print(dto.getTall() + "\t");
				System.out.println(dto.getPlayerNation() + "\t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	   private void printteam(TeamDTO dto) {
		      System.out.printf("%-4S",dto.getTeamCode());
		      System.out.printf("%-27S\t",dto.getTeamName());
		      System.out.printf("%-15S\t",dto.getHeadcoach());
		      System.out.printf("%-15S\t",dto.getCaptain());
		      System.out.printf("%-15S\t",dto.getStadiumName());
		      System.out.println(dto.getRegion()+"\t");
		   }
}
