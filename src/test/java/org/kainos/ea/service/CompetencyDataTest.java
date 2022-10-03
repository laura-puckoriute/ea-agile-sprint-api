package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.data.CompetencyData;
import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.Competency;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CompetencyDataTest {

    CompetencyData competencyData = Mockito.mock(CompetencyData.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    CompetencyService competencyService = new CompetencyService(competencyData, databaseConnector);

    Connection conn;

    @Test
    void getCompetencyByBandLevel_shouldReturnCompetencyList_whenCompetencyReturnsCompetencies() throws DatabaseConnectionException, SQLException {
        List<Competency> expectedResult = new ArrayList<>();
        int id = 1;

        Competency j = new Competency(1, "Personal Performance", "Developing self-awareness",
                "Understands others strengths and areas for development. Recognisingdiversity and its value within self andteam.Proactively uses wellbeing tools to support self-regulation.");
        expectedResult.add(j);

        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyData.getCompetenciesByBandLevel(id, conn)).thenReturn(expectedResult);
        List<Competency> result = competencyService.getCompetenciesByBandLevel(id);

        System.out.println(result);
        assertEquals(expectedResult, result);
    }

    @Test
    void getCompetencyByBandLevel_shouldThrowSQLException_whenCompetencyThrowsSQLException () throws SQLException, DatabaseConnectionException {
        int id = 6;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyData.getCompetenciesByBandLevel(id, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> competencyService.getCompetenciesByBandLevel(id));
    }

    @Test
    void getCompetency_shouldThrowDatabaseConnectionException_whenCompetencyThrowsDatabaseConnectionException () throws SQLException, DatabaseConnectionException {
        int id = 6;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyData.getCompetenciesByBandLevel(id, conn)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> competencyService.getCompetenciesByBandLevel(id));
    }
}
