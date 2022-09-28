package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoles {
    private String jobTitle;
    private String description;
    private String band;
    private String capability;

    @JsonCreator
    public JobRoles(
            @JsonProperty("jobtitle") String jobTitle,
            @JsonProperty("description") String description,
            @JsonProperty("band") String band,
            @JsonProperty("capability") String capability)
            {
        this.setJobTitle(jobTitle);
        this.setDescription(description);
        this.setBand(band);
        this.setCapability(capability);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }
}
