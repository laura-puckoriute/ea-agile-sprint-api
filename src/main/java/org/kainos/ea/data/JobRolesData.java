package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.models.JobSpecificationResponse;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRolesData {

    //US001 - View Job Roles
    public List<JobRolesResponse> getJobRoles(Connection c) throws SQLException, DatabaseConnectionException {

        String query = ("SELECT Role.id, Role.title, Capability.title AS 'capability', Band_Level.title AS 'band_level', Role.capabilityID AS 'CapabilityID', Role.band_levelID AS 'BandID'" +
                "FROM Role JOIN Capability ON Role.capabilityID = Capability.id JOIN Band_Level ON Role.band_levelID = Band_Level.id;");

        PreparedStatement st = c.prepareStatement(query);

        ResultSet rs = st.executeQuery();


        List<JobRolesResponse> jobRole = new ArrayList<>();

        while (rs.next()) {

            JobRolesResponse jobs = new JobRolesResponse(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("capability"),
                    rs.getString("band_level"),
                    rs.getInt("CapabilityID"),
                    rs.getInt("BandID")
            );

            jobRole.add(jobs);

        }

        String query2 = "SELECT b.id AS 'Band ID', c.id AS 'Capability ID', b.title AS 'Band Title', c.title AS 'Capability Title' FROM Band_Level b, Capability c GROUP BY b.id, c.id order by b.id;";
        PreparedStatement st2 = c.prepareStatement(query2);
        ResultSet rs2 = st2.executeQuery();

        while(rs2.next()) {
            JobRolesResponse bands = new JobRolesResponse(
                    rs2.getString("Capability Title"),
                    rs2.getString("Band Title"),
                    rs2.getInt("Capability ID"),
                    rs2.getInt("Band ID")
            );

            jobRole.add(bands);
        }
        return jobRole;
    }

    public JobSpecificationResponse getJobSpecification(Connection c, int id) throws SQLException, DatabaseConnectionException {

        String query = "SELECT title, description, link FROM Role WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(query);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        JobSpecificationResponse jobSpecification = new JobSpecificationResponse();

        while (rs.next()) {

            jobSpecification.setTitle(rs.getString("title"));
            jobSpecification.setDescription(rs.getString("description"));
            jobSpecification.setLink(rs.getString("link"));

        }

        return jobSpecification;
    }
}
