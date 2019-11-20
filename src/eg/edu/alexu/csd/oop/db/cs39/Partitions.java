package eg.edu.alexu.csd.oop.db.cs39;
import java.util.*;
public class Partitions {
public static String databasename;
public void Insert(String s) {
		String table_name=(s.substring(s.indexOf("INTO")+5,s.indexOf('('))).trim();
		String temp[]=((s.substring(s.indexOf('(')+1, s.indexOf(')')).trim())).split(",");
		Vector<String> columns=new Vector();
		for(String t : temp) {
			t=t.trim();
			columns.add(t);
		}
		Vector<String> values=new Vector();
		temp=((s.substring(s.lastIndexOf('(')+1, s.lastIndexOf(')')).trim())).split(",");
		for(String t : temp) {t=t.trim();
		values.add(t);}
	}
public void Delete(String s) {
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	String column=(s.substring(s.indexOf("WHERE")+6, s.indexOf('='))).trim();
	String value=(s.substring(s.indexOf('=')+1)).trim();
	
	}
public void Update(String s) {
	String table_name=(s.substring(s.indexOf("UPDATE")+7, s.indexOf("SET"))).trim();
	String column1=(s.substring(s.indexOf("SET")+4, s.indexOf('='))).trim();
	String column2=(s.substring(s.indexOf("WHERE")+6,s.lastIndexOf('='))).trim();
	String value1=(s.substring(s.indexOf('=')+1, s.indexOf("WHERE"))).trim();
	String value2=(s.substring(s.lastIndexOf('=')+1)).trim();
 }
public void CreateTable(String s) {
	String table_name=(s.substring(s.indexOf("TABLE")+6, s.indexOf('('))).trim();
	String temp[]=((s.substring(s.indexOf('(')+1, s.indexOf(')')).trim())).split(",");
	Vector<String> types=new Vector();
	Vector<String> columns=new Vector();
	for(String t : temp) {
		t=t.trim();
		columns.add(t.substring(t.indexOf(t.charAt(0)),t.indexOf(' ')));
		types.add(t.substring(t.lastIndexOf(' ')+1));
	}
	try{DB Database_object=new DB(databasename);
    Database_object.createTable(table_name, columns, types); 
}
catch(Exception e) {e.printStackTrace();}
}
public void CreateDatabase(String s) {
	String Database_name=(s.substring(s.indexOf("DATABASE")+9)).trim();	
	try{DB Database_object=new DB(Database_name);}
	catch(Exception e) {e.printStackTrace();}
	databasename=Database_name;
}
public void SelectTable(String s) {
	String table_name=(s.substring(s.indexOf("FROM")+5)).trim();	
}
public void DropTable(String s) {
	String table_name=(s.substring(s.indexOf("TABLE")+6)).trim();
}
public void DropDatabase(String s) {
	String Database_name=(s.substring(s.indexOf("DATABASE")+9)).trim();
}
public void Select(String s) {
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	String column=(s.substring(s.indexOf("WHERE")+6, s.indexOf('='))).trim();
	String value=(s.substring(s.indexOf('=')+1)).trim();	
}

}