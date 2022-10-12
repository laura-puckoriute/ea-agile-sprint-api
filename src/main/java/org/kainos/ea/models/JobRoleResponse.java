package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class JobRoleResponse {

    @ApiModelProperty( value = "the job role's id value" )
    private int id;

    @ApiModelProperty( value = "the job role name" )
    private String title;

    @ApiModelProperty( value = "the job role's description, which can contain requirements")
    private String description;

    @ApiModelProperty( value = "the job role responsibilities" )
    private String responsibilities;

    @ApiModelProperty( value = "the sharepoint link to a detailed page of the job's specification")
    private String link;

    @ApiModelProperty( value = "the job role's band level")
    private String bandLevel;

    @ApiModelProperty( value = "the unique id for the band level the role belongs to" )
    private int bandLevelID;

    @ApiModelProperty( value = "the capability role belongs in" )
    private String capability;

    @ApiModelProperty( value = "the unique id for the capability the role belongs to" )
    private int capabilityID;

    @ApiModelProperty( value = "the job family the role belongs to" )
    private String jobFamily;

    public JobRoleResponse() {}

    public JobRoleResponse( int id,
                            String title,
                            String description,
                            String responsibilities,
                            String link,
                            String bandLevel,
                            int bandLevelID,
                            String capability,
                            int capabilityID,
                            String jobFamily) {
        setId( id );
        setTitle( title );
        setDescription( description );
        setResponsibilities( responsibilities );
        setLink( link );
        setBandLevel( bandLevel );
        setBandLevelID( bandLevelID );
        setCapability( capability );
        setCapabalityID( capabilityID );
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

    @Override
    public boolean equals( Object o ) {

        if ( this == o ) return true;

        if ( o == null || getClass() != o.getClass() ) return false;

        JobRoleResponse that = (JobRoleResponse) o;

        return id == that.id && bandLevelID == that.bandLevelID && capabilityID == that.capabilityID && title.equals( that.title ) && description.equals( that.description ) && responsibilities.equals( that.responsibilities ) && link.equals( that.link ) && bandLevel.equals( that.bandLevel ) && capability.equals( that.capability ) && jobFamily.equals( that.jobFamily );
    }
}
