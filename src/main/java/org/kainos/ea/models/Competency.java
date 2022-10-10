package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Competency {

    @JsonCreator
    public Competency(
            @JsonProperty("id") int id,
            @JsonProperty("title") String title,
            @JsonProperty("responsibility_name") String responsibilityName,
            @JsonProperty("responsibility_description") String responsibilityDescription) {
        this.setId(id);
        this.setTitle(title);
        this.setResponsibilityName(responsibilityName);
        this.setResponsibilityDescription(responsibilityDescription);
    }

    private int id;
    private String title;
    private String responsibilityName;
    private String responsibilityDescription;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResponsibilityDescription() {
        return responsibilityDescription;
    }

    public void setResponsibilityDescription(String responsibilityDescription) {
        this.responsibilityDescription = responsibilityDescription;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }
}

