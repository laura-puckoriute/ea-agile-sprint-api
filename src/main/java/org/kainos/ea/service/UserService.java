package org.kainos.ea.service;


import com.google.common.hash.Hashing;
import io.jsonwebtoken.InvalidClaimException;
import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidUserCredentialsException;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.util.JwtToken;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class UserService {

    public UserData userData;

    public DatabaseConnection databaseConnection;

    public UserService( UserData userData, DatabaseConnection databaseConnection ) {

        this.userData = userData;
        this.databaseConnection = databaseConnection;
    }

    public String authenticateUser( String email, String password ) throws DatabaseConnectionException, SQLException, InvalidUserCredentialsException {

        int id = checkCredentials( email, password );

        if ( id > 0 ) {

            String token = JwtToken.generateToken( email );

            if ( token != null ) {

                userData.insertToken( databaseConnection.getConnection(), id, token );

                return token;
            }
        }

        throw new InvalidUserCredentialsException("email or password is incorrect");
    }

    public String removeUserToken( String token ) throws DatabaseConnectionException, SQLException, InvalidClaimException {

        String email = JwtToken.verifyToken( token );

        if ( userData.removeToken( databaseConnection.getConnection(), email, token ) ) {

            return "logout successful";
        }

        return "invalid token";
    }

    private int checkCredentials( String email, String password ) throws DatabaseConnectionException, SQLException {

        return userData.checkCredentials( databaseConnection.getConnection(), email, generateHash( password ) );
    }

    private String generateHash( String password ) {

        String hash = Hashing.sha256()
                .hashString( password, StandardCharsets.UTF_8 )
                .toString();

        return hash;
    }
}
