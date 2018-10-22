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

    /**
     * get user role.
     * @return user roles.
     */
    public Roles getRole() {
        return role;
    }

    /**
     * get user id.
     * @return a string represent user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * get email
     * @return String represent email
     */
    public String getEmail() {
        return email;
    }

    /**
     * check the user is uploaded or not.
     * @return true if it is already uploaded.
     */
    public Boolean isUploaded() {
        return uploaded;
    }

    /**
     * add creation data.
     * @return Date class of the account
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * user login
     * @param userId the id of the user
     * @param password the password of the user
     * @return true login success, false otherwise.
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public Boolean logIn(String userId, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        authenticate = new AuthenticateImpl(userId);
        return (authenticate.authenticate(password));
    }
    
}
