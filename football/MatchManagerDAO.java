package football;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.util.footballDBConn;

public class MatchManagerDAO {
	
	private Connection conn = footballDBConn.getConnection();
	
//	1.경기일정 추가
	public int insertMatch(MatchScheduleDTO dto) throws SQLException{
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = " INSERT INTO match(matchNumber,matchdate,Tcode,Tcode2)values(matches_seq.nextval,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMatchDate());
			pstmt.setInt(2, dto.getHomenum());
			pstmt.setInt(3, dto.getAwaynum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}
		
		return result;
	}
	
//	2.경기일정 수정
	public int updateMatch(MatchScheduleDTO dto) throws SQLException{
		int result=0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = " UPDATE match SET matchdate = ?,tcode = ?,tcode2=? where matchnumber =? ";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getMatchDate());
			pstmt.setInt(2,dto.getHomenum());
			pstmt.setInt(3,dto.getAwaynum());
			pstmt.setInt(4,dto.getMatchNumber());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}
		
		return result;
	}
//	3.경기일정 삭제
	public int deleteMatch(int matchnumber)throws SQLException{
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM match WHERE matchnumber = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, matchnumber);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}
		
		return result;
	}

//	5.모든 경기 조회
	public List<MatchScheduleDTO> allMatch() {
		List<MatchScheduleDTO> list = new ArrayList<MatchScheduleDTO>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT matchnumber,to_char(matchdate,'yyyy-mm-dd') as matchdate, t.teamname as hometeam,homescore, t2.teamname as awayteam,awayscore, s.stadiumname, nvl(r.name,'미정') as Referee "
					+ " FROM match m " + " LEFT OUTER JOIN team t ON m.tcode = t.teamcode "
					+ " LEFT OUTER JOIN team t2 ON m.tcode2 = t2.teamcode " + " LEFT OUTER JOIN referee r ON m.rcode = r.refereenum "
					+ " LEFT OUTER JOIN stadium s ON t.teamcode = s.teamcode "
					+ " ORDER BY matchdate ASC";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MatchScheduleDTO dto = new MatchScheduleDTO();
				
				dto.setMatchNumber(rs.getInt("matchnumber"));
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

}
