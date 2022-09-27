package org.kainos.ea.controller;

import org.kainos.ea.models.JobRoles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jobs {

    //US001 - View Job Roles
    public static List<JobRoles> getJobRoles(Connection c) throws SQLException {
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
}
