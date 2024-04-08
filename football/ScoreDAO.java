package football;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.util.footballDBConn;

public class ScoreDAO {
	private Connection conn = footballDBConn.getConnection();

	// 득점 등록
	public int insert(ScoreDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "INSERT INTO score(scoreId, playerId, scoreTime, matchNumber) " + " VALUES(sId_seq.NEXTVAL,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getPlayerId());
			pstmt.setString(2, dto.getScoreTime());
			pstmt.setInt(3, dto.getMatchNumber());

			result = pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			DBUtil.close(pstmt);

			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
			}
		}

		return result;
	}

	// 득점 수정
	public int updateScore(ScoreDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);

			sql = "UPDATE score SET scoreTime = ?, playerId = ?, matchNumber = ?" + " WHERE scoreId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getScoreTime());
			pstmt.setInt(2, dto.getPlayerId());
			pstmt.setInt(3, dto.getMatchNumber());
			pstmt.setString(4, dto.getScoreId());

			result = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			DBUtil.close(pstmt);
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
			}
		}

		return result;
	}

	// 득점 삭제
	public int deleteScore(String scoreId) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn.setAutoCommit(false);
			sql = "DELETE FROM score WHERE scoreId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scoreId);

			result = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			DBUtil.close(pstmt);

			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
			}
		}

		return result;
	}

	// 선수정보조회
	public List<ScoreDTO> viewPlayer(String name) {
		List<ScoreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT playerId, playerName, t.teamCode, teamName " 
					+ " FROM player p "
					+ " JOIN team t ON p.teamCode = t.teamCode " 
					+ " WHERE INSTR(playerName, ?) > 0"
					+ " ORDER BY playerName";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setPlayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	// 경기정보조회
	public List<ScoreDTO> viewMatch(String matchDate) {
		List<ScoreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT matchNumber,TO_CHAR(matchDate,'YYYY-MM-DD') AS matchDate, t1.teamCode AS homeCode, t1.teamname AS homeName, homeScore, "
					+ " t2.teamCode AS awayCode, t2.teamName as awayName, awayScore " 
					+ " FROM match m "
					+ " JOIN team t1 ON m.tCode = t1.teamcode " 
					+ " JOIN team t2 ON m.tcode2 = t2.teamcode "
					+ " WHERE matchDate = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, matchDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setMatchNumber(rs.getInt("matchNumber"));
				dto.setMatchDate(rs.getString("matchDate"));
				dto.setHomeCode(rs.getString("homeCode"));
				dto.setHomeName(rs.getString("homeName"));
				dto.setHomeScore(rs.getInt("homeScore"));
				dto.setAwayCode(rs.getString("awayCode"));
				dto.setAwayName(rs.getString("awayName"));
				dto.setAwayScore(rs.getInt("awayScore"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	// 득점 조회(선수이름)
	public List<ScoreDTO> listScoreP(String playerName) {
		List<ScoreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT scoreId, p.playerId, playerName, t.teamCode, teamName, "
					+ " m.matchNumber, scoreTime, TO_CHAR(matchDate,'YYYY-MM-DD') matchDate" 
					+ " FROM score s"
					+ " JOIN player p ON s.playerId = p.playerId " 
					+ " JOIN team t ON p.teamCode = t.teamCode "
					+ " JOIN match m ON s.matchNumber = m.matchNumber" 
					+ " WHERE INSTR(playerName,?) > 0"
					+ " ORDER BY matchDate, scoreTime";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, playerName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setScoreId(rs.getString("scoreId"));
				dto.setPlayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setMatchNumber(rs.getInt("matchNumber"));
				dto.setScoreTime(rs.getString("scoreTime"));
				dto.setMatchDate(rs.getString("matchDate"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	
	// 득점 검색(경기일자)
	public List<ScoreDTO> listScoreM(String matchDate) {
		List<ScoreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT scoreId, p.playerId, playerName, t.teamCode, teamName, "
					+ " m.matchNumber, scoreTime, TO_CHAR(matchDate,'YYYY-MM-DD') matchDate" 
					+ " FROM score s"
					+ " JOIN player p ON s.playerId = p.playerId " 
					+ " JOIN team t ON p.teamCode = t.teamCode "
					+ " JOIN match m ON s.matchNumber = m.matchNumber" 
					+ " WHERE matchDate = ? "
					+ " ORDER BY m.matchNumber";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, matchDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setScoreId(rs.getString("scoreId"));
				dto.setPlayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setMatchNumber(rs.getInt("matchNumber"));
				dto.setScoreTime(rs.getString("scoreTime"));
				dto.setMatchDate(rs.getString("matchDate"));

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

	// 전체리스트
	public List<ScoreDTO> listScore() {
		List<ScoreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT scoreId, p.playerId, playerName, t.teamCode, teamName, "
					+ " m.matchNumber, scoreTime, TO_CHAR(matchDate,'YYYY-MM-DD') matchDate" 
					+ " FROM score s"
					+ " JOIN player p ON s.playerId = p.playerId " 
					+ " JOIN team t ON p.teamCode = t.teamCode "
					+ " JOIN match m ON s.matchNumber = m.matchNumber " 
					+ " ORDER BY matchNumber, scoreTime";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setScoreId(rs.getString("scoreId"));
				dto.setPlayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setMatchNumber(rs.getInt("matchNumber"));
				dto.setScoreTime(rs.getString("scoreTime"));
				dto.setMatchDate(rs.getString("matchDate"));

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	// 순위리스트
	public List<ScoreDTO> rankList() {
		List<ScoreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT RANK() OVER(ORDER BY COUNT(*) DESC) AS rank, " 
					+ " p.playerId, playerName, t.teamCode, teamName, COUNT(*) AS goals"
					+ " FROM score s" 
					+ " JOIN player p ON s.playerId = p.playerId "
					+ " JOIN team t ON p.teamCode = t.teamCode " 
					+ " GROUP BY p.playerId, playerName, t.teamCode, teamName" 
					+ " ORDER BY rank, playerName";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setRank(rs.getInt("rank"));
				dto.setPlayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setGoals(rs.getInt("goals"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}

		return list;
	}
}