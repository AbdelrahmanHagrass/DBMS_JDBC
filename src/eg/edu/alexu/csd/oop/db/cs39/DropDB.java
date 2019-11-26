package eg.edu.alexu.csd.oop.db.cs39;

import java.sql.SQLException;
import java.util.Map;

public class DropDB implements Command {
	
	String databaseName;
	Map<String, DB> m ;

	public DropDB(String databaseName,Map<String, DB> m) {
		
		this.databaseName= databaseName;
		this.m = m ;
		
		

	}

	@Override
	public DB getDB() {
		return m.get(databaseName);
	}

	@Override
	public String getnameofDB() {
		return m.get(databaseName).getDatabaseName();
	}
	
	@Override
	public String getpathofDB() {

		return  m.get(databaseName).getAbsolutePath();
	}
	@Override
	public void execute() throws SQLException {

		try {
			System.out.println(databaseName);
			m.get(databaseName).DropDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
