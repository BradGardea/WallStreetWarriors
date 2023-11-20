package entity.SignUpLogIn;

import entity.User;
import entity.SignUpLogIn.UserFactory;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public User create(String name, String password, String email) {
        return new CommonUser(name, password, email);
    }
}
