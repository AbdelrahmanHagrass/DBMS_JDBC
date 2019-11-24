package eg.edu.alexu.csd.oop.db.cs39;

public class Update {
	
	Table toBeUpdated;
	
	String TableName;
	DB ParentDB;
	
	int type;
	String Condition;
	String field;
	String ID;
	String NewValue;
	
	public Update(String TableName , DB ParentDB , int type , String Condition , String ID , String field , String NewValue) {
		
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
			if(ParentDB.Tables.get(i).getTable_Name() == TableName)
			{
				toBeUpdated =  ParentDB.Tables.get(i) ;
			}
		}
		
		
	}
	
	public int execute () throws Exception
	{
		return toBeUpdated.UpdateWithCondition(type, Condition, ID, field, NewValue) ;
	}

}
