package com.User.TEQStaff;

import com.User.Organization.Organization;
import com.User.Organization.SupportType;
import com.User.Roles;
import com.User.User;

import java.util.Date;

public class TEQStaff extends User {

    private String firstName;
    private String lastName;

    public TEQStaff(String firstName, String lastName, Roles roles, String userId, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = roles;
        this.userId = userId;
        this.email = email;
        this.uploaded = false;
        this.creationDate = new Date();
    }

    @Override
    public Boolean logIn(int userId, String password) {
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean changeLimitation(int userId) {
        return true;
    }

    public Object runQuery(String query) {
        return null;
    }

    public Organization createOrganizationAccount(String name, String userId, String email,
                                                  String postalCode,
                                                  SupportType supportType) {
        return new Organization(name, userId, email, postalCode, supportType);
    }

    public void addTemplateField() {

    }

    public void generateReport() {

    }
}

