package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoles {
    private String jobTitle;
    private String description;
    private String band_level;
    private String capability;

    @JsonCreator
    public JobRoles(
            @JsonProperty("jobtitle") String jobTitle,
            @JsonProperty("description") String description,
            @JsonProperty("band_level") String band_level,
            @JsonProperty("capability") String capability)
            {
        this.setJobTitle(jobTitle);
        this.setDescription(description);
        this.setBand_level(band_level);
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

    public String getBand_level() {
        return band_level;
    }

    public void setBand_level(String band_level) {
        this.band_level = band_level;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }
}
