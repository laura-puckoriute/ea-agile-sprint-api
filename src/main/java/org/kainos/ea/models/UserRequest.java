package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {
    private String email;
    private String password;
    private int userRoleID;

    @JsonCreator
    public UserRequest(@JsonProperty("email") String email,
                       @JsonProperty("password") String password,
                       @JsonProperty("user_roleID") int userRoleID) {
        this.email = email;
        this.password = password;
        this.userRoleID = userRoleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }
}
