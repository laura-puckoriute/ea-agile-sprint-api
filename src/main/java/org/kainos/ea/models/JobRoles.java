package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoles {
    private String title;
    private String description;
    private String band_level;
    private String capability;

    @JsonCreator
    public JobRoles(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("band_level") String band_level,
            @JsonProperty("capability") String capability)
            {
        this.setTitle(title);
        this.setDescription(description);
        this.setBand_level(band_level);
        this.setCapability(capability);
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
