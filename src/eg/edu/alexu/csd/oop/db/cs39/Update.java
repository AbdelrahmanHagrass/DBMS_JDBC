package eg.edu.alexu.csd.oop.db.cs39;
import java.sql.SQLException;
import java.util.*;
public class Update {
	
	Table toBeUpdated;
	
	String TableName;
	DB ParentDB;
	
	int type;
	String Condition;
	String field;
	String ID;
	String NewValue;
	Vector<String> col;
	Vector<Object> values;
	boolean f=false;
	public Update(String TableName , DB ParentDB , int type , String Condition , String ID , String field , String NewValue) {
		f=true;
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
		this.ParentDB = ParentDB;
		this.col=col;
		this.values=values;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0)
			{   System.out.println("d5l el update");
				toBeUpdated =  ParentDB.Tables.get(i) ;
			}
		}
		
	}
	
	public int execute () throws SQLException
	{ 
	if(toBeUpdated==null) {
		 return 0 ;
	}
	
		if(f) { f=false;
			return toBeUpdated.UpdateWithCondition(type, Condition, ID, field, NewValue) ;}
		else{   
			 return toBeUpdated.Updatecolumns(col, values);}
	}

}