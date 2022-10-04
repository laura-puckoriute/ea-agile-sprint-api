package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompetenciesWithBandName {

    private BandLevel bandLevel;

    private List<Competency> competencies;

    @JsonCreator
    public CompetenciesWithBandName() { }

    @JsonCreator
    public CompetenciesWithBandName(@JsonProperty("competencies") List<Competency> competencies,
                                    @JsonProperty("band_level") BandLevel bandLevel) {
        this.setCompetencies(competencies);
        this.setBandName(bandLevel);
    }

    public BandLevel getBandName() { return bandLevel; }

    public void setBandName(BandLevel bandLevel) { this.bandLevel = bandLevel; }

    public List<Competency> getCompetencies() { return competencies; }

    public void setCompetencies(List<Competency> competencies) { this.competencies = competencies; }
}
