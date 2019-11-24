package eg.edu.alexu.csd.oop.db.cs39;

public class Delete {
	
	
	Table toBeDeleted;
	
	String TableName;
	DB ParentDB;
	

	String field;
	String ID;

	
	public Delete(String TableName , DB ParentDB , String ID , String field ) {
		

		this.ID=ID;
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		this.field = field;
		
		this.ParentDB = ParentDB;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			if(ParentDB.Tables.get(i).getTable_Name() == TableName)
			{
				toBeDeleted =  ParentDB.Tables.get(i) ;
			}
		}
		
		
	}
	
	public int execute () throws Exception
	{
		return toBeDeleted.DeleteFromTableWithCondition(field, ID);
	}

}
