package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capability {

    private int id;

    private String title;

    @JsonCreator
    public Capability( @JsonProperty("id") int id,
                       @JsonProperty("title") String title ) {

        setId( id );
        setTitle( title );
    }

    public int getId() { return id; }

    public void setId( int id ) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle( String title ) { this.title = title; }
}
