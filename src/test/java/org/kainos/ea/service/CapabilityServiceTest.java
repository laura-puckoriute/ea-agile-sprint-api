package org.kainos.ea.service;

import org.mockito.Mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.kainos.ea.models.Capability;
import org.kainos.ea.data.CapabilityData;

import org.kainos.ea.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;

import java.util.ArrayList;
import java.util.List;

public class CapabilityServiceTest {

    private CapabilityData capabilityData = Mockito.mock( CapabilityData.class );

    private DatabaseConnection databaseConnector = Mockito.mock( DatabaseConnection.class );

    private CapabilityService capabilityService = new CapabilityService( capabilityData, databaseConnector );

    private Connection conn;

    @Test
    void getCapabilities_shouldReturnCapabilityList() throws DatabaseConnectionException, SQLException {

        List<Capability> capabilities = new ArrayList<>();

        Capability capability = new Capability( 1, "Engineering" );

        capabilities.add( capability );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( capabilityData.getCapabilities( conn ) ).thenReturn( capabilities );

        List<Capability> result = capabilityService.getCapabilities();

        Assertions.assertTrue( result.size() > 0 );
        Assertions.assertEquals( capabilities, result );
    }

    @Test
    void getCapabilities_shouldThrowSQLException_whenQueryThrowsSQLException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( capabilityData.getCapabilities( conn ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> capabilityService.getCapabilities() );
    }

    @Test
    void getCapabilities_shouldThrowDatabaseConnectionException_whenConnectionThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( capabilityData.getCapabilities( conn ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> capabilityService.getCapabilities() );
    }
}
