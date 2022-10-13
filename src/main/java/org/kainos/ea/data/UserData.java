package org.kainos.ea.data;

import com.google.common.hash.Hashing;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.UserRequest;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class UserData {

    public int checkCredentials( Connection conn, String email, String passwordHash ) throws DatabaseConnectionException, SQLException {

        String query = "SELECT id FROM `User` WHERE email = ? AND password = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setString( 1, email );
        st.setString( 2, passwordHash );

        ResultSet rs = st.executeQuery();

        if ( rs.next() ) {

            return rs.getInt( "id" );
        }

        return -1;
    }

    public int insertToken( Connection conn, int userId, String token ) throws DatabaseConnectionException, SQLException {

        String query = "INSERT INTO `Token` (`userID`, `value`) VALUES (?, ?);";

        PreparedStatement st = conn.prepareStatement( query );

        st.setInt( 1, userId );
        st.setString( 2, token );

        if ( st.executeUpdate() > 0 ) {

            return 1;
        }

        return -1;

    }

    public boolean removeToken( Connection conn, String email, String token ) throws DatabaseConnectionException, SQLException {

        String query = "DELETE Token FROM Token " +
                "JOIN User ON User.id = Token.userID " +
                "WHERE User.email = ? AND Token.value = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setString( 1, email );
        st.setString( 2, token );

        if ( st.executeUpdate() > 0 ) {

            return true;
        }

        return false;

    }

    private String generateHash( String password ) {

        String hash = Hashing.sha256()
                .hashString( password, StandardCharsets.UTF_8 )
                .toString();

        return hash;
    }
    public int registerUser(UserRequest user, Connection c) throws SQLException {
        String query = "INSERT INTO `User`(email, password, user_roleID) VALUES (?, ?, ?);";

        PreparedStatement st = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, user.getEmail());
        st.setString(2, generateHash(user.getPassword()));
        st.setInt(3, user.getUserRoleID());

        int affectedRows = st.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        int userNo = 0;

        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                userNo = rs.getInt(1);
            }

            return userNo;
        }
    }
}
