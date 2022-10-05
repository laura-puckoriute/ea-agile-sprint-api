package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandLevel {

    private int id;

    private String title;

    public BandLevel() {}

    @JsonCreator
    public BandLevel( @JsonProperty("band_title") String title ) {

        this.setBandName( title );
    }

    @JsonCreator
    public BandLevel( @JsonProperty("band_id") int id,
                      @JsonProperty("band_title") String title ) {

        this.setId( id );
        this.setBandName( title );
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getBandName() { return title; }

    public void setBandName(String bandName) { this.title = bandName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandLevel bandLevel = (BandLevel) o;
        return id == bandLevel.id && title.equals(bandLevel.title);
    }
}
