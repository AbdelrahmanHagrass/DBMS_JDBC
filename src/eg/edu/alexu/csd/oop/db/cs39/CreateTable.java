package eg.edu.alexu.csd.oop.db.cs39;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

public class CreateTable implements Command {
	
	Table newTable;
	String Table_Name;
	Vector<String>names;
	Vector<String>types;
	String ParentDB;
	DB ParentDBc;
	
	public CreateTable(String Table_Name,Vector<String>names,Vector<String>types,String ParentDB,DB ParentDBc) {
		
		this.ParentDBc = ParentDBc;
		this.Table_Name = Table_Name ;
		this.names = names;
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
		
		//newTable = new Table (Table_Name,name,types,ParentDB,ParentDBc);
		
		try {
			ParentDBc.createTable(this.Table_Name,this.names,this.types);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
