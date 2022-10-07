package org.kainos.ea.service;

import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidUserCredentialsException;
import org.kainos.ea.models.User;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.util.JwtToken;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    UserData userData = Mockito.mock(UserData.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    UserService userService = new UserService( userData, databaseConnector );

    Connection conn;

    @Test
    public void authenticateUser_shouldReturnToken_whenCredentialsAreValid() throws DatabaseConnectionException, SQLException, InvalidUserCredentialsException {

        int id = 3;

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!");

        String hash = "3864354c74e2eb4ce6a4c1a4b12bdb998651f626c47bc418558bbcc0b56ee6d6";

        String expectedResult = "eyJhbGciOiJIUzUxMiJ9";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), hash ) ).thenReturn( id );
        Mockito.when( userData.insertToken( conn, id, expectedResult )).thenReturn( true );

        String result = userService.authenticateUser( user.getEmail(), user.getPasswordHash() );

        Assertions.assertTrue( expectedResult.equals( result.split("\\.")[0] ));
    }

    @Test
    public void authenticateUser_shouldThrowSQLException_whenAuthenticateUserThrowsSQLException() throws DatabaseConnectionException, SQLException {

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!");

        String hash = "3864354c74e2eb4ce6a4c1a4b12bdb998651f626c47bc418558bbcc0b56ee6d6";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), hash ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> userService.authenticateUser( user.getEmail(), user.getPasswordHash() ));
    }

    @Test
    public void authenticateUser_shouldThrowDatabaseConnectionException_whenAuthenticateUserThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!");

        String hash = "3864354c74e2eb4ce6a4c1a4b12bdb998651f626c47bc418558bbcc0b56ee6d6";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), hash ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> userService.authenticateUser( user.getEmail(), user.getPasswordHash() ));
    }

    @Test
    public void authenticateUser_shouldThrowInvalidUserCredentialsException_whenAuthenticateUserThrowsInvalidUserCredentialsException() throws DatabaseConnectionException, SQLException, InvalidUserCredentialsException {

        int id = 3;

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!");

        String hash = "3864354c74e2eb4ce6a4c1a4b12bdb998651f626c47bc418558bbcc0b56ee6d6";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), hash ) ).thenReturn( id );

        Assertions.assertThrows( InvalidUserCredentialsException.class,
                () -> userService.authenticateUser( user.getEmail(), "wrongpassword" ));
    }

    @Test
    public void removeUserToken_shouldReturnLogoutSuccessful_whenTokenValid() throws DatabaseConnectionException, SQLException {

        String email = "testemail@email.com";

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTMzMjM3fQ" +
                ".CVEwuYNfJcdhcKtoylIeLnmwJSh_uJsoTgo4BiyLzHeeqM0qRvxikubF11NQcrGGszQQBHyMf4yq02TqwTfB4w";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.removeToken( conn, email, token ) ).thenReturn( true );

        String response = userService.removeUserToken( token );

        Assertions.assertEquals( "logout successful", response );

    }

    @Test
    public void removeUserToken_shouldReturnInvalidToken_whenTokenNotFound() throws DatabaseConnectionException, SQLException {

        String email = "testemail@email.com";

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTMzMjM3fQ" +
                ".CVEwuYNfJcdhcKtoylIeLnmwJSh_uJsoTgo4BiyLzHeeqM0qRvxikubF11NQcrGGszQQBHyMf4yq02TqwTfB4w";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.removeToken( conn, email, token ) ).thenReturn( false );

        String response = userService.removeUserToken( token );

        Assertions.assertEquals( "invalid token", response );

    }

    @Test
    public void removeUserToken_shouldThrowSignatureException_whenTokenNotValid() throws DatabaseConnectionException, SQLException {

        String email = "testemail@email.com";

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTMzMjM3fQ" +
                ".CVEwuYNfJcdhcKtoylIeLnmwJSh_uJsoTgo4BiyLzHeeqM0RvxikubF11NQcrGGszQQBHyMf4yq02TqwTfB4w";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.removeToken( conn, email, token ) ).thenReturn( false );

        Assertions.assertThrows( SignatureException.class,
                () -> userService.removeUserToken( token ));

    }
}
