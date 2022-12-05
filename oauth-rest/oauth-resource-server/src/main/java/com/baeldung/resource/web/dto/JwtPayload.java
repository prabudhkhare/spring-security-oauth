package com.baeldung.resource.web.dto;

import com.google.gson.annotations.SerializedName;

public class JwtPayload {

    @SerializedName("preferred_username")
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
