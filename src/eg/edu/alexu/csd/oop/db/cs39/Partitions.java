package eg.edu.alexu.csd.oop.db.cs39;
import java.util.*;
public class Partitions {
public static String databasename,tablename,Column1,Column2,Value1,Value2,Column,Value;
Vector<String> Columns,Types;
Vector<Object> Values;
public void Partitions(String query) {
	Parser Par =new Parser();
	Par.checkInput(query);
}
public Vector<Object> Insert(String s) {
		String table_name=(s.substring(s.indexOf("INTO")+5,s.indexOf('('))).trim();
		tablename=table_name;
		String temp[]=((s.substring(s.indexOf('(')+1, s.indexOf(')')).trim())).split(",");
		Vector<String> columns=new Vector();
		for(String t : temp) {
			t=t.trim();
			columns.add(t);
		}
		Vector<Object> values=new Vector();
		temp=((s.substring(s.lastIndexOf('(')+1, s.lastIndexOf(')')).trim())).split(",");
		for(String t : temp) {t=t.trim();
		values.add(t);}
		Columns=columns;Values=values;
		return values ;
	}
public void Delete(String s) {
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	tablename=table_name;
	String column=(s.substring(s.indexOf("WHERE")+6, s.indexOf('='))).trim();
	String value=(s.substring(s.indexOf('=')+1)).trim();
	
	}
public void Update(String s) {
	String table_name=(s.substring(s.indexOf("UPDATE")+7, s.indexOf("SET"))).trim();
	tablename=table_name;
	String column1=(s.substring(s.indexOf("SET")+4, s.indexOf('='))).trim();
	String column2=(s.substring(s.indexOf("WHERE")+6,s.lastIndexOf('='))).trim();
	String value1=(s.substring(s.indexOf('=')+1, s.indexOf("WHERE"))).trim();
	String value2=(s.substring(s.lastIndexOf('=')+1)).trim();
	Column1=column1;
	Column2=column2;
	Value1=value1;
	Value2=value2;
 }
public Vector<String> CreateTable(String s) {
	
	String table_name=(s.substring(s.indexOf("TABLE")+7, s.indexOf('('))).trim();
	tablename=table_name;
	String temp[]=((s.substring(s.indexOf('(')+1, s.indexOf(')')).trim())).split(",");
	
	Vector<String> types=new Vector();
	Vector<String> columns=new Vector();
	Vector<String> columnsGui=new Vector();
	for(String t : temp) {
		t=t.trim();
		columns.add(t.substring(t.indexOf(t.charAt(0)),t.indexOf(' ')));
		columnsGui.add(t.substring(t.indexOf(t.charAt(0)),t.indexOf(' ')));
		types.add(t.substring(t.lastIndexOf(' ')+1));
	}
	columnsGui.add(table_name);
	try{DB Database_object=new DB(databasename);
    Database_object.createTable(table_name, columns, types); 
}
catch(Exception e) {

	e.printStackTrace();
	return null;}
	Columns=columns;Types=types;
	return columnsGui ;
	
}
public String CreateDatabase(String s) {
	String Database_name=(s.substring(s.indexOf("DATABASE")+8)).trim();	
	try{DB Database_object=new DB(Database_name);}
	catch(Exception e) {e.printStackTrace();}
	databasename=Database_name;
	return Database_name ;
}
public void SelectTable(String s) {
	String table_name=(s.substring(s.indexOf("FROM")+5)).trim();
	tablename=table_name;
}
public void DropTable(String s) {
	String table_name=(s.substring(s.indexOf("TABLE")+6)).trim();
	tablename=table_name;
}
public void DropDatabase(String s) {
	String Database_name=(s.substring(s.indexOf("DATABASE")+9)).trim();
}
public void Select(String s) {
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	tablename=table_name;
	String column=(s.substring(s.indexOf("WHERE")+6, s.indexOf('='))).trim();
	String value=(s.substring(s.indexOf('=')+1)).trim();	
	Column=column;
	Value=value;
}
public String getTablename() {return tablename;}
public String getDatabasename() {return databasename;}
public String getDeletecolumn() {return Column;}
public String getDeletevalue() {return Value;}
public String getUpdatecolumn1() {return Column1;}
public String getUpdatecolumn2() {return Column2;}
public String getUpdatevalue1() {return Value1;}
public String getUpdatevalue2() {return Value2;}
public Vector<Object> getvalues(){return Values;}
public Vector<String> getcolumns(){return Columns;}
public Vector<String> gettypes(){return Types;}

}