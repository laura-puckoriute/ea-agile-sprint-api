package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.APIApplication;
import org.kainos.ea.APIConfiguration;
import org.kainos.ea.models.User;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthenticationServiceIntegrationTest {

    private final String API_URL = System.getenv( "API_URL" );

    private final String SIGNIN_ENDPOINT = "/auth/signin";

    private final String SIGNOUT_ENDPOINT = "/auth/signout";

    static final DropwizardAppExtension<APIConfiguration> APP = new DropwizardAppExtension<>(
            APIApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void postAuthenticateUser_shouldReturnToken() {

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!" );

        String response = APP.client().target(API_URL + SIGNIN_ENDPOINT )
                .request()
                .post( Entity.entity( user, MediaType.APPLICATION_JSON_TYPE ) )
                .readEntity( String.class );

        // eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIn0 is the subject email encoded in base64

        Assertions.assertEquals( response.split("\\.")[1], "eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIn0");
    }

    @Test
    void postAuthenticateUser_withInvalidCredentials_shouldReturn401Error() {

        User user = new User( "testemail@email.com", "THISISNOTTHEPASSWORD");

        int response = APP.client().target(API_URL + SIGNIN_ENDPOINT )
                .request()
                .post( Entity.entity( user, MediaType.APPLICATION_JSON_TYPE ) )
                .getStatus();

        Assertions.assertEquals( HttpStatus.UNAUTHORIZED_401, response );
    }

    @Test
    void postRemoveUserToken_shouldReturnLogoutSucccessful_whenTokenValidAndExists() {

        User user = new User( "testemail@email.com", "THISIS@PASSWORD!" );

        String token = APP.client().target(API_URL + SIGNIN_ENDPOINT )
                .request()
                .post( Entity.entity( user, MediaType.APPLICATION_JSON_TYPE ) )
                .readEntity( String.class );

        String response = APP.client().target( API_URL + SIGNOUT_ENDPOINT )
                .request()
                .header( HttpHeaders.AUTHORIZATION, token )
                .post( null, String.class );

        Assertions.assertEquals( "logout successful", response );
    }

    @Test
    void postRemoveUserToken_shouldReturn400Error_whenTokenInvalid() {

        int response = APP.client().target( API_URL + SIGNOUT_ENDPOINT )
                .request()
                .header( HttpHeaders.AUTHORIZATION, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWxAZW1haWwuY29tIiwiaWF0IjoxNjY1MTQxMDg2fQ.oF2qiJc_brncM8Cex_XvTTVwIdcqVqWPCF93s0wq7NqhHNwS7UiUDOLd2CybYFupcbwEbC0X_oAJv3Aqqv8O3w" )
                .post( null, Response.class ).getStatus();

        Assertions.assertEquals( 400, response );
    }

    @Test
    void postRemoveUserToken_shouldReturn400Error_whenTokenFormatInvalid() {

        int response = APP.client().target( API_URL + SIGNOUT_ENDPOINT )
                .request()
                .header( HttpHeaders.AUTHORIZATION, "random string as token")
                .post( null, Response.class ).getStatus();

        Assertions.assertEquals( 400, response );
    }
}
