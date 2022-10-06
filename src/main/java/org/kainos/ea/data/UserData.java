package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {

    public int checkCredentials( Connection conn, String email, String password ) throws SQLException, DatabaseConnectionException {

        String query = "SELECT id FROM `User` WHERE email = ? AND password = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setString( 1, email );
        st.setString( 2, password );

        ResultSet rs = st.executeQuery();

        if ( rs.next() ) {

            return rs.getInt( "id" );
        }

        return -1;
    }

    public boolean insertToken( Connection conn, int userId, String token ) throws SQLException {

        String query = "INSERT INTO `Token` (`userID`, `value`) VALUES (?, ?);";

        PreparedStatement st = conn.prepareStatement( query );

        st.setInt( 1, userId );
        st.setString( 2, token );

        if ( st.executeUpdate() > 0 ) {

            return true;
        }

        return false;

    }

    public boolean removeToken( Connection conn, String token ) throws SQLException {

        String query = "DELETE FROM `Token` WHERE value = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setString( 1, token );

        if ( st.executeUpdate() > 0 ) {

            return true;
        }

        return false;

    }
}
