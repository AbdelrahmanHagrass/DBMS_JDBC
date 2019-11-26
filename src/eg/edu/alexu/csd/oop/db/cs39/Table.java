package eg.edu.alexu.csd.oop.db.cs39;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.*;
import java.util.*;

import org.junit.runners.ParentRunner;

public class Table {
	String ParentDB;
	Vector<String> types = new Vector<String>();
	Vector<String> names = new Vector<String>();
	Map<String, String> col_type = new HashMap<String, String>();
	String Table_Name;
	File file;
	ArrayList<Vector<Object>> items = new ArrayList<Vector<Object>>();

	public Table(String Table_Name, Vector<String> names, Vector<String> types, String ParentDB) {

		this.ParentDB = ParentDB;
		this.Table_Name = Table_Name;
		this.types = (Vector<String>) types.clone();
		this.names = (Vector<String>) names.clone();
		this.file = new File(this.ParentDB + "\\" + this.Table_Name + ".xml");
		for (int i = 0; i < types.size(); i++) {
			col_type.put(this.names.get(i), this.types.get(i));
		}
	}

	public Table(String Table_Name, Vector<String> names, Vector<String> types) {
		this.Table_Name = Table_Name;
		this.types = (Vector<String>) types.clone();
		this.names = (Vector<String>) names.clone();
		for (int i = 0; i < types.size(); i++) {
			col_type.put(this.names.get(i), this.types.get(i));
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

	public Table(String ParentDB) {
		this.ParentDB = ParentDB;
	}

	public Table() {

	}

	public int InsertIntoTable(Vector<Object> input) throws Exception {
		if (input == null || input.size() != names.size()) {
			return 0;
		}
		for (int i = 0; i < types.size(); i++) {
			String a = input.get(i).getClass().getSimpleName();
			if (types.get(i).compareTo("varchar")==0) {
				if (a.compareTo("String") != 0) {
					System.out.println("Invalid Input");
					return 0;
				}
			} else {
				if (a.compareTo("Integer") != 0) {
					System.out.println("Invalid Input");
					return 0;
				}
			}
		}
		items.add((Vector<Object>) input.clone());
		return 1;
	}

	public void DeleteFromTable(int Row)// Delete from table Where col=value and rows suppose to start from 1
	{
		if (Row == 0)// if WHERE isn't there then delete all items in the table
		{
			items.clear();
			return;
		}
		items.remove(Row - 1);
	}

	public int DeleteFromTableWithCondition(String field, String ID)// example Delete From Table WHERE employeID=3
	{
		int X = names.indexOf(field);
		int counter = 0;
		for (int i = 0; i < items.size(); i++) {
			String j = items.get(i).get(X).toString();
			if (j.compareTo(ID) == 0) {
				DeleteFromTable(i + 1);
				i--;
				counter++;
			}
		}
		return counter;
	}

	/**
	 * 
	 * @param col == to the names of columns(equal to the names when the * in the
	 *            command line
	 * @return new table with the desired colomns 
	 * @throws Exception
	 */
	public Table SelectFromTable(Vector<String> col) throws Exception {
		Table New = new Table(this.ParentDB);
		Vector<Integer> index = new Vector<Integer>();
		for (int i = 0; i < col.size(); i++) {
			String a = col.get(i);
			String b = col_type.get(a);
			New.names.add(a);
			New.types.add(b);
			index.add(names.indexOf(a));
		}
		for (int i = 0; i < items.size(); i++) {
			Vector<Object> input = new Vector<Object>();
			for (int j = 0; j < index.size(); j++) {
				int x = index.get(j);
				input.add(items.get(i).get(x));
			}
			New.InsertIntoTable(input);
		}
		return New;
	}

	public Object[][] SelectFromTable2DArray(Vector<String> col) throws Exception {
		Table a = SelectFromTable(col);
		Object[][] out = new Object[a.items.size()][a.items.get(0).size()];
		for (int i = 0; i < a.items.size(); i++) {
			for (int j = 0; j < a.items.get(0).size(); j++) {
				out[i][j] = a.items.get(i).get(j);
			}
		}
		return out;
	}
	
	public Object[][] SelectTable() throws Exception {
		return SelectFromTable2DArray(this.names);
	}

	/**
	 * 
	 * @param type      =0 when equal type =1 when bigger type =-1 when smaller
	 * @param field     = colomn name
	 * @param Condition
	 * @return
	 */
	public Object[][] SelectFromTableCondition(int type, String field, String Condition) {
		if (items.size() == 0) {
			return null;
		}
		ArrayList<Vector<Object>> a = new ArrayList<Vector<Object>>();
		int X = names.indexOf(field);

		for (int i = 0; i < items.size(); i++) {

			String j = items.get(i).get(X).toString();
			if (this.col_type.get(field).compareTo("int") == 0) {
				int x = Integer.parseInt(j);
				int y = Integer.parseInt(Condition);
				if (type == 0 && x == y) {
					a.add((Vector<Object>) items.get(i).clone());
				} else if (type == 1 && x > y) {
					a.add((Vector<Object>) items.get(i).clone());
				} else if (type == -1 && x < y) {
					a.add((Vector<Object>) items.get(i).clone());
				}
			} else {
				if (type == 0) {
					if (j.compareTo(Condition) == 0) {
						a.add((Vector<Object>) items.get(i).clone());
					}
				} else if (type == -1) {
					if (j.compareTo(Condition) < 0) {
						a.add((Vector<Object>) items.get(i).clone());
					}
				} else {
					if (j.compareTo(Condition) > 0) {
						a.add((Vector<Object>) items.get(i).clone());
					}
				}
			}

		}
		Object[][] out = new Object[a.size()][items.get(0).size()];
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < items.get(0).size(); j++) {
				out[i][j] = a.get(i).get(j);
			}
		}
		return out;
	}
	public Object[][] SelectFromTableMultipleCondition(String field,Vector<String>Condition)
	{
		//example SELECT * FROM Customers
		//WHERE Country IN ('Germany', 'France', 'UK');
		if (items.size() == 0) {
			return null;
		}
		ArrayList<Vector<Object>> a = new ArrayList<Vector<Object>>();
		int X = names.indexOf(field);

		for (int i = 0; i < items.size(); i++) {

			String j = items.get(i).get(X).toString();
			
			for(int k=0;k<Condition.size();k++)
			{
			if (this.col_type.get(field).compareTo("int") == 0) {
				int x = Integer.parseInt(j);
				int y = Integer.parseInt(Condition.get(k));
				if (x == y) {
					a.add((Vector<Object>) items.get(i).clone());
					break;
				} 
			} else {
					if (j.compareTo(Condition.get(k)) == 0) {
						a.add((Vector<Object>) items.get(i).clone());
						break;
				} 
			}
			}
		}
		Object[][] out = new Object[a.size()][items.get(0).size()];
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < items.get(0).size(); j++) {
				out[i][j] = a.get(i).get(j);
			}
		}
		return out;
			
		
	}
	public void Update(int Row, String field, String NewValue)// Rows starts with 1
	{
		int X = names.indexOf(field);
		if (types.get(X).compareTo("varchar") == 0) {
			items.get(Row - 1).setElementAt(NewValue, X);
		} else {
			int New = Integer.parseInt(NewValue);
			items.get(Row - 1).setElementAt(New, X);
		}
	}

	public int UpdateWithCondition(int type, String Condition, String ID, String field, String NewValue) {// type=0
																											// means
					// equal																											// type =1
					// means
																											// bigger
																											// type =-1
																											// means
																											// smaller
		int X = names.indexOf(ID);
		int count = 0;
		for (int i = 0; i < items.size(); i++) {
			String j = items.get(i).get(X).toString();
			if (this.col_type.get(ID).compareTo("int") == 0) {
				int x = Integer.parseInt(j);
				int y = Integer.parseInt(Condition);
				System.out.println("asdasdsa");
				if (type == 0 && x == y) {
					Update(i + 1, field, NewValue);
					count++;
				} else if (type == 1 && x > y) {
					Update(i + 1, field, NewValue);
					count++;
				} else if (type == -1 && x < y) {
					Update(i + 1, field, NewValue);
					count++;
				}
			}
			else
			{
			if (type == 0) {
				if (j.compareTo(Condition) == 0) {
					Update(i + 1, field, NewValue);
					count++;
				}
			} else if (type == -1) {
				if (j.compareTo(Condition) < 0) {
					Update(i + 1, field, NewValue);
					count++;
				}
			} else {
				if (j.compareTo(Condition) > 0) {
					Update(i + 1, field, NewValue);
					count++;
				}
			}
			}
		}
		
		return count;

	}

	public ArrayList<Vector<Object>> getItems() {
		return items;
	}

	public void SaveTable() throws Exception// assumed that you have created a database that will contian the table
	{
		FileOutputStream file = new FileOutputStream(ParentDB + "\\" + Table_Name + ".xml");
		XMLEncoder a = new XMLEncoder(file);
		Table s = new Table(this.Table_Name, this.names, this.types, this.ParentDB);
		s.items=(ArrayList<Vector<Object>>) SelectFromTable(this.names).getItems().clone();
		
		a.writeObject(s);
		a.close();
		file.close();
	}

	public Table LoadTable(String path) throws IOException
	// Table h=new Table(); h=h.load(path);
	{
		FileInputStream file = new FileInputStream(path);
		XMLDecoder a = new XMLDecoder(file);
		Table s = (Table) a.readObject();
		a.close();
		file.close();
		return s;
	}

	public File getFile() {
		return file;
	}

	public void DropTable() throws Exception// delete the table file and the table it self
	{
		File file = getFile();
		file.delete();
	}
}
