package com.User.Organization;

import com.User.User;

import java.util.ArrayList;

public class Organization extends User {
    private String name;
    private String postalCode;
    private Boolean uploaded;
    private SupportType supportType;

    public Organization(String name,
                        int userId,
                        String password,
                        String email,
                        String postalCode,
                        SupportType supportType) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.postalCode = postalCode;
        this.supportType = supportType;
    }

    @Override
    public Boolean logIn(int userId, String password) {
        return null;
    }

    public Boolean uploadTemplates(ArrayList<String> fileName) {
        return true;
    }

    public Boolean isUploaded(){
        return uploaded;
    }

    public Boolean checkingTemplates() {
        return true;
    }
}
