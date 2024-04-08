package football;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBUtil;
import com.util.footballDBConn;

public class TeamDAOImpl implements TeamDAO {

	private Connection conn = footballDBConn.getConnection();
	
	@Override
	public int insertTeam(TeamDTO dto) throws SQLException {
		int result=0;
		
		PreparedStatement pstmt =null;
		String sql;
		
		try {
			conn.setAutoCommit(false);
			sql="INSERT ALL"
					+" INTO team(teamCode,teamName,headcoach,captain) VALUES (?,?,?,?)"
					+" INTO stadium(teamCode,stadiumName,region) VALUES (?,?,?)"
					+" SELECT *FROM dual";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTeamCode());
			pstmt.setString(2, dto.getTeamName());
			pstmt.setString(3, dto.getHeadcoach());
			pstmt.setString(4, dto.getCaptain());
			pstmt.setString(5, dto.getTeamCode());
			pstmt.setString(6, dto.getStadiumName());
			pstmt.setString(7, dto.getRegion());
			
			result =pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			throw e;
		} finally {
			DBUtil.close(pstmt);
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	@Override
	public int updateTeam(TeamDTO dto) throws SQLException {
		int result =0;
		
		PreparedStatement pstmt =null;
		
		String sql;
		
		try {
			conn.setAutoCommit(false);
			sql="UPDATE team SET teamName=?,headcoach=?,captain=? WHERE teamCode=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTeamName());
			pstmt.setString(2, dto.getHeadcoach());
			pstmt.setString(3, dto.getCaptain());
			pstmt.setString(4, dto.getTeamCode());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			sql="UPDATE stadium SET stadiumName=?,region=? WHERE teamCode=?";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getStadiumName());
			pstmt.setString(2, dto.getRegion());
			pstmt.setString(3, dto.getTeamCode());
			
			result =pstmt.executeUpdate();
			
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

	@Override
	public int deleteTeam(String teamCode) throws SQLException {
		int result =0;
		PreparedStatement pstmt =null;
		String sql;
		
		try {
			sql="DELETE FROM stadium WHERE teamCode =?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teamCode);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
			pstmt=null;
			sql=null;
			
			sql="DELETE FROM team WHERE teamCode =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teamCode);
			
			result=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw e;
		} finally {
			DBUtil.close(pstmt);
		}
		return result;
	}

	@Override
	public List<TeamDTO> listTeam(String teamName) {
		List<TeamDTO> list =new ArrayList<TeamDTO>();
		
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		String sql;
		try {
			sql="SELECT t1.teamCode,teamName,headcoach,captain,stadiumName,region"
					+" FROM team t1"
					+" JOIN stadium s1 ON t1.teamCode=s1.teamCode"
					+" WHERE INSTR(teamName,?) >=1";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, teamName);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				TeamDTO dto = new TeamDTO();
				
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setHeadcoach(rs.getString("headcoach"));
				dto.setCaptain(rs.getString("captain"));
				dto.setStadiumName(rs.getString("stadiumName"));
				dto.setRegion(rs.getString("region"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
		return list;
	}

	@Override
	public List<TeamDTO> listTeam() {
		List<TeamDTO> list = new ArrayList<TeamDTO>();
		
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		String sql;
		
		try {
			sql= "SELECT t1.teamCode,teamName,headcoach,captain,stadiumName,region"
					+" FROM team t1"
					+" JOIN stadium s1 ON t1.teamCode=s1.teamCode"
					+" ORDER BY teamCode ASC";
			
				pstmt=conn.prepareStatement(sql);
			
				rs=pstmt.executeQuery();
				
		while(rs.next()) {
				TeamDTO dto = new TeamDTO();
				
				dto.setTeamCode(rs.getString("teamCode"));
				dto.setTeamName(rs.getString("teamName"));
				dto.setHeadcoach(rs.getString("headcoach"));
				dto.setCaptain(rs.getString("captain"));
				dto.setStadiumName(rs.getString("stadiumName"));
				dto.setRegion(rs.getString("region"));
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
