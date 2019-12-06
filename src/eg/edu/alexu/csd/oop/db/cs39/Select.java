
package eg.edu.alexu.csd.oop.db.cs39;

import java.util.Vector;

public class Select {
	
	Table toBeSelected;
	Table toBeSelected2;
	String TableName;
	DB ParentDB;
	//Vector<String> cols;
	
	int type;
	String field; 
	String field2; 
	String Condition;
	
	public Select(String TableName , DB ParentDB , int type , String field , String Condition) {
		
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		//this.cols = cols;
		this.type = type;
		this.field = field;
		this.Condition = Condition;
		try {
			for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
			{
				if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0 )
				{
					toBeSelected =  ParentDB.Tables.get(i) ;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
//	String field,String Condition,String ID
	
public Select(String TableName , DB ParentDB,int type , String field2 , String field , String Condition) {
		
		this.TableName = TableName;
		this.ParentDB = ParentDB;
		//this.cols = cols;
		this.type = type;
		this.field2 = field2;
		this.field=field;
		this.Condition = Condition;
		for(int i = 0 ; i < ParentDB.Tables.size() ; i++)
		{  //System.out.println(ParentDB.Tables.get(i).getTable_Name()+" compare " + TableName);
			if(ParentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0 )
			{  //System.out.println("conditional d5alllll");
				toBeSelected =  ParentDB.Tables.get(i) ;
			}
		}
	}
	public Vector<String> getNames () throws Exception
	{
		return toBeSelected.getNames();
	}
	
	public Object[][] execute () throws Exception
	{      if(field==null) {
		return toBeSelected.SelectTable();
	}
		//return toBeSelected.SelectFromTable2DArray(cols);
		return toBeSelected.SelectFromTableCondition(type, field, Condition);
	}
	public Object[][] executeColumn () throws Exception
	{
		System.out.println("ana gada3");
		System.out.println(toBeSelected==null);
		return toBeSelected.SelectCell(type,field2, field, Condition);
	}
	//The Methods added here are added to access Vectors of : names,types,table name in the statment class 
	//to put it in the constructor of the resultset
	public Vector<String> getTypes () throws Exception
	{
		return toBeSelected.getTypes();
	}
	public String getTableName()
	{
		System.out.println("eltable");
		System.out.println(toBeSelected2==null);
		return toBeSelected.getTable_Name();
	}
	

}