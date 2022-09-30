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

        String query = "SELECT id, title, capabilityID, band_levelID FROM Role;";

        PreparedStatement st = c.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        List<JobRolesResponse> jobRoleNoForeignKeys = new ArrayList<>();

        while (rs.next()) {
            String capabilityTitle = getCapabilityTitle(c, rs.getInt("capabilityID"));
            String bandLevelTitle = getBandLevelTitle(c, rs.getInt("band_levelID"));

            JobRolesResponse jobs = new JobRolesResponse(
                    rs.getInt("id"),
                    rs.getString("title"),
                    capabilityTitle,
                    bandLevelTitle
            );

            jobRoleNoForeignKeys.add(jobs);

        }
        return jobRoleNoForeignKeys;
    }

    private String getCapabilityTitle(Connection c, int capabilityID) throws SQLException {

        String queryCapability = "SELECT title FROM Capability WHERE id=?;";

        PreparedStatement stCapability = c.prepareStatement(queryCapability);

        stCapability.setInt(1, capabilityID);

        ResultSet rsCapability = stCapability.executeQuery();

        String capabilityTitle = null;

        if (rsCapability.next()) {
            capabilityTitle = rsCapability.getString("title");
        }

        return capabilityTitle;
    }

    private String getBandLevelTitle(Connection c, int bandLevelID) throws SQLException {

        String queryBandLevel = "SELECT title FROM Band_Level WHERE id=?;";

        PreparedStatement stBandLevel = c.prepareStatement(queryBandLevel);

        stBandLevel.setInt(1, bandLevelID);

        ResultSet rsBandLevel = stBandLevel.executeQuery();

        String bandLevelTitle = null;

        if (rsBandLevel.next()) {
            bandLevelTitle = rsBandLevel.getString("title");
        }

        return bandLevelTitle;
    }

    public JobSpecificationResponse getJobSpecification(Connection c, int id ) throws SQLException {

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
