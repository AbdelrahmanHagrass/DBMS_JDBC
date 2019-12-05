package eg.edu.alexu.csd.oop.db.cs39;


import java.io.FileNotFoundException;

import java.sql.SQLException;

public class CreateDB implements Command{

	DB newDB ;
	String databasename;
	String MainDirectoryPath;
	
	public CreateDB(String databasename,String MainDirectoryPath) {
		
		this.databasename = databasename;
		this.MainDirectoryPath= MainDirectoryPath;
	}
	
	public DB getDB() {
		return newDB;
	}
	
	public String getnameofDB()
	{
		return databasename;
	}
	
	public String getpathofDB()
	{
		return newDB.getAbsolutePath();
	}
	
	@Override
	public void execute() throws SQLException {
		
		try {
			newDB = new DB(this.databasename,MainDirectoryPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	
//	File file;
//	
//	public CreateDB(String databaseName) {
		
		
//		String fileSeparator = System.getProperty("file.separator");
//		String relativePath = "MainDirectory"+fileSeparator+databaseName;
//        file = new File(relativePath);
//        if(file.mkdir())
//        {
//		    System.out.println(relativePath+" File Created in Project root directory");
//		}
//        else
//        {
//        	System.out.println("File "+relativePath+" already exists in the project root directory");
//        }
        //System.out.println(file.getAbsolutePath());
//        try {
//			System.out.println(file.getCanonicalPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        	
//	}
//	
//	public String getDbPath()
//	{
//		return this.file.getAbsolutePath();
//	}
	

}
