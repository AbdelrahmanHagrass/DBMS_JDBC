package eg.edu.alexu.csd.oop.db.cs39;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.*;
import java.util.*;
public class DB {
	public String getDatabaseName() {
		return DatabaseName;
	}
	public void setDatabaseName(String databaseName) {
		DatabaseName = databaseName;
	}
	public ArrayList<Table> getTables() {
		return Tables;
	}
	public void setTables(ArrayList<Table> tables) {
		Tables = tables;
	}
	String DatabaseName;
	
	ArrayList<Table>Tables=new ArrayList<Table>();
	public DB(String DatabaseName) throws FileNotFoundException
	{
		this.DatabaseName=DatabaseName;
		File File =new File(DatabaseName);
		 if(!File.exists())
		 {
			 File.mkdir();
		 }
	}
	public Table createTable(String tablename,Vector<String>names,Vector<String>types) throws FileNotFoundException
	{
		Table New=new Table(tablename,names,types,DatabaseName);
		FileOutputStream file2=new FileOutputStream(DatabaseName+"\\"+tablename+".xml");
		Tables.add(New);
		return New;
	}
	

}
