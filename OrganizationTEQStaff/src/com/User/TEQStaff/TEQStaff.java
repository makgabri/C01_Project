package com.User.TEQStaff;

import com.User.Organization.Organization;
import com.User.Organization.SupportType;
import com.User.Roles;
import com.User.User;

public class TEQStaff extends User {

    public TEQStaff(String firstName, String lastName, Roles roles, int userId, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = roles;
        this.userId = userId;
        this.email = email;
        this.password = password;

    }

    @Override
    public Boolean logIn(int userId, String password) {
        return null;
    }

    public Boolean changeLimitation(int userId) {
        return true;
    }

    public Object runQuery(String query) {
        return null;
    }

    public Organization createOrganizationAccount(String name, int userId, String password, String email,
                                                  String postalCode,
                                                  SupportType supportType) {
        return new Organization(name, userId, password, email, postalCode, supportType);
    }

    public void addTemplateField() {

    }

    public void generateReport() {

    }
}

