package ranking;

import java.sql.SQLException;

public class TestDao {
	public static void main(String[] args) {
		RankingDao dao = new RankingDaoImpl();

		try {
			System.out.println(dao.scoreRank());
			System.out.println(dao.scoreRank().size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
