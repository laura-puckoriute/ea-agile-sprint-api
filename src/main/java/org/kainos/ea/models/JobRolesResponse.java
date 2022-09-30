package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRolesResponse {
    private int id;
    private String title;

    private String capability;


    private String bandLevelTitle;

    @JsonCreator
    public JobRolesResponse(@JsonProperty("id") int id,
                            @JsonProperty("title") String title,
                            @JsonProperty("capability") String capability,
                            @JsonProperty("band_level") String bandLevel) {
        this.setId(id);
        this.setTitle(title);
        this.setCapability(capability);
        this.setBandLevelTitle(bandLevel);
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getCapability() { return capability; }

    public void setCapability(String capability) { this.capability = capability; }

    public String getBandLevelTitle() { return bandLevelTitle; }

    public void setBandLevelTitle(String bandLevelTitle) { this.bandLevelTitle = bandLevelTitle; }
}
