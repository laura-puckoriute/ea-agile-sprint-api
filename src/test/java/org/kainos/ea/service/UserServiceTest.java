package org.kainos.ea.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidUserCredentialsException;
import org.kainos.ea.models.User;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.util.JwtToken;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    UserData userData = Mockito.mock(UserData.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    JwtToken jwtToken = Mockito.mock(JwtToken.class);

    UserService userService = new UserService( userData, databaseConnector );

    Connection conn;

    @Test
    public void authenticateUser_shouldReturnToken_whenCredentialsAreValid() throws DatabaseConnectionException, SQLException, InvalidUserCredentialsException {

        int id = 3;

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!");

        String hash = "3864354c74e2eb4ce6a4c1a4b12bdb998651f626c47bc418558bbcc0b56ee6d6";

        String expectedResult = "eyJhbGciOiJIUzUxMiJ9";

        Mockito.when( databaseConnector.getConnection() ).thenReturn(conn);
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), hash ) ).thenReturn( id );
        Mockito.when( userData.insertToken( conn, id, expectedResult )).thenReturn( true );

        String result = userService.authenticateUser( user.getEmail(), user.getPasswordHash() );

        Assertions.assertTrue( expectedResult.equals( result.split("\\.")[0] ));
    }
}
