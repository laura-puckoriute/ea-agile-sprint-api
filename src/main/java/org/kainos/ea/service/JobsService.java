package org.kainos.ea.service;

import org.kainos.ea.controller.Jobs;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class JobsService {
    public Jobs jobs;
    public DatabaseConnection databaseConnection;

    public JobsService(Jobs jobs, DatabaseConnection databaseConnection){
        this.jobs = jobs;
        this.databaseConnection = databaseConnection;
    }
    public List<JobRolesResponse> getJobRoles() throws SQLException, DatabaseConnectionException {
        return jobs.getJobRoles(databaseConnection.getConnection());
    }
}
