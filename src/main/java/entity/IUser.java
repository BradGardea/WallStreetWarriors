package entity;

import java.util.HashMap;
import java.util.UUID;

public interface IUser {

    String getUserName();

    String getPassword();

    String getUUID();

    HashMap<String, Contest> getContests();
}
