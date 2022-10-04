package org.kainos.ea.service;

import org.kainos.ea.data.BandLevelData;
import org.kainos.ea.data.CompetencyData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.CompetenciesWithBandLevel;
import org.kainos.ea.models.Competency;
import org.kainos.ea.models.Competency;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class CompetencyService {

    public CompetencyData competenciesData;

    public BandLevelData bandLevelData;

    public DatabaseConnection databaseConnector;

    public CompetencyService(CompetencyData competenciesData, BandLevelData bandLevelData, DatabaseConnection databaseConnector) {
        this.competenciesData = competenciesData;
        this.bandLevelData = bandLevelData;
        this.databaseConnector = databaseConnector;
    }

    public CompetenciesWithBandLevel getCompetenciesByBandLevel(int id) throws DatabaseConnectionException, SQLException, DataNotFoundException {

        CompetenciesWithBandLevel response = new CompetenciesWithBandLevel();

        response.setCompetencies( competenciesData.getCompetenciesByBandLevel(id, databaseConnector.getConnection()) );
        response.setBandLevel( bandLevelData.getBandLevelName( databaseConnector.getConnection(), id ) );

        if ( response != null ) {
            return response;
        }
        throw new DataNotFoundException();
    }
}