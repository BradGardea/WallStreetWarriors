package use_case.enrolled;

import entity.StockContest;

/**
 * Interface for DAO to have a get method to retrieve a StockContest object by its UUID.
 *
 * This is to make it easier to retrieve specific contests, i.e. ones the logged-in user is enrolled in.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public interface EnrolledUserDataAccessInterface {
    // TODO Add more necessary methods here

    StockContest get(String uuid);
}
