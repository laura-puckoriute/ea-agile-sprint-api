package org.kainos.ea.service;

import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class JobsService {
    public JobRolesData jobRolesData;
    public DatabaseConnection databaseConnection;

    public JobsService(JobRolesData jobRolesData, DatabaseConnection databaseConnection){
        this.jobRolesData = jobRolesData;
        this.databaseConnection = databaseConnection;
    }
    public List<JobRolesResponse> getJobRoles() throws SQLException, DatabaseConnectionException {
        return jobRolesData.getJobRoles(databaseConnection.getConnection());
    }
}
