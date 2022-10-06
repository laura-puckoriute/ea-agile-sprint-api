package org.kainos.ea.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtToken {

    private static final String priv = System.getenv( "PRIVATE_KEY" );

    private static final SecretKey key = Keys.hmacShaKeyFor( priv.getBytes() );

    public static String generateToken( String email ) {

        String jws = Jwts.builder()
                .setSubject( email )
                .setIssuedAt( new Date() )
                .signWith( key )
                .compact();

        return jws;
    }

    public static boolean verifyToken( String token ) throws InvalidClaimException {

        Jws<Claims> jws;

        try {

            jws = Jwts.parserBuilder()
                    .setSigningKey( key )
                    .build()
                    .parseClaimsJws( token );

            return true;

        } catch ( InvalidClaimException e ) {

            return false;
        }
    }
}
