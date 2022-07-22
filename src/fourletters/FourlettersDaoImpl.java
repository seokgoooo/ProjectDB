package fourletters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gg.DBUtil;

public class FourlettersDaoImpl implements Dao {
	List<fourletters> list = new ArrayList<fourletters>();
	
	// 한 행을 볼수 있게
	private fourletters resultMapping(ResultSet rs) throws SQLException {
		int number = rs.getInt("number");
		String question = rs.getString("question");
		String awnser = rs.getString("awnser");
		String hint = rs.getString("hint");
		// ResultSet은 한 행을 읽을 수 있게 도와주는 객체

		return new fourletters(number, question, awnser, hint);
	}

	// 문제 불러오기
	private fourletters answerMapping(ResultSet rs) throws SQLException {
//		int number = rs.getInt("number");
		String question = rs.getString("question");
		
		return new fourletters(question);
	}
	// 정답으로 번호불러오기
	private fourletters numberMapping(ResultSet rs) throws SQLException {
		int number = rs.getInt("number");
//		String question = rs.getString("question");
		
		return new fourletters(number);
	}

	// 추가를 여러번 할수 있게
	public int[] create(List<fourletters> list) throws SQLException {
		String query = "INSERT INTO fourletters (question, awnser, hint) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);

			for (fourletters f : list) {
				pstmt.setString(1, f.getQuestion());
				pstmt.setString(2, f.getAwnser());
				pstmt.setString(3, f.getHint());
				pstmt.addBatch();
				// 스테이트먼트가 한줄을 준비하고 반복하면 그 반복을 더해서 준비해줌
				// 같은 쿼리문으로 한것을
				// 똑같은 작업 여러번 할때 배치
			}
			// 익스큐트배치는반복문으로 실행된 것을 한번에 실행하게끔 함
			return pstmt.executeBatch();
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}

	}

	@Override
	public int create(String question, String awnser, String hint) throws SQLException {
		String query = "INSERT INTO fourletters (question, awnser, hint) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			// 준비과정을 set으로
			pstmt.setString(1, question);
			pstmt.setString(2, awnser);
			pstmt.setString(3, hint);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
	}
	
	// 다푼문제에 저장
	@Override
	public int clearSave(String id, int quizNum) throws SQLException {
		String query = "INSERT INTO clearquiz_copy (id, quiznumber) VALUES (?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			// 준비과정을 set으로
			pstmt.setString(1, id);
			pstmt.setInt(2, quizNum);
			
			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
	}

	@Override
	public List<fourletters> read() throws SQLException {
		String query = "SELECT * FROM fourletters";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(resultMapping(rs));
			}
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return list;

	}
	
	//번호로 문제 불러오기
	@Override
	public fourletters read(int number) throws SQLException {
		String query = "SELECT * FROM fourletters WHERE number = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return answerMapping(rs);
			}
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}

		return null;
	}
	//문제로 번호불러 오기
	@Override
	public fourletters read(String question) throws SQLException {
		String query = "SELECT number FROM fourletters WHERE question = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, question);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return numberMapping(rs);
			}
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
		return null;
	}

	@Override
	public int update(int number, String question, String awnser, String hint) throws SQLException {
		String query = "UPDATE fourletters SET question = ?, awnser = ?, hint = ? WHERE number = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			// 준비과정을 set으로
			pstmt.setString(1, question);
			pstmt.setString(2, awnser);
			pstmt.setString(3, hint);
			pstmt.setInt(4, number);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}

//		return 0;
	}

	@Override
	public int delete(int number) throws SQLException {
		String query = "DELETE FROM fourletters WHERE number = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			// 준비과정을 set으로
			pstmt.setInt(1, number);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeConn(conn);
		}
	}

}
