package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AvailableContest extends StockContest{
    public AvailableContest(String title, String description, ArrayList<String> members, String industry, LocalDateTime startTime, LocalDateTime endTime) {
        super(title, description, members, industry, startTime, endTime);
    }
}
