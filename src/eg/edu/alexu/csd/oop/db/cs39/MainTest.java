package eg.edu.alexu.csd.oop.db.cs39;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;


public class MainTest {
	
	public static void main(String[] args) throws Exception {
		
//		//IDataBase idb = new IDataBase();
//		IDataBase idb = IDataBase.getUniqueInstance();
//		
//		Vector<String> names = new Vector<>();
//    	Vector<String> types = new Vector<>();
//    	Vector<Object> input = new Vector<>();
//    	DB first = new DB("data1");
//    	names.add("ahmed");
//		types.add("VARCHAR");
//		names.add("aly");
//		types.add("VARCHAR");
//		names.add("ID");
//		types.add("INT");
//		input.add("Mexico");
//		input.add("das");
//		input.add(12312);
//    	Table H = null;
//		try {
//			H = first.createTable("a0", names, types);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 	   first.SaveDataBase();
// 	  H=H.LoadTable("E:\\JavaProjectsEclipse\\DBMS\\DATA1\\a0.xml");
// 	  H.InsertIntoTable(input);
// 	  H.InsertIntoTable(input);
// 	  Object aj[][] = H.SelectTable();
// 	   for(int i=0;i<aj.length;i++)
// 	   {
// 		   for(int j=0;j<aj[0].length;j++)
// 		   {
// 			   System.out.println(aj[i][j]);
// 		   }
// 	   }
// 	  System.out.println(H.getTypes());
//		
//		//Test load
////		try {
////			idb.load();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		idb.QueryManagement("SELECT * FROM table1");
////		for (Map.Entry<String, DB> entry : idb.m.entrySet()) {
////			
////			System.out.println(entry.getValue().getDatabaseName());
////			System.out.println(entry.getValue().Tables.size());
////		}
//		//idb.QueryManagement("CREATE DATABASE second");
//		//idb.executeStructureQuery("CREATE TABLE incomplete_table_name1");
//		//idb.executeStructureQuery("CREATE TABLE table_name9(column_name1 varchar, column_name2 int, column_name3 varchar)");
//        //int count = idb.executeUpdateQuery("UPDATE table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'");
//		
//		//idb.QueryManagement("CREATE DATABASE second");
//		//idb.QueryManagement("DROP DATABASE second");
//		
//		//idb.lastDB.
//		//create database
//		
////		idb.QueryManagement("CREATE DATABASE data1"); // De httms7
////		idb.QueryManagement("CREATE DATABASE data2");
////		idb.QueryManagement("CREATE DATABASE data3");
////		idb.QueryManagement("CREATE DATABASE data4");
//		
////		System.out.println();
////		System.out.println(idb.m.size() + "eh b2a ");
////		for (Map.Entry<String, DB> entry : idb.m.entrySet()) {
////			
////		System.out.println(entry.getValue().getDatabaseName());
////
////		}
////		System.out.println();
////		
////		System.out.println(idb.lastDB.getDatabaseName());
////		System.out.println();
//		
////		idb.QueryManagement("CREATE DATABASE test"); // de htt7at feeha el tables kolaha
////		
//		//drop database 
////		idb.QueryManagement("DROP DATABASE data1"); // error ...create new database and delete it ?!
//		
////		System.out.println();
////		System.out.println(idb.m.size() + "eh b2a ");
////		for (Map.Entry<String, DB> entry : idb.m.entrySet()) {
////			
////		System.out.println(entry.getValue().getDatabaseName());
////
////		}
////		System.out.println();
////		
////		System.out.println(idb.lastDB.getDatabaseName());
////		System.out.println();
//		
//		//create table
////		idb.QueryManagement("create table table1(id int,name varchar)");
////		idb.QueryManagement("create table table2(id int,name varchar)");
////		idb.QueryManagement("CREATE TABLE table1(id int,name varchar)");
////		idb.QueryManagement("CREATE TABLE table2(id int,name varchar)");
////		idb.QueryManagement("CREATE TABLE table3(id int,name varchar)"); //error in name ! "able3"
//		
//		//drop table
////		idb.QueryManagement("drop table table1"); //error ..table must be added to the db arraylist in class table
//		
//		//insert 			//error ...insert in the parser is not selected
//		//idb.QueryManagement("insert into table1 values (1,'ziad')");
//		//idb.QueryManagement("INSERT INTO table1 VALUES (1,'ziad')");
////		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (19,'ayman')");
////		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (39,'body')");
////		idb.QueryManagement("DELETE FROM table1 WHERE id = 19");
////		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (99,'hagrosy el 3agrosy')"); //error
////		try { 
////			idb.save();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (99,'ahmed')");
////		try {
////			//idb.lastDB.SaveDataBase();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
////		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (1001010,'ahaaaaaaa')");
////		
////		try {
////			idb.lastDB.SaveDataBase();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		//idb.QueryManagement("DELETE From table1  WHERE name='ayman'");
////		
////		idb.QueryManagement("DROP DATABASE data1"); //tmam
////		
////		try {
////			idb.save();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
////		
//		//select
//		
////		idb.QueryManagement("select * from table1");
////		idb.QueryManagement("");
////		idb.QueryManagement("");
////		idb.QueryManagement("");
//		
//		//update 
////		idb.QueryManagement("update table1 set name = 'Ali' where id = 3 ");
////		idb.QueryManagement("");
////		idb.QueryManagement("");
//		//delete
////		idb.QueryManagement("delete from table1 where id = 2");
////		idb.QueryManagement("");
////		idb.QueryManagement("");
////		idb.QueryManagement("");
//	
//		
	}

}