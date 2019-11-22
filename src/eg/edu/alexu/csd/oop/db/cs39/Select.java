package eg.edu.alexu.csd.oop.db.cs39;

import java.util.Vector;

public class Select {
	
	Table toBeSelected;
	String TableName;
	DB ParentDB;
	Vector<String> cols;
	
	public Select(String TableName , DB ParentDB , Vector<String> cols) {
		
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		this.cols = cols;
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
		return toBeSelected.SelectFromTable2DArray(cols);
	}

}
