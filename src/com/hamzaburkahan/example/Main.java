package com.hamzaburkahan.example;

import java.sql.SQLException;
import java.util.List;

import com.hamzaburakhan.bookstore.Book;
import com.hamzaburakhan.jdbc.DatabaseConnection;
import com.hamzaburakhan.jdbc.QueryParams;

public class Main {
	public static final String HOST = "localhost";
	public static final String DB = "bookstore";
	public static final String USERNAME = "user";
	public static final String PASSWORD = "pass";
	public static final boolean ENCODE_UTF8 = true;
	
	public static void main(String[] args) throws Exception {
		
		
		DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, ENCODE_UTF8);
		String selectSQL = "Select * FROM books,persons Where books.verilen =persons.id ";
		//Parametre yoksa null girilir.
		List<Book> users = dbconnection.selectRecods(selectSQL,null,Book.class);
	
	
	}
	

	
	
	public static void updateExample() throws SQLException {
		DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, ENCODE_UTF8);
		String updateSQL = "UPDATE tabloadi SET name=?, lastname=? WHERE id=?";
		//Sorgundaki soru işaretleri için parametreleri giriyorsun.Sırasıyla girmen önemli.
		QueryParams params = new QueryParams();
		params.addParams("Update");
		params.addParams("Example");
		params.addParams(6);
	    dbconnection.update(updateSQL, params);
	}
	
	public static void insertExample() throws Exception {
		DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, ENCODE_UTF8);
		dbconnection.showSqlCode(true);	
		User user = new User(20,"New","New");
		dbconnection.insertRow(user);
	}
	
	public static void selectExample() throws SQLException {
		DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, ENCODE_UTF8);
		String selectSQL = "Select * FROM tabloadi Where id < ? ";
		//Sorgundaki soru işaretleri için parametreleri giriyorsun.Sırasıyla girmen önemli.
		QueryParams paramaters = new QueryParams();
		paramaters.addParams(8);
		List<User> users = dbconnection.selectRecods(selectSQL, paramaters,User.class);
		for (User user : users) {
			System.out.print("id: "+user.getId());
			System.out.print("\tname: "+user.getAd());
			System.out.println(" \tlastName: "+user.getSoyad());

		}
	}

	public static void deleteExample() throws SQLException {
		DatabaseConnection dbconnection = new DatabaseConnection(HOST, DB, USERNAME, PASSWORD, ENCODE_UTF8);
		dbconnection.showSqlCode(true);
		String deleteSQL = "DELETE FROM tabloadi WHERE id=? AND name=?";
		//Sorgundaki soru işaretleri için parametreleri giriyorsun.Sırasıyla girmen önemli.
		QueryParams paramaters = new QueryParams();
		paramaters.addParams(20);
		paramaters.addParams("New");
		int result = dbconnection.delete(deleteSQL, paramaters);
		System.out.println(result); // Kaç satır etkilendi.
	}

}
