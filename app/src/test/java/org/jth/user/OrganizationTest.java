package org.jth.user;

import static org.jth.user.SupportType.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.security.AuthenticateImpl;
import org.jth.security.PasswordHelpers;
import org.junit.jupiter.api.Test;

public class OrganizationTest {
    private String organizationId = new DatabaseSelectHelperImpl().getUserId("FGH@gmail.com");
    private User organization = new Organization("FGH", organizationId,
                                                 "FGH@gmail.com", "M1P 3B2", LEGAL_SERVICES);
    DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
    //Map<String, String> organizationMap = dbi.insertUser(Roles.ORGANIZATION.name(), "FGH@gmail.com", "123");
    AuthenticateImpl user = new AuthenticateImpl(organizationId);

    @Test
    void logInSucessful() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // TODO store hashed password into the database
        String databasePassword = PasswordHelpers.passwordHash("123");
        assertTrue(organization.logIn(organizationId, "123"));
    }

    @Test
    void logInFailure() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // TODO store hashed password into database
        String databasePassword = PasswordHelpers.passwordHash("123");
        assertFalse(organization.logIn(organizationId, "123323"));
    }

    @Test
    void getOrganizationName() {
        assertEquals("FGH", ((Organization) organization).getOrganizationName());
    }

    @Test
    void getOrganizationEmail() {
        assertEquals("FGH@gmail.com", organization.getEmail());
    }

    @Test
    void getOrganizationId() {
        assertEquals(organizationId, organization.getUserId());
    }

    @Test
    void getOrganizationRole() {
        assertEquals(Roles.ORGANIZATION, organization.getRole());
    }
}
