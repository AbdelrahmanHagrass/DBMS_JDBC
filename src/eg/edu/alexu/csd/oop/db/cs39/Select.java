package eg.edu.alexu.csd.oop.db.cs39;

import java.util.Vector;

public class Select {
	
	Table toBeSelected;
	String TableName;
	DB ParentDB;
	//Vector<String> cols;
	
	int type;
	String field; 
	String Condition;
	
	public Select(String TableName , DB ParentDB , int type , String field , String Condition) {
		
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		//this.cols = cols;
		this.type = type;
		this.field = field;
		this.Condition = Condition;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name() == TableName)
			{
				toBeSelected =  ParentDB.Tables.get(i) ;
			}
		}
	}
	
	public Object[][] execute () throws Exception
	{
		//return toBeSelected.SelectFromTable2DArray(cols);
		return toBeSelected.SelectFromTableCondition(type, field, Condition);
	}

}
