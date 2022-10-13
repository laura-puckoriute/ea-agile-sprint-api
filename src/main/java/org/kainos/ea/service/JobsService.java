package org.kainos.ea.service;

import java.sql.SQLException;

import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.data.JobRolesData;

import org.kainos.ea.models.JobRoleResponse;

import java.util.List;

public class JobsService {
    public JobRolesData jobRolesData;
    public DatabaseConnection databaseConnection;

    public JobsService( JobRolesData jobRolesData, DatabaseConnection databaseConnection ){
        this.jobRolesData = jobRolesData;
        this.databaseConnection = databaseConnection;
    }

    public JobRoleResponse getJobRole(int id ) throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRoleResponse jobRoleResponse = jobRolesData.getJobRole( databaseConnection.getConnection(), id );

        if ( jobRoleResponse.getTitle() == null ) throw new DataNotFoundException();

        return jobRoleResponse;
    }

    public String updateJobRole( int id, JobRoleRequest jobRoleRequest ) throws DatabaseConnectionException, SQLException, DataNotFoundException {

        boolean success = jobRolesData.updateJobRole( databaseConnection.getConnection(), id, jobRoleRequest );

        if ( success ) return "role has been updated";

        throw new DataNotFoundException();
    }

    public List<JobRolesResponse> getJobRoles() throws SQLException, DatabaseConnectionException {
        return jobRolesData.getJobRoles( databaseConnection.getConnection() );
    }

    public int addRole(JobRoleRequest role) throws DatabaseConnectionException, SQLException {
        return jobRolesData.addRole(role, databaseConnection.getConnection());
    }

}
