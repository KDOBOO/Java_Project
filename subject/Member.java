package subject;

import java.util.List;

public interface Member {
	// 회원 등록
	public void insertMember(MemberVO vo) ;
	// 회원 삭제
	public boolean deleteMember(String id);
	// 회원 전체 리스트
	public List<MemberVO> listMember();
	// 회원번호 검색
	public MemberVO findById(String id);
}
