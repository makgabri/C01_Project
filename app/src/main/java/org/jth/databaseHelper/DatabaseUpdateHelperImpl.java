package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUpdateHelperImpl implements DatabaseUpdateHelper {

	public void updateUserRole(String userId, String role) {
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "UPDATE USERS SET ROLEID=? WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, new DatabaseSelectHelperImpl().getRoleId(role));
			stmt.setString(2, userId);
			
			stmt.executeUpdate();
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
	}

	public void updateUploadStatus(String userId, Boolean status) {
		Connection conn = DatabaseDriver.connectOrCreateDatabase();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int intStatus = (status) ? 1 : 0;

		try {
			String sql = "UPDATE USERS SET UPLOADED=? WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, intStatus);
			stmt.setString(2, userId);

			stmt.executeUpdate();
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
	}

}
