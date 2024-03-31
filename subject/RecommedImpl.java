package subject;

import java.util.ArrayList;
import java.util.List;


public class RecommedImpl implements Recommend {
	
	BookVO b = new BookVO();
	private List<RecommendVO> list = new ArrayList<>();	
	
	// 추천도서등록
	@Override
	public void insertRecommend(RecommendVO vo) {
		list.add(vo);
		
	}
	
	// 도서명검색
	@Override
	public List<RecommendVO> listRecommend(String name) {
	List<RecommendVO> findList = new ArrayList<RecommendVO>();
		
		for(RecommendVO vo : list) {
			if(vo.getName().equals(name)) {
				findList.add(vo);
			}
		}
		return findList;
	}

	//장르검색
	@Override
		public List<RecommendVO> listRecommendG (String genre) {
			List<RecommendVO> findListG = new ArrayList<RecommendVO>();
			
			for(RecommendVO vo : list) {
				if(vo.getGenre().equals (genre)) {
					findListG.add(vo);
				}
			}
			return findListG;
	}

	// 추천리스트 전체
	@Override
	public List<RecommendVO> listRecommend() {
		return list;
	}

		 
		 
	 
	

}