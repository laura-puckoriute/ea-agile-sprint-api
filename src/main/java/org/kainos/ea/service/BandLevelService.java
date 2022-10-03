package org.kainos.ea.service;

import org.kainos.ea.data.BandLevelData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;

public class BandLevelService {

    public BandLevelData bandLevelData;

    public DatabaseConnection databaseConnector;

    public BandLevelService( BandLevelData bandLevelData, DatabaseConnection databaseConnector) {

        this.bandLevelData = bandLevelData;
        this.databaseConnector = databaseConnector;
    }

    public BandLevel getBandLevelName( int id ) throws DatabaseConnectionException, SQLException, DataNotFoundException {

        BandLevel response = bandLevelData.getBandLevelName( databaseConnector.getConnection(), id );

        if ( response.getBandName() == null ) {

            throw new DataNotFoundException();
        }

        return response;
    }
}
