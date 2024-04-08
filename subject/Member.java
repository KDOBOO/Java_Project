<<<<<<< HEAD
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
=======
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
>>>>>>> c1dce160f423e1166a16664b95b883140ea323f5
