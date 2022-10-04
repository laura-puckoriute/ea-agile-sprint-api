package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobSpecificationResponse;
import org.kainos.ea.util.DatabaseConnection;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobSpecTest {

    JobRolesData jobRolesData = Mockito.mock(JobRolesData.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    JobsService jobsService = new JobsService(jobRolesData, databaseConnector);

    Connection conn;

    @Test
    void getJobSpecification_shouldReturnSpecification_whenJobsRolesReturnsSpecification() throws DatabaseConnectionException, SQLException, DataNotFoundException {
        JobSpecificationResponse expectedResult = new JobSpecificationResponse("Software Engineer",
                "This is a description for Software Engineer",
                "jobspec.com");

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobSpecification(conn, 1)).thenReturn(expectedResult);

        JobSpecificationResponse result = jobsService.getJobSpecification(1);

        assertEquals(expectedResult, result);
    }

    @Test
    void getJobSpecification_shouldThrowSQLException_whenJobsRolesThrowsSQLException() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobSpecification(conn, 1)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobsService.getJobSpecification(1));
    }

    @Test
    void getJobSpecification_shouldThrowDatabaseConnectionException_whenJobsRolesThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobSpecification(conn, 1)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobsService.getJobSpecification(1));
    }

    @Test
    void getJobSpecification_shouldThrowDataNotFoundException_whenJobsRolesThrowsDataNotFoundException() throws DatabaseConnectionException, SQLException, DataNotFoundException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobSpecification(conn, 1)).thenThrow(DataNotFoundException.class);

        assertThrows(DataNotFoundException.class,
                () -> jobsService.getJobSpecification(1));
    }
}
