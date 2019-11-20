package eg.edu.alexu.csd.oop.db.cs39;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.*;
import java.util.*;

import org.junit.runners.ParentRunner;
public class Table {
	  String ParentDB;
	 Vector<String>types=new Vector<String>() ;
	 Vector<String>names=new Vector<String>();
	Map<String,String> col_type=new HashMap<String,String>();
	 String Table_Name;
	ArrayList<Vector<Object>> items=new ArrayList<Vector<Object>>();
	public Table(String Table_Name,Vector<String>names,Vector<String>types,String ParentDB)
	{
		this.ParentDB=ParentDB;
		this.Table_Name=Table_Name;
		this.types=(Vector<String>) types.clone();
		this.names=(Vector<String>) names.clone();
		for(int i=0;i<types.size();i++)
		{
			col_type.put(this.names.get(i),this.types.get(i));
		}
	}
	public Table(String Table_Name,Vector<String>names,Vector<String>types)
	{
		this.Table_Name=Table_Name;
		this.types=(Vector<String>) types.clone();
		this.names=(Vector<String>) names.clone();
		for(int i=0;i<types.size();i++)
		{
			col_type.put(this.names.get(i),this.types.get(i));
		}
	}
	public String getParentDB() {
		return ParentDB;
	}
	public void setParentDB(String parentDB) {
		ParentDB = parentDB;
	}
	public Vector<String> getTypes() {
		return types;
	}
	public void setTypes(Vector<String> types) {
		this.types = types;
	}
	public Vector<String> getNames() {
		return names;
	}
	public void setNames(Vector<String> names) {
		this.names = names;
	}
	public Map<String, String> getCol_type() {
		return col_type;
	}
	public void setCol_type(Map<String, String> col_type) {
		this.col_type = col_type;
	}
	public String getTable_Name() {
		return Table_Name;
	}
	public void setTable_Name(String table_Name) {
		Table_Name = table_Name;
	}
	public void setItems(ArrayList<Vector<Object>> items) {
		this.items = items;
	}
	public Table(String ParentDB)
	{
		this.ParentDB=ParentDB;
	}
	public Table()
	{
		
	}
	public void InsertIntoTable(Vector<Object>input) throws Exception
	{
		if(input==null||input.size()!=names.size())
		{
			return;
		}
		for(int i=0;i<types.size();i++)
		{
			String a=input.get(i).getClass().getSimpleName();
			if(types.get(i)=="varchar")
			{
				if(a.compareTo("String")!=0)
				{
					System.out.println("Invalid Input");
					throw new Exception("Invalid Input");
				}
			}
			else 
			{
				if(a.compareTo("Integer")!=0)
				{
				
					System.out.println("Invalid Input");
					throw new Exception("Invalid Input");
					
				}
			}
		}
		items.add((Vector<Object>) input.clone());
	}
	public void DeleteFromTable(int Row)//Delete from table Where col=value and rows suppose to start from 1
	{	
		if(Row==0)//if WHERE isn't there then delete all items in the table
		{
			items.clear();
			return;
		}
		items.remove(Row-1);
	}
	public void DeleteFromTableWithCondition(String field,String ID)//example Delete From Table WHERE employeID=3
	{
		int X=names.indexOf(field);
		for(int i=0;i<items.size();i++)
		{
			String j=items.get(i).get(X).toString();
			System.out.println(j);
			if(j.compareTo(ID)==0)
			{
				DeleteFromTable(i+1);
			}
		}
	}
	/**
	 * 
	 * @param col == to the names of columns(equal to the names when the * in the command line
	 * @return new table with the desired colomns
	 * @throws Exception 
	 */
	public Table SelectFromTable(Vector<String>col) throws Exception 
	{
			Table New =new Table(this.ParentDB);
		Vector<Integer>index=new Vector<Integer>();
		for(int i=0;i<col.size();i++)
		{
			String a=col.get(i);
			String b=col_type.get(a);
			New.names.add(a);
			New.types.add(b);
			index.add(names.indexOf(a));
		}
		for(int i=0;i<items.size();i++)
		{
			Vector<Object>input=new Vector<Object>();
			for(int j=0;j<index.size();j++)
			{
				int x=index.get(j);
				input.add(items.get(i).get(x));
			}
			New.InsertIntoTable(input);
		}
		return New;
	}
	public void Update(int Row,String field,String NewValue)//Rows starts with 1
	{
		int X=names.indexOf(field);
		
		if(types.get(X)=="varchar")
		{
			items.get(Row-1).setElementAt(NewValue, X);
		}
		else 
		{
			int New=Integer.parseInt(NewValue);
			items.get(Row-1).setElementAt(New, X);
		}
		
	}
	public void UpdateWithCondition(String field,String ID,String NewValue)
	{
		int X=names.indexOf(field);
		for(int i=0;i<items.size();i++)
		{
			String j=items.get(i).get(X).toString();
		
			if(j.compareTo(ID)==0)
			{
				
				Update(i+1,field,NewValue);
			}
		}
	}
	public ArrayList<Vector<Object>> getItems()
	{
		return items;
	}

}
