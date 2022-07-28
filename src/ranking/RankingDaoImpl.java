package ranking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.greenart.dbutil.QuizDBUtil;

public class RankingDaoImpl implements RankingDao {
	
	private Ranking resultMapping(ResultSet rs) throws SQLException {
		String title = rs.getString("question");
		int ratio = rs.getInt("ratio");
		return new Ranking(title, ratio);
	}

	@Override
	public List<Ranking> correctRatio(String table) throws SQLException {
		int low,high;
		if(table.equals("music")) {
			low = 3000;
			high = 3999;
		} else if(table.equals("fourletters")) {
			low = 2000;
			high = 2999;
		} else {
			low = 1000;
			high = 1999;
		}
		String query = "call Correct_Ratio(?, ?, ?)";
		List<Ranking> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, low);
			pstmt.setInt(2, high);
			pstmt.setString(3, table);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(resultMapping(rs));
			}
			
		}finally {
			QuizDBUtil.closeRS(rs);
			QuizDBUtil.closePstmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		
		return list;
	}

	@Override
	public List<Ranking> incorrectRatio(String table) throws SQLException {
		int low,high;
		if(table.equals("music")) {
			low = 3000;
			high = 3999;
		} else if(table.equals("fourletters")) {
			low = 2000;
			high = 2999;
		} else {
			low = 1000;
			high = 1999;
		}
		String query = "call inCorrect_Ratio(?, ?, ?)";
		List<Ranking> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, low);
			pstmt.setInt(2, high);
			pstmt.setString(3, table);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(resultMapping(rs));
			}
			
		}finally {
			QuizDBUtil.closeRS(rs);
			QuizDBUtil.closePstmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		
		return list;
	}

	@Override
	public List<Ranking> favoriteTop(String table) throws SQLException {
		String query = "call favorite_top(?)";
		List<Ranking> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = QuizDBUtil.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, table);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(resultMapping(rs));
			}
			
		}finally {
			QuizDBUtil.closeRS(rs);
			QuizDBUtil.closePstmt(pstmt);
			QuizDBUtil.closeConn(conn);
		}
		
		return list;
	}

}
