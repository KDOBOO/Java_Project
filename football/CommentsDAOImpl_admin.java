package football;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;

public class CommentsDAOImpl_admin implements CommentsDAO_admin {

	private Connection conn = com.util.footballDBConn.getConnection();

// player
	
	@Override
	// 선수코멘트 삭제
	public int player_deletecomments(int commentNumber) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			// 1)쿼리작성
			sql = "DELETE FROM player_comments WHERE commentNumber = ? ";

			// 2)쿼리를 인자로 Pre객체 생성
			pstmt = conn.prepareStatement(sql);

			// 3)setter를 이용해 ?에 값 할당
			pstmt.setInt(1, commentNumber);

			// 4)실행할 쿼리를 변수에 저장
			result = pstmt.executeUpdate();

			// SQL예외
		} catch (SQLException e) {
			throw e;
		} finally {
			// 5)객체닫기
			DBUtil.close(pstmt);
		}

		return result;
	}

	@Override
	// 선수코멘트리스트 출력
	public List<CommentsDTO_admin> player_listComments(int playerId) {
		List<CommentsDTO_admin> list = new ArrayList<CommentsDTO_admin>();
		// pstmt는 쿼리를 실행할 때 사용
		PreparedStatement pstmt = null;
		// rs는 실행된 쿼리의 결과를 받아오는 데 사용
		ResultSet rs = null;
		String sql;

		try {
			// 1)쿼리작성
			sql = "SELECT commentNumber,c.playerId,commentNick,commentCon,commentDate " + "	FROM player_comments c "
					+ "	JOIN player p ON c.playerId = p.playerId " + "	WHERE INSTR(c.playerId, ?) >= 1 ";

			// 2)쿼리를 인자로 Pre객체 생성
			pstmt = conn.prepareStatement(sql);

			// 3)setter를 이용해 ?에 값 할당
			pstmt.setInt(1, playerId);

			// 4)실행할 쿼리를 변수에 저장
			rs = pstmt.executeQuery();

			// 5)결과 집합을 반복하면서 DTO 객체를 생성하고, 리스트에 추가
			while (rs.next()) {
				CommentsDTO_admin dto = new CommentsDTO_admin();

				dto.setCommentNumber(rs.getInt("commentNumber"));
				dto.setPlayerId(rs.getInt("playerId"));
				dto.setCommentNick(rs.getString("commentNick"));
				dto.setCommentCon(rs.getString("commentCon"));
				dto.setCommentDate(rs.getString("commentDate"));

				list.add(dto);

			}

			// 전체예외
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6)객체들 닫기
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	@Override
	// 선수이름으로 선수ID 조회
	public List<CommentsDTO_admin> FinbByPlayerName(String playerName) {
		List<CommentsDTO_admin> list = new ArrayList<CommentsDTO_admin>();
		// pstmt는 쿼리를 실행할 때 사용
		PreparedStatement pstmt = null;
		// rs는 실행된 쿼리의 결과를 받아오는 데 사용
		ResultSet rs = null;
		String sql;

		try {
			// 1)쿼리작성
			sql = "SELECT playerName,playerId FROM player WHERE playerName = ? ";

			// 2)쿼리를 인자로 Pre객체 생성
			pstmt = conn.prepareStatement(sql);

			// 3)setter를 이용해 ?에 값 할당
			pstmt.setString(1, playerName);

			// 4)실행할 쿼리를 변수에 저장
			rs = pstmt.executeQuery();

			// 5)결과 집합을 반복하면서 DTO 객체를 생성하고, 리스트에 추가
			while (rs.next()) {
				CommentsDTO_admin dto = new CommentsDTO_admin();

				dto.setPlayerId(rs.getInt("playerId"));
				dto.setPlayerName(rs.getString("playerName"));

				list.add(dto);
			}

			// 전체예외
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6)객체닫기
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}

		return list;
	}

