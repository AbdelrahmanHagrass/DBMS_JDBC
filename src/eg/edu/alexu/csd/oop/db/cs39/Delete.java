package eg.edu.alexu.csd.oop.db.cs39;

public class Delete {
	
	
	Table toBeDeleted;
	
	String TableName;
	DB ParentDB;
	

	String field;
	String ID;

	
	public Delete(String TableName , DB ParentDB , String ID , String field ) {
		
		//System.out.println("size of db tables "+ParentDB.getTables().size());
		this.ID=ID;
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		this.field = field;
		
		this.ParentDB = ParentDB;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			//System.out.println(i+" "+ParentDB.getDatabaseName()+" "+ParentDB.Tables.get(i).getTable_Name() + "  is equal " + TableName);
			//System.out.println(ParentDB.Tables.get(i).getTable_Name()+" "+TableName);
			//System.out.println();
			//System.out.println(ParentDB.Tables.get(i).getTable_Name().compareTo(TableName) + " shoooooo");
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0 )
			{
				//System.out.println("da5al el delete");
				toBeDeleted =  ParentDB.Tables.get(i) ;
			}
		}
		
		
	}
	
	public int execute () throws Exception
	{  if(ID==null) {
		return toBeDeleted.DeleteFromTable(0);
	}
		return toBeDeleted.DeleteFromTableWithCondition(field, ID);
	}

}