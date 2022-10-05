package org.kainos.ea.data;

import org.kainos.ea.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {

    public boolean checkCredentials( Connection conn, String email, String password ) throws SQLException, DatabaseConnectionException {

        String query = "SELECT id FROM `User` WHERE email = ? AND password = ?;";

        PreparedStatement st = conn.prepareStatement( query );

        st.setString( 1, email );
        st.setString( 2, password );

        ResultSet rs = st.executeQuery();

        if ( rs.next() ) {

            return true;
        }

        return false;
    }
}
