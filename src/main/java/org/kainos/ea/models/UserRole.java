package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRole {

    private int id;

    private String role;

    @JsonCreator
    public UserRole( @JsonProperty("id") int id,
                     @JsonProperty("title") String role ) {
        setId( id );
        setRole( role );
    }

    public int getId() { return id; }

    public void setId( int id ) { this.id = id; }

    public String getRole() { return role; }

    public void setRole( String role ) { this.role = role; }
}