package football;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBUtil;
import com.util.footballDBConn;

public class MatchSchedulDAO {

	private Connection conn = footballDBConn.getConnection();

	public List<MatchScheduleDTO> preResult() {
		List<MatchScheduleDTO> list = new ArrayList<MatchScheduleDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT to_char(matchdate,'yyyy-mm-dd') as matchdate, t.teamname as hometeam, homescore, t2.teamname as awayteam, awayscore, s.stadiumname, r.name as Referee "
					+ " FROM match m " + " JOIN team t ON m.tcode = t.teamcode "
					+ " JOIN team t2 ON m.tcode2 = t2.teamcode " + " JOIN referee r ON m.rcode = r.refereenum "
					+ " JOIN stadium s ON t.teamcode = s.teamcode " + " WHERE homescore IS NOT NULL "
					+ " ORDER BY matchdate ASC";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MatchScheduleDTO dto = new MatchScheduleDTO();

				dto.setMatchDate(rs.getString("matchdate"));
				dto.setHomeName(rs.getString("hometeam"));
				dto.setAwayName(rs.getString("awayteam"));
				dto.setHomeScore(rs.getInt("homescore"));
				dto.setAwayScore(rs.getInt("awayscore"));
				dto.setStadium(rs.getString("stadiumname"));
				dto.setMainReferee(rs.getString("Referee"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}
	
	public List<MatchScheduleDTO> listMatch(String matchMonth){
		List<MatchScheduleDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select to_char(matchdate,'yyyy-mm') as matchdate,t.teamname hometeam,homescore,t2.teamname awayteam,awayscore,s.stadiumname,r.name Referee\r\n"
					+ "from match m\r\n"
					+ "join team t on m.tcode = t.teamcode\r\n"
					+ "join team t2 on m.tcode2 = t2.teamcode\r\n"
					+ "join referee r on m.rcode = r.refereenum\r\n"
					+ "join stadium s on t.teamcode = s.teamcode\r\n"
					+ "where homescore is not null and to_char(matchdate,'yyyy-mm') = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, matchMonth);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MatchScheduleDTO dto = new MatchScheduleDTO();
				
				dto.setMatchDate(rs.getString("matchdate"));
				dto.setHomeName(rs.getString("hometeam"));
				dto.setAwayName(rs.getString("awayteam"));
				dto.setHomeScore(rs.getInt("homescore"));
				dto.setAwayScore(rs.getInt("awayscore"));
				dto.setStadium(rs.getString("stadiumname"));
				dto.setMainReferee(rs.getString("Referee"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}


	public List<MatchScheduleDTO> nextMatch() {
		List<MatchScheduleDTO> list = new ArrayList<MatchScheduleDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT to_char(matchdate,'yyyy-mm-dd') as matchdate,t.teamname hometeam,t2.teamname awayteam,s.stadiumname\r\n" 
					+ "FROM match m\r\n"
					+ "JOIN team t ON m.tcode = t.teamcode\r\n" + "join team t2 on m.tcode2 = t2.teamcode\r\n"
					+ "JOIN stadium s ON t.teamcode = s.teamcode\r\n" + "where homescore is null\r\n"
					+ "ORDER BY matchdate asc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MatchScheduleDTO dto = new MatchScheduleDTO();

				dto.setMatchDate(rs.getString("matchdate"));
				dto.setHomeName(rs.getString("hometeam"));
				dto.setAwayName(rs.getString("awayteam"));
				dto.setStadium(rs.getString("stadiumname"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}


	public List<Map.Entry<String, Integer>> Ranking() {
	    Map<String, Integer> teamPoint = new HashMap<>();

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql;

	    try {
	       
	        sql = "SELECT matchdate, t.teamname as hometeam, homescore, t2.teamname as awayteam, awayscore "
	                + " FROM match m " + " JOIN team t ON m.tcode = t.teamcode "
	                + " JOIN team t2 ON m.tcode2 = t2.teamcode " + " WHERE homescore IS NOT NULL "
	                + " ORDER BY matchdate ASC";

	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();

	        
	        while (rs.next()) {
	            String homeTeamName = rs.getString("hometeam");
	            String awayTeamName = rs.getString("awayteam");
	            int homeScore = rs.getInt("homescore");
	            int awayScore = rs.getInt("awayscore");

	            
	            updatePoints(teamPoint, homeTeamName, homeScore, awayScore);
	            updatePoints(teamPoint, awayTeamName, awayScore, homeScore);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.close(rs);
	        DBUtil.close(pstmt);
	    }

	    List<Map.Entry<String, Integer>> sortedTeamPointsList = new ArrayList<>(teamPoint.entrySet());
	    sortedTeamPointsList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    return sortedTeamPointsList;
	}

	
	private void updatePoints(Map<String, Integer> teamPoint, String teamName, int homescore, int awayscore) {
	    int points = teamPoint.getOrDefault(teamName, 0);
	    if (homescore > awayscore) {
	        points += 3;
	    } else if (homescore == awayscore) {
	        points += 1; 
	    }
	    teamPoint.put(teamName, points);
	}

	public List<MatchScheduleDTO> listYear(String matchYear){
		List<MatchScheduleDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select to_char(matchdate,'yyyy') as matchdate,t.teamname hometeam,homescore,t2.teamname awayteam,awayscore,s.stadiumname,r.name Referee\r\n"
					+ "from match m\r\n"
					+ "join team t on m.tcode = t.teamcode\r\n"
					+ "join team t2 on m.tcode2 = t2.teamcode\r\n"
					+ "join referee r on m.rcode = r.refereenum\r\n"
					+ "join stadium s on t.teamcode = s.teamcode\r\n"
					+ "where homescore is not null and to_char(matchdate,'yyyy') = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, matchYear);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MatchScheduleDTO dto = new MatchScheduleDTO();
				
				dto.setMatchDate(rs.getString("matchdate"));
				dto.setHomeName(rs.getString("hometeam"));
				dto.setAwayName(rs.getString("awayteam"));
				dto.setHomeScore(rs.getInt("homescore"));
				dto.setAwayScore(rs.getInt("awayscore"));
				dto.setStadium(rs.getString("stadiumname"));
				dto.setMainReferee(rs.getString("Referee"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}
	
	public List<MatchScheduleDTO> teammatchRecode(String teamCode) {
		List<MatchScheduleDTO> list = new ArrayList<MatchScheduleDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT to_char(matchdate,'yyyy-mm-dd') as matchdate, t.teamname as hometeam, homescore, t2.teamname as awayteam, awayscore, s.stadiumname, r.name as Referee "
					+ " FROM match m " + " JOIN team t ON m.tcode = t.teamcode "
					+ " JOIN team t2 ON m.tcode2 = t2.teamcode " + " JOIN referee r ON m.rcode = r.refereenum "
					+ " JOIN stadium s ON t.teamcode = s.teamcode " + " WHERE t.teamcode = ? or t2.teamcode =?"
					+ " ORDER BY matchdate ASC";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teamCode);
			pstmt.setString(2, teamCode);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				MatchScheduleDTO dto = new MatchScheduleDTO();

				dto.setMatchDate(rs.getString("matchdate"));
				dto.setHomeName(rs.getString("hometeam"));
				dto.setAwayName(rs.getString("awayteam"));
				dto.setHomeScore(rs.getInt("homescore"));
				dto.setAwayScore(rs.getInt("awayscore"));
				dto.setStadium(rs.getString("stadiumname"));
				dto.setMainReferee(rs.getString("Referee"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		}
		
		return list;
	}

}
