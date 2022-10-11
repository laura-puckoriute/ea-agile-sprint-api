package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Competency {

    @ApiModelProperty( value = "the competency id" )
    private int id;

    @ApiModelProperty( value = "the competency name" )
    private String title;

    @ApiModelProperty( value = "the competency's element name" )
    private String responsibilityName;

    @ApiModelProperty( value = "the competency's element description" )
    private String responsibilityDescription;

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

