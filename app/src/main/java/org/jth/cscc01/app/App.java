package org.jth.cscc01.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.jth.databaseHelper.DatabaseDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DatabaseDriver driver = new DatabaseDriver();
        Connection connection = driver.connectOrCreateDatabase();
        // DatabaseDriver.initializeDatabase(connection);
        try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM sqlite_master WHERE type='table'";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("" + rs.getString("name"));
			}
			sql = "SELECT * FROM USERS";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("" + rs.getString("ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
