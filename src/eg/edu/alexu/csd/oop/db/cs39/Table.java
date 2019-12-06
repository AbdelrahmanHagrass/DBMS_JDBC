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
    Vector<String> names2 = new Vector<String>();
    Map<String, String> col_type = new HashMap<String, String>();
    Map<String, String> col_type2 = new HashMap<String, String>();
    String Table_Name;
    File file;
    ArrayList<Vector<Object>> items = new ArrayList<Vector<Object>>();
 
    public Table(String Table_Name, Vector<String> names, Vector<String> types, String ParentDB) {
 
        this.ParentDB = ParentDB;
        this.Table_Name = Table_Name;
        this.types = (Vector<String>) types.clone();
        this.names = (Vector<String>) names.clone();
        for(String temp : names) {this.names2.add(temp.toUpperCase());}
        this.file = new File(this.ParentDB + "\\" + this.Table_Name + ".xml");
        for (int i = 0; i < types.size(); i++) {
            col_type.put(this.names.get(i), this.types.get(i));
            col_type2.put(this.names.get(i).toUpperCase(), this.types.get(i));
        }
       
    }
     public Map<String, String> getCol_type2() {
		return col_type2;
	}

	public void setCol_type2(Map<String, String> col_type2) {
		this.col_type2 = col_type2;
	}

	public void setNames2(Vector<String> names2) {
		this.names2 = names2;
	}
    public Table(String Table_Name, Vector<String> names, Vector<String> types) {
        this.Table_Name = Table_Name;
        this.types = (Vector<String>) types.clone();
        this.names = (Vector<String>) names.clone();
        for(String temp : names) {this.names2.add(temp.toUpperCase());}
        for (int i = 0; i < types.size(); i++) {
            col_type.put(this.names.get(i), this.types.get(i));
            col_type2.put(this.names.get(i).toUpperCase(), this.types.get(i));
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

	public Vector<String> getNames2() {
		return names2;
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
            if (types.get(i).compareTo("VARCHAR")==0) {
                if (a.compareTo("String") != 0) {
                    //System.out.println("Invalid Input");
                    return 0;
                }
            } else {
                if (a.compareTo("Integer") != 0) {
                    //System.out.println("Invalid Input");
                    return 0;
                }
            }
        }
        items.add((Vector<Object>) input.clone());
        //System.out.println("Insert is done");
        return 1;
    }
 
    public int DeleteFromTable(int Row)// Delete from table Where col=value and rows suppose to start from 1
    {
        if (Row == 0)// if WHERE isn't there then delete all items in the table
        { int x = items.size() ;
            items.clear();
            return x;
        }
        items.remove(Row - 1);
        return 0;
    }
 
    public int DeleteFromTableWithCondition(String field, String ID)// example Delete From Table WHERE employeID=3
    {  int X=names2.indexOf(field);
        int counter = 0;
        for (int i = 0; i < items.size(); i++) {
            String j = items.get(i).get(X).toString();
            if (j.compareToIgnoreCase(ID) == 0) {
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
        System.out.println(col);
        Table New = new Table(this.ParentDB);
        Vector<Integer> index = new Vector<Integer>();
        for (int i = 0; i < col.size(); i++) {
            String a = col.get(i);
            String b = col_type2.get(a.toUpperCase());
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
        if(a.items.size()>0) {
        Object[][] out = new Object[a.items.size()][a.items.get(0).size()];
        for (int i = 0; i < a.items.size(); i++) {
            for (int j = 0; j < a.items.get(0).size(); j++) {
                out[i][j] = a.items.get(i).get(j);
            }
        }
        return out;
    }
        else{
 
            Object[][] out1 = new Object[0][0];
        return out1 ;  
        }
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
        int X = names2.indexOf(field);
           
        for (int i = 0; i < items.size(); i++) {
 
            String j = items.get(i).get(X).toString();
            if (this.col_type.get(names.get(X)).compareTo("INT") == 0) {
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
        int X = names2.indexOf(field);
 
        for (int i = 0; i < items.size(); i++) {
 
            String j = items.get(i).get(X).toString();
           
            for(int k=0;k<Condition.size();k++)
            {
            if (this.col_type2.get(field).compareTo("INT") == 0) {
                int x = Integer.parseInt(j);
                int y = Integer.parseInt(Condition.get(k));
                if (x == y) {
                    a.add((Vector<Object>) items.get(i).clone());
                    break;
                }
            } else {
                    if (j.compareToIgnoreCase(Condition.get(k)) == 0) {
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
    /**
     *
     * @param field the column which will be selected
     * @param Condition the condition field
     * @param ID the condition field=ID
     */
    public Object[][] SelectCell(int type,String field,String Condition,String ID)
    {
        if(items.size()==0)
        {
            return null;
        }
        //System.out.println(field);
       // System.out.println(Condition);
       // System.out.println(ID);
        int X=0;
        int Y=0;
        X=names2.indexOf(Condition);
        Y=names2.indexOf(field);
        Object [][]out=new Object[items.size()][1];
        int k =0;
        for(int i=0;i<items.size();i++)
        {
            String j=items.get(i).get(X).toString();
            if (this.col_type.get(names.get(X)).compareTo("INT") == 0) {
                int x = Integer.parseInt(j);
                int y = Integer.parseInt(ID);
                if (type == 0 && x == y) {
                	out[k][0]=items.get(i).get(Y);
                    k++;
                } else if (type == 1 && x > y) {
                	out[k][0]=items.get(i).get(Y);
                    k++;
                } else if (type == -1 && x < y) {
                	out[k][0]=items.get(i).get(Y);
                    k++;
                }
            }
            else {
            
            if (type == 0 && j.compareTo(ID)==0) {
                out[k][0]=items.get(i).get(Y);
                k++;
            } else if (type == 1 && j.compareTo(ID)>0) {
            	System.out.println(ID+" and "+j);
                out[k][0]=items.get(i).get(Y);
                k++;
            } else if (type == -1 && j.compareTo(ID)<0) {
                out[k][0]=items.get(i).get(Y);
                k++;
            }
        }
    }
        Object [][]out1=new Object[k][1];
        for(int i=0;i<k;i++) {
            out1[i][0]=out[i][0];
        }
        return out1 ;
 
    }
    public void Update(int Row, String field, String NewValue)// Rows starts with 1
    {
        int X = names2.indexOf(field);
        if (types.get(X).compareTo("VARCHAR") == 0) {
            items.get(Row - 1).setElementAt(NewValue, X);
        } else {
            int New = Integer.parseInt(NewValue);
            items.get(Row - 1).setElementAt(New, X);
        }
    }
 
    public int UpdateWithCondition(int type, String Condition, String ID, String field, String NewValue) {// type=0
                                                                                                            // means
                    // equal                                                                                                            // type =1
                    // means
                                                                                                            // bigger
                                                                                                            // type =-1
                                                                                                            // means
                                                                                                            // smaller
    int X=names2.indexOf(ID);
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            String j = items.get(i).get(X).toString().toUpperCase();
            if (this.col_type2.get(ID).compareTo("INT") == 0) {
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
   
    public int Updatecolumns (Vector<String> col,Vector<Object> values) {
        int count=0;
       // System.out.println("el size"+items.size());
        for(int i=0;i<items.size();i++) {
            count++;
            for(int j=0;j<items.get(0).size();j++) {
                Update(i + 1,col.get(j), (String) values.get(j));
//              items.get(i).setElementAt(values.get(j),j);
            }
        }
        System.out.println("el count"+count);
        return count;
    }
   
//   public int Updatecolumnscondition(Vector<String> col,Vector<Object> values,String column,String value) {
//	     int X=names2.indexOf(column),row=-1;
//	     int count=0;
//	    
//	     for(int i=0;i<items.size();i++) {
//	    	 if(value.compareToIgnoreCase(items.get(i).get(X).toString())==0) {
//	    		
//	    		 row=i;
//	    		 count++;
//	    	 }
//	    	 
//	     }
//	     if(row==-1) {return 0;}
//	    int i=0;
//	     for(Object O : values) {
//	    	 items.get(row).setElementAt(O,names2.indexOf(col.get(i++)));
//	    	 
//	     }
//	     //System.out.println("count="+count);
//	     return count;
//   }
    public int Updatecolumnscondition(Vector<String> col,Vector<Object> values,String column,String value,int Operator) {
	     int X=names2.indexOf(column);
	     int count=0;
	    if(Operator==0) {
	     for(int i=0;i<items.size();i++) {
	    	 if(value.compareToIgnoreCase(items.get(i).get(X).toString())==0) {
	    		 count++;
	    int j=0;
	     for(Object O : values) {
	    	 items.get(i).setElementAt(O,names2.indexOf(col.get(j++)));
	    	 }
          }    	 
	     }
	    }
	    else if(Operator==1) {
	    	  for(int i=0;i<items.size();i++) {
	 	    	 if(value.compareToIgnoreCase(items.get(i).get(X).toString())<0) {
	 	    		 count++;
	 	    int j=0;
	 	     for(Object O : values) {
	 	    	 items.get(i).setElementAt(O,names2.indexOf(col.get(j++)));
	 	    	 }
	            }    	 
	 	     }
	    }
	    else {
	    	  for(int i=0;i<items.size();i++) {
	 	    	 if(value.compareToIgnoreCase(items.get(i).get(X).toString())>0) {
	 	    		 count++;
	 	    int j=0;
	 	     for(Object O : values) {
	 	    	 items.get(i).setElementAt(O,names2.indexOf(col.get(j++)));
	 	    	 }
	            }    	 
	 	     }
	    }
	    
	    
	     return count;
  }
   
   
   
 
    public ArrayList<Vector<Object>> getItems() {
        return items;
    }
 
    public void SaveTable(String mainDirectoryPath) throws Exception// assumed that you have created a database that will contian the table
    {
    	 FileOutputStream file ;
    	if(mainDirectoryPath==null) {

            file = new FileOutputStream(ParentDB + "\\" + Table_Name + ".xml");	
    	}else {

             file = new FileOutputStream(mainDirectoryPath+"\\"+ParentDB + "\\" + Table_Name + ".xml");	
    	}
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
        s.file=new File(path);
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