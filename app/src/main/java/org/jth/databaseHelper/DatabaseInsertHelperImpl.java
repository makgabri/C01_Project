package org.jth.databaseHelper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jth.security.PasswordHelpers;

public class DatabaseInsertHelperImpl implements DatabaseInsertHelper {
	
	
	/**
	 * Inserts a new role into the database.
	 * @param roleName - the role you'd like to insert.
	 * @return roleId - an integer representing the ID of the inserted role.
	 */
	public int insertRole(String roleName) {
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int roleId = -1;
		
		try {
			String sql = "INSERT INTO ROLETYPES(NAME) VALUES (?);";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, roleName.toUpperCase());
			
			int id = stmt.executeUpdate();
			
			if (id > 0) {
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					roleId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return roleId;
	}
	
	
	/**
	 * Inserts a user into the database.
	 * @param role - the role of the user.
	 * @param email - the email of the user.
	 * @param password - the user's password.
	 * @return userId - a string representing a unique userId if the
	 * user was inserted. null otherwise.
	 */
	public Map<String, String> insertUser(String role, String email, String password) {
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		PreparedStatement stmt = null;
		String userId = null;
		String hashedPassword = null;
		
		int roleId = new DatabaseSelectHelperImpl().getRoleId(role);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		String simpleDate = simpleFormat.format(date);
		userId = PasswordHelpers.passwordHash(role + email + simpleDate);
		hashedPassword = PasswordHelpers.passwordHash(password);
	
		String sql = "INSERT INTO USERS VALUES (?,1,0,?,?,?);";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, formattedDate);
			stmt.setInt(3, roleId);
			stmt.setString(4, email);
			
			int id = stmt.executeUpdate();
			
			if (id > 0) {
				
				sql = "INSERT INTO USERPW VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, userId);
				stmt.setString(2, hashedPassword);
				
				id = stmt.executeUpdate();
				if (id > 0) {
					
					Map<String, String> result = new HashMap<String, String>();
					result.put("userId", userId);
					result.put("creationDate", formattedDate);
					
					return result;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
