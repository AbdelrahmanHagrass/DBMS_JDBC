package eg.edu.alexu.csd.oop.db.cs39;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class DB {
	
	String MainDirectoryPath;
	
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
	public DB(String DatabaseName,String MainDirectoryPath) throws FileNotFoundException
	{
		this.MainDirectoryPath = MainDirectoryPath;
		File File;
		if(MainDirectoryPath!=null)
		{
			File =new File(MainDirectoryPath+System.getProperty("file.separator")+DatabaseName);
		}
		else
		{
			File = new File(DatabaseName);
		}
		 if(!File.exists())
		 {
			 File.mkdirs();
		 }
		 this.file=File;
		 for(int i=DatabaseName.length()-1;i>=0;i--)
		 {
		 }
		 this.DatabaseName=DatabaseName;
	}
	public DB()
	{
		
	}

	public Table createTable(String tablename,Vector<String>names,Vector<String>types) throws IOException
	{
		Table New=new Table(tablename,names,types,DatabaseName);
		FileOutputStream file2 ;
		if(MainDirectoryPath!=null) {
	    file2=new FileOutputStream(MainDirectoryPath+"\\"+DatabaseName+"\\"+tablename+".xml");
		}else {
		file2=new FileOutputStream(DatabaseName+"\\"+tablename+".xml");
		}
		file2.close();
		this.Tables.add(New);
		try {
			SaveDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return New;
	}

	public void DropDatabase() throws Exception
	{
		for(int i=0;i<Tables.size();i++)
		{
			this.Tables.get(i).DropTable();
		}
		//System.out.println(this.file.getAbsoluteFile());
		final File[] files = this.file.listFiles();
		for(int i = 0 ; i < files.length ; i++)
		{
			files[i].delete();
		}
		//System.out.println(files.length);	
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
			this.Tables.get(i).SaveTable(MainDirectoryPath);
		
		}
	}
	
	public DB LoadDataBase(String Databasename) throws 
IOException ,  FileNotFoundException
	{

		DB temp=new DB(Databasename,MainDirectoryPath);
		File file=new File(Databasename);
		if(!file.exists())
		{
			return null;
		}
		File []tables=file.listFiles();
		for(File contents:tables)
		{

			String path=contents.getAbsolutePath();
			String extension=path.substring(path.length()-4);
			if(extension.compareTo(".xml")!=0)
			{
				continue;
			}
			Table a=new Table(Databasename);
			a=a.LoadTable(path);
			//System.out.println("table is loaded");
			temp.Tables.add(a);
		}
		return temp;
	}
	
}
