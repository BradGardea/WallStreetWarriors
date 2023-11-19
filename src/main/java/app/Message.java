package app;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import entity.User;

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
    }

    public String getText() {
        return text;
    }
}
