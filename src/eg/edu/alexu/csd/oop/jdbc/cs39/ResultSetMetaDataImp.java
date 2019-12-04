package eg.edu.alexu.csd.oop.jdbc.cs39;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

public class ResultSetMetaDataImp implements java.sql.ResultSetMetaData {

	Object[][] items;
	Vector<String>Names;
	Vector<String>Types;
	String TableName;
	public ResultSetMetaDataImp(String TableName,Object Res[][],Vector<String>Names,Vector<String>Types)
	{
		this.TableName=TableName;
		this.Types=Types;
		this.items=Res;
		this.Names=Names;
		
	}
	public  int getColumnCount()
	{
		return Names.size();
	}
	public  String getColumnLabel(int column)
	{
		if(column<1||column>Names.size())
		{
			return null;
		}
		return Names.get(column);
	}
	public  String getColumnName(int column)
	{
		if(column<1||column>Names.size())
		{
			return null;
		}
		return Names.get(column);

	}
	public int getColumnType(int column)
	{
		if(Types.get(column).compareTo("VARCHAR")==0)
		{
			return java.sql.Types.VARCHAR;
		}
		return java.sql.Types.INTEGER;
	}
	public String getTableName(int column)
	{
		return TableName;
	}
	///////////////////
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAutoIncrement(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCaseSensitive(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isSearchable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCurrency(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int isNullable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isSigned(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getColumnDisplaySize(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getSchemaName(int column) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getPrecision(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getScale(int column) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getCatalogName(int column) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getColumnTypeName(int column) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isReadOnly(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isWritable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isDefinitelyWritable(int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getColumnClassName(int column) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
