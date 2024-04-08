package football;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
	//선수테이블 DTO
	public int insertplayer(PlayerDTO dto) throws SQLException;		//등록
	public int updateplayer(PlayerDTO dto) throws SQLException;		//수정
	public int deleteplayer(int playerId) throws SQLException;	//삭제
	
	public List<PlayerDTO> listplayerC(String playerNation); //국가로 선수 찾기
	public List<PlayerDTO> listplayer(String playerName); //이름으로 검색하기
	public List<PlayerDTO> listplayerP(String position); //포지션으로 검색하기
	public List<PlayerDTO> listplayer(); //선수전체리스트
	public List<PlayerDTO> teamlistplayer(String teamcode); //팀의 선수목록리스트
}
