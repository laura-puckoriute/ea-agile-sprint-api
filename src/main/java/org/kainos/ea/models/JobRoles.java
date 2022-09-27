package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoles {
    private String jobTitle;
    private String jobFamily;
    private String band;
    private String capability;

    @JsonCreator
    public JobRoles(
            @JsonProperty("jobtitle") String jobTitle,
            @JsonProperty("jobfamily") String jobFamily,
            @JsonProperty("band") String band,
            @JsonProperty("capability") String capability) {
        this.setJobTitle(jobTitle);
        this.setJobFamily(jobFamily);
        this.setBand(band);
        this.setCapability(capability);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
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
