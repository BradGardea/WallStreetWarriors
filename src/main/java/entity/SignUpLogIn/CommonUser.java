package entity.SignUpLogIn;

import entity.User;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final String email;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }


}
