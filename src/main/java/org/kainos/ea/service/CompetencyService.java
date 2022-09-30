package org.kainos.ea.service;

import org.kainos.ea.data.CompetencyData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.Competency;
import org.kainos.ea.models.Competency;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class CompetencyService {

    public org.kainos.ea.data.CompetencyData competenciesData;
    public DatabaseConnection databaseConnector;

    public CompetencyService(CompetencyData competenciesData, DatabaseConnection databaseConnector) {
        this.competenciesData = competenciesData;
        this.databaseConnector = databaseConnector;
    }

    public List<Competency> getCompetenciesByBandLevel(int id) throws DatabaseConnectionException, SQLException {
        List<Competency> response = competenciesData.getCompetenciesByBandLevel(id, databaseConnector.getConnection());
        if (response != null) {
            return response;
        }
        return null; //throw error instead
    }
}