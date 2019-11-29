package eg.edu.alexu.csd.oop.db.cs39;

import java.util.Vector;

public class Insert {
	
	Table toBeInsertedIn;
	String TableName;
	DB ParentDB;
	Vector<Object> inputs;
	
	public Insert(String TableName , DB ParentDB , Vector<Object> inputs) {
		
		//System.out.println("size of db tables "+ParentDB.getTables().size());
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		this.inputs = inputs;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{
			//System.out.println(i+" "+ParentDB.getDatabaseName()+" "+ParentDB.Tables.get(i).getTable_Name() + "  is equal " + TableName);
			//System.out.println(ParentDB.Tables.get(i).getTable_Name()+" "+TableName);
			//System.out.println();
			//System.out.println(ParentDB.Tables.get(i).getTable_Name().compareTo(TableName) + " shoooooo");
			//if(ParentDB.Tables.get(i).getTable_Name() == TableName)
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0 )
			{
				//System.out.println("da5al");
				toBeInsertedIn =  ParentDB.Tables.get(i) ;
			}
		}
	}
	
	public int execute () throws Exception
	{
	
		return toBeInsertedIn.InsertIntoTable(inputs);
	}

}