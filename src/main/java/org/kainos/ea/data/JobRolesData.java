package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.models.JobSpecificationResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRolesData {

    public JobRolesResponse getJobRole( Connection conn, int id ) throws SQLException {

        String query =
                "SELECT Role.id, Role.title, Capability.title AS capability, Band_Level.title AS band_level " +
                "FROM Role JOIN Capability ON Role.capabilityID = Capability.id " +
                "JOIN Band_Level ON Role.band_levelID = Band_Level.id " +
                "WHERE Role.id = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        JobRolesResponse jobRolesResponse = new JobRolesResponse();

        if ( rs.next() ) {

            jobRolesResponse = new JobRolesResponse(
                    rs.getInt( "id" ),
                    rs.getString( "title" ),
                    rs.getString( "capability" ),
                    rs.getString( "band_level" )
            );
        }

        return jobRolesResponse;
    }

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

        String query = "SELECT title, description, link, responsibilities FROM Role WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(query);

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        JobSpecificationResponse jobSpecification = new JobSpecificationResponse();

        while (rs.next()) {

            jobSpecification.setTitle( rs.getString("title") );
            jobSpecification.setDescription( rs.getString("description") );
            jobSpecification.setLink( rs.getString("link") );
            jobSpecification.setResponsibilities( rs.getString("responsibilities"));

        }

        return jobSpecification;
    }
}
