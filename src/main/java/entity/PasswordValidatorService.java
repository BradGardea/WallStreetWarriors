package entity;

import entity.PasswordValidator;

public class PasswordValidatorService implements PasswordValidator {
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }
}
