package football;

import java.sql.SQLException;
import java.util.List;



public interface CommentsDAO_admin {
	
	public int player_deletecomments(int commentNumber) throws SQLException;
	public List<CommentsDTO_admin> player_listComments(int playerId);
	public List<CommentsDTO_admin> FinbByPlayerName(String playerName);
	
	
	
	public int team_deletecomments(int commentNumber) throws SQLException;
	public List<CommentsDTO_admin> team_listComments(String teamCode);
	public List<CommentsDTO_admin> FinbByTeamName(String teamName);
	
	
	public int match_deletecomments(int commentNumber) throws SQLException;
	public List<CommentsDTO_admin> match_listComments(int matchNumber);
	public List<CommentsDTO_admin> FindBymatchNumber(String matchDate, String stadiumName);
	
}
