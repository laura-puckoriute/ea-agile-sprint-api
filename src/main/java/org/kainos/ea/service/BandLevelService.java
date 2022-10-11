package org.kainos.ea.service;

import org.kainos.ea.data.BandLevelData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class BandLevelService {

    private BandLevelData bandLevelData;

    private DatabaseConnection databaseConnection;

    public BandLevelService ( BandLevelData bandLevelData, DatabaseConnection databaseConnection ) {

        this.bandLevelData = bandLevelData;
        this.databaseConnection = databaseConnection;
    }

    public List<BandLevel> getBandLevels() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        List<BandLevel> bandLevels = bandLevelData.getBandLevels( databaseConnection.getConnection() );

        if ( !(bandLevels.size() > 0) ) throw new DataNotFoundException();

        return bandLevels;
    }
}
