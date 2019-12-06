package eg.edu.alexu.csd.oop.jdbc.cs39;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.Vector;

import eg.edu.alexu.csd.oop.db.cs39.IDataBase;
import eg.edu.alexu.csd.oop.db.cs39.Partitions;


public class StatementImp implements java.sql.Statement {
	
	private Connection connection;
	private boolean closed = false;
	private IDataBase DbManager = IDataBase.getUniqueInstance();
	private ArrayList<String> commands = new ArrayList<>();
	private int queryTimeout = Integer.MAX_VALUE;
	
	public StatementImp(ConnectionImp connection) {
		this.connection = connection;
	}
	public StatementImp(String path , ConnectionImp connection ) {
		
		this.connection = connection;
		DbManager.SetPath(path);
		
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		if(closed)
		{
			throw new SQLException("The statement has been closed.");
		}
		return connection;
	}
	//Note:When a Statement object is closed, its current ResultSet object, if one exists, is also closed.
	@Override
	public void close() throws SQLException {
		if (closed) {
			throw new SQLException();
		}
		closed = true;	
	}
	//true if the first result is a ResultSet object; false if it is an update count or there are no results
	@Override
	public boolean execute(String sql) throws SQLException {
		if(!closed)
		{
			if(sql.trim().split("\\s+")[0].equalsIgnoreCase("select"))
			{
				//Return true if it is a resultset
				DbManager.executeQuery(sql);
				return true;
			}
			else if (   sql.trim().split("\\s+")[0].equalsIgnoreCase("insert")
					 || sql.trim().split("\\s+")[0].equalsIgnoreCase("delete")
					 || sql.trim().split("\\s+")[0].equalsIgnoreCase("update")    )
			{
				final int result = executeUpdate(sql);
				return result > 0 ;
//				return false;
			}
			else if (	sql.trim().split("\\s+")[0].equalsIgnoreCase("create")
					  ||sql.trim().split("\\s+")[0].equalsIgnoreCase("drop")	  )
			{
				 DbManager.QueryManagement(sql);
				 return true;
				//return false;
			}
		}
		throw new SQLException();
	}
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		if (!closed) {
			final Object[][] table = DbManager.executeQuery(sql);
			final String tableName = DbManager.getSelectCommand().getTableName();
			Vector<String> Names = null;
			Vector<String> Types = null;
			try {
				Names = DbManager.getSelectCommand().getNames();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Types = DbManager.getSelectCommand().getTypes();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ResultSetImp currentResultSet = new ResultSetImp(tableName, table,Names , this, Types);
			//ResultSetImp(TableName, result, Names, Statement, Types)
			return currentResultSet;
		}
		throw new SQLException();
	}
	@Override
	public int executeUpdate(String sql) throws SQLException {
		if (!closed) {
			return  DbManager.executeUpdateQuery(sql);
		}
		throw new SQLException();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		if (seconds < 0) {
			throw new SQLException("Invalid Value.");
		}
		queryTimeout = seconds;
	}
	@Override
	public int getQueryTimeout() throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		return queryTimeout;
	}
	@Override
	public void addBatch(String sql) throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		if (sql == null) {
			throw new SQLException();
		}
		if (!sql.trim().startsWith("insert") && !sql.trim().startsWith("update") && !sql.trim().startsWith("delete")
				&& !sql.trim().startsWith("create") && !sql.trim().startsWith("drop")) {
			throw new SQLException("INSERT, UPDATE or DELETE queries only");
		}
		commands.add(sql);
	}
	@Override
	public int[] executeBatch() throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		int[] results = new int[commands.size()];
		int i = 0;
		for (String command : commands) {
			if (!command.trim().startsWith("create") && !command.trim().startsWith("drop")) {
				results[i] = executeUpdate(command);
			} else {
				results[i] = 0;
			}
			i++;
		}
		return results;
	}
	@Override
	public void clearBatch() throws SQLException {
		if (closed) {
			throw new SQLException("The statement has been closed.");
		}
		commands = new ArrayList<>();		
	}
	//*******************************Unsupported Methods**************************************************
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxRows() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		throw new UnsupportedOperationException();
	}
	@Override
	public void cancel() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		throw new UnsupportedOperationException();
	}
	@Override
	public ResultSet getResultSet() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetType() throws SQLException {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean getMoreResults(int current) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		throw new UnsupportedOperationException();
	}

}
