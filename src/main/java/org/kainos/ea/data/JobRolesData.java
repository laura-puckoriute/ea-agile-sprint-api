package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.models.JobRoleRequest;
import org.kainos.ea.models.JobRoleResponse;
import org.kainos.ea.models.JobRolesResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRolesData {

    public JobRoleResponse getJobRole(Connection conn, int id ) throws SQLException, DatabaseConnectionException {

        String query =
                "SELECT " +
                        "Role.id, Role.title, Role.description, Role.responsibilities, Role.link, " +
                        "Capability.title AS capability, Capability.id AS capabilityID, " +
                        "Band_Level.title AS band_level, Band_Level.id AS band_levelID, " +
                        "Family.title AS job_family " +
                "FROM Role JOIN Capability ON Role.capabilityID = Capability.id " +
                "JOIN Job_Family AS Family ON Role.job_familyID = Family.id " +
                "JOIN Band_Level ON Role.band_levelID = Band_Level.id " +
                "WHERE Role.id = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        JobRoleResponse jobRoleResponse = new JobRoleResponse();

        if ( rs.next() ) {

            jobRoleResponse = new JobRoleResponse(

                    rs.getInt   ( "id" ),
                    rs.getString( "title" ),
                    rs.getString( "description" ),
                    rs.getString( "responsibilities" ),
                    rs.getString( "link" ),
                    rs.getString( "band_level" ),
                    rs.getInt   ( "band_levelID"),
                    rs.getString( "capability" ),
                    rs.getInt   ( "capabilityID"),
                    rs.getString( "job_family" )
            );
        }

        return jobRoleResponse;
    }

    public boolean updateJobRole( Connection conn, int id, JobRoleRequest jobRoleRequest ) throws SQLException, DatabaseConnectionException {

        String query =
                "UPDATE `Role` " +
                "SET `title` = ?, `description` = ?, `responsibilities` = ?, `link` = ?, `capabilityID` = ?, `band_levelID` = ?, `job_familyID` = ? " +
                "WHERE `id` = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setString( 1, jobRoleRequest.getTitle() );
        st.setString( 2, jobRoleRequest.getRequirements() );
        st.setString( 3, jobRoleRequest.getResponsibilities() );
        st.setString( 4, jobRoleRequest.getLink() );
        st.setInt   ( 5, jobRoleRequest.getCapabilityID() );
        st.setInt   ( 6, jobRoleRequest.getBandLevelID() );
        st.setInt   ( 7, jobRoleRequest.getJobFamilyID() );
        st.setInt   ( 8, id );

        if ( st.executeUpdate() > 0 ) {

            return true;
        }

        return false;
    }

    //US001 - View Job Roles
    public List<JobRolesResponse> getJobRoles( Connection c ) throws SQLException, DatabaseConnectionException {

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
        return jobRole;
    }

}
