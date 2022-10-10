package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    String email;

    String password;

    @JsonCreator
    public UserResponse( @JsonProperty("email") String email,
                         @JsonProperty("password") String password ) {

        setEmail( email );
        setPassword( password );
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
