

import java.sql.SQLException;
import java.util.List;

public interface CapitalsDao {

	int create(int number, String question, String answer, String continent) throws SQLException;

	List<Capitals> read() throws SQLException;

	int update(int number, String question, String answer, String continet) throws SQLException;

	int delete(int number) throws SQLException;
}
