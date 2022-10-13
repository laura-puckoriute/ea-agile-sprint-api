package org.kainos.ea.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.models.JobRoleResponse;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRoles( conn ) ).thenReturn( expectedResult );

        List<JobRolesResponse> result = jobsService.getJobRoles();

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void getJobRoles_shouldThrowSQLException_whenJobsThrowsSQLException () throws SQLException, DatabaseConnectionException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRoles( conn ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> jobsService.getJobRoles() );
    }

    @Test
    void getJobRoles_shouldThrowDatabaseConnectionException_whenJobsThrowsDatabaseConnectionException () throws SQLException, DatabaseConnectionException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRoles( conn ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> jobsService.getJobRoles() );
    }

    @Test
    void getJobRole_shouldReturnSpecification_whenJobsRolesReturnsSpecification() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRoleResponse expectedResult = new JobRoleResponse(
                1,
                "Software Engineer",
                "This is a description for Software Engineer",
                "As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.",
                "jobspec.com",
                "Trainee",
                6,
                "Engineering",
                1,
                "Engineering");

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRole( conn, 1 ) ).thenReturn( expectedResult );

        JobRoleResponse result = jobsService.getJobRole( 1 );

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void getJobRole_shouldThrowSQLException_whenJobsRolesThrowsSQLException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRole( conn, 1 ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> jobsService.getJobRole( 1 ) );
    }

    @Test
    void getJobRole_shouldThrowDatabaseConnectionException_whenJobsRolesThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRole( conn, 1 ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> jobsService.getJobRole( 1 ) );
    }

    @Test
    void getJobRole_shouldThrowDataNotFoundException_whenJobsRolesThrowsDataNotFoundException() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.getJobRole( conn, 1 ) ).thenThrow( DataNotFoundException.class );

        Assertions.assertThrows( DataNotFoundException.class,
                () -> jobsService.getJobRole( 1 ) );
    }

    @Test
    void updateJobRole_shouldReturnRoleHasBeenUpdated_whenJobRoleInsertSuccessful() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a description for Software Engineer",
                "As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.",
                "jobspec.com",
                6,
                1,
                2 );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.updateJobRole( conn, 1, jobRoleRequest )).thenReturn( true );

        String result = jobsService.updateJobRole( 1, jobRoleRequest );

        Assertions.assertEquals( "role has been updated", result );
    }

    @Test
    void updateJobRole_shouldThrowSQLException_whenJobRoleInsertThrowsSQLException() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a description for Software Engineer",
                "As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.",
                "jobspec.com",
                6,
                1,
                2 );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.updateJobRole( conn, 1, jobRoleRequest )).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> jobsService.updateJobRole( 1, jobRoleRequest ) );
    }

    @Test
    void updateJobRole_shouldThrowDatabaseConnectionException_whenJobRoleInsertThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a description for Software Engineer",
                "As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.",
                "jobspec.com",
                6,
                1,
                2 );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.updateJobRole( conn, 1, jobRoleRequest )).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> jobsService.updateJobRole( 1, jobRoleRequest ) );
    }

    @Test
    void updateJobRole_shouldThrowDataNotFoundException_whenJobRoleDoesNotExist() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "This is a description for Software Engineer",
                "As a Software Engineer (Associate) in Kainos, you’ll be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide. You’ll do this whilst learning about new technologies and approaches, with talented colleagues that will help you to learn, develop and grow.",
                "jobspec.com",
                6,
                1,
                2 );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( jobRolesData.updateJobRole( conn, 1, jobRoleRequest )).thenReturn( false );

        Assertions.assertThrows( DataNotFoundException.class,
                () -> jobsService.updateJobRole( 1, jobRoleRequest ) );
    }
}
