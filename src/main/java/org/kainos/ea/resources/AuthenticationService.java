package org.kainos.ea.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidUserCredentialsException;
import org.kainos.ea.models.User;
import org.kainos.ea.models.UserResponse;
import org.kainos.ea.service.UserService;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.util.JwtToken;

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


    @GET
    @Path("/gen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateToken( String email ) {

        try {

            return Response.ok(JwtToken.generateToken( email )).build();

        } catch ( JwtException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyToken( String token ) {

        try {

            return Response.ok( JwtToken.verifyToken( token ) ).build();

        } catch ( Exception e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser( UserResponse user ) {

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUserToken( String token ) {

        try {

            return Response.ok( userService.removeUserToken( token ) ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch ( InvalidClaimException | InvalidUserCredentialsException e ) {

            return Response.status( HttpStatus.BAD_REQUEST_400 ).build();

        }
    }
}
