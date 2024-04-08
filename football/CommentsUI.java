package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

public class CommentsUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private CommentsDAO dao = new CommentsDAOImpl();
	
//player
	//선수코멘트 관련 메인메뉴	
	public void player_menu() {
		int ch;
		
		while(true) {
			try {
				System.out.println("\n[선수 코멘트]");
				System.out.println("------------------------------");
				System.out.println("1.코멘트등록 2.코멘트조회 3.선수ID찾기 4.이전 ");
				System.out.println("------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {  //4가 되면 돌아감
					return;
				}
				switch(ch) {
				case 1: player_insert(); break;
				case 2: player_listAll(); break;
				case 3: findByPlayerName(); break;
				}
				
			} catch (Exception e) {
			}
		}
	}
	

// 선수코멘트 달기
	protected void player_insert() {
		System.out.println("\n[코멘트 달기]");
		System.out.println("-------------------------------------");
		//입력받은 정보를 dto에 저장하여 DB에 저장하거나 사용
		CommentsDTO dto = new CommentsDTO();
		
		try {
			System.out.print("닉네임 : ");
			dto.setCommentNick(br.readLine());
			System.out.print("선수ID : ");
			dto.setPlayerId(Integer.parseInt(br.readLine()));
			System.out.print("코멘트 : ");
			dto.setCommentCon(br.readLine());
			
			//현재 날짜를 컴퓨터의 시스템 날짜로 설정
			LocalDate now = LocalDate.now();
			dto.setCommentDate(now.toString());
			
			dao.player_insertComments(dto);

			System.out.println("\n코멘트가 등록되었습니다.");
		
		//기본키 중복	
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode() == 1) {
				System.out.println("에러 _ 등록된 닉네임입니다");
			} else if (e.getErrorCode() == 1400) {   
				System.out.println("에러 _ 필수입력 사항을 입력하지 않았음");  //NOT NULL제약 위반
			} else if(e.getErrorCode() == 2291) {
				System.out.println("!!존재하지 않는 선수ID입니다.");
			} else {
				System.out.println(e.toString());
			}
			
		//날짜형식
		} catch (SQLDataException e) {
			if(e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("에러 _ 날짜형식이 틀림");
			}
			
		//SQL예외
		} catch (SQLException e) {
			e.printStackTrace();
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
		
	//선수의 코멘트들 출력
	protected void player_listAll() {
		System.out.println("\n[코멘트 리스트]");
		System.out.println("-------------------------------------");
		int playerId;
		
		try {
			System.out.print("검색할 코멘트의 선수ID : ");
			playerId = Integer.parseInt(br.readLine()); 
			
			//DAO를 통해 선수ID로 해당선수의 코멘트리스트를 가져옴
			List<CommentsDTO> list = dao.player_listComments(playerId);
			
			if(list.size() == 0) {    //입력한 선수ID가 존재하는지 확인. 없으면 리턴
				System.out.println("\n!!해당 선수의 코멘트가 없습니다. \n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 출력
			for(CommentsDTO dto : list) {
				player_printcomments(dto);  //리스트 출력(아래 player_printcomments의 리스트들)
			}
			
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
				
	
	//출력되는 선수의 코멘트들
	protected void player_printcomments(CommentsDTO dto) {
		System.out.printf("번호: %-5s\t",dto.getCommentNumber());
	      System.out.printf("닉네임: %-8s\t",dto.getCommentNick());
	      System.out.printf("등록된 날짜: %-10s\t", dto.getCommentDate());
	      System.out.printf("코멘트: "+dto.getCommentCon());
	      System.out.println();
	}
	
	
	
	
	//선수이름으로 선수ID 찾기
	protected void findByPlayerName() {
		System.out.println("\n[선수ID 찾기]");
		System.out.println("-------------------------------------");
		
		String playerName;
		
		try {
			System.out.print("검색할 선수 이름 : ");
			playerName = br.readLine();
			
			//DAO를 통해 선수이름으로 검색하여 코멘트 리스트를 가져옴
			List<CommentsDTO> list = dao.FindByPlayerName(playerName);
			
			//코멘트 리스트의 크기가 0인 경우 등록된 선수가 아님을 알림
			if(list.size() == 0) {
				System.out.println("!!등록된 선수가 아닙니다.\n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 출력
			for(CommentsDTO dto : list) {
				playerNumber_(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	//출력되는 선수이름과 선수ID
	protected void playerNumber_(CommentsDTO dto) {
		System.out.print("\n선수이름 : "+dto.getPlayerName()+"\t");
		System.out.print("선수ID : "+dto.getPlayerId() + "\t" );
		System.out.println();
		
	}
	
	
//team
	//팀코멘트 관련 메인메뉴	
	public void team_menu() {
		int ch;
		
		while(true) {
			try {
				System.out.println("\n[팀 코멘트]");
				System.out.println("------------------------------");
				System.out.println("1.코멘트등록 2.코멘트조회 3.팀코드찾기 4.이전 ");
				System.out.println("------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {
					return;
				}
				
				switch(ch) {
				case 1: team_insert(); break;
				case 2: team_listAll(); break;
				case 3: findByTeamName(); break;
				}
				
			} catch (Exception e) {
			}
		}
	}
	
	//팀코멘트 달기
	protected void team_insert() {
		System.out.println("\n[코멘트 달기]");
		System.out.println("-------------------------------------");
		
		//입력받은 정보를 dto에 저장하여 DB에 저장하거나 사용
		CommentsDTO dto = new CommentsDTO();
		
		try {
			System.out.print("닉네임 : ");
			dto.setCommentNick(br.readLine());
			System.out.print("팀코드 : ");
			dto.setTeamCode(br.readLine());
			System.out.print("코멘트 : ");
			dto.setCommentCon(br.readLine());
			
			//현재 날짜를 컴퓨터의 시스템 날짜로 설정
			LocalDate now = LocalDate.now();
			dto.setCommentDate(now.toString());
			
			
			//DAO를 사용하여 코멘트를 DB에 삽입
			dao.team_insertComments(dto);
			
			System.out.println("\n코멘트가 등록되었습니다.");
		
			
			
		//기본키 중복	
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode() == 1) {
				System.out.println("에러 _ 등록된 닉네임입니다");
			} else if (e.getErrorCode() == 1400) {   
				System.out.println("에러 _ 필수입력 사항을 입력하지 않았음");  //NOT NULL제약 위반
			} else if(e.getErrorCode() == 2291) {
				System.out.println("!!존재하지 않는 팀 코드입니다.");
			} else {
				System.out.println(e.toString());
			}
		//날짜형식
		} catch (SQLDataException e) {
			if(e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("에러 _ 날짜형식이 틀림");
			}
		//SQL예외
		} catch (SQLException e) {
			e.printStackTrace();
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//팀의 코멘트들 출력
	protected void team_listAll() {
		
		String teamCode;
		
		try {
			System.out.println("\n[팀 코멘트]");
			System.out.println("-------------------------------------");
			
			System.out.print("검색할 코멘트의 팀코드 : ");
			teamCode = br.readLine(); 
			System.out.println();
			
			//DAO를 통해 해당 팀의 코멘트 리스트를 가져옴
			List<CommentsDTO> list = dao.team_listComments(teamCode);
			
			//가져온 코멘트 리스트가 비어있을 경우 해당 팀의 코멘트가 없음을 알림
			if(list.size() == 0) {
				System.out.println("\n!!해당 팀의 등록된 코멘트가 없습니다.\n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 각 코멘트를 출력
			for(CommentsDTO dto : list) {
				team_printcomments(dto);
			}
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//출력되는 팀의 코멘트들
	protected void team_printcomments(CommentsDTO dto) {
		System.out.printf("번호: %-5s\t",dto.getCommentNumber());
	      System.out.printf("닉네임: %-8s\t",dto.getCommentNick());
	      System.out.printf("등록된 날짜: %-10s\t", dto.getCommentDate());
	      System.out.printf("코멘트: "+dto.getCommentCon());
	      System.out.println();
	}
	
	//팀이름으로 팀Code 찾기
	protected void findByTeamName() {
		System.out.println("\n[팀코드 찾기]");
		System.out.println("-------------------------------------");
		String teamName;
		
		try {
			System.out.print("검색할 팀이름 : ");
			teamName = br.readLine();
			
			//DAO를 통해 팀 이름으로 검색하여 코멘트 리스트를 가져옴
			List<CommentsDTO> list = dao.FindByTeamName(teamName);
			
			if(list.size() == 0) {
				System.out.println("!!등록된 팀이 아닙니다.\n");
				return;
			}
			
			//코멘트 리스트가 비어있을 경우 등록된 팀이 아님을 알림
			for(CommentsDTO dto : list) {
				teamNumber_(dto);
			}
			
			
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
					//출력되는 팀이름과 팀Code
	protected void teamNumber_(CommentsDTO dto) {
		System.out.print("\n팀이름 : "+dto.getTeamName()+"\t");
		System.out.print("팀코드 : "+dto.getTeamCode() + "\t" );
		System.out.println();
		
	}
	
	//match
	//경기코멘트 관련 메인메뉴	
	public void match_menu() {
		int ch;
		
		
		while(true) {
			try {
				System.out.println("\n[경기 코멘트]");
				System.out.println("-----------------------------------");
				System.out.println("1.코멘트등록 2.코멘트조회 3.경기번호찾기 4.종료 ");
				System.out.println("-----------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {
					return;
				}
				
				switch(ch) {
				case 1: match_insert(); break;
				case 2: match_listAll(); break;
				case 3: findByMatchNumber(); break;
				}
				
			} catch (Exception e) {
			}
		}
	}
	
	//경기코멘트 달기
	protected void match_insert() {
		System.out.println("\n[코멘트 달기]");
		System.out.println("-------------------------------------");
		
		CommentsDTO dto = new CommentsDTO();
		
		try {
			System.out.print("닉네임 : ");
			dto.setCommentNick(br.readLine());
			System.out.print("경기번호 : ");
			dto.setMatchNumber(Integer.parseInt(br.readLine()));
			System.out.print("코멘트 : ");
			dto.setCommentCon(br.readLine());
			
			//현재 날짜를 컴퓨터의 시스템 날짜로 설정
			LocalDate now = LocalDate.now();
			dto.setCommentDate(now.toString());

			//DAO를 사용하여 코멘트를 DB에 삽입
			dao.match_insertComments(dto);
			
			System.out.println("\n코멘트가 등록되었습니다.");
	
		//기본키 중복	
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode() == 1) {
				System.out.println("에러 _ 등록된 닉네임입니다");
			} else if (e.getErrorCode() == 1400) {   
				System.out.println("에러 _ 필수입력 사항을 입력하지 않았음");  //NOT NULL제약 위반
			} else if(e.getErrorCode() == 2291) {
				System.out.println("!!존재하지 않는 경기번호입니다.");
			} else {
				System.out.println(e.toString());
			}
		//날짜형식
		} catch (SQLDataException e) {
			if(e.getErrorCode() == 1840 || e.getErrorCode() == 1861) {
				System.out.println("에러 _ 날짜형식이 틀림");
			}
		//SQL예외
		} catch (SQLException e) {
			e.printStackTrace();
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//경기의 코멘트들 출력
	protected void match_listAll() {
		System.out.println("\n[코멘트 리스트]");
		System.out.println("-------------------------------------");
		
		int matchNumber;
		
		try {
			
			System.out.print("검색할 코멘트의 경기번호 : ");
			matchNumber = Integer.parseInt(br.readLine()); 
			System.out.println();
			
			//DAO를 통해 해당 경기의 코멘트 리스트를 가져옴
			List<CommentsDTO> list = dao.match_listComments(matchNumber);
			
			//가져온 코멘트 리스트가 비어있을 경우 해당 경기의 코멘트가 없음을 알림
			if(list.size() == 0) {
				System.out.println("\n!!해당 경기의 코멘트가 없습니다.\n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 각 코멘트를 출력
			for(CommentsDTO dto : list) {
				match_printcomments(dto);
			}
			
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//출력되는 경기의 코멘트들
	protected void match_printcomments(CommentsDTO dto) {
		System.out.print("경기번호: "+dto.getMatchNumber() + "\t");
		System.out.print("닉네임: "+dto.getCommentNick() + "\t");
		System.out.print("코멘트: "+dto.getCommentCon() + "\t");
		System.out.println("등록된 날짜: "+dto.getCommentDate());
		
	}
	
	//경기날짜와 구장이름으로 경기번호 찾기
	protected void findByMatchNumber() {
		System.out.println("\n[경기번호 찾기]");
		System.out.println("-------------------------------------");
		String matchDate;
		String stadiumName;
		
		try {
			System.out.print("검색할 경기날짜 : ");
			matchDate = br.readLine();
			System.out.print("검색할 구장이름 : ");
			stadiumName = br.readLine(); 
			System.out.println();
			
			
			//DAO를 통해 경기 번호를 검색하여 코멘트 리스트를 가져옴
			List<CommentsDTO> list = dao.FindBymatchNumber(matchDate, stadiumName);
			
			//코멘트 리스트가 비어있을 경우 매칭된 경기가 없음을 알림
			if(list.size() == 0) {
				System.out.println("!!매칭된 경기가 없습니다.\n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 출력
			for(CommentsDTO dto : list) {
				matchNumber_(dto);
			}
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//출력되는 경기번호
	protected void matchNumber_(CommentsDTO dto) {
		System.out.print("경기번호 : "+dto.getMatchNumber() + "\t" );
		System.out.println();
		
	}	
}
