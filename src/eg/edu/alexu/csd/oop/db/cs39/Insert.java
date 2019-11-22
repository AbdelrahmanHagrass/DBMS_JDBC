package eg.edu.alexu.csd.oop.db.cs39;

import java.util.Vector;

public class Insert {
	
	Table toBeInsertedIn;
	String TableName;
	DB ParentDB;
	Vector<String> inputs;
	
	public Insert(String TableName , DB ParentDB , Vector<String> inputs) {
		
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		this.inputs = inputs;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name() == TableName)
			{
				toBeInsertedIn =  ParentDB.Tables.get(i) ;
			}
		}
	}
	
	public int execute () throws Exception
	{
		//return toBeInsertedIn.InsertIntoTable(inputs);
		return 0;
	}

}
