package football;

import java.sql.SQLException;
import java.util.List;


public interface TeamDAO {

	
	public int insertTeam(TeamDTO dto) throws SQLException;
	public int updateTeam(TeamDTO dto) throws SQLException;
	public int deleteTeam(String teamCode) throws SQLException;
	public List<TeamDTO> listTeam(String teamName);
	public List<TeamDTO> listTeam();
}
