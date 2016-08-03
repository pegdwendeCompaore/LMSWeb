package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDemo {
	
	private static String driver = "";
	private static String url = "jdbc:mysql://localhost/library";
	private static String user = "root";
	private static String pass = "root";

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			String query = "select * from tbl_author where authorName = ? AND authorId= ?";
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a new Author Name: ");
			String searchString = scan.nextLine();
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			//Statement stmt = conn.createStatement();
			query = "insert into tbl_author (authorName) values (?)";
			query = "update tbl_author set authorName = ? where authorId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchString);
			pstmt.setInt(2, 222);
//			
//			ResultSet rs = pstmt.executeQuery();
//			while(rs.next()){
//				System.out.println("Author ID: " +rs.getInt("authorId"));
//				System.out.println("Author Name: " +rs.getString("authorName"));
//				System.out.println("--------------------");
//			}
			pstmt.executeUpdate();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally{
			conn.close();
		}
	}

}
