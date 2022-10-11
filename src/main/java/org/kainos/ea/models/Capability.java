package org.kainos.ea.models;

public class Capability {

    private int id;

    private String title;

    public Capability() {}

    public Capability( int id, String title ) {

        setId( id );
        setTitle( title );
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
}
