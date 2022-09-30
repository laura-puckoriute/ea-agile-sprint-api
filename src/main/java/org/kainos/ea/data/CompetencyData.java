package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.Competency;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetencyData {

    public List<Competency> getCompetenciesByBandLevel(int id, Connection c) throws SQLException, DatabaseConnectionException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT Competency.title, Responsibility.responsibility_name, Responsibility.description " +
                        "FROM Competency " +
                        "JOIN Responsibility ON Competency.id = Responsibility.CompetencyID " +
                        "JOIN Band_Level ON Responsibility.band_levelID = Band_Level.id " +
                        "WHERE Band_Level.id = " + id + ";");

        List<Competency> competencies = new ArrayList<>();

        while (rs.next()) {
            Competency competency = new Competency(
                    rs.getString("title"),
                    rs.getString("responsibility_name"),
                    rs.getString("description")
            );

            competency.setTitle(rs.getString("title"));
            competencies.add(competency);
        }
        return competencies;
    }
}

