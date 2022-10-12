package org.kainos.ea.models;

public class JobFamily {

    private int id;

    private String title;

    public JobFamily( int id, String title ) {

        setId( id );
        setTitle( title );
    }

    public int getId() { return id; }

    public void setId( int id ) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle( String title) { this.title = title; }
}
