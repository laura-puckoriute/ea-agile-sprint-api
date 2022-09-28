package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.Jobs;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobRoles;
import org.kainos.ea.resources.WebService;
import org.kainos.ea.util.DatabaseConnection;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.attribute.standard.MediaSize;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobsTest {

    Jobs jobs = Mockito.mock(Jobs.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    JobsService jobsService = new JobsService(jobs, databaseConnector);

    Connection conn;

    @Test
    void getJobRoles_shouldReturnJobRoles_whenJobsReturnsJobRoles () throws SQLException, DatabaseConnectionException{
        List<JobRoles> expectedResult = new ArrayList<>();

        JobRoles j = new JobRoles("Engineer", "Engineering", "Associate", "Engineering");
        expectedResult.add(j);

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobs.getJobRoles(conn)).thenReturn(expectedResult);
        List<JobRoles> result = jobsService.getJobRoles();

        assertEquals(expectedResult, result);
    }

    @Test
    void getJobRoles_shouldThrowSQLException_whenJobsThrowsSQLException () throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobs.getJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobsService.getJobRoles());
    }


}
