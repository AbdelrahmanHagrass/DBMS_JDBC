package eg.edu.alexu.csd.oop.db.cs39;

import java.sql.SQLException;

public class MainTest {
	
	public static void main(String[] args) throws SQLException {
		
		IDataBase idb = new IDataBase();
		
		//idb.QueryManagement("CREATE DATABASE second");
		//idb.QueryManagement("DROP DATABASE second");
		
		//idb.lastDB.
		//create database
		idb.QueryManagement("CREATE DATABASE data1"); // De httms7
		idb.QueryManagement("CREATE DATABASE data2");
		idb.QueryManagement("CREATE DATABASE test"); // de htt7at feeha el tables kolaha
		
		//drop database 
//		idb.QueryManagement("DROP DATABASE data1"); // error ...create new database and delete it ?!
		
		//create table
//		idb.QueryManagement("create table table1(id int,name varchar)");
//		idb.QueryManagement("create table table2(id int,name varchar)");
		idb.QueryManagement("CREATE TABLE table1(id int,name varchar)");
		idb.QueryManagement("CREATE TABLE table2(id int,name varchar)");
		idb.QueryManagement("CREATE TABLE table3(id int,name varchar)"); //error in name ! "able3"
		
		//drop table
//		idb.QueryManagement("drop table table1"); //error ..table must be added to the db arraylist in class table
		
		//insert 			//error ...insert in the parser is not selected
		//idb.QueryManagement("insert into table1 values (1,'ziad')");
		//idb.QueryManagement("INSERT INTO table1 VALUES (1,'ziad')");
		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (19,'ayman')");
		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (39,'body')");
		//idb.QueryManagement("DELETE FROM table1 WHERE id = 19");
		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (99,'hagrosy el 3agrosy')"); //error
		try {
			idb.lastDB.SaveDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (99,'ahmed')");
		try {
			idb.lastDB.SaveDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idb.QueryManagement("INSERT INTO table1 (id,name) VALUES (1001010,'ahaaaaaaa')");
		
		try {
			idb.lastDB.SaveDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//idb.QueryManagement("DELETE From table1  WHERE name='ayman'");
		
		idb.QueryManagement("DROP DATABASE data1"); //tmam
		
		
		//select
		
//		idb.QueryManagement("select * from table1");
//		idb.QueryManagement("");
//		idb.QueryManagement("");
//		idb.QueryManagement("");
		
		//update 
//		idb.QueryManagement("update table1 set name = 'Ali' where id = 3 ");
//		idb.QueryManagement("");
//		idb.QueryManagement("");
		//delete
//		idb.QueryManagement("delete from table1 where id = 2");
//		idb.QueryManagement("");
//		idb.QueryManagement("");
//		idb.QueryManagement("");
	
		
	}

}
