package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class JobRolesResponse {

    @ApiModelProperty( value = "the job role's id value")
    private int id;

    @ApiModelProperty( value = "the job role name" )
    private String title;

    @ApiModelProperty( value = "the job role's description, which can contain requirements")
    private String description;

    @ApiModelProperty( value = "the job role responsibilities" )
    private String responsibilities;

    @ApiModelProperty( value = "the sharepoint link to a detailed page of the job's specification")
    private String link;

    @ApiModelProperty( value = "the capability role belongs in" )
    private String capability;

    @ApiModelProperty( value = "the unique id for the capability the role belongs to" )
    private int capabilityID;

    @ApiModelProperty( value = "the job role's band level")
    private String bandLevel;

    @ApiModelProperty( value = "the unique id for the band level the role belongs to" )
    private int bandLevelID;

    @ApiModelProperty( value = "the job family the role belongs to" )
    private String jobFamily;

    public JobRolesResponse() {}

    @JsonCreator
    public JobRolesResponse(@JsonProperty("id") int id,
                            @JsonProperty("title") String title,
                            @JsonProperty("capability") String capability,
                            @JsonProperty("band_level") String bandLevel,
                            @JsonProperty("capability_id") int capabilityID,
                            @JsonProperty("band_level_id") int bandLevelID) {
        this.setId(id);
        this.setTitle(title);
        this.setCapability(capability);
        this.setBandLevel(bandLevel);
        this.setCapabalityID(capabilityID);
        this.setBandLevelID(bandLevelID);
    }

    public JobRolesResponse( int id,
                             String title,
                             String description,
                             String responsibilities,
                             String link,
                             String capability,
                             String bandLevel,
                             String jobFamily) {
        setId( id );
        setTitle( title );
        setDescription( description );
        setResponsibilities( responsibilities );
        setLink( link );
        setCapability( capability );
        setBandLevel( bandLevel );
        setJobFamily( jobFamily );
    }

    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle( String title ) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription( String description ) { this.description = description; }

    public String getResponsibilities() { return responsibilities; }

    public void setResponsibilities( String responsibilities ) { this.responsibilities = responsibilities; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public String getBandLevel() { return bandLevel; }

    public void setBandLevel( String bandLevel ) { this.bandLevel = bandLevel; }

    public int getBandLevelID() { return bandLevelID; }

    public void setBandLevelID( int bandLevelID ) { this.bandLevelID = bandLevelID; }

    public String getCapability() { return capability; }

    public void setCapability( String capability ) { this.capability = capability; }

    public int getCapabilityID() { return capabilityID; }

    public void setCapabalityID( int capabilityID ) { this.capabilityID = capabilityID; }

    public String getJobFamily() { return jobFamily; }

    public void setJobFamily( String jobFamily ) { this.jobFamily = jobFamily; }


}
