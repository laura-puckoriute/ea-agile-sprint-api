package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class JobSpecificationResponse {

    private String title;
    private String description;
    private String link;

    public JobSpecificationResponse() {};

    @JsonCreator
    public JobSpecificationResponse(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("link") String link)
    {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSpecificationResponse response = (JobSpecificationResponse) o;
        return title.equals(response.title) && description.equals(response.description) && link.equals(response.link);
    }
}
