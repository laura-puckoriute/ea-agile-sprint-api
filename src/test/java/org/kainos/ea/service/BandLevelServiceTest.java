package org.kainos.ea.service;

import org.mockito.Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.kainos.ea.models.BandLevel;
import org.kainos.ea.data.BandLevelData;

import org.kainos.ea.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;

import java.util.ArrayList;
import java.util.List;

public class BandLevelServiceTest {

    private BandLevelData bandLevelData = Mockito.mock( BandLevelData.class );

    private DatabaseConnection databaseConnector = Mockito.mock( DatabaseConnection.class );

    private BandLevelService bandLevelService = new BandLevelService( bandLevelData, databaseConnector );

    private Connection conn;

    @Test
    void getBandLevels_shouldReturnBandLevelList() throws DatabaseConnectionException, SQLException {

        List<BandLevel> bandLevels = new ArrayList<>();

        BandLevel bandLevel = new BandLevel( 1, "Trainee" );

        bandLevels.add( bandLevel );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( bandLevelData.getBandLevels( conn ) ).thenReturn( bandLevels );

        List<BandLevel> result = bandLevelService.getBandLevels();

        Assertions.assertTrue( result.size() > 0 );
        Assertions.assertEquals( bandLevels, result );
    }

    @Test
    void getBandLevels_shouldThrowSQLException_whenQueryThrowsSQLException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( bandLevelData.getBandLevels( conn ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> bandLevelService.getBandLevels() );
    }

    @Test
    void getBandLevels_shouldThrowDatabaseConnectionException_whenConnectionThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( bandLevelData.getBandLevels( conn ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> bandLevelService.getBandLevels() );
    }

    @Test
    void getBandLevels_shouldThrowDataNotFoundException_whenNoBandLevels() throws DatabaseConnectionException, SQLException {

        List<BandLevel> bandLevels = new ArrayList<>();

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( bandLevelData.getBandLevels( conn ) ).thenReturn( bandLevels );

        Assertions.assertThrows( DataNotFoundException.class,
                () -> bandLevelService.getBandLevels() );
    }
}
