package eg.edu.alexu.csd.oop.db.cs39;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface Command  {
	
	
	public DB getDB();
	public String getpathofDB();
	public String getnameofDB();
	/**
	 * it executes the given command. 
	 * @return ... the the results of the execution.
	 */
	public void execute() throws SQLException;
	




	
}
