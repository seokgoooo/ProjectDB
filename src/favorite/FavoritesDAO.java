package favorite;

import java.sql.SQLException;
import java.util.List;

public interface FavoritesDAO {
	// 추가
	int create(String id, int quizNumber) throws SQLException;

	// 특정 인물 즐찾 목록
	List<Integer> read(String id) throws SQLException;

	// 삭제
	int delete(String id,int quizNumber) throws SQLException;

}
