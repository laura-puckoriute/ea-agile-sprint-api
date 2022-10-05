package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.Competency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetencyData {

    public List<Competency> getCompetenciesByBandLevel(int id, Connection c) throws SQLException, DatabaseConnectionException {

        String query =
                "SELECT Competency.id as id, " +
                "Competency.title as title, " +
                "Responsibility.responsibility_name as responsibility_name, " +
                "Responsibility.description as responsibility_description " +
                "FROM Competency " +
                "JOIN Responsibility ON Competency.id = Responsibility.CompetencyID " +
                "JOIN Band_Level ON Responsibility.band_levelID = Band_Level.id " +
                "WHERE Band_Level.id = ?;";

        PreparedStatement st = c.prepareStatement( query );

        st.setInt( 1, id );

        ResultSet rs = st.executeQuery();

        List<Competency> competencies = new ArrayList<>();

        while ( rs.next() ) {

            Competency competency = new Competency(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("responsibility_name"),
                    rs.getString("responsibility_description")
            );

            competencies.add(competency);
        }

        return competencies;
    }
}

