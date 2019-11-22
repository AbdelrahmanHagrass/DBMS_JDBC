package eg.edu.alexu.csd.oop.db.cs39;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class IDataBase implements Database {
	
	Partitions p = new Partitions();
	Map<String,DB> m = new HashMap<String, DB >();
	DB lastDB;
	private Command DBcommandCreate ;
	private Command DBcommandDrop ;
	private Command DropTable;
	private Command CreateTable;
	Select selecTable;
	Insert insertTable;
	

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
			DBcommandDrop = new DropDB(databaseName, m);
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
			return m.get(databaseName).getAbsolutePath();
		}
		
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		
		query = query.toLowerCase();
		
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
		else if (query.contains("create") && query.contains("table"))
		{
			// i will call class partitions with string query ..to get table name,names , types ,parent DB..
			return true;
		}
		else if( query.contains("drop") && query.contains("table") )
		{
			// i will call class partitions with string query ..to get table name.
			return true;
		}
		else
		{
			//no query is right ...
			return false;
		}
		
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		
		//selecTable = new Select(TableName, ParentDB, cols);
		try {
			return selecTable.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null; //should be handled
		}
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		
		//insert //update //delete
		query = query.toLowerCase();
		if(query.contains("insert"))
		{
			//el p.insert de of type Objects
			//insertTable = new Insert(TableName, lastDB, p.Insert(query) );
			//return insertTable.execute();
			return 0;
		}
		else if(query.contains("update"))
		{
			return 0;
		}
		else if(query.contains("delete"))
		{
			return 0;
		}
		else
		{		
			return 0;
		}

	}

}
