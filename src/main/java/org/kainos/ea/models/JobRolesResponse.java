package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRolesResponse {

    private int id;

    private String title;

    private String description;

    private String responsibilities;

    private String link;

    private String capability;

    private String bandLevel;

    private String jobFamily;

    public JobRolesResponse() {}

    @JsonCreator
    public JobRolesResponse(@JsonProperty("id") int id,
                            @JsonProperty("title") String title,
                            @JsonProperty("capability") String capability,
                            @JsonProperty("band_level") String bandLevel) {
        this.setId(id);
        this.setTitle(title);
        this.setCapability(capability);
        this.setBandLevel(bandLevel);
    }

    public JobRolesResponse( int id,
                             String title,
                             String description,
                             String responsibilities,
                             String link,
                             String capability,
                             String bandLevel,
                             String jobFamily) {
        setId( id );
        setTitle( title );
        setDescription( description );
        setResponsibilities( responsibilities );
        setLink( link );
        setCapability( capability );
        setBandLevel( bandLevel );
        setJobFamily( jobFamily );
    }

    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getResponsibilities() { return responsibilities; }

    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public String getCapability() { return capability; }

    public void setCapability(String capability) { this.capability = capability; }

    public String getBandLevel() { return bandLevel; }

    public void setBandLevel(String bandLevelTitle) { this.bandLevel = bandLevelTitle; }

    public String getJobFamily() { return jobFamily; }

    public void setJobFamily(String jobFamily) { this.jobFamily = jobFamily; }
}
