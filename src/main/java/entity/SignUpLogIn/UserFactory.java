package entity.SignUpLogIn;

import entity.User;

import java.time.LocalDateTime;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password, String email);
}