// team
	@Override
	// 팀코멘트 삭제
	public int team_deletecomments(int commentNumber) throws SQLException {
		int result = 0;
		// pstmt는 쿼리를 실행할 때 사용
		PreparedStatement pstmt = null;
		String sql;

		try {
			// 1)쿼리작성
			sql = "DELETE FROM team_comments WHERE commentNumber = ?  ";

			// 2)쿼리를 인자로 Pre객체 생성
			pstmt = conn.prepareStatement(sql);

			// 3)setter를 이용해 ?에 값 할당
			pstmt.setInt(1, commentNumber);

			// 4)실행할 쿼리를 변수에 저장
			result = pstmt.executeUpdate();

			// SQL예외
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}

		return result;
	}

	@Override
	// 팀 코멘트리스트 출력
	public List<CommentsDTO_admin> team_listComments(String teamCode) {
		List<CommentsDTO_admin> list = new ArrayList<CommentsDTO_admin>();
		// pstmt는 쿼리를 실행할 때 사용
		PreparedStatement pstmt = null;
		// rs는 실행된 쿼리의 결과를 받아오는 데 사용
		ResultSet rs = null;
		String sql;

		try {
			// 1)쿼리작성
			sql = "SELECT commentNumber,c.teamCode,commentCon,commentsDate,commentNick " + "	FROM team_comments c "
					+ "	JOIN team t ON c.teamCode=t.teamCode " + "	WHERE c.teamCode = ? ";

			// 2)쿼리를 인자로 Pre객체 생성
			pstmt = conn.prepareStatement(sql);

			// 3)setter를 이용해 ?에 값 할당
			pstmt.setString(1, teamCode);

			// 4)실행할 쿼리를 변수에 저장
			rs = pstmt.executeQuery();

			// 5)결과 집합을 반복하면서 DTO 객체를 생성하고, 리스트에 추가
			while (rs.next()) {
				CommentsDTO_admin dto = new CommentsDTO_admin();

				dto.setCommentNumber(rs.getInt("commentNumber"));
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setCommentNick(rs.getString("commentNick"));
				dto.setCommentCon(rs.getString("commentCon"));
				dto.setCommentDate(rs.getString("commentsDate"));

				list.add(dto);
			}

			// 전체예외
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6)객체닫기
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	@Override
	// 팀이름으로 팀Code 찾기
	public List<CommentsDTO_admin> FinbByTeamName(String teamName) {
		List<CommentsDTO_admin> list = new ArrayList<CommentsDTO_admin>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT teamName,teamCode FROM team WHERE teamName = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, teamName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentsDTO_admin dto = new CommentsDTO_admin();

				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));

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

// match
	@Override
	// 경기코멘트 삭제
	public int match_deletecomments(int commentNumber) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "DELETE FROM match_comments WHERE commentNumber = ?  ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, commentNumber);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}

		return result;
	}

	@Override
	// 경기코멘트리스트 출력
	public List<CommentsDTO_admin> match_listComments(int matchNumber) {
		List<CommentsDTO_admin> list = new ArrayList<CommentsDTO_admin>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "SELECT commentNumber,m.matchNumber,commentCon,commentDate,commentNick " + "FROM match_comments c "
					+ "JOIN match m ON m.matchNumber=c.matchNumber " + "WHERE m.matchNumber = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, matchNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentsDTO_admin dto = new CommentsDTO_admin();

				dto.setCommentNumber(rs.getInt("commentNumber"));
				dto.setMatchNumber(rs.getInt("matchNumber"));
				dto.setCommentNick(rs.getString("commentNick"));
				dto.setCommentCon(rs.getString("commentCon"));
				dto.setCommentDate(rs.getString("commentDate"));

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

	@Override
	// 경기날짜와 구장이름으로 경기번호찾기
	public List<CommentsDTO_admin> FindBymatchNumber(String matchDate, String stadiumName) {
		List<CommentsDTO_admin> list = new ArrayList<CommentsDTO_admin>();
		// pstmt는 쿼리를 실행할 때 사용
		PreparedStatement pstmt = null;
		// rs는 실행된 쿼리의 결과를 받아오는 데 사용
		ResultSet rs = null;
		String sql;

		try {
			// 1)쿼리작성
			sql = "SELECT matchnumber " + "FROM match JOIN stadium ON match.tcode=stadium.teamcode "
					+ "WHERE match.matchdate = ? AND stadium.stadiumname = ? ";

			// 2)쿼리를 인자로 Pre객체 생성
			pstmt = conn.prepareStatement(sql);

			// 3)setter를 이용해 ?에 값 할당
			pstmt.setString(1, matchDate);
			pstmt.setString(2, stadiumName);

			// 4)실행할 쿼리를 변수에 저장
			rs = pstmt.executeQuery();

			// 5)결과 집합을 반복하면서 DTO 객체를 생성하고, 리스트에 추가
			while (rs.next()) {
				CommentsDTO_admin dto = new CommentsDTO_admin();

				dto.setMatchNumber(rs.getInt("matchNumber"));

				list.add(dto);
			}

			// 전체예외
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6)객체닫기
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}

		return list;
	}

}
