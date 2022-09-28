package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRolesResponse {
    private int id;
    private String title;
    private String description;
    private String link;

    @JsonCreator
    public JobRolesResponse(@JsonProperty("id") int id,
                            @JsonProperty("title") String title,
                            @JsonProperty("description") String description,
                            @JsonProperty("link") String link){
        this.setId(id);
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
