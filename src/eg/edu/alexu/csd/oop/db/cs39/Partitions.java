package eg.edu.alexu.csd.oop.db.cs39;
import java.util.*;
public class Partitions {
public static String databasename,tablename,Column1,Column2,Value1,Value2,Column,Value,Column3,Value3;
Vector<String> Columns,Types;
Vector<Object> Values;
int Operator;
String Database_name;
DB parent;
public void Partitions(String query) {
	Parser Par =new Parser();
	Par.checkInput(query);
}
public Vector<Object> Insert(String s) {
		String temp[]=((s.substring(s.indexOf('(')+1, s.indexOf(')')).trim())).split(",");
		Vector<String> columns=new Vector();
		Vector<Object> values=new Vector();
		if(s.indexOf('(')==s.lastIndexOf('(')) {
			for(String t : temp) {
				t=t.trim();
				if(t.chars().allMatch(Character::isDigit)) {values.add(Integer.parseInt(t));}
				else {values.add(t);}
				
			}
			return Values=values;
	    }
		for(String t : temp) {
			t=t.trim();
			columns.add(t.toUpperCase());
			
		}
		  
		
		temp=((s.substring(s.lastIndexOf('(')+1, s.lastIndexOf(')')).trim())).split(",");
		for(String t : temp) {t=t.trim();
		if(t.chars().allMatch(Character::isDigit)) {values.add(Integer.parseInt(t));}
		else {values.add(t);}
		}
		Columns=columns;
		s=s.toUpperCase();
		String table_name=(s.substring(s.indexOf("INTO")+5,s.indexOf('('))).trim();
		tablename=table_name;
		
		return Values=sort(values,Columns) ;
	}

public void Delete(String s) {
	String temp=s.replaceAll("\\s+","");
	s=s.toUpperCase();
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	tablename=table_name;
	int index;
	if (s.contains("=")){index=s.indexOf('=');Operator=0;}
	else if (s.contains(">")){index=s.indexOf('>');Operator=1;}
	else{index=s.indexOf('<');Operator=-1;}
	Column=(s.substring(s.indexOf("WHERE")+6, index)).trim();
	Value=s.substring(index+1).trim();
	//Value=(s.substring(index+1)).trim();
	}
public void Update(String s) {
    String temp=s.replaceAll("\\s+","");
    s=s.toUpperCase();
    String table_name=(s.substring(s.indexOf("UPDATE")+7, s.indexOf("SET"))).trim();
    tablename=table_name;
    int index;
    if (s.contains("<")){index=s.indexOf('<');Operator=-1;Value2=temp.substring(temp.lastIndexOf('<')+1);}
    else if (s.contains(">")){index=s.indexOf('>');Operator=1;Value2=temp.substring(temp.lastIndexOf('>')+1);}
    else{index=s.lastIndexOf('=');Operator=0;Value2=temp.substring(temp.lastIndexOf('=')+1);}
    Column1=(s.substring(s.indexOf("SET")+4, s.indexOf('='))).trim();
    Column2=(s.substring(s.indexOf("WHERE")+6,index)).trim();
    Value1=(temp.substring(temp.indexOf('=')+1, temp.toUpperCase().indexOf("WHERE")));
    //Value2=(s.substring(index+1)).trim();
 }
public Vector<String> CreateTable(String s) {

	if(!s.contains("(")) {
    	Columns=new Vector();
    	Values=new Vector();
    	Types=new Vector();
    	return Columns;
    }
	String temp[]=((s.substring(s.indexOf('(')+1, s.indexOf(')')).trim())).split(",");
	Vector<String> types=new Vector();
	Vector<String> columns=new Vector();
	Vector<String> columnsGui=new Vector();
	for(String t : temp) {
		t=t.trim();
		columns.add(t.substring(t.indexOf(t.charAt(0)),t.indexOf(' ')));
		columnsGui.add(t.substring(t.indexOf(t.charAt(0)),t.indexOf(' ')));
		types.add((t.substring(t.lastIndexOf(' ')+1)).toUpperCase());
	}
	s = s.replaceAll("\\s+","");
	String table_name=(s.substring(11, s.indexOf('('))).trim();
	tablename=table_name;
	columnsGui.add(table_name);
	Columns=columns;Types=types;
	return columnsGui ;

}
public String CreateDatabase(String s) {
	s = s.replaceAll("\\s+","");
	String Database_name=(s.substring(14)).trim();	
	databasename=Database_name;
	System.out.println(databasename);
	return Database_name ;
}
public void SelectTable(String s) {
	s=s.toUpperCase();
	String table_name=(s.substring(s.indexOf("FROM")+5)).trim();
	tablename=table_name;
}
public void DropTable(String s) {
	s=s.toUpperCase();
	String table_name=(s.substring(s.indexOf("TABLE")+6)).trim();
	tablename=table_name;
}
public void DropDatabase(String s) {
	s=s.toUpperCase();
	 Database_name=(s.substring(s.indexOf("DATABASE")+9)).trim();
}
public void Select(String s) {
	s=s.toUpperCase();
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	tablename=table_name;
	int index;
	if (s.contains("=")){index=s.indexOf('=');Operator=0;}
	else if (s.contains(">")){index=s.indexOf('>');Operator=1;}
	else{index=s.indexOf('<');Operator=-1;}
	Column3=(s.substring(s.indexOf("WHERE")+6, index)).trim();
	Value3=(s.substring(index+1)).trim();	
}
public Vector<Object> sort(Vector<Object> values,Vector<String> columns){
	ArrayList<Table>Tables=parent.getTables();
	Table T=null;
	for(Table t : Tables) {
		if(t.getTable_Name().compareToIgnoreCase(tablename)==0) {T=t;break;}
	}
    if(T==null) {return null;}
	 int i=0;
	 Vector<String> sortedcolumns=T.getNames2();
     Vector<Object> sortedvalues=new Vector();
     sortedvalues.setSize(values.size());
     for(String temp : columns) {
    	 
    	 sortedvalues.set(sortedcolumns.indexOf(temp), values.elementAt(i));
    	 i++;
     }
    
     return Values=sortedvalues;
}
public void selecttwocolumnscondition(String s) {
	String temp=s.replaceAll("\\s+","");
	s=s.toUpperCase();
	String table_name=(s.substring(s.indexOf("FROM")+5, s.indexOf("WHERE"))).trim();
	tablename=table_name;
	Column1=(s.substring(s.indexOf("SELECT")+7, s.indexOf("FROM"))).trim();
	int index;
	if (s.contains("=")){index=s.indexOf('=');Operator=0;}
	else if (s.contains(">")){index=s.indexOf('>');Operator=1;}
	else{index=s.indexOf('<');Operator=-1;}
	Column2=(s.substring(s.indexOf("WHERE")+6, index)).trim();
	//Value=s.substring(index+1).trim();
	Value=temp.substring(temp.indexOf(s.charAt(index))+1);
}
//public void Updatecolumnsconditions(String s) {
//	String main=s;
//	s=s.toUpperCase();
//	tablename=(s.substring(s.indexOf("UPDATE")+7, s.indexOf("SET"))).trim();
//	String temp []=(main.substring(s.indexOf("SET")+4, s.indexOf("WHERE"))).split(",");
//	Vector<String> columns=new Vector();
//	Vector<Object> values=new Vector();
//	for(String t : temp) {
//		t=t.trim();
//		columns.add((t.substring(0, t.indexOf('='))).trim().toUpperCase());
//		String g=t.substring(t.indexOf('=')+1).trim();
//		if(g.chars().allMatch(Character::isDigit)) {
//		values.add(Integer.parseInt(g));}
//		else {values.add(g);}
//	}
//	int index=0;
//	Column1=s.substring(s.indexOf("WHERE")+6, s.lastIndexOf('=')).trim();
//	Value1=main.substring(main.lastIndexOf('=')+1).trim();
//	
//	Columns=columns;Values=values;
//}
public void Updatecolumnsconditions(String s) {
	String main=s;
	s=s.toUpperCase();
	tablename=(s.substring(s.indexOf("UPDATE")+7, s.indexOf("SET"))).trim();
	String temp []=(main.substring(s.indexOf("SET")+4, s.indexOf("WHERE"))).split(",");
	Vector<String> columns=new Vector();
	Vector<Object> values=new Vector();
	for(String t : temp) {
		t=t.trim();
		columns.add((t.substring(0, t.indexOf('='))).trim().toUpperCase());
		String g=t.substring(t.indexOf('=')+1).trim();
		if(g.chars().allMatch(Character::isDigit)) {
		values.add(Integer.parseInt(g));}
		else {values.add(g);}
	}
	int index=0;
	if(s.contains(">")) {index=s.indexOf('>');Operator=1;}
	else if(s.contains("<")) {index=s.indexOf('<');Operator=-1;}
	else {index=s.lastIndexOf('=');Operator=0;}
	Column1=s.substring(s.indexOf("WHERE")+6, index).trim();
	Value1=main.substring(index+1).trim();
	Columns=columns;Values=values;
}




public void DeleteAll (String s) {
	s=s.toUpperCase();
	String table_name=(s.substring(s.indexOf("FROM")+5)).trim();
	tablename=table_name;
}

public  void Updatecolumns (String s) {
	Vector <String> columns=new Vector();
	Vector <Object> values=new Vector();
	String temp[]=s.substring(s.toUpperCase().indexOf("SET")+4).trim().split(",");
	for(String t : temp) {
		t=t.trim();
		columns.add(t.substring(0, t.indexOf('=')).toUpperCase().trim());
		values.add(t.substring(t.indexOf('=')+1).trim());
	}
	s=s.toUpperCase();
	tablename=(s.substring(s.indexOf("UPDATE")+7, s.indexOf("SET"))).trim(); 
	Columns=columns;Values=values;
	
	}

public String getTablename() {return tablename;}
public String getDropDataBaseName() {return Database_name;}
public String getDatabasename() {return databasename;}
public String getDeletecolumn() {return Column;}
public String getDeletevalue() {return Value;}
public String getUpdatecolumn1() {return Column1;}
public String getUpdatecolumn2() {return Column2;}
public String getUpdatevalue1() {return Value1;}
public String getUpdatevalue2() {return Value2;}
public String getSelectcolumn() {return Column3;}
public String getSelectvalue() {return Value3;}
public Vector<Object> getvalues(){return Values;}
public Vector<String> getcolumns(){return Columns;}
public Vector<String> gettypes(){return Types;}
public String getselectconditioncloumn1() {return Column1;}
public String getselectconditioncloumn2() {return Column2;}
public String getselectconditionvalue() {return Value;}
public int getOperator(){return Operator;}
}