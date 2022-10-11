package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {

    private String title;

    private String requirements;

    private String responsibilities;

    private String link;

    private int bandLevelID;

    private int capabilityID;

    @JsonCreator
    public JobRoleRequest( @JsonProperty("title") String title,
                           @JsonProperty("requirements") String requirements,
                           @JsonProperty("responsibilities") String responsibilities,
                           @JsonProperty("link") String link,
                           @JsonProperty("band") int bandLevelID,
                           @JsonProperty("capability") int capabilityID ) {
        setTitle( title );
        setRequirements( requirements );
        setResponsibilities( responsibilities );
        setLink( link );
        setBandLevelID(bandLevelID);
        setCapabilityID( capabilityID );
    }

    public String getTitle() { return title; }

    public void setTitle( String title ) { this.title = title; }

    public String getRequirements() { return requirements; }

    public void setRequirements(String requirements) { this.requirements = requirements; }

    public String getResponsibilities() { return responsibilities; }

    public void setResponsibilities( String responsibilities ) { this.responsibilities = responsibilities; }

    public String getLink() { return link; }

    public void setLink( String link ) { this.link = link; }

    public int getBandLevelID() { return bandLevelID; }

    public void setBandLevelID(int bandLevelID) { this.bandLevelID = bandLevelID; }

    public int getCapabilityID() { return capabilityID; }

    public void setCapabilityID( int capabilityID ) { this.capabilityID = capabilityID; }
}
