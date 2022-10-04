package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandLevel {

    private int id;

    private String bandName;

    @JsonCreator
    public BandLevel(@JsonProperty("band_name") String bandName) {

        this.setBandName( bandName );
    }

    @JsonCreator
    public BandLevel(@JsonProperty("id") int id,
                     @JsonProperty("band_name") String bandName) {

        this.setId( id );
        this.setBandName( bandName );
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getBandName() { return bandName; }

    public void setBandName(String bandName) { this.bandName = bandName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BandLevel bandLevel = (BandLevel) o;
        return id == bandLevel.id && bandName.equals(bandLevel.bandName);
    }
}
