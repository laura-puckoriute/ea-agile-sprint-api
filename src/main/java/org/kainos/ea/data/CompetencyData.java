package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;
import org.kainos.ea.models.CompetenciesWithBandLevel;
import org.kainos.ea.models.Competency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetencyData {

    public CompetenciesWithBandLevel getCompetenciesByBandLevel( int id, Connection c ) throws SQLException, DatabaseConnectionException {

        String query =
                "SELECT " +
                        "COMP.id AS competency_id, " +
                        "    COMP.title AS competency_title, " +
                        "    RES.responsibility_name, " +
                        "    RES.description AS responsibility_description, " +
                        "    BAND.id AS band_id, " +
                        "    BAND.title AS band_title " +

                        "FROM `Competency` AS COMP " +
                        "JOIN `Responsibility` AS RES " +
                        "ON COMP.id = RES.competencyID " +
                        "JOIN `Band_Level` as BAND " +
                        "ON BAND.id = RES.band_levelID " +
                        "WHERE BAND.id = ?;";

        PreparedStatement st = c.prepareStatement( query );

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        List<Competency> competencies = new ArrayList<>();
        BandLevel bandLevel = new BandLevel();

        while ( rs.next() ) {

            Competency competency = new Competency (

                    rs.getInt("competency_id"),
                    rs.getString("competency_title"),
                    rs.getString("responsibility_name"),
                    rs.getString("responsibility_description")
            );

            bandLevel.setId( rs.getInt("band_id") );
            bandLevel.setBandName( rs.getString("band_title") );

            competencies.add(competency);
        }

        return new CompetenciesWithBandLevel( competencies, bandLevel );
    }
}

