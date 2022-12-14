package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class JobRolesResponse {

    @ApiModelProperty( value = "the job role's id value")
    private int id;

    @ApiModelProperty( value = "the job role name" )
    private String title;

    @ApiModelProperty( value = "the capability role belongs in" )
    private String capability;

    @ApiModelProperty( value = "the job role's band level")
    private String bandLevel;
    private int capabilityID;
    private int bandLevelID;

    @JsonCreator
    public JobRolesResponse(@JsonProperty("id") int id,
                            @JsonProperty("title") String title,
                            @JsonProperty("capability") String capability,
                            @JsonProperty("band_level") String bandLevel,
                            @JsonProperty("capability_id") int capabilityID,
                            @JsonProperty("band_level_id") int bandLevelID) {
        this.setId(id);
        this.setTitle(title);
        this.setCapability(capability);
        this.setBandLevel(bandLevel);
        this.setCapabalityID(capabilityID);
        this.setBandLevelID(bandLevelID);
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getCapability() { return capability; }

    public void setCapability(String capability) { this.capability = capability; }

    public String getBandLevel() { return bandLevel; }

    public void setBandLevel(String bandLevelTitle) { this.bandLevel = bandLevelTitle; }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabalityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }

    public int getBandLevelID() {
        return bandLevelID;
    }

    public void setBandLevelID(int bandLevelID) {
        this.bandLevelID = bandLevelID;
    }
}
