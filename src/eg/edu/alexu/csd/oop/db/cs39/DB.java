package eg.edu.alexu.csd.oop.db.cs39;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
	File file;
	public DB(String DatabaseName) throws FileNotFoundException
	{
		this.DatabaseName=DatabaseName;
		File File =new File(DatabaseName);
		 if(!File.exists())
		 {
			 File.mkdir();
		 }
		 this.file=File;
	}
	public Table createTable(String tablename,Vector<String>names,Vector<String>types) throws IOException
	{
		Table New=new Table(tablename,names,types,DatabaseName);
		FileOutputStream file2=new FileOutputStream(DatabaseName+"\\"+tablename+".xml");
		file2.close();
		this.Tables.add(New);
		return New;
	}
	public void DropDatabase() throws Exception
	{
		for(int i=0;i<Tables.size();i++)
		{
			this.Tables.get(i).DropTable();
		}
		this.file.delete();
		
	}
	public String getAbsolutePath()
	{
		return this.file.getAbsolutePath();
	}
	public void addTable(Table NEW) throws IOException
	{
		FileOutputStream file2=new FileOutputStream(DatabaseName+"\\"+NEW.Table_Name+".xml");
		file2.close();
		this.Tables.add(NEW);
	}
	public void SaveDataBase() throws Exception
	{
		for(int i=0;i<Tables.size();i++)
		{
			this.Tables.get(i).SaveTable();
		}
	}
	/*
	public DB LoadDataBase(String name) throws FileNotFoundException //
	{
		DB temp=new DB("name");
		File file=new File("name");
		
		return null;
	}
	*/
}
