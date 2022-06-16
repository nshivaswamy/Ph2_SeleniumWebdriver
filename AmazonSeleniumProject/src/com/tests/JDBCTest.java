package com.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	static Connection myConn = null;
	static Statement myStmt = null;
	static ResultSet myResSet = null;

	public static void ConnectDatabase() {
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "password";

		try {
			myConn = DriverManager.getConnection(url, username, password);
			myStmt = myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected to MySQL Database");
	}

	public static void CreateDatabase() {
		try {
			myStmt.executeUpdate("CREATE DATABASE productdb");
			System.out.println("Created Database");
		} catch (SQLException e) {
			System.out.println("Database exists");
		}
		try {
			myStmt.executeUpdate("USE productdb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void CreateTable() {
		try {
			myStmt.executeUpdate("CREATE TABLE itemdata (category varchar(40), company varchar(40), description varchar(500), price varchar (10))");
			System.out.println("Created Table");
		} catch (SQLException e) {
			System.out.println("Table exists");
		}
	}

	public static void deleteAllRecords() throws SQLException {
		myStmt.executeUpdate("DELETE from itemdata where category!='AAA'");
		System.out.println("Initially deleting all records in JDBC Database");
		printNoOfRecords();
	}

	public static void printNoOfRecords() throws SQLException {
		// Query to get the number of rows in a table
		myResSet = myStmt.executeQuery("SELECT count(*) from itemdata");
		// Retrieving the result
		myResSet.next();
		int count = myResSet.getInt(1);
		System.out.println("Number of records in the Database : " + count);
	}

	public static void UpdateItemIntoDB(String category, String itemCompany, String itemDescription, String itemPrice)
			throws SQLException {
		itemCompany = itemCompany.replace("'", "\\'");
		itemDescription = itemDescription.replace("'", "\\'");
		itemPrice = itemPrice.replace("'", "\\'");
		//System.out.println(itemCompany + " | " + itemDescription + " | " + itemPrice + " | ");
		String query = "INSERT into itemdata (category, company, description, price) VALUES ('" + category + "','"
				+ itemCompany + "','" + itemDescription + "','" + itemPrice + "')";
		myStmt.executeUpdate(query);
	}

	public static void printAllDBRecords() throws SQLException {
		System.out.println("Printing All Database Records");
		myResSet = myStmt.executeQuery("SELECT * from itemdata");
		while (myResSet.next()) {
			System.out.println("| " + myResSet.getString("category") + " | " + myResSet.getString("company") + " | "
					+ myResSet.getString("description") + " | " + myResSet.getString("price") + " | ");
		}

		printNoOfRecords();
	}

	public static void closeDBConnection() throws SQLException {
		myConn.close();

	}

}
