package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRolesResponse {
    private int id;
    private String title;

    @JsonCreator
    public JobRolesResponse(@JsonProperty("id") int id,
                            @JsonProperty("title") String title){
        this.setId(id);
        this.setTitle(title);

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

}
