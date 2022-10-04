package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompetenciesWithBandLevel {

    private BandLevel bandLevel;

    private List<Competency> competencies;

    @JsonCreator
    public CompetenciesWithBandLevel() { }

    @JsonCreator
    public CompetenciesWithBandLevel( @JsonProperty("competencies") List<Competency> competencies,
                                      @JsonProperty("band_level") BandLevel bandLevel) {
        this.setCompetencies(competencies);
        this.setBandLevel(bandLevel);
    }

    public BandLevel getBandLevel() { return bandLevel; }

    public void setBandLevel( BandLevel bandLevel ) { this.bandLevel = bandLevel; }

    public List<Competency> getCompetencies() { return competencies; }

    public void setCompetencies( List<Competency> competencies ) { this.competencies = competencies; }
}
