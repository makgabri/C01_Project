package org.jth.databaseHelper;

import java.util.Map;

public interface DatabaseInsertHelper {
	
	public int insertRole(String roleName);
	
	public Map<String, String> insertUser(String role, String email, String password);
	
}
