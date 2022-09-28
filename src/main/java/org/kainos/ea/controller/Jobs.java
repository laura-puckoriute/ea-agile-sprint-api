package org.kainos.ea.controller;

import org.kainos.ea.models.JobRoles;
import org.kainos.ea.models.JobSpecification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jobs {

    //US001 - View Job Roles
    public List<JobRoles> getJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM Role;");

        List<JobRoles> jobRoles = new ArrayList<>();

        while (rs.next()) {
            JobRoles jobs = new JobRoles(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("band_level"),
                    rs.getString("capability")
            );

            jobRoles.add(jobs);
        }
        return jobRoles;
    }

    public JobSpecification getJobSpecification( Connection c, int id ) throws SQLException {

        String query = "SELECT title, description, link FROM Role WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(query);

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        JobSpecification jobSpecification = new JobSpecification();

        while (rs.next()) {

            jobSpecification.setTitle( rs.getString("title") );
            jobSpecification.setDescription( rs.getString("description") );
            jobSpecification.setDescription( rs.getString("link") );

        }

        return jobSpecification;
    }
}
