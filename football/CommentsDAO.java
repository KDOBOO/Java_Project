package football;

import java.sql.SQLException;
import java.util.List;


public interface CommentsDAO {
	
	public int player_insertComments(CommentsDTO dto) throws SQLException;
	public List<CommentsDTO> player_listComments(int playerId);
	public List<CommentsDTO> FindByPlayerName(String playerName);
	
	public int team_insertComments(CommentsDTO dto) throws SQLException;
	public List<CommentsDTO> team_listComments(String teamCode);
	public List<CommentsDTO> FindByTeamName(String teamName);
	
	public int match_insertComments(CommentsDTO dto) throws SQLException;
	public List<CommentsDTO> match_listComments(int matchNumber);
	public List<CommentsDTO> FindBymatchNumber(String matchDate, String stadiumName);
	
}
