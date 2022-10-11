package org.kainos.ea.service;

import java.sql.SQLException;

import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.data.JobRolesData;

import org.kainos.ea.models.JobSpecificationResponse;
import org.kainos.ea.models.JobRolesResponse;

import java.util.List;

public class JobsService {
    public JobRolesData jobRolesData;
    public DatabaseConnection databaseConnection;

    public JobsService(JobRolesData jobRolesData, DatabaseConnection databaseConnection){
        this.jobRolesData = jobRolesData;
        this.databaseConnection = databaseConnection;
    }

    public JobRolesResponse getJobRole( int id ) throws DatabaseConnectionException, SQLException, DataNotFoundException {

        JobRolesResponse jobRolesResponse = jobRolesData.getJobRole( databaseConnection.getConnection(), id );

        if ( jobRolesResponse.getTitle() == null ) throw new DataNotFoundException();

        return jobRolesResponse;
    }

    public List<JobRolesResponse> getJobRoles() throws SQLException, DatabaseConnectionException {
        return jobRolesData.getJobRoles(databaseConnection.getConnection());
    }

    public JobSpecificationResponse getJobSpecification( int id ) throws SQLException, DatabaseConnectionException, DataNotFoundException {
        JobSpecificationResponse jobSpecification = jobRolesData.getJobSpecification( databaseConnection.getConnection(), id );
        if (jobSpecification.getTitle() == null) {
            throw new DataNotFoundException();
        }
        return jobSpecification;
    }
}
