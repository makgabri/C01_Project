package org.jth.user;

import com.User.User;

import java.util.ArrayList;
import java.util.Date;

public class Organization extends User {
    private String name;
    private String postalCode;
    private SupportType supportType;

    public Organization(String name,
                        String userId,
                        String email,
                        String postalCode,
                        SupportType supportType) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.postalCode = postalCode;
        this.supportType = supportType;
        this.uploaded = false;
        this.creationDate = new Date();
    }

    @Override
    public Boolean logIn(int userId, String password) {
        return null;
    }

    public Boolean uploadTemplates(ArrayList<String> fileName) {
        return true;
    }

    public Boolean checkingTemplates() {
        return true;
    }
}
