package org.jth.user;

import org.jth.security.Authenticate;
import org.jth.security.AuthenticateImpl;
import org.jth.user.Roles;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    
    public Boolean logIn(String userId, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Authenticate userAuthentication = new AuthenticateImpl(userId);
        if (userAuthentication.authenticate(password)) {
        	return true;
        }
        return false;
    }
    
}
