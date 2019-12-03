package eg.edu.alexu.csd.oop.jdbc.cs39;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class DriverImp implements java.sql.Driver {

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		File dir = (File) info.get("path");
		String path = dir.getAbsolutePath();
		return new ConnectionImp(path); // pool
		}
	@Override
	public boolean acceptsURL(String url) throws SQLException {

		return true;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {

		return null;
	}
	//*************************************Unsupported Methods*********************************************************
	@Override
	public int getMajorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean jdbcCompliant() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

}
