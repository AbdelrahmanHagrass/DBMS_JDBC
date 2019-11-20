package eg.edu.alexu.csd.oop.db.cs39;

import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLXML;

import javax.sql.rowset.serial.SQLInputImpl;

public class IDataBase implements Database {

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		// TODO Auto-generated method stub
		
		if(dropIfExists)
		{
			
		}

		CreateDB db = new CreateDB(databaseName);
		
		
	
		return db.getDbPath();
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
