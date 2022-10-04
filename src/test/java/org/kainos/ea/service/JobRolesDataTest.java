package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRolesDataTest {

    JobRolesData jobRolesData = Mockito.mock(JobRolesData.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    JobsService jobsService = new JobsService(jobRolesData, databaseConnector);

    Connection conn;

    @Test
    void getJobRoles_shouldReturnJobRoles_whenJobsReturnsJobRoles () throws SQLException, DatabaseConnectionException{
        List<JobRolesResponse> expectedResult = new ArrayList<>();

        JobRolesResponse j = new JobRolesResponse(1, "Software Engineer", "Engineering", "Trainee", 1, 1);
        expectedResult.add(j);

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobRoles(conn)).thenReturn(expectedResult);
        List<JobRolesResponse> result = jobsService.getJobRoles();

        assertEquals(expectedResult, result);
    }

    @Test
    void getJobRoles_shouldReturnBandAndCapability_whenJobsReturnsBandAndCapability () throws SQLException, DatabaseConnectionException{
        List<JobRolesResponse> expectedResult = new ArrayList<>();

        JobRolesResponse j = new JobRolesResponse("Engineering", "Trainee", 1, 1);
        expectedResult.add(j);

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobRoles(conn)).thenReturn(expectedResult);
        List<JobRolesResponse> result = jobsService.getJobRoles();

        assertEquals(expectedResult, result);
    }

    @Test
    void getJobRoles_shouldThrowSQLException_whenJobsThrowsSQLException () throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobsService.getJobRoles());
    }

    @Test
    void getJobRoles_shouldThrowDatabaseConnectionException_whenJobsThrowsDatabaseConnectionException () throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.getJobRoles(conn)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobsService.getJobRoles());
    }
}
