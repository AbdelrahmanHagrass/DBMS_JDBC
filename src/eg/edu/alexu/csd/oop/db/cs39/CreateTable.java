package eg.edu.alexu.csd.oop.db.cs39;

import java.sql.SQLException;
import java.util.Vector;

public class CreateTable implements Command {
	
	Table newTable;
	String Table_Name;
	Vector<String>name;
	Vector<String>types;
	String ParentDB;
	
	public CreateTable(String Table_Name,Vector<String>names,Vector<String>types,String ParentDB) {
		
		this.Table_Name = Table_Name ;
		this.name = names;
		this.types = types;
		this.ParentDB = ParentDB;
	}

	@Override
	public DB getDB() {
		
		//return newTable.getParentDB();
		return null;
	}

	@Override
	public String getpathofDB() {

		return null;
	}

	@Override
	public String getnameofDB() {
		
		return newTable.getParentDB();
	}

	@Override
	public void execute() throws SQLException {
		
		newTable = new Table (Table_Name,name,types,ParentDB);
		
	}

}
