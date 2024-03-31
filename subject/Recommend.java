package subject;

import java.util.List;

public interface Recommend {

//기능 구현
	
	//1. 추천도서 등록 
	public void insertRecommend(RecommendVO vo);	
	
	
	//2. 추천검색
	public List<RecommendVO> listRecommend(String name);
	
	
	//3. 장르검색
	public List<RecommendVO> listRecommendG(String genre);
	
	
	//4. 추천도서리스트출력
	public List<RecommendVO> listRecommend();


	

	
	
	
	
	
	
}
