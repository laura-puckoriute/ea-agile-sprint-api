package org.kainos.ea.exception;

import io.dropwizard.auth.AuthenticationException;

public class InvalidUserCredentialsException extends AuthenticationException {

    public InvalidUserCredentialsException( String message ) { super(message); }
}
