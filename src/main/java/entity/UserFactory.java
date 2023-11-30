package entity;

import java.util.ArrayList;

public class UserFactory implements IUserFactory{
    /**
     * Requires: password is valid.
     * @param username
     * @param password
     */

    @Override
    public User create(String username, String password) {

        ArrayList<String> empty1 = new ArrayList<>();
        ArrayList<String> empty2 = new ArrayList<>();
         //TODO fix id
        return new User("1", username, password, empty1, empty2);
    }

}
