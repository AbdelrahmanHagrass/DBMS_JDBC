package eg.edu.alexu.csd.oop.jdbc.cs39;

import java.io.File;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import com.sun.jdi.connect.spi.Connection;

public class MainTest {
	
	public static void main(String[] args) throws SQLException {
		
//		Driver driver = new DriverImp();
//		Properties info = new Properties();
//		File dbDir = new File("/*your database folder location*/");
//		info.put("path", dbDir.getAbsoluteFile());
//		Connection connection = (Connection) driver.connect("jdbc:xmldb://localhost", info);
		
//		String s = "create database data1";
//		System.out.println(s.trim().split("\\s")[0]);
//		System.out.println(s.trim().split("\\s")[1] + " 1");
//		System.out.println(s.trim().split("\\s")[2]);
//		System.out.println(s.trim());
		
		String s = "/home/databasename//nono";
		System.out.println(s.trim().split("(/)")[1]);
		String[] pathnames = s.trim().split("(/)");
		System.out.println(pathnames[pathnames.length-1]);
	}
	

}
