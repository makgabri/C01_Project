package org.jth.databaseHelper;

import java.util.List;

import org.jth.user.User;

public interface DatabaseSelectHelper {
	
	public User getUser(String userId);
	
	public List<User> getUsers();
	
	public int getRoleId(String role);
	
	public String getRoleName(int roleId);
	
	public String getHashedPassword(String userId);
	
	public String getUserId(String email);
	
	public String getCreationDate(String userId);

	public Boolean checkUploaded(String userId);

}
