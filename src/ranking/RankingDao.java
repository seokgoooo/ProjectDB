package ranking;

import java.sql.SQLException;
import java.util.List;

public interface RankingDao {
	
	List<Ranking> correctRatio(String table) throws SQLException;
	
	List<Ranking> incorrectRatio(String table) throws SQLException;
	
	List<Ranking> favoriteTop(String table) throws SQLException;
	
	List<Ranking> maxTest(String table) throws SQLException;
}
