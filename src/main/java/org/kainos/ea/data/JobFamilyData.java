package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.JobFamily;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobFamilyData {

    public List<JobFamily> getJobFamilies( Connection conn ) throws SQLException, DatabaseConnectionException {

        String query = "SELECT * FROM Job_Family;";

        PreparedStatement st = conn.prepareStatement( query );

        ResultSet rs = st.executeQuery();

        List<JobFamily> jobFamilies = new ArrayList<>();

        while ( rs.next() ) {

            JobFamily jobFamily = new JobFamily(

                    rs.getInt( "id" ),
                    rs.getString( "title" )
            );

            jobFamilies.add( jobFamily );
        }

        return jobFamilies;
    }
}
