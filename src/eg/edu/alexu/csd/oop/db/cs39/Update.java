//package eg.edu.alexu.csd.oop.db.cs39;
//import java.util.*;
//public class Update {
//	
//	Table toBeUpdated;
//	
//	String TableName,column,value;
//	DB ParentDB;
//	int type;
//	String Condition;
//	String field;
//	String ID;
//	String NewValue;
//	Vector<String> col;
//	Vector<Object> values;
//	int f=0;
//	public Update(String TableName , DB ParentDB , int type , String Condition , String ID , String field , String NewValue) {
//		f=1;
//		this.type=type;
//		this.Condition=Condition;
//		this.ID=ID;
//		this.NewValue=NewValue;
//		this.TableName = TableName;
//		this.ParentDB = ParentDB;
//		this.field = field;
//		
//		this.ParentDB = ParentDB;
//		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
//		{
//			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
//			{
//				toBeUpdated =  ParentDB.Tables.get(i) ;
//			}
//		}	
//	}
//	public Update(String TableName,DB ParentDB,Vector<String> col,Vector<Object> values) {
//		f=2;
//		this.ParentDB = ParentDB;
//		this.col=col;
//		this.values=values;
//		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
//		{
//			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
//			{
//				toBeUpdated =  ParentDB.Tables.get(i) ;
//			}
//		}
//		
//	}
//	public Update(String TableName,DB ParentDB,Vector<String> col,Vector<Object> values,String column,String value) {
//		f=3;
//		this.ParentDB = ParentDB;
//		this.col=col;
//		this.values=values;
//		this.column=column;
//		this.value=value;
//		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
//		{
//			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
//			{
//				toBeUpdated =  ParentDB.Tables.get(i) ;
//			}
//		}
//	}
//	
//	public int execute () throws Exception
//	{    
//		 if(toBeUpdated==null) {f=0;
//			 return 0;}
//		
//		if(f==1) { f=0;
//			return toBeUpdated.UpdateWithCondition(type, Condition, ID, field, NewValue) ;}
//		else if (f==2){   f=0;
//			return toBeUpdated.Updatecolumns(col, values);}
//	
//	else {  f=0;
//        	return 	toBeUpdated.Updatecolumnscondition(col, values, column, value);
//	}
//	}
//}
package eg.edu.alexu.csd.oop.db.cs39;
import java.util.*;
public class Update {
	
	Table toBeUpdated;
	
	String TableName,column,value;
	DB ParentDB;
	int type,Operator;
	String Condition;
	String field;
	String ID;
	String NewValue;
	Vector<String> col;
	Vector<Object> values;
	int f=0;
	public Update(String TableName , DB ParentDB , int type , String Condition , String ID , String field , String NewValue) {
		f=1;
		this.type=type;
		this.Condition=Condition;
		this.ID=ID;
		this.NewValue=NewValue;
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		this.field = field;
		
		this.ParentDB = ParentDB;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
			{
				toBeUpdated =  ParentDB.Tables.get(i) ;
			}
		}	
	}
	public Update(String TableName,DB ParentDB,Vector<String> col,Vector<Object> values) {
		f=2;
		this.ParentDB = ParentDB;
		this.col=col;
		this.values=values;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
			{  
				toBeUpdated =  ParentDB.Tables.get(i) ;
			}
		}
		
	}
	public Update(String TableName,DB ParentDB,Vector<String> col,Vector<Object> values,String column,String value,int operator) {
		f=3;
		this.ParentDB = ParentDB;
		this.col=col;
		this.values=values;
		this.column=column;
		this.value=value;
		this.Operator=operator;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
			{
				toBeUpdated =  ParentDB.Tables.get(i) ;
			}
		}
	}
	
	public int execute () throws Exception
	{    
		 if(toBeUpdated==null) {f=0;
			 return 0;}
		
		if(f==1) { f=0;
			return toBeUpdated.UpdateWithCondition(type, Condition, ID, field, NewValue) ;}
		else if (f==2){   f=0;
			return toBeUpdated.Updatecolumns(col, values);}
	
	else {  f=0;
        	return 	toBeUpdated.Updatecolumnscondition(col, values, column, value,Operator);
	}
	}
}