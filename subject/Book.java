package subject;

import java.util.List;

public interface Book {
	//등록
	public void insertBook(BookVO vo) throws MyDuplicationException;
	//도서검색
	public BookVO readBook(String name,String writer);
	//도서번호 검색
	public BookVO readNumber(String number);
	//도서목록 출력
	public List<BookVO> listBook();
	//도서명 검색
	public List<BookVO> listBook(String name);
	//삭제
	public boolean deleteBook(String number);
	public String generatenumber();
}
