
package eg.edu.alexu.csd.oop.db.cs39;

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
			LastDBpath = this.createDatabase(parser.object.getDatabasename(), false);
			System.out.println("Database is Created / Deleted");

		} else if (parser.checkInput(query) == 3) {
			this.executeStructureQuery(query);
		}
		// create table,drop table,called internally when create db , drop db
		else if (parser.checkInput(query) == 4 || parser.checkInput(query) == 8) {
			this.executeStructureQuery(query);
		}
		// update method
		else if (parser.checkInput(query) == 5 || parser.checkInput(query) == 6 || parser.checkInput(query) == 7) {
			System.out.println("ha?");
			this.executeUpdateQuery(query);
		}
		// select
		else if (parser.checkInput(query) == 9||parser.checkInput(query) == 1) {
			this.executeQuery(query);
		} else {
			System.out.println("no query is selected");
		}

	}

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {

		if (m.containsKey(databaseName) == false) {
			System.out.println("kkkkkkkkkkk mfeesh database bel esm da");
			DBcommandCreate = new CreateDB(databaseName);
			try {
				executeStructureQuery("createdatabase");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return DBcommandCreate.getpathofDB();
		} else if (m.containsKey(databaseName) == true && dropIfExists) {
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
			return m.get(databaseName).getAbsolutePath();
		}

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {

		querySmall = query.toLowerCase();

		if (query == "createdatabase") {
			DBcommandCreate.execute();
			m.put(DBcommandCreate.getnameofDB(), DBcommandCreate.getDB());
			lastDB = DBcommandCreate.getDB();
			return true;
		} else if (query == "dropdatabase") {
			DBcommandDrop.execute();
			m.remove(DBcommandDrop.getnameofDB());
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
			return true;
		} else if (querySmall.contains("drop") && querySmall.contains("database")) {
			p.DropDatabase(query);
			DBcommandDrop = new DropDB(p.getDropDataBaseName(), m);
			DBcommandDrop.execute();
			m.remove(p.getDatabasename());
			return true;
		} else if (querySmall.contains("create") && querySmall.contains("table")) {
			// i will call class partitions with string query ..to get table name,names ,
			// types ,parent DB..
			p.CreateTable(query);
			// System.out.println("n!!!!" + p.getTablename());
			CreateTable = new CreateTable(p.getTablename(), p.getcolumns(), p.gettypes(), lastDB.getDatabaseName(),
					lastDB);
			CreateTable.execute();

			return true;
		} else if (querySmall.contains("drop") && querySmall.contains("table")) {
			// i will call class partitions with string query ..to get table name.
			p.DropTable(query);
			DropTable = new DropTable(p.getTablename(), lastDB);
			System.out.println("a7a gowa");
			System.out.println(lastDB.getAbsolutePath());
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
			System.out.println(p.getTablename() + "lma nshof");
			selecTable = new Select(p.getTablename(), lastDB, 0, null, query);
			try {
				return selecTable.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return null; // should be handled
			}
		} else {
			p.SelectTable(query);
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
			insertTable = new Insert(p.getTablename(), lastDB, p.Insert(query));
			try {
				System.out.println("hey i am inserted");
				return insertTable.execute();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("hey i am not  inserted");
				return 0;
			}

		} else if (querySmall.contains("update")) {
			p.Update(query);
			updateTable = new Update(p.getTablename(), lastDB, p.getOperator(), p.getUpdatevalue2(),
					p.getUpdatecolumn2(), p.getUpdatecolumn1(), p.getUpdatevalue1());
			try {
				return updateTable.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 0;
			}

		} else if (querySmall.contains("delete")) {
			p.Delete(query);
			deleteTable = new Delete(p.getTablename(), lastDB, p.getDeletevalue(),  p.getDeletecolumn());
			try {
				return deleteTable.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}

	}

}