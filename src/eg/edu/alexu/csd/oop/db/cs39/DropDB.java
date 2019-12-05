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
		return m.get(databaseName.toUpperCase()).getDatabaseName();
	}
	
	@Override
	public String getpathofDB() {

		return  m.get(databaseName).getAbsolutePath();
	}
	@Override
	public void execute() throws SQLException {

		try {
			//System.out.println(databaseName + "gowa el execute bta3 el dropdb");
			if(m.get(databaseName.toUpperCase()) != null)
			{
				m.get(databaseName.toUpperCase()).DropDatabase();
			}
			
			//m.remove(databaseName.toUpperCase());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("error");
			e.printStackTrace();
		}
	}
	

}