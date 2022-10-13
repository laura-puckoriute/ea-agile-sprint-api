package org.kainos.ea.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.models.UserRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {

    UserValidator userValidator = new UserValidator();

    @Test
    public void isValidUser_shouldReturnTrue_whenValidUser() {
        UserRequest userRequest = new UserRequest(
                "matthew.knox@email.com",
                "TestPassword!",
                1
        );

        assertTrue(userValidator.isValidUser(userRequest));
    }

    @Test
    public void isValidUser_shouldReturnFalse_whenUserEmailIsInvalid() {
        UserRequest userRequest = new UserRequest(
                "matthew.knoxemail.com",
                "TestPassword!",
                1
        );

        assertFalse(userValidator.isValidUser(userRequest));
    }

    @Test
    public void isValidUser_shouldReturnFalse_whenUserPasswordIsInvalid() {
        UserRequest userRequest = new UserRequest(
                "matthew.knox@email.com",
                "a",
                1
        );

        assertFalse(userValidator.isValidUser(userRequest));
    }
}
