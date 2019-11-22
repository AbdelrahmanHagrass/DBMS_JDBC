package eg.edu.alexu.csd.oop.db.cs39;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class IDataBase implements Database {
	
	Map<String,DB> m = new HashMap<String, DB >();
	Command DBcommandCreate ;
	Command DBcommandDrop ;
	Command DropTable;
	Command CreateTable;
	

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		
		
		if(m.containsKey(databaseName) == false)
		{
			DBcommandCreate = new CreateDB(databaseName);
			try {
				executeStructureQuery("createdatabase");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return DBcommandCreate.getpathofDB();
		}
		else if ( m.containsKey(databaseName) == true &&  dropIfExists )
		{
			DBcommandDrop = new DropDB();
			try {
				executeStructureQuery("dropdatabase");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBcommandCreate = new CreateDB(databaseName);
			try {
				executeStructureQuery("createdatabase");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return DBcommandCreate.getpathofDB();
		}
		else //It exists and it is not required to drop it 
		{
			//m.get(databaseName).getpath
		}
		return databaseName;
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		
		if(query == "createdatabase")
		{
			DBcommandCreate.execute();
			m.put(DBcommandCreate.getnameofDB(), DBcommandCreate.getDB() );
			return true;
		}
		else if (query == "dropdatabase")
		{
			DBcommandDrop.execute();
			m.remove(DBcommandDrop.getnameofDB());
			return true;
		}
		else
		{
			
		}
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		
		return 0;
	}

}
