package org.jth.user;

import java.util.Date;

public class TEQStaff extends User {

    private String firstName;
    private String lastName;

    public TEQStaff(String firstName, String lastName, Roles roles, String userId, String email, String creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = roles;
        this.userId = userId;
        this.email = email;
        this.uploaded = false;
        this.creationDate = creationDate;
    }

    /**
     * get first name
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get last name.
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    // TODO
    /**
     * change limitation of a specific account.
     * @param userId the user id of the account wants to change.
     * @return the limitation change success or not.
     */
    public Boolean changeLimitation(int userId) {
        return true;
    }

    // TODO
    /**
     * run query to search all the same data in the database and present in a table form.
     * @param query the data wants to search.
     * @return a table contain the result.
     */
    public Object runQuery(String query) {
        return null;
    }

    /**
     * create a organization account.
     * @param name name of the organization.
     * @param userId the id of the new account.
     * @param email email of the organization.
     * @param postalCode postal code of organization.
     * @param supportType support service of organization.
     * @return a Organization class contain all information.
     */
    public Organization createOrganizationAccount(String name, String userId, String email,
                                                  String postalCode,
                                                  SupportType supportType) {
        //return new Organization(name, userId, email, postalCode, supportType);
        return null;
    }

    // TODO parameters depend on how the templates store in the database
    // TODO
    /**
     * add fields into the templates.
     * @return true add success false otherwise.
     */
    public Boolean addTemplateField() {
        return true;
    }

    // TODO need to discuss the type of report
    /**
     * generate report
     */
    public void generateReport() {

    }
}

