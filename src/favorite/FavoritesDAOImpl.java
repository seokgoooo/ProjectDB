package favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.dbutil.QuizDBUtil;

public class FavoritesDAOImpl implements FavoritesDAO {

	// 즐찾에 추가
	@Override
	public int create(String id, int quizNumber) throws SQLException {
		String query = "INSERT INTO favoritesquiz (id, quiznumber) values (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, quizNumber);
			return pstmt.executeUpdate();
		} finally {
			QuizDBUtil.closePstmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
	}

	// 특정 ID 즐찾 목록
	@Override
	public List<Integer> read(String id) throws SQLException {
		String query = "SELECT quiznumber FROM favoritesquiz WHERE id = ?";
		List<Integer> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("quizNumber"));
			}
		} finally {
			QuizDBUtil.closeRS(rs);
			QuizDBUtil.closePstmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		return list;
	}

	// 특정 ID 즐찾 목록 삭제
	@Override
	public int delete(String id, int quizNumber) throws SQLException {
		String query = "DELETE FROM favoritesquiz where id = ? and quiznumber = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, quizNumber);
			return pstmt.executeUpdate();
		} finally {
			QuizDBUtil.closePstmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
	}

}
