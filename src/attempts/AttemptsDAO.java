package attempts;

import java.sql.SQLException;
import java.util.List;

public interface AttemptsDAO {
	// 추가
	int create(String id, int quizNumber) throws SQLException;

	// 해당인물이 해당 문제를 몇번? 클리어 여부
	List<AttemptsQuiz> read(String id,int quizNumber) throws SQLException;

	// 횟수 변경
	int update(String id, int quizNumber, int attemptsCount) throws SQLException;
	
	// 삭제
	int delete(String id, int quizNumber) throws SQLException;
}