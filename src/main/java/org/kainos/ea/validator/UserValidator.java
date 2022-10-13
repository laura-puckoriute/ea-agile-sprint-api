package org.kainos.ea.validator;

import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.models.UserRequest;

public class UserValidator {
    String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{9,20}$"; //more than 8 chars one upper, one lower, one special
    public boolean isValidUser(UserRequest user) {
        if (!user.getEmail().matches(emailRegex) || !user.getPassword().matches(passwordRegex)) {
            return false;
        }
        if (user.getUserRoleID() != 1 && user.getUserRoleID() != 2) {
            throw new DataNotFoundException();
        }
        return true;
    }
}
