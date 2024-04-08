package football;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class CommentsUI_admin {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private CommentsDAO_admin dao = new CommentsDAOImpl_admin();
	
//player
				//선수코멘트(관리) 메인메뉴
	public void player_menu() {
		int ch;
		
		while(true) {
			try {
				System.out.println("\n[선수 코멘트(관리)]");
				System.out.println("----------------------------------");
				System.out.println("1.코멘트조회 2.코멘트삭제 3.선수ID찾기 4.이전 ");
				System.out.println("----------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {
					return;
				}
				
				switch (ch) {
				case 1: player_listAll(); break;
				case 2: player_delete(); break;
				case 3: findByPlayerName(); break;
				}
				
				
			} catch (Exception e) {		
			}
		}
	}		
		
	//선수코멘트 삭제
	private void player_delete() {
		System.out.println("\n[코멘트 삭제]");
		System.out.println("-------------------------------------");
		
		int commentNumber;
		
		try {
			System.out.print("삭제할 코멘트 번호 : ");
			commentNumber = Integer.parseInt(br.readLine());
			
			int result = dao.player_deletecomments(commentNumber);
			
			
			if(result > 0) {
				System.out.println("\n해당 코멘트가 삭제되었습니다.");
			} else {
				
				System.out.println("\n!!존재하지 않는 코맨트입니다.");
			}
			
			
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode()==1400) {
				System.out.println("에러 _ 필수입력 사항을 입력하지 않았음");
			}/*else if(e.getErrorCode() == 2291) {
				System.out.println("존재하지 않는 데이터입니다.");
			}*/ else {
				System.out.println(e.toString());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	//선수코멘트리스트 출력
	private void player_listAll() {
		System.out.println("\n[코멘트 리스트]");
		System.out.println("-------------------------------------");
		
		int playerId;
		
		try {
			System.out.print("검색할 코멘트의 선수ID : ");
			playerId = Integer.parseInt(br.readLine());
			
			//DAO를 통해 선수ID로 해당선수의 코멘트리스트를 가져옴
			List<CommentsDTO_admin> list = dao.player_listComments(playerId);
			
			
			if(list.size() == 0) {    //입력한 선수ID가 존재하는지 확인
				System.out.println("!!해당 선수의 코멘트가 없습니다.\n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 출력
			for(CommentsDTO_admin dto : list) {
				player_printcomments(dto); //리스트 출력(아래 player_printcomments의 리스트들)
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	//선수코멘트리스트들
	protected void player_printcomments(CommentsDTO_admin dto) {
		System.out.print("\n번호: "+dto.getCommentNumber()+"\t");
		System.out.print("닉네임: "+dto.getCommentNick() + "\t" );
		System.out.print("선수코드: "+dto.getPlayerId() + "\t" );
		System.out.print("등록된 날짜: "+dto.getCommentDate()+"\t");
		System.out.print("코멘트: "+dto.getCommentCon() + "\t" );
		
		
	}

	//선수이름으로 선수ID 조회
	protected void findByPlayerName() {
		System.out.println("\n[선수 ID 찾기]");
		System.out.println("-------------------------------------");
		
		String playerName;
		
		try {
			System.out.print("검색할 선수이름 : ");
			playerName = br.readLine();
			
			//DAO를 통헤 선수이름으로 검색하여 코멘트리스트 가져옴
			List<CommentsDTO_admin> list = dao.FinbByPlayerName(playerName);
			
			//코멘트 리스트의 크기가 0인 경우 등록된 선수가 아님을 알림
			if(list.size() == 0) {
				System.out.println("!!등록된 선수가 아닙니다.\n");
				return;
			}
			
			//코멘트 리스트를 반복하면서 출력
			for(CommentsDTO_admin dto : list) {
				playerNumber_(dto);
			}
			
		//전체예외
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//출력되는 선수이름과 선수ID
	protected void playerNumber_(CommentsDTO_admin dto) {
		System.out.print("\n선수이름: "+dto.getPlayerName()+"\t");
		System.out.print("선수 ID: "+dto.getPlayerId() + "\t" );
		System.out.println();
		
	}
	
	
//team
	//팀코멘트(관리) 메인메뉴
	public void team_menu() {
		int ch;
		
		while(true) {
			try {
				System.out.println("\n[팀 코멘트(관리)]");
				System.out.println("----------------------------------");
				System.out.println("1.코멘트조회 2.코멘트삭제 3.팀코드찾기 4.이전 ");
				System.out.println("----------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {
					return;
				}
				
				switch (ch) {
				case 1: team_listAll(); break;
				case 2: team_delete(); break;
				case 3: findByTeamName(); break;
				}
			} catch (Exception e) {
			}
		}
	}		
		
	
				//팀코멘트 삭제
	private void team_delete() {
		System.out.println("\n[코멘트 삭제]");
		
		int commentNumber;
		
		try {
			System.out.print("\n삭제할 코멘트 번호 : ");
			commentNumber = Integer.parseInt(br.readLine());
			
			int result = dao.team_deletecomments(commentNumber);
			
			
			if(result > 0) {    //삭제된 행의 수가 1보다 크면 코멘트가 삭제된 것을 알림
				System.out.println("\n해당 코멘트가 삭제되었습니다.");
			} else {
								//삭제된 행의 수가 0이면 존재하지 않는 코멘트임을 알림
				System.out.println("!!존재하지 않는 코맨트입니다.");
			}
			
			
		//기본키 중복
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode()==1400) {
				System.out.println("에러 _ 필수입력 사항을 입력하지 않았음");
			} /*else if(e.getErrorCode() == 2291) {
				System.out.println("존재하지 않는 코멘트입니다.");
			}*/ else {
				System.out.println(e.toString());
			}
		//전체예외
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
				//팀코멘트리스트 출력
	private void team_listAll() {
		
		String teamCode;
		
		try {			
			System.out.println("\n[코멘트 리스트]");
			System.out.print("검색할 코멘트의 팀코드 : ");
			teamCode = br.readLine();
			System.out.println();
			
			List<CommentsDTO_admin> list = dao.team_listComments(teamCode);
			
			
			if(list.size() == 0) {
				System.out.println("!!해당 팀의 등록된 코멘트가 없습니다.\n");
				return;
			}
			
			for(CommentsDTO_admin dto : list) {
				//리스트
				team_printcomments(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	//팀코멘트리스트들
	   protected void team_printcomments(CommentsDTO_admin dto) {
		      // System.out.printf("번호: "+dto.getCommentNumber()+"\t");
		      System.out.printf("번호: %-5s\t",dto.getCommentNumber());
		      System.out.printf("닉네임: %-8s\t",dto.getCommentNick());
		      System.out.printf("팀번호: %-6s\t",dto.getTeamCode());
		      System.out.printf("등록된 날짜: %-10s\t", dto.getCommentDate());
		      System.out.printf("코멘트: "+dto.getCommentCon());
		      System.out.println();
		   }
	
	//팀이름으로 팀Code찾기
	protected void findByTeamName() {
		System.out.println("\n[팀코드 찾기]");
		
		String teamName;
		
		try {
			System.out.print("검색할 팀이름 : ");
			teamName = br.readLine();
			
			List<CommentsDTO_admin> list = dao.FinbByTeamName(teamName);
			
			if(list.size() == 0) {
				System.out.println("!!등록된 팀이 아닙니다.\n");
				return;
			}
			
			for(CommentsDTO_admin dto : list) {
				teamNumber_(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
				
	protected void teamNumber_(CommentsDTO_admin dto) {
		System.out.print("\n팀 이름: "+dto.getTeamName()+"\t");
		System.out.print("팀 코드: "+dto.getTeamCode() + "\t" );
		System.out.println();
		
	}
	

//match
	//경기코멘트(관리) 메인메뉴
	public void match_menu() {
		int ch;
		
		while(true) {
			try {
				System.out.println("\n[경기 코멘트(관리)]");
				System.out.println("-----------------------------------");
				System.out.println("1.코멘트조회 2.코멘트삭제 3.경기번호찾기 4.이전 ");
				System.out.println("-----------------------------------");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {
					return;
				}
				
				switch (ch) {
				case 1: match_listAll(); break;
				case 2: match_delete(); break;
				case 3: findByMatchNumber(); break;
				}
				
			} catch (Exception e) {
				
			}
		}
	}
	
	//경기코멘트 삭제
	private void match_delete() {
		System.out.println("\n[코멘트 삭제]");
		int commentNumber;
		
		try {
			System.out.print("삭제할 코멘트 번호 : ");
			commentNumber = Integer.parseInt(br.readLine());
			
			int result = dao.match_deletecomments(commentNumber);
			
			
			if(result > 0) {
				System.out.println("해당 코멘트가 삭제되었습니다.");
			} else {
				
				System.out.println("!!존재하지 않는 코멘트입니다.");
			}
			
			
			
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode()==1400) {
				System.out.println("에러 _ 필수입력 사항을 입력하지 않았음");
			} /*else if(e.getErrorCode() == 2291) {
				System.out.println("존재하지 않는 데이터입니다.");
			}*/ else {
				System.out.println(e.toString());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	//경기코멘트리스트 출력
	private void match_listAll() {
		System.out.println("\n[코멘트 리스트]");
		
		int matchNumber;
		
		try {
			
			System.out.print("검색할 코멘트의 경기번호 : ");
			matchNumber = Integer.parseInt(br.readLine()) ;
			
			List<CommentsDTO_admin> list = dao.match_listComments(matchNumber);
			
			
			if(list.size() == 0) {
				System.out.println("\n!!해당 경기의 코멘트가 없습니다.\n");
				return;
			}
			
			for(CommentsDTO_admin dto : list) {
				//리스트
				match_printcomments(dto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	//경기코멘트리스트들
	protected void match_printcomments(CommentsDTO_admin dto) {
		System.out.print("\n번호: "+dto.getCommentNumber()+"\t");
		System.out.print("닉네임: "+dto.getCommentNick() + "\t" );
		System.out.print("경기번호: "+dto.getMatchNumber() + "\t" );
		System.out.print("코멘트: "+dto.getCommentCon() + "\t" );
		System.out.print("등록된 날짜: "+dto.getCommentDate()+"\t");
		System.out.println();
	}
	
	//경기날짜와 구장이름으로 경기번호 조회
	protected void findByMatchNumber() {
		System.out.println("\n[경기번호 찾기]");
		String matchDate;
		String stadiumName;
		
		try {
			System.out.print("검색할 경기날짜 : ");
			matchDate = br.readLine();
			System.out.print("검색할 구장이름 : ");
			stadiumName = br.readLine();
			System.out.println();
			
			List<CommentsDTO_admin> list = dao.FindBymatchNumber(matchDate, stadiumName);
			
			if(list.size() == 0) {
				System.out.println("!!매칭된 경기가 없습니다.\n");
				return;
			}
			
			for(CommentsDTO_admin dto : list) {
				matchNumber_(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	//출력되는 경기번호
	protected void matchNumber_(CommentsDTO_admin dto) {
		System.out.print("경기번호 : "+dto.getMatchNumber() + "\t" );
		System.out.println();
		
	}
	

	
}