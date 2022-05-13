package com.csc510.resa.security.model;

/**
 * Holds information about the rest obtained from the JWT token Used for
 * authentication and authorization purposes
 *
 * @author Steven Green
 */
public class JwtUser {

    private String id;

    /**
     * Comma separated list of roles
     */
    private String role;

    /**
     * Gets the rest's id
     *
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the rest's id
     *
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the rest's roles as a comma separated list
     *
     * @return The rest's roles
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the rest's roles (comma separated list)
     *
     * @param role The rest's roles
     */
    public void setRole(String role) {
        this.role = role;
    }
}
