package com.hamzaburakhan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String dbUrl;
	private String unicode = "?useUnicode=true&characterEncoding=utf-8";
	private String user;
	private String pass;
	private boolean showSqlCode = false;

	public DatabaseConnection(String host, String dbName, String user, String pass, boolean utf8_Encoding) {
		this.dbUrl = "jdbc:mysql://" + host + "/" + dbName;
		if (utf8_Encoding) {
			this.dbUrl += unicode;
		}
		this.user = user;
		this.pass = pass;
	}

	public Connection getConnection() {
		Connection dbConnection = null;

		try {

			Class.forName(JDBC_DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}

		try {

			dbConnection = (Connection) DriverManager.getConnection(dbUrl, user, pass);
			return dbConnection;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}

		return dbConnection;
	}

	public int insertRow(Object record) throws Exception {
		int result = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = SQLGenerator.insertSQL(record);

		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			PsSetter.setIntserValues(preparedStatement, record);
			result = preparedStatement.executeUpdate();

			if (showSqlCode) {
				System.out.println(insertTableSQL);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return result;
	}

	public int update(String updateSQL, QueryParams parameters) throws SQLException {
		int result = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		if (showSqlCode) {
			System.out.println(updateSQL);
		}
		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			PsSetter.setValues(preparedStatement, parameters);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

		return result;
	}

	public int delete(String deleteSQL, QueryParams parameters) throws SQLException {

		int result = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		if (showSqlCode) {
			System.out.println(deleteSQL);
		}
		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			PsSetter.setValues(preparedStatement, parameters);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return result;
	}

	public <T> List<T> selectRecods(String selectSQL, QueryParams parameters, Class<T> dataObjectType)
			throws SQLException {
		List<T> list = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		if (showSqlCode) {
			System.out.println(selectSQL);
		}
		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			if (parameters != null)
				PsSetter.setValues(preparedStatement, parameters);
			rs = preparedStatement.executeQuery();
			list = new ArrayList<T>();
			while (rs.next()) {
				@SuppressWarnings("unchecked")
				T obj = (T) RsGetter.getObject(rs, dataObjectType);
				list.add(obj);
			}

		} catch (SQLException e) {

			list = null;
			e.printStackTrace();
			System.out.println(e.getMessage());

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

		return list;
	}

	public void showSqlCode(boolean showIt) {
		showSqlCode = showIt;
	}

}
