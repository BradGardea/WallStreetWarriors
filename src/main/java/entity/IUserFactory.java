package entity;

public interface IUserFactory {
    /** Requires: password is valid. */
    User create(String name, String password);
}
