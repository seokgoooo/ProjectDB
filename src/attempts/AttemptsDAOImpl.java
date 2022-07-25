package attempts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.quizdbutil.QuizDBUtil;

public class AttemptsDAOImpl implements AttemptsDAO {

	private AttemptsQuiz resultMapping(ResultSet rs) throws SQLException {
		String id = rs.getString("id");
		int quizNumber = rs.getInt("quiznumber");
		int attemptsCount = rs.getInt("attemptsCount");
		boolean clear = rs.getBoolean("clear");
		return new AttemptsQuiz(id, quizNumber, attemptsCount, clear);
	}
	
	@Override
	public int create(String id, int quizNumber) throws SQLException {
		String query = "INSERT INTO attemptsquiz (id, quizNumber) valuse (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, quizNumber);
			return pstmt.executeUpdate();
		}finally {
			QuizDBUtil.closeStmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
	}

	@Override
	public List<AttemptsQuiz> read(String id, int quizNumber) throws SQLException {
		String query = "SELECT * FROM attemptsquiz WHERE id = ? and quizNumber = ?";
		List<AttemptsQuiz> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, quizNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(resultMapping(rs));
			}
			
		}finally {
			QuizDBUtil.closeRS(rs);
			QuizDBUtil.closeStmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		
		return list;
	}

	@Override
	public int delete(String id, int quizNumber) throws SQLException {
		String query = "DELETE FROM attemptsquiz WHERE id = ? and quizNumber = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, quizNumber);
			return pstmt.executeUpdate();
		}finally {
			QuizDBUtil.closeStmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		
	}

	@Override
	public int update(String id, int quizNumber, int attemptsCount) throws SQLException {
		String query = "UPDATE attemptsquiz SET attemptsCount = ? WHERE id = ? and quizNUmber = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, attemptsCount);
			pstmt.setString(2, id);
			pstmt.setInt(3, quizNumber);
			
			return pstmt.executeUpdate();
		}finally {
			QuizDBUtil.closeStmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		
	}

}
