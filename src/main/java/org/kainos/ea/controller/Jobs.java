package org.kainos.ea.controller;

import org.kainos.ea.models.JobRolesResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jobs {

    //US001 - View Job Roles
    public List<JobRolesResponse> getJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * "
                        + "FROM Role;");

        List<JobRolesResponse> jobRoleNoForeignKeys = new ArrayList<>();

        while (rs.next()) {
            JobRolesResponse jobs = new JobRolesResponse(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("link")
            );

            jobRoleNoForeignKeys.add(jobs);
        }
        return jobRoleNoForeignKeys;
    }
}
