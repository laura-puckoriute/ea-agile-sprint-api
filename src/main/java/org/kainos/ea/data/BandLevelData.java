package org.kainos.ea.data;

import org.kainos.ea.models.BandLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandLevelData {

    public List<BandLevel> getBandLevels( Connection conn ) throws SQLException {

        String query = "SELECT * FROM Band_Level;";

        PreparedStatement st = conn.prepareStatement( query );

        ResultSet rs = st.executeQuery();

        List<BandLevel> bandLevels = new ArrayList<>();

        while ( rs.next() ) {

            BandLevel bandLevel = new BandLevel(

                    rs.getInt( "id" ),
                    rs.getString( "title" )
            );

            bandLevels.add( bandLevel );
        }

        return bandLevels;
    }
}
