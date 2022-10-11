package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private int id;

    private String email;

    private String password;

    private UserRole userRole;

    @JsonCreator
    public User( @JsonProperty("email") String email,
                 @JsonProperty("password") String password ) {

        setEmail( email );
        setPassword( password );
    }

    @JsonCreator
    public User( @JsonProperty("id") int id,
                 @JsonProperty("email") String email,
                 @JsonProperty("password") String password) {

        setId( id );
        setEmail( email );
        setPassword( password );
    }

    public int getId() { return id; }

    public void setId( int id ) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail( String email ) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword( String password ) { this.password = password; }

    public UserRole getUserRole() { return userRole; }

    public void setUserRole( UserRole userRole ) { this.userRole = userRole; }
}
