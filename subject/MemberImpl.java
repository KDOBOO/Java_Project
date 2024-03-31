package subject;

import java.util.ArrayList;
import java.util.List;

// 회원 관리 클래스
public class MemberImpl implements Member {
	 			//MemberVO 객체들을 담을 수 있는 리스트를 생성
	private List<MemberVO> list = new ArrayList<>(); 

	/**
	 * 새로운 회원 등록 (list에 등록)
	 */
	@Override
	public void insertMember(MemberVO vo){
		list.add(vo);
	}

	
	
	/**
	 * 회원 삭제 (list에서 삭제)
	 * @param id	회원번호
	 * @return		삭제했으면 true, 그렇지 않으면 false
	 */
	@Override
	public boolean deleteMember(String id) {
	 //회원 객체를 반환하여 vo에 저장		//findById 메소드를 호출
		MemberVO vo = findById(id);
		return list.remove(vo);
	}

	
	
	
	/**
	 * 회원 전체 리스트
	 * @return		전체 회원 리스트
	 */
	// 전체 리스트
	@Override
	public List<MemberVO> listMember() {
		return list;
	}

	
	/**
	 * 회원번호 검색
	 * @param id	회원번호
	 * @return		입력받은 id와 list의 id가 일치하면 회원 객체 반환, 그렇지 않으면 null
	 */
	// 회원번호 찾기
	@Override
	public MemberVO findById(String id) {
		for(MemberVO vo : list) {
			if(vo.getId().equals(id)) {
				return vo;
			}
		}
		return null;
	}
}
