package app;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import entity.User;

import java.util.UUID;

/**
 * Sample class for testing firestore functionality
 */
@IgnoreExtraProperties
class Message {
    private String author;
    private String text;


    @Exclude private User user;

    private Message() {}

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
        System.out.println("the setter was called");
        User user1 = new User(UUID.randomUUID().toString(), "dhruv", "1000", null, null);
        this.user = user1;
        System.out.println(this.user.getUserName());
    }

    public String getText() {
        return text;
    }
}
