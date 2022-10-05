package org.kainos.ea.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtTokenGenerator {

    public static String generateToken( String secret, String email ) {

        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println(Encoders.BASE64.encode(key.getEncoded()));

        String jws = Jwts.builder()
                .setSubject( email )
                .signWith( key )
                .compact();

        return jws;
    }
}
