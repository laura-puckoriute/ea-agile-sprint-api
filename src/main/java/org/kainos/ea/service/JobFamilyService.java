package org.kainos.ea.service;

import org.kainos.ea.data.JobFamilyData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobFamily;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class JobFamilyService {

    private JobFamilyData jobFamilyData;

    private DatabaseConnection databaseConnector;

    public JobFamilyService( JobFamilyData jobFamilyData, DatabaseConnection databaseConnector ) {

        this.jobFamilyData = jobFamilyData;
        this.databaseConnector = databaseConnector;
    }

    public List<JobFamily> getJobFamilies() throws DatabaseConnectionException, SQLException {

        return jobFamilyData.getJobFamilies( databaseConnector.getConnection() );
    }
}
