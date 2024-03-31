package subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;




//1.도서등록  2.도서수정  3.도서명검색  4.도서전체조회  5.도서삭제  6.뒤로가기
//선택 =>
public class BookUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private Book bkImpl = null;
	
	public BookUI(Book bkImpl) {
		this.bkImpl = bkImpl;
	}
	
	// 도서 관리 메뉴창
	public void menu() {
		
		int ch;
		
		while(true) {
			try {
				do {
					System.out.println("\t\t[도서 관리]");
					System.out.println("------------------------------------------------");
					System.out.println("1.도서등록 2.도서수정 3. 도서검색 4.도서리스트 5.도서 삭제 6.뒤로가기 ");
					System.out.println("선택 => ");
					ch = Integer.parseInt(br.readLine());
				}while(ch < 1 || ch > 7);
				
				if(ch == 6) {
					break;
				}
				
				switch(ch) {
				case 1:insert(); break;
				case 2:update(); break;
				case 3:findByName(); break;
				case 4:showbooks(); break;
				case 5:delete(); break;
				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	//도서등록
	protected void insert() {
	
		System.out.println("\n[도서등록]");
		BookVO vo = new BookVO();
		
		try {
			
		System.out.print("도서 이름 :");
		vo.setName(br.readLine());
		System.out.print("출판사 :");
		vo.setPub(br.readLine());
		System.out.print("글쓴이 :");
		vo.setWriter(br.readLine());
		System.out.print("장르 :");
		vo.setGenre(br.readLine());
		
		// 등록일 자동입력
		vo.setRegDate(Calendar.getInstance());
		
		vo.setNumber(bkImpl.generatenumber());
		
		bkImpl.insertBook(vo);
	
			
		
		
		}catch (MyDuplicationException e) {
			System.out.println("등록된 책입니다.\n");
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	//도서수정
	protected void update() {
		System.out.println("\n[도서수정]");
		
		String num;
		try {
			System.out.println("수정할 도서 번호 :");
			num=br.readLine();
			
			BookVO vo = bkImpl.readNumber(num);					//입력받은 도서 번호로 도서를 조회합니다.
			if(vo == null) {      							//만약 조회 결과가 null이라면 등록된 도서가 아니므로,
				System.out.println("등록된 도서가 아닙니다.\n");	//사용자에게 메시지를 출력하고 메소드를 종료합니다.
				return;
			}
			
			System.out.println("도서이름 수정=>");
			vo.setName(br.readLine());
			System.out.println("출판사 수정=>");
			vo.setPub(br.readLine());
			System.out.println("글쓴이 수정=>");
			vo.setWriter(br.readLine());
			System.out.println("장르 수정=>");
			vo.setGenre(br.readLine());
			
			System.out.println("수정이 완료되었습니다.\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//도서명 검색
	protected void findByName() {
		System.out.println("\n[도서명검색]");
		int ch;
		
		
			try {
				do {
					System.out.println("1.도서명으로 검색 \n2.도서정보(도서명,글쓴이)로 검색 \n3.도서번호로 검색  => ");
					ch = Integer.parseInt(br.readLine());
				}while(ch < 1 || ch > 2);
				
				switch(ch) {
				case 1:{
					String name;
					try {
						System.out.println("검색할 도서명 : ");
						name = br.readLine();
						
						List<BookVO>list = bkImpl.listBook(name);
						
						if(list.size()==0) {
							System.out.println("등록된 도서가 없습니다.\n");
							return;
						}
						System.out.println("[검색된 도서]");
						for(BookVO vo:list) {
							System.out.print(vo.getName()+"\t");
							System.out.print(vo.getNumber()+"\t");
							System.out.print(vo.getPub()+"\t");
							System.out.print(vo.getWriter()+"\t");
							System.out.print(vo.getGenre()+"\t");
							Calendar regDate = vo.getRegDate();
							SimpleDateFormat add = new SimpleDateFormat("yyyy-MM-dd");
							System.out.print(add.format(regDate.getTime())+ "\t");
							System.out.println();
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}break;
				case 2:{
					String name;
					String writer;
					System.out.println("검색할 도서 이름=>");
					name =br.readLine();
					System.out.println("검색할 도서의 글쓴이=>");
					writer =br.readLine();
					
					BookVO vo = bkImpl.readBook(name, writer);
					if(vo == null) {
						System.out.println("등록된 도서가 아닙니다.\n");
						return;
					}
						System.out.println("[검색된 도서]");
						System.out.print(vo.getName()+"\t");
						System.out.print(vo.getNumber()+"\t");
						System.out.print(vo.getPub()+"\t");
						System.out.print(vo.getWriter()+"\t");
						System.out.print(vo.getGenre()+"\t");
						Calendar regDate = vo.getRegDate();
						SimpleDateFormat add = new SimpleDateFormat("yyyy-MM-dd");
						System.out.print(add.format(regDate.getTime())+ "\t");
						System.out.println();
						
					} break;
					
				//도서번호로 검색
				case 3: {
					String s;
					System.out.println("검색할 도서 번호 =>");
					s=br.readLine();
					
					BookVO vo=bkImpl.readNumber(s);
					if(vo==null) {
						System.out.println("등록된 도서가 아닙니다");
						return;
					}
					System.out.println("[검색된 도서]");
					System.out.print(vo.getName()+"\t");
					System.out.print(vo.getNumber()+"\t");
					System.out.print(vo.getPub()+"\t");
					System.out.print(vo.getWriter()+"\t");
					System.out.print(vo.getGenre()+"\t");
					Calendar regDate = vo.getRegDate();
					SimpleDateFormat add = new SimpleDateFormat("yyyy-MM-dd");
					System.out.print(add.format(regDate.getTime())+ "\t");
					System.out.println();
					
					
				}break;
				 
				}
				}catch (Exception e) {
			}
		
	}
	
	
	//도서 리스트 출력
	protected void showbooks() {
		System.out.println("\t\t[도서리스트]");
		System.out.print("도서이름\t");
		System.out.print("도서번호\t");
		System.out.print("출판사\t");
		System.out.print("글쓴이\t");
		System.out.print("장르\t");
		System.out.println("등록일\t");

		
		List<BookVO> list= bkImpl.listBook();
		
		for(BookVO vo: list) {
			System.out.print(vo.getName()+"\t");
			System.out.print("\t"+vo.getNumber()+"\t");
			System.out.print(vo.getPub()+"\t");
			System.out.print(vo.getWriter()+"\t");
			System.out.print(vo.getGenre()+"\t");
			Calendar regDate = vo.getRegDate();
			SimpleDateFormat add = new SimpleDateFormat("yyyy-MM-dd");
			System.out.print(add.format(regDate.getTime())+ "\t");
			System.out.println();
			
		}
		System.out.println();
	}
	
	
	//도서 삭제
	protected void delete() {
		System.out.println("\n[도서삭제]");
		
		String num;
		try {
			System.out.print("삭제할 도서번호 ?");
			num = br.readLine();
			//성공적으로 삭제되었는지를 반환	 //deleteBook 메소드를 호출하여 해당 도서를 삭제
			boolean b = bkImpl.deleteBook(num);
			if(b) {
				System.out.println(num+ "도서정보가 삭제 되었습니다.\n");
			}else {
				System.out.println(num + "등록된 도서가 아닙니다.\n");
			}
			
		} catch (Exception e) {
		}
		
	}
	
	
}
