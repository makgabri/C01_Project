package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jth.user.Organization;
import org.jth.user.Roles;
import org.jth.user.TEQStaff;
import org.jth.user.UTSCStaff;
import org.jth.user.User;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

	public User getUser(String userId) {
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		User user = null;
		
		sql = "SELECT * FROM USERS WHERE ID='" + userId + "';";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String roleName = getRoleName(rs.getInt("ROLEID"));
				if (roleName.equals(Roles.ORGANIZATION.name())) {
					user = new Organization(roleName, rs.getString("ID"), rs.getString("EMAIL"), roleName, null);
				} else if (roleName.equals(Roles.TEQ.name())) {
					user = new TEQStaff(roleName, roleName, null, rs.getString("ID"), rs.getString("EMAIL"));
				} else if (roleName.equals(Roles.UTSC.name())) {
					user = new UTSCStaff(roleName, roleName, rs.getString("ID"), rs.getString("EMAIL"));
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getUsers() {
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		List<User> users = new ArrayList<User>();
		User user = null;
		
		sql = "SELECT * FROM USERS;";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String roleName = getRoleName(rs.getInt("ROLEID"));
				if (roleName.equals(Roles.ORGANIZATION.name())) {
					user = new Organization(roleName, rs.getString("ID"), rs.getString("EMAIL"), roleName, null);
				} else if (roleName.equals(Roles.TEQ.name())) {
					user = new TEQStaff(roleName, roleName, null, rs.getString("ID"), roleName);
				} else if (roleName.equals(Roles.UTSC.name())) {
					user = new UTSCStaff(roleName, roleName, rs.getString("ID"), roleName);
				}
				users.add(user);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public int getRoleId(String role) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		int roleId = -1;
		
		sql = "SELECT ID FROM ROLETYPES WHERE NAME='" + role.toUpperCase() + "';";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				roleId = rs.getInt("ID");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleId;
	}

	public String getRoleName(int roleId) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		String roleName = null;
		
		sql = "SELECT NAME FROM ROLETYPES WHERE ID=" + roleId + ";";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				roleName = rs.getString("NAME");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleName;
	}

	public String getHashedPassword(String userId) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		String password = null;
		
		sql = "SELECT PASSWORD FROM USERPW WHERE USERID='" + userId + "';";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				password = rs.getString("PASSWORD");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public String getUserId(String email) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		String password = null;
		
		sql = "SELECT ID FROM USERS WHERE EMAIL='" + email + "';";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				password = rs.getString("ID");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public String getCreationDate(String userId) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		Statement stmt;
		String sql = "";
		ResultSet rs;
		String creationDate = null;
		
		sql = "SELECT CREATIONDATE FROM USERS WHERE ID='" + userId + "';";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				creationDate = rs.getString("CREATIONDATE");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creationDate;
	}

}
