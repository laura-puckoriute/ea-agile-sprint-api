package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {

    private String title;
    private String description;
    private String responsibilities;
    private String link;
    private int capabilityID;
    private int band_levelID;
    private int job_familyID;

    @JsonCreator
    public JobRoleRequest(@JsonProperty("title") String title,
                          @JsonProperty("description") String description,
                          @JsonProperty("responsibilities") String responsibilities,
                          @JsonProperty("link") String link,
                          @JsonProperty("capabilityID") int capabilityID,
                          @JsonProperty("band_levelID") int band_levelID,
                          @JsonProperty("job_familyID") int job_familyID) {
        this.title = title;
        this.description = description;
        this.responsibilities = responsibilities;
        this.link = link;
        this.capabilityID = capabilityID;
        this.band_levelID = band_levelID;
        this.job_familyID = job_familyID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }

    public int getBand_levelID() {
        return band_levelID;
    }

    public void setBand_levelID(int band_levelID) {
        this.band_levelID = band_levelID;
    }

    public int getJob_familyID() {
        return job_familyID;
    }

    public void setJob_familyID(int job_familyID) {
        this.job_familyID = job_familyID;
    }
}

