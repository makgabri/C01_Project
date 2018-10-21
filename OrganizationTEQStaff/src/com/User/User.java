package com.User;

public abstract class User implements UserLogin {
    protected String firstName;
    protected String lastName;
    protected Roles role;
    protected int userId;
    protected String email;
    protected String password;
    // protected Authenticate authenticate;

    public Roles getRole() {
        return role;
    }

    public int getUserId() {
        return userId;
    }

    public abstract Boolean logIn(int userId, String password);
}
