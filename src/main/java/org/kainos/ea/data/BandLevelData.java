package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;

import java.sql.*;

public class BandLevelData {

    public BandLevel getBandLevelName ( Connection c, int id ) throws SQLException, DatabaseConnectionException {

        String query = "SELECT title from Band_Level WHERE id = ?;";

        PreparedStatement st = c.prepareStatement( query );

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        rs.next();

        BandLevel bandLevel = new BandLevel( id, rs.getString( "title" ) );

        return bandLevel;
    }
}
