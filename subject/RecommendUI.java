<<<<<<< HEAD
package subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;



public class RecommendUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Book bkImpl = null;
	private Recommend reco = new RecommedImpl();
	
	public RecommendUI(Book bkImpl) {
		this.bkImpl = bkImpl;
	}
	
	
	// 추천도서관리 메뉴
	public void  menu() {
		int ch;
		
		while(true) {
			try {
				do {
					System.out.println("\t\t[추천도서 관리]");
					System.out.println("-----------------------------------------------------------");
					System.out.println("1.추천도서 등록 2.책이름 검색 3.장르별 검색 4.추천도서 리스트 5. 뒤로가기\n");
					System.out.println("선택 => ");
					
					ch = Integer.parseInt(br.readLine());
				    } while(ch < 1 || ch > 5);
				   
				if(ch==5) { 
				  return; // main 으로 돌아가기.
				}
				    
				    switch(ch) {
					case 1 : insert(); break;
					case 2 : search(); break;
					case 3 : genre(); break;
					case 4 : list(); break;
					
				}
			} catch (Exception e) {
				
			}
		}
	}
	

	//1. 추천도서등록 
	public void insert() {
		
		System.out.println("\n[추천도서 등록]");
		String number;
		
		try {
			System.out.print("도서번호 : ");  //도서번호를 입력
			number = br.readLine();
			
			// BookVO 받아오기   //기존에 도서리스트에서 확인후 입력받게 하기.
			BookVO bvo = bkImpl.readNumber(number); 
				
		  if(bvo == null ) {   // 기존도서랑 일치하지 않으면 사라짐. 
			 System.out.println("존재하지 않는 도서입니다.");
			 return;
			}	
	  
		// 객체 받아와서 리스트에 저장
	 	RecommendVO vo = new RecommendVO();
	 	vo.setNumber(bvo.getNumber());
	 	vo.setGenre(bvo.getGenre());
	 	vo.setWriter(bvo.getWriter());
	 	vo.setName(bvo.getName());
	 	vo.setPub(bvo.getPub());
	 	
		reco.insertRecommend(vo); //내 리스트에 저장.
		
			System.out.println("등록완료\n");
	  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//2. 책이름으로 검색 (search) 
	protected void search() {
		System.out.println("\n[추천도서검색]");	
		
		String name; 
		
		try {
			System.out.print("추천받을 책 이름 : ");
			name = br.readLine();
			
			List<RecommendVO> vo = reco.listRecommend(name); 
			
			if(vo.size()==0) { 
				System.out.println("등록된 도서명이 없습니다.");
				return;
			}
			System.out.println("도서번호 \t이름 \t작가 \t출판사 \t장르");
			System.out.println("-----------------------------------------------------------");
			
		for(RecommendVO list : vo) {
			System.out.print(list.getNumber()+"\t");
			System.out.print(list.getName()+"\t");
			System.out.print(list.getWriter()+"\t");
			System.out.print(list.getPub()+"\t");
			System.out.println(list.getGenre());
		}
		System.out.println();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3. 장르별검색 (genre) 
	protected void genre() {
		System.out.println("\n[장르별검색]");
		
		String genre;
		
		try {
			System.out.print("장르 : ");
			genre =br.readLine();
			
			List<RecommendVO> vo = reco.listRecommendG(genre); 		
			
			if(vo.size() == 0) {
				System.out.println("등록되지 않은 장르입니다.");
			    return;
			}
			
			System.out.println("도서번호 \t이름 \t작가 \t출판사 \t장르");
			System.out.println("-----------------------------------------------------------");
		
		for(RecommendVO list : vo) {
			System.out.print(list.getNumber()+"\t");
			System.out.print(list.getName()+"\t");
			System.out.print(list.getWriter()+"\t");
			System.out.print(list.getPub()+"\t");
			System.out.println(list.getGenre());
	
		}
			System.out.println();
		} catch (Exception e) {
	
		}
	}
	
	//4. 추천도서 리스트(list) 
	protected void list() {
		System.out.println("\n[추천도서리스트]");
		
		List<RecommendVO> list = reco.listRecommend();
		
		System.out.println("도서번호 \t이름 \t작가 \t출판사 \t장르");
		System.out.println("-----------------------------------------------------------");
		for(RecommendVO vo : list) {
			System.out.print(vo.getNumber()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getWriter()+"\t");
			System.out.print(vo.getPub()+"\t");
			System.out.println(vo.getGenre());
		}
		System.out.println();
	}
	
}
=======
package subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;



public class RecommendUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Book bkImpl = null;
	private Recommend reco = new RecommedImpl();
	
	public RecommendUI(Book bkImpl) {
		this.bkImpl = bkImpl;
	}
	
	
	// 추천도서관리 메뉴
	public void  menu() {
		int ch;
		
		while(true) {
			try {
				do {
					System.out.println("\t\t[추천도서 관리]");
					System.out.println("-----------------------------------------------------------");
					System.out.println("1.추천도서 등록 2.책이름 검색 3.장르별 검색 4.추천도서 리스트 5. 뒤로가기\n");
					System.out.println("선택 => ");
					
					ch = Integer.parseInt(br.readLine());
				    } while(ch < 1 || ch > 5);
				   
				if(ch==5) { 
				  return; // main 으로 돌아가기.
				}
				    
				    switch(ch) {
					case 1 : insert(); break;
					case 2 : search(); break;
					case 3 : genre(); break;
					case 4 : list(); break;
					
				}
			} catch (Exception e) {
				
			}
		}
	}
	

	//1. 추천도서등록 
	public void insert() {
		
		System.out.println("\n[추천도서 등록]");
		String number;
		
		try {
			System.out.print("도서번호 : ");  //도서번호를 입력
			number = br.readLine();
			
			// BookVO 받아오기   //기존에 도서리스트에서 확인후 입력받게 하기.
			BookVO bvo = bkImpl.readNumber(number); 
				
		  if(bvo == null ) {   // 기존도서랑 일치하지 않으면 사라짐. 
			 System.out.println("존재하지 않는 도서입니다.");
			 return;
			}	
	  
		// 객체 받아와서 리스트에 저장
	 	RecommendVO vo = new RecommendVO();
	 	vo.setNumber(bvo.getNumber());
	 	vo.setGenre(bvo.getGenre());
	 	vo.setWriter(bvo.getWriter());
	 	vo.setName(bvo.getName());
	 	vo.setPub(bvo.getPub());
	 	
		reco.insertRecommend(vo); //내 리스트에 저장.
		
			System.out.println("등록완료\n");
	  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//2. 책이름으로 검색 (search) 
	protected void search() {
		System.out.println("\n[추천도서검색]");	
		
		String name; 
		
		try {
			System.out.print("추천받을 책 이름 : ");
			name = br.readLine();
			
			List<RecommendVO> vo = reco.listRecommend(name); 
			
			if(vo.size()==0) { 
				System.out.println("등록된 도서명이 없습니다.");
				return;
			}
			System.out.println("도서번호 \t이름 \t작가 \t출판사 \t장르");
			System.out.println("-----------------------------------------------------------");
			
		for(RecommendVO list : vo) {
			System.out.print(list.getNumber()+"\t");
			System.out.print(list.getName()+"\t");
			System.out.print(list.getWriter()+"\t");
			System.out.print(list.getPub()+"\t");
			System.out.println(list.getGenre());
		}
		System.out.println();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3. 장르별검색 (genre) 
	protected void genre() {
		System.out.println("\n[장르별검색]");
		
		String genre;
		
		try {
			System.out.print("장르 : ");
			genre =br.readLine();
			
			List<RecommendVO> vo = reco.listRecommendG(genre); 		
			
			if(vo.size() == 0) {
				System.out.println("등록되지 않은 장르입니다.");
			    return;
			}
			
			System.out.println("도서번호 \t이름 \t작가 \t출판사 \t장르");
			System.out.println("-----------------------------------------------------------");
		
		for(RecommendVO list : vo) {
			System.out.print(list.getNumber()+"\t");
			System.out.print(list.getName()+"\t");
			System.out.print(list.getWriter()+"\t");
			System.out.print(list.getPub()+"\t");
			System.out.println(list.getGenre());
	
		}
			System.out.println();
		} catch (Exception e) {
	
		}
	}
	
	//4. 추천도서 리스트(list) 
	protected void list() {
		System.out.println("\n[추천도서리스트]");
		
		List<RecommendVO> list = reco.listRecommend();
		
		System.out.println("도서번호 \t이름 \t작가 \t출판사 \t장르");
		System.out.println("-----------------------------------------------------------");
		for(RecommendVO vo : list) {
			System.out.print(vo.getNumber()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getWriter()+"\t");
			System.out.print(vo.getPub()+"\t");
			System.out.println(vo.getGenre());
		}
		System.out.println();
	}
	
}
>>>>>>> c1dce160f423e1166a16664b95b883140ea323f5
