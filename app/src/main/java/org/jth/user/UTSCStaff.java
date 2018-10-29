package org.jth.user;

public class UTSCStaff extends User {
	private String firstName;
	private String lastName;
	
	/**
	 * Initializes the UTSC staff member
	 * 
	 * @param firstName - the first name of the UTSC staff member
	 * @param lastName - the last name of the UTSC staff member
	 * @param userId - the user id  of the UTSC staff member
	 * @param email - the email of the UTSC staff member
	 */
	public UTSCStaff(String firstName, String lastName, String userId, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.email = email;
		this.role = Roles.UTSC;
	}
	
	/**
	 * Gets the first name of a UTSC staff member
	 * 
	 * @return the first name of the UTSC staff member
	 */
    public String getFirstName() {
        return firstName;
    }

	/**
	 * Gets the last name of a UTSC staff member
	 * 
	 * @return the last name of the UTSC staff member
	 */
    public String getLastName() {
        return lastName;
    }
    
    // TODO
    public Boolean changeLimitation(int userId) {
        return true;
    }
    
    // TODO 
    public Object runQuery(String query) {
        return null;
    }
    
    // TODO
    public Organization createOrganizationAccount(String name, String userId, String email,
                                                  String postalCode,
                                                  SupportType supportType) {
        return new Organization(name, userId, email, postalCode, supportType);
    }
    
    // TODO
    public void addTemplateField() {

    }
    
    // TODO
    public void generateReport() {

    }

}
