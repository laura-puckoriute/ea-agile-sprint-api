package org.kainos.ea.service;

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

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        String expectedResult = JwtToken.generateToken( user.getEmail() );

        Mockito.when( databaseConnector.getConnection() ).thenReturn(conn);
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenReturn(user.getId() );
        Mockito.when( userData.insertToken( conn, user.getId(), expectedResult )).thenReturn( 1 );

        String result = userService.authenticateUser( user.getEmail(), password );

        Assertions.assertTrue( expectedResult.split("\\.")[0].equals( result.split("\\.")[0] ));
    }

    @Test
    public void authenticateUser_shouldThrowSQLException_whenCheckCredentialsThrowsSQLException() throws DatabaseConnectionException, SQLException {

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> userService.authenticateUser( user.getEmail(), password ));
    }

    @Test
    public void authenticateUser_shouldThrowSQLException_whenInsertTokenThrowsSQLException() throws DatabaseConnectionException, SQLException {

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenReturn( user.getId() );
        Mockito.when( userData.insertToken( conn, user.getId(), JwtToken.generateToken( user.getEmail() ) ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> userService.authenticateUser( user.getEmail(), password ));
    }

    @Test
    public void authenticateUser_shouldThrowDatabaseConnectionException_whenCheckCredentialsThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> userService.authenticateUser( user.getEmail(), password ));
    }

    @Test
    public void authenticateUser_shouldThrowDatabaseConnectionException_whenInsertTokenThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenReturn( user.getId() );
        Mockito.when( userData.insertToken( conn, user.getId(), JwtToken.generateToken( user.getEmail() ) ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> userService.authenticateUser( user.getEmail(), password ));
    }

    @Test
    public void authenticateUser_shouldThrowInvalidUserCredentialsException_whenAuthenticateUserThrowsInvalidUserCredentialsException() throws DatabaseConnectionException, SQLException {

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );

        Assertions.assertThrows( InvalidUserCredentialsException.class,
                () -> userService.authenticateUser( "email@email.com", "wrongpassword" ));
    }

    @Test
    public void checkCredentials_shouldReturnUserId_whenCredentialsAreValid() throws DatabaseConnectionException, SQLException {

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenReturn( user.getId() );

        int response = userService.checkCredentials( user.getEmail(), password );

        Assertions.assertEquals( response, user.getId() );
    }

    @Test
    public void checkCredentials_shouldNotReturnId_whenCredentialsAreInvalid() throws DatabaseConnectionException, SQLException {

        String password = "randompassword";

        User user = new User( 1,
                "email@email.com",
                userService.generateHash(password) );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.checkCredentials( conn, user.getEmail(), user.getPasswordHash() ) ).thenReturn( user.getId() );

        int invalidPassword     = userService.checkCredentials( user.getEmail(), "password" );
        int invalidEmail        = userService.checkCredentials( "email", password );
        int invalidCredentials  = userService.checkCredentials( "email", "password" );

        Assertions.assertNotEquals( user.getId(), invalidPassword );
        Assertions.assertNotEquals( user.getId(), invalidEmail );
        Assertions.assertNotEquals( user.getId(), invalidCredentials );
    }

    @Test
    public void insertToken_shouldReturnOne_whenInsertSuccessful() throws DatabaseConnectionException, SQLException {

        int id = 1;

        String token = JwtToken.generateToken( "email@email.com" );

        int expectedResult = 1;

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.insertToken( conn, id, token ) ).thenReturn( expectedResult );

        int result = userService.insertToken( id, token );

        Assertions.assertEquals( expectedResult, result );
    }

    @Test
    public void insertToken_shouldThrowSQLException_whenInsertThrowsSQLException() throws DatabaseConnectionException, SQLException {

        int id = 1;

        String token = JwtToken.generateToken( "email@email.com" );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.insertToken( conn, id, token ) ).thenThrow( SQLException.class );

        Assertions.assertThrows( SQLException.class,
                () -> userService.insertToken( id, token ) );
    }

    @Test
    public void insertToken_shouldThrowDatabaseConnectionException_whenInsertThrowsDatabaseConnectionException() throws DatabaseConnectionException, SQLException {

        int id = 1;

        String token = JwtToken.generateToken( "email@email.com" );

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.insertToken( conn, id, token ) ).thenThrow( DatabaseConnectionException.class );

        Assertions.assertThrows( DatabaseConnectionException.class,
                () -> userService.insertToken( id, token ) );
    }

    @Test
    public void removeUserToken_shouldReturnLogoutSuccessful_whenTokenValid() throws DatabaseConnectionException, SQLException, InvalidUserCredentialsException {

        String email = "testemail@email.com";

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTMzMjM3fQ" +
                ".CVEwuYNfJcdhcKtoylIeLnmwJSh_uJsoTgo4BiyLzHeeqM0qRvxikubF11NQcrGGszQQBHyMf4yq02TqwTfB4w";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.removeToken( conn, email, token ) ).thenReturn( true );

        String response = userService.removeUserToken( token );

        Assertions.assertEquals( "logout successful", response );

    }

    @Test
    public void removeUserToken_shouldThrowInvalidUserCredentialsException_whenTokenNotFound() throws DatabaseConnectionException, SQLException {

        String email = "testemail@email.com";

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTMzMjM3fQ" +
                ".CVEwuYNfJcdhcKtoylIeLnmwJSh_uJsoTgo4BiyLzHeeqM0qRvxikubF11NQcrGGszQQBHyMf4yq02TqwTfB4w";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );
        Mockito.when( userData.removeToken( conn, email, token ) ).thenReturn( false );

        Assertions.assertThrows( InvalidUserCredentialsException.class,
                () -> userService.removeUserToken( token ));

    }

    @Test
    public void removeUserToken_shouldThrowSignatureException_whenTokenNotValid() throws DatabaseConnectionException, SQLException {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTMzMjM3fQ" +
                ".CVEwuYNfJcdhcKtoylIeLnmwJSh_uJsoTgo4BiyLzHeeqM0RvxikubF11NQcrGGszQQBHyMf4yq02TqwTfB4w";

        Mockito.when( databaseConnector.getConnection() ).thenReturn( conn );

        Assertions.assertThrows( SignatureException.class,
                () -> userService.removeUserToken( token ));

    }

    @Test
    public void generateHash_shouldReturnIdenticalHash_whenGivenSameInput() {

        String first    = "IDENTICALSTRING";
        String second   = "IDENTICALSTRING";

        Assertions.assertEquals( userService.generateHash( first ), userService.generateHash( second ) );
    }

    @Test
    public void generateHash_shouldNotReturnIdenticalHash_whenGivenDifferentInput() {

        String first    = "IDENTICALSTRING";
        String second   = "NOTIDENTICALSTRING";

        Assertions.assertNotEquals( userService.generateHash( first ), userService.generateHash( second ) );
    }
}
