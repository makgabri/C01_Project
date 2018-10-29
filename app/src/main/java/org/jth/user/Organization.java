package org.jth.user;

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
        this.role = Roles.ORGANIZATION;
    }

    // TODO
    /**
     * upload templates to database.
     * @param fileName list of templates that need upload.
     * @return true if upload success, false otherwise.
     */
    public Boolean uploadTemplates(ArrayList<String> fileName) {
        return true;
    }

    // TODO
    /**
     * system automatically check of conflict.
     * @return true if there is no conflict, false otherwise.
     */
    public Boolean checkingTemplates() {
        return true;
    }

    public String getOrganizationName() {
        return name;
    }
}
