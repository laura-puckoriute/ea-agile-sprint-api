package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobRoleRequest;
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

    JobRoleRequest role = new JobRoleRequest("test", "test", "test", "est",
            1, 1, 1);

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

    @Test
    void addNewJobRole_shouldAddNewJobRole_whenValidJobRoleAdded() throws DatabaseConnectionException, SQLException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.addRole(role, conn)).thenReturn(expectedResult);

        int actualResult = jobsService.addRole(role);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addNewJobRole_shouldThrow500Error_whenJobRoleAlreadyExists() throws DatabaseConnectionException, SQLException {
        JobRoleRequest duplicateRole = new JobRoleRequest("test", "test", "test", "est",
                1, 1, 1);

        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.addRole(role, conn)).thenReturn(expectedResult);
        Mockito.when(jobRolesData.addRole(duplicateRole, conn)).thenReturn(expectedResult);

        int actualResult = jobsService.addRole(duplicateRole);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addNewJobRole_shouldThrowSQLException_whenSQLExceptionIsThrown() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.addRole(role, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobsService.addRole(role));
    }

    @Test
    public void addNewJobRole_shouldThrowDatabaseConnectionException_whenDatabaseConnectionExceptionIsThrown() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.addRole(role, conn)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobsService.addRole(role));
    }

    @Test
    public void addNewJobRole_shouldThrowDataNotFoundException_whenDataNotFoundExceptionIsThrown() throws DatabaseConnectionException, SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRolesData.addRole(role, conn)).thenThrow(DataNotFoundException.class);

        assertThrows(DataNotFoundException.class,
                () -> jobsService.addRole(role));
    }
}
