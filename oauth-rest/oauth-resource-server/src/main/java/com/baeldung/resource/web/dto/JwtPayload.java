package com.baeldung.resource.web.dto;

public class JwtPayload {

    private String preferredUsername;

    public JwtPayload() {
    }

    public JwtPayload(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }
}
