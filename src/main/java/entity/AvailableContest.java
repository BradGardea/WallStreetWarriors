package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class AvailableContest extends StockContest{
    public AvailableContest(String contestId, String title, String description, ArrayList<String> members, String industry, LocalDateTime startTime, LocalDateTime endTime) {
        super(contestId, title, description, members, industry, startTime, endTime);
    }
}
