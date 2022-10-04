package org.kainos.ea.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.data.BandLevelData;
import org.kainos.ea.data.CompetencyData;
import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;
import org.kainos.ea.models.CompetenciesWithBandLevel;
import org.kainos.ea.models.Competency;
import org.kainos.ea.models.JobRolesResponse;
import org.kainos.ea.util.DatabaseConnection;
import org.mockito.Mock;
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

    BandLevelData bandLevelData = Mockito.mock(BandLevelData.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    CompetencyService competencyService = new CompetencyService(competencyData, bandLevelData, databaseConnector);

    Connection conn;

    @Test
    void getCompetencyByBandLevel_shouldReturnCompetenciesAndBandLevelName_whenGivenValidBandLevelId() throws DatabaseConnectionException, SQLException {

        CompetenciesWithBandLevel expectedResult = new CompetenciesWithBandLevel();

        int id = 1;

        List<Competency> competencyList = new ArrayList<>();

        BandLevel bandLevel = new BandLevel( 6, "Trainee" );

        competencyList.add(new Competency(1, "Personal Performance", "Developing self-awareness",
                "Understands others strengths and areas for development. Recognisingdiversity and its value within self andteam.Proactively uses wellbeing tools to support self-regulation."));

        expectedResult.setCompetencies( competencyList );
        expectedResult.setBandLevel( new BandLevel(6, "Trainee") );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( competencyData.getCompetenciesByBandLevel( id, conn ) ).thenReturn( competencyList );
        Mockito.when( bandLevelData.getBandLevelName( conn, id ) ).thenReturn( bandLevel );

        CompetenciesWithBandLevel result = competencyService.getCompetenciesByBandLevel( id );

        assertEquals(expectedResult.getCompetencies(), result.getCompetencies());
        Assertions.assertTrue( expectedResult.getBandLevel().equals( result.getBandLevel() ) );
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

    @Test
    void getCompetency_shouldThrowDataNotFoundException_whenCompetencyThrowsDataNotFoundException() throws DataNotFoundException, DatabaseConnectionException, SQLException {
        int id = 6;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyData.getCompetenciesByBandLevel(id, conn)).thenThrow(DataNotFoundException.class);

        assertThrows(DataNotFoundException.class,
                () -> competencyService.getCompetenciesByBandLevel(id));
    }
}
