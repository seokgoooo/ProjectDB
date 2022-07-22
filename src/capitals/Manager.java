package capitals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import kr.co.greeart.dbutil.DBUtil;
// 관리자 클래스 CRUD 기능 

public class Manager implements CapitalsDao {
	private Capitals resultMapping(ResultSet rs) throws SQLException {
		int number = rs.getInt("number");
		String question = rs.getString("question");
		String answer = rs.getString("answer");
		String continent = rs.getString("continent");

		return new Capitals(number, question, answer, continent);
	}

	@Override
	public int create(int number, String question, String answer, String continent) throws SQLException {
		String query = "INSERT INTO Capitals(number, question, answer, continent) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, number);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			pstmt.setString(4, continent);

			return pstmt.executeUpdate();

		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeconn(conn);
		}
	}

	@Override
	public List<Capitals> read() throws SQLException {
		String query = "SELECT * FROM Capitals";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<Capitals> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				list.add(resultMapping(rs));

			}

		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closeStmt(stmt);
			DBUtil.closeconn(conn);
		}

		return list;
	}

	@Override
	public int update(int number, String question, String answer, String continent) throws SQLException {
		String query = "UPDATE Capitals Set question = ?, answer = ?, continent = ? Where number = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, question);
			pstmt.setString(2, answer);
			pstmt.setString(3, continent);
			pstmt.setInt(4, number);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeconn(conn);
		}

	}

	@Override
	public int delete(int number) throws SQLException {
		String query = "DELETE FROM Capitals Where number = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, number);

			return pstmt.executeUpdate();

		} finally {
			DBUtil.closeStmt(pstmt);
			DBUtil.closeconn(conn);
		}
	}

}
