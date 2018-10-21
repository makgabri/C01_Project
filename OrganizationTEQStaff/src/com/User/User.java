package com.User;

import java.util.Date;

public abstract class User {
    protected Date creationDate;
    protected Boolean uploaded;
    protected Roles role;
    protected String userId;
    protected String email;
    public Authenticate authenticate;

    public Roles getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isUploaded() {
        return uploaded;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public abstract Boolean logIn(int userId, String password);
}
