
package eg.edu.alexu.csd.oop.db.cs39;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class IDataBase implements Database {

	Parser parser = new Parser();
	Partitions p = new Partitions();
	// Map of all created databases
	Map<String, DB> m = new HashMap<String, DB>();
	DB lastDB;
	Command DBcommandCreate;
	Command DBcommandDrop;
	Command DropTable;
	Command CreateTable;
	Select selecTable;
	Insert insertTable;
	Update updateTable;
	String querySmall;
	Delete deleteTable;
	String LastDBpath;

	public void QueryManagement(String query) throws SQLException {
		// create db
		if (parser.checkInput(query) == 2) {
			// drop-if-exist should be handled
			p.CreateDatabase(query);
			LastDBpath = this.createDatabase(p.getDatabasename(), false);

		} else if (parser.checkInput(query) == 3) {
			this.executeStructureQuery(query);
		}
		// create table,drop table,called internally when create db , drop db
		else if (parser.checkInput(query) == 4 || parser.checkInput(query) == 8) {
			
			this.executeStructureQuery(query);
		}
		// update method
		else if (parser.checkInput(query) == 5 || parser.checkInput(query) == 6 || parser.checkInput(query) == 7|| parser.checkInput(query) == 11) {
			
			this.executeUpdateQuery(query);
		}
		// select
		else if (parser.checkInput(query) == 9||parser.checkInput(query) == 1||parser.checkInput(query)==10) {
			this.executeQuery(query);
		} else {

		}

	}

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {

		if (m.containsKey(databaseName.toUpperCase()) == false) {
			
			System.out.println("test msh mwgood 5ales");

			DBcommandCreate = new CreateDB(databaseName);
			try {
				executeStructureQuery("createdatabase");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return DBcommandCreate.getpathofDB();
		} else if (m.containsKey(databaseName.toUpperCase()) == true && dropIfExists) {
			
			System.out.println("Test mwgood w drop if exisits");
			
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
		} else // It exists and it is not required to drop it
		{
			System.out.println("test mwgood bs seebo");
			return m.get(databaseName.toUpperCase()).getAbsolutePath();
		}

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {

		querySmall = query.toLowerCase();

		if (query == "createdatabase") {
			DBcommandCreate.execute();
			m.put(DBcommandCreate.getnameofDB().toUpperCase(), DBcommandCreate.getDB());
			lastDB = DBcommandCreate.getDB();
			return true;
		} else if (query == "dropdatabase") {
			DBcommandDrop.execute();
			m.remove(DBcommandDrop.getnameofDB());

			return true;
		} else if (querySmall.contains("drop") && querySmall.contains("database")) {
			p.DropDatabase(query);
			System.out.println(p.getDropDataBaseName()+"   zx");
			DBcommandDrop = new DropDB(p.getDropDataBaseName(), m);
			DBcommandDrop.execute();
			m.remove(p.getDropDataBaseName());


			
			return true;
		} else if (querySmall.contains("create") && querySmall.contains("table")) {

			Boolean tableExist = false ;
			p.CreateTable(query);
			for(int i = 0 ; i < lastDB.Tables.size() ; i++)
			{
				if( p.getTablename().compareTo(lastDB.Tables.get(i).getTable_Name()) == 0 )
				{
					tableExist = true;
				}
			}
			if(tableExist)
			{
				return false;
			}
			else
			{
				CreateTable = new CreateTable(p.getTablename(), p.getcolumns(), p.gettypes(), lastDB.getDatabaseName(),lastDB);		
				CreateTable.execute();
				return true;
			}

			
		} else if (querySmall.contains("drop") && querySmall.contains("table")) {
			// i will call class partitions with string query ..to get table name.
			p.DropTable(query);
			DropTable = new DropTable(p.getTablename(), lastDB);
			DropTable.execute();

			return true;
		} else {
			// no query is right ...
			return false;
		}

	}

	public Vector<String> getNames(String query) throws SQLException {

		selecTable = new Select(query, lastDB, 0, null, query);
		try {
			return selecTable.getNames();
		} catch (Exception e) {
			e.printStackTrace();
			return null; // should be handled
		}
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		if (parser.checkInput(query) == 1) {
			p.SelectTable(query);
			selecTable = new Select(p.getTablename(), lastDB, 0, null, query);
			try {
				return selecTable.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return null; // should be handled
			}
		}
		
		else if (parser.checkInput(query) == 10) {
			p.selecttwocolumnscondition(query);
			selecTable = new Select(p.getTablename(),lastDB,p.getOperator(),p.getselectconditioncloumn1(),p.getselectconditioncloumn2(),p.getselectconditionvalue());
			try {
				return selecTable.executeColumn();
			} catch (Exception e) {
				e.printStackTrace();
				return null; // should be handled
			}
		}
		else {
			p.Select(query);
			selecTable = new Select(p.getTablename(), lastDB, p.getOperator(), p.getSelectcolumn(), p.getSelectvalue());
			try {
				return selecTable.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return null; // should be handled
			}
		}
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		
		// insert //update //delete
		querySmall = query.toLowerCase();
		if (querySmall.contains("insert")) {
			 p.parent=lastDB;
		     p.Insert(query);
			insertTable = new Insert(p.getTablename() , lastDB, p.getvalues());
			try {

				return insertTable.execute();

			} catch (Exception e) {
				e.printStackTrace();

				return 0;
			}

		} else if (querySmall.contains("update")) {
			p.Update(query);
			updateTable = new Update(p.getTablename(), lastDB, p.getOperator(), p.getUpdatevalue2(),
					p.getUpdatecolumn2(), p.getUpdatecolumn1(), p.getUpdatevalue1());
			try {
				return updateTable.execute();
			} catch (Exception e) {
				return 0;
			}
		} else if (querySmall.contains("delete")) {
			System.out.println("a7a delete");
			if(parser.checkInput(query)==5) {
				p.Delete(query);
			System.out.println(p.getDeletevalue()+p.getDeletecolumn());
			deleteTable = new Delete(p.getTablename(), lastDB, p.getDeletevalue(),  p.getDeletecolumn());
			try {
				return deleteTable.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}else if (parser.checkInput(query)==11) {
			p.DeleteAll(query);
//			System.out.println(p.getDeletevalue()+p.getDeletecolumn());
			deleteTable = new Delete(p.getTablename(), lastDB,null,null);
			System.out.println(p.getTablename()+"deleteall");
			try {
				return deleteTable.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
			
		
		
		
		
		} else {
			return 0;
		}
		return 0;
	}
	public void save() throws Exception
	{
		savedatabasenames();
		for (Map.Entry<String, DB> entry : m.entrySet()) {
			entry.getValue().SaveDataBase();
		}
	}
	public void savedatabasenames() throws Exception
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(".\\DatabaseNames.txt"));
		for (Map.Entry<String, DB> entry : m.entrySet()) {
			bw.write(entry.getValue().getDatabaseName());
			bw.newLine();
		}
		bw.close();	
	}	
	public void load() throws IOException
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(".\\DatabaseNames.txt"));
			String databaseaname;
			while ( (databaseaname = br.readLine()) !=null)
			{
				DB loaded = new DB().LoadDataBase(databaseaname);
				m.put(databaseaname, loaded);
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}