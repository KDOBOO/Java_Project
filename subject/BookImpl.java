package subject;

import java.util.ArrayList;
import java.util.List;



public class BookImpl implements Book {
	
	//BookVO 객체의 리스트를 생성
	public List<BookVO> Booklist =new ArrayList<BookVO>();
	
	@Override								 //새로 추가하려는 책이 이미 도서 목록에 있는 경우를 처리하기 위해 
	public void insertBook(BookVO vo) throws MyDuplicationException {
		if(readBook(vo.getName(),vo.getWriter())!=null) {
			throw new MyDuplicationException("등록된 책");
		}
		Booklist.add(vo);
	}
	
	
	//도서 목록에서 도서 이름, 작가를 기준으로 도서를 검색
	@Override
	public BookVO readBook(String name, String writer) {
		for(BookVO vo: Booklist) {
			               //두 문자열의 내용이 동일한지를 비교하기 위함
			if(vo.getName().equals(name)&&vo.getWriter().equals(writer)) {
				return vo;
			}
		}
		return null;
	}

	
	//도서번호로 도서 찾기
	@Override
	public BookVO readNumber(String number) {
		for(BookVO vo : Booklist) {
			if(vo.getNumber().equals(number)) {
				return vo;
			}
		}
		return null;
	}

	
	@Override
	public List<BookVO> listBook() {
		return Booklist;
	}

	
	//도서이름으로 도서 찾기
	@Override
	public List<BookVO> listBook(String name) {
		List<BookVO> findList = new ArrayList<BookVO>();
		
		for(BookVO vo : Booklist) {
			               //도서의 이름이 지정된 이름으로 시작하는지를 확인합니다.
			if(vo.getName().startsWith(name)) {
				//해당 도서 객체를 findList에 추가
				findList.add(vo);
			}
		}
		return findList;
	}

	
	//도서 삭제
	@Override
	public boolean deleteBook(String number) {
		BookVO vo=readNumber(number);
		
		boolean b=Booklist.remove(vo);
		
		return b;
	}
	
	
	//도서번호생성
	@Override
	public String generatenumber() {
		String s= "00000000";
		if(Booklist.size()>0){
			                        //0번째 부터 시작해야
			BookVO book=Booklist.get(Booklist.size()-1);
			s=book.getNumber();
		}
		                   //정수로 도서번호를 만들기 위해
		int n=Integer.parseInt(s)+1;
		              //숫자를 8자리로 표현하는데, 필요한 경우 앞에 0으로 채움
		s=String.format("%08d",n);
		
		return s;
	}
	
	
}
