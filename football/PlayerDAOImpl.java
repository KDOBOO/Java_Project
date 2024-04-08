package football;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.util.footballDBConn;

public class PlayerDAOImpl implements PlayerDAO {
	private Connection conn = footballDBConn.getConnection();

	//등록
	@Override
	public int insertplayer(PlayerDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			conn.setAutoCommit(false);

			sql = "INSERT INTO player (playerId, playerName, teamCode, position, uniformNum, birth, tall, playerNation )"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getplayerId());
			pstmt.setString(2, dto.getPlayerName());
			pstmt.setString(3, dto.getTeamCode());
			pstmt.setString(4, dto.getPosition());
			pstmt.setInt(5, dto.getUniformNum());
			pstmt.setString(6, dto.getBirth());
			pstmt.setInt(7, dto.getTall());
			pstmt.setString(8, dto.getPlayerNation());

			result = pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			DBUtil.rollback(conn);

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

	//수정
	@Override
	public int updateplayer(PlayerDTO dto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			conn.setAutoCommit(false);

			sql = "UPDATE player SET playerName= ?, teamCode =?, position=?, uniformNum=?, birth=?, tall=?, playerNation=?  "
					+ "  WHERE playerId = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPlayerName());
			pstmt.setString(2, dto.getTeamCode());
			pstmt.setString(3, dto.getPosition());
			pstmt.setInt(4, dto.getUniformNum());
			pstmt.setString(5, dto.getBirth());
			pstmt.setInt(6, dto.getTall());
			pstmt.setString(7, dto.getPlayerNation());
			pstmt.setInt(8, dto.getplayerId());

			result = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {

			DBUtil.rollback(conn);

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

	//삭제
	@Override
	public int deleteplayer(int playerId) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM player WHERE playerId = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, playerId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}

		return result;
	}

	//국가로 선수 찾기
	@Override
	public List<PlayerDTO> listplayerC(String playerNation) {
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT playerId, playerName, p.teamCode,teamName ,position, uniformNum,TO_CHAR(birth,'YYYY-MM-DD')birth, tall, playerNation "
					+ " FROM player p " + " JOIN team t ON p.teamcode = t.teamcode "
					+ " WHERE INSTR(playerNation,?)>=1";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playerNation);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PlayerDTO dto = new PlayerDTO();

				dto.setplayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setPosition(rs.getString("position"));
				dto.setUniformNum(rs.getInt("uniformNum"));
				dto.setBirth(rs.getString("birth"));
				dto.setTall(rs.getInt("tall"));
				dto.setPlayerNation(rs.getString("playerNation"));

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

	//이름으로 검색하기
	@Override
	public List<PlayerDTO> listplayer(String playerName) {
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT playerId, playerName, p.teamCode,teamName ,position, uniformNum,TO_CHAR(birth,'YYYY-MM-DD')birth, tall, playerNation "
					+ " FROM player p " + " JOIN team t ON p.teamcode = t.teamcode " + " WHERE INSTR(playerName,?)>=1";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playerName);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PlayerDTO dto = new PlayerDTO();

				dto.setplayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setPosition(rs.getString("position"));
				dto.setUniformNum(rs.getInt("uniformNum"));
				dto.setBirth(rs.getString("birth"));
				dto.setTall(rs.getInt("tall"));
				dto.setPlayerNation(rs.getString("playerNation"));

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

	//선수전체리스트
	@Override
	public List<PlayerDTO> listplayer() {
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT playerId, playerName, p.teamCode, teamName, position, uniformNum,TO_CHAR(birth,'YYYY-MM-DD')birth, tall, playerNation "
					+ " FROM player p " + " JOIN team t ON p.teamCode=t.teamCode " + " ORDER BY playerId";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PlayerDTO dto = new PlayerDTO();

				dto.setplayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setPosition(rs.getString("position"));
				dto.setUniformNum(rs.getInt("uniformNum"));
				dto.setBirth(rs.getString("birth").toString());
				dto.setTall(rs.getInt("tall"));
				dto.setPlayerNation(rs.getString("playerNation"));

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
	
	// 포지션으로 찾기
	@Override
	public List<PlayerDTO> listplayerP(String position) {
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT playerId, playerName, p.teamCode,teamName ,position, uniformNum,TO_CHAR(birth,'YYYY-MM-DD')birth, tall, playerNation "
					+ " FROM player p " + " JOIN team t ON p.teamcode = t.teamcode " 
					+ " WHERE position = UPPER(?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, position);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PlayerDTO dto = new PlayerDTO();

				dto.setplayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setPosition(rs.getString("position"));
				dto.setUniformNum(rs.getInt("uniformNum"));
				dto.setBirth(rs.getString("birth"));
				dto.setTall(rs.getInt("tall"));
				dto.setPlayerNation(rs.getString("playerNation"));

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

	@Override
	public List<PlayerDTO> teamlistplayer(String teamcode) {
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT playerId, playerName, p.teamCode, teamName, position, uniformNum,TO_CHAR(birth,'YYYY-MM-DD')birth, tall, playerNation "
					+ " FROM player p " + " JOIN team t ON p.teamCode=t.teamCode "
					+ " WHERE t.teamCode =? "
					+ " ORDER BY playerId";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teamcode);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				PlayerDTO dto = new PlayerDTO();

				dto.setplayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setPosition(rs.getString("position"));
				dto.setUniformNum(rs.getInt("uniformNum"));
				dto.setBirth(rs.getString("birth").toString());
				dto.setTall(rs.getInt("tall"));
				dto.setPlayerNation(rs.getString("playerNation"));

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
