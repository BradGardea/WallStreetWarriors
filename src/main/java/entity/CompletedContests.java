package entity;

import FirebaseDataAccess.IFirebaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CompletedContests extends Contest{
    public CompletedContests(String contestId, String title, String description, ArrayList<String> members, String industry, LocalDateTime startTime, LocalDateTime endTime) {
        super(contestId, title, description, members, industry, startTime, endTime);
    }
}
