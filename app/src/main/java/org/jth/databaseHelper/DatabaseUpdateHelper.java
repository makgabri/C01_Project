package org.jth.databaseHelper;

public interface DatabaseUpdateHelper {

	public void updateUserRole(String userId, String role);

	public void updateUploadStatus(String userId, Boolean status);
	
}
