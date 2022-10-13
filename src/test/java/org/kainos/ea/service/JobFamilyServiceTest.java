package org.kainos.ea.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.data.JobFamilyData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;
import org.kainos.ea.models.JobFamily;
import org.kainos.ea.util.DatabaseConnection;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobFamilyServiceTest {

    private JobFamilyData jobFamilyData = Mockito.mock( JobFamilyData.class );

    private DatabaseConnection databaseConnector = Mockito.mock( DatabaseConnection.class );

    private JobFamilyService jobFamilyService = new JobFamilyService( jobFamilyData, databaseConnector );

    private Connection conn;

    @Test
    void getJobFamilies_shouldReturnJobFamilyList() throws DatabaseConnectionException, SQLException {

        List<JobFamily> jobFamilies = new ArrayList<>();

        JobFamily jobFamily = new JobFamily( 1, "Strategy and Planning" );

        jobFamilies.add( jobFamily );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobFamilyData.getJobFamilies( conn ) ).thenReturn( jobFamilies );

        List<JobFamily> result = jobFamilyService.getJobFamilies();

        Assertions.assertTrue( result.size() > 0 );
        Assertions.assertEquals( jobFamilies, result );
    }

    @Test
    void getJobFamilies_shouldThrowSQLException_whenQueryThrowsSQLException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobFamilyData.getJobFamilies( conn ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> jobFamilyService.getJobFamilies() );
    }

    @Test
    void getJobFamilies_shouldThrowDatabaseConnectionException_whenConnectionThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobFamilyData.getJobFamilies( conn ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> jobFamilyService.getJobFamilies() );
    }
}
