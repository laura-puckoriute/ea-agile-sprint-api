package org.kainos.ea.service;


import com.google.common.hash.Hashing;
import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidUserCredentialsException;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.util.JwtTokenGenerator;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class UserService {

    private String SECRET = System.getenv("SECRET");

    public UserData userData;

    public DatabaseConnection databaseConnection;

    public UserService( UserData userData, DatabaseConnection databaseConnection ) {

        this.userData = userData;
        this.databaseConnection = databaseConnection;
    }

    public String authenticateUser( String email, String encryptedPassword ) throws DatabaseConnectionException, SQLException, InvalidUserCredentialsException {

        if ( checkCredentials( email, decrypt( encryptedPassword ) ) ) {

            return JwtTokenGenerator.generateToken( "SECRET", email );
        }

        throw new InvalidUserCredentialsException("email or password is incorrect");
    }

    public boolean checkCredentials( String email, String password ) throws DatabaseConnectionException, SQLException {

        return userData.checkCredentials( databaseConnection.getConnection(), email, generateHash( password ) );
    }

    public String decrypt( String password ) {

        // some decryption logic here using private key

        return password;
    }
    public String generateHash( String password ) {

        String hash = Hashing.sha256()
                .hashString( password, StandardCharsets.UTF_8 )
                .toString();

        return hash;
    }
}
