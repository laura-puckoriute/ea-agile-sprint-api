package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.models.JobSpecificationResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRolesData {

    //US001 - View Job Roles
    public List<JobRolesResponse> getJobRoles(Connection c) throws SQLException, DatabaseConnectionException {

        String query = ("SELECT Role.id, Role.title, Capability.title AS capability, Band_Level.title AS band_level " +
                "FROM Role JOIN Capability ON Role.capabilityID = Capability.id JOIN Band_Level ON Role.band_levelID = Band_Level.id;");

        PreparedStatement st = c.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        List<JobRolesResponse> jobRoleNoForeignKeys = new ArrayList<>();

        while (rs.next()) {

            JobRolesResponse jobs = new JobRolesResponse(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("capability"),
                    rs.getString("band_level")
            );

            jobRoleNoForeignKeys.add(jobs);

        }
        return jobRoleNoForeignKeys;
    }


    public JobSpecificationResponse getJobSpecification(Connection c, int id ) throws SQLException, DatabaseConnectionException {

        String query = "SELECT title, description, link FROM Role WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(query);

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        JobSpecificationResponse jobSpecification = new JobSpecificationResponse();

        while (rs.next()) {

            jobSpecification.setTitle( rs.getString("title") );
            jobSpecification.setDescription( rs.getString("description") );
            jobSpecification.setLink( rs.getString("link") );

        }

        return jobSpecification;
    }
}
