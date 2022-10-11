package org.kainos.ea.resources;

import io.jsonwebtoken.JwtException;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidUserCredentialsException;
import org.kainos.ea.models.User;
import org.kainos.ea.service.UserService;
import org.kainos.ea.util.DatabaseConnection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api/auth")
public class AuthenticationService {

    private static UserService userService;

    public AuthenticationService() {

        userService = new UserService( new UserData(), new DatabaseConnection() );
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser( User user ) {

        try {

            return Response.ok( userService.authenticateUser( user.getEmail(), user.getPassword() ) ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch ( InvalidUserCredentialsException e ) {

            return Response.status( HttpStatus.UNAUTHORIZED_401 ).entity( e.getMessage() ).build();
        }
    }

    @POST
    @Path("/signout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUserToken( @HeaderParam("Authorization") String token ) {

        try {

            return Response.ok( userService.removeUserToken( token ) ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch ( JwtException | InvalidUserCredentialsException e ) {

            return Response.status( HttpStatus.BAD_REQUEST_400 ).build();

        }
    }
}
