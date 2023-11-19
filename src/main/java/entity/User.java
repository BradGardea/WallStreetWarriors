package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User implements IUser{

    private UUID id;
    private String username;
    private String password;
    private HashMap<String, Contest> contests;

    public User(UUID id, String username, String password, HashMap<String, Contest> contests){
        this.id = id;
        this.username = username;
        this.password = password;
        this.contests = contests;
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUUID() {
        return this.id.toString();
    }

    @Override
    public HashMap<String, Contest> getContests() {
        return this.contests;
    }
}
