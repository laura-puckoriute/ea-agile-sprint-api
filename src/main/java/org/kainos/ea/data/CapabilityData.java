package org.kainos.ea.data;

import org.kainos.ea.models.Capability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapabilityData {

    public List<Capability> getCapabilities( Connection conn ) throws SQLException {

        String query = "SELECT * FROM Capability;";

        PreparedStatement st = conn.prepareStatement( query );

        ResultSet rs = st.executeQuery();

        List<Capability> capabilities = new ArrayList<>();

        while ( rs.next() ) {

            Capability capability = new Capability(

                    rs.getInt( "id" ),
                    rs.getString( "title" )
            );

            capabilities.add( capability );
        }

        return capabilities;
    }
}
