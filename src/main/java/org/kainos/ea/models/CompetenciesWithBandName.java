package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompetenciesWithBandName {

    private BandLevel bandName;

    private List<Competency> competencies;

    @JsonCreator
    public CompetenciesWithBandName() { }

    @JsonCreator
    public CompetenciesWithBandName(@JsonProperty("competencies") List<Competency> competencies,
                                    @JsonProperty("band_name") BandLevel bandName) {
        this.setCompetencies(competencies);
        this.setBandName(bandName);
    }

    public BandLevel getBandName() { return bandName; }

    public void setBandName(BandLevel bandName) { this.bandName = bandName; }

    public List<Competency> getCompetencies() { return competencies; }

    public void setCompetencies(List<Competency> competencies) { this.competencies = competencies; }
}
