package eg.edu.alexu.csd.oop.db.cs39;

import java.sql.SQLException;

public class DropTable implements Command {
	
	String TableName;
	DB parentDB;
	Table tobeDropped;

	public DropTable( String TableName, DB parentDB ) {
		
		//System.out.println(parentDB.Tables.size());
		this.TableName = TableName;
		this.parentDB = parentDB;
		for (int i = 0 ; i < parentDB.Tables.size() ; i++)
		{

			//if(TableName == parentDB.Tables.get(i).getTable_Name())
			if( parentDB.Tables.get(i).getTable_Name().compareToIgnoreCase(TableName)==0 )
			{

				tobeDropped = parentDB.Tables.get(i);
			}
		}
	}
	
	@Override
	public DB getDB() {

		return null;
	}

	@Override
	public String getpathofDB() {

		return null;
	}

	@Override
	public String getnameofDB() {

		return tobeDropped.getParentDB();
	}

	@Override
	public void execute() throws SQLException {

		try {
			tobeDropped.DropTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}