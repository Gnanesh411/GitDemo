package DataBase.JDBCdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdBcDemo {
	public static void main(String[] args) throws Exception, IllegalAccessException, ClassNotFoundException {

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "mydb";
		String dbUrl = url + dbName;
		String uName = "root";
		String passWord = "root";
		Connection conn = null;

		try {

			// Load the MySQL JDBC driver and establish connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting to Database");

			// Connect to mysql data system
			conn = DriverManager.getConnection(dbUrl, uName, passWord);

			// executing the SQL queries on the connected mybd database
			if (!conn.isClosed()) {
				System.out.println("successfully connected to mydb database");
				// excuting the query to retrive the persons table records
//				Statement statement = conn.createStatement();
//				ResultSet resultset = statement.executeQuery("Select * from persons");
//				while (resultset.next()) {
//					System.out.println(resultset.getInt("PersonID") + " " + resultset.getString("LastName") + " "
//							+ resultset.getString("FirstName") + " " + resultset.getString("Address") + " "
//							+ resultset.getString("City"));
//				}
				// excute prepared statements to retrive the filtered records from the person table records
				PreparedStatement prepstatement = conn
						.prepareStatement("Select * from persons  where PersonID=? and LastName=?");
				prepstatement.setInt(1, 3);
				prepstatement.setString(2, "lilly");
				ResultSet resultset2 = prepstatement.executeQuery();
				while (resultset2.next()) {
					System.out.println(resultset2.getInt("PersonID") + " " + resultset2.getString("LastName") + " "
							+ resultset2.getString("FirstName") + " " + resultset2.getString("Address") + " "
							+ resultset2.getString("City"));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing the database connection
			conn.close();
			try {
				if (conn.isClosed()) {
					System.out.println("successfully closed the connection of mydb database");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
