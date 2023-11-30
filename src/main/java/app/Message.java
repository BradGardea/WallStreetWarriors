package app;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import entity.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Sample class for testing firestore functionality
 */
@IgnoreExtraProperties
class Message {
    private String author;
    private String text;

    public Map<String, Map<String, Object>> getDict() {
        return dict;
    }

    public void setDict(Map<String, Map<String, Object>> dict) {
        this.dict = dict;
    }

    private Map<String, Map<String, Object>> dict;

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    private ArrayList<String> list;


    @Exclude private User user;

    private Message() {}

    public Message(String author, String text, Map<String, Map<String, Object>> dict, ArrayList<String> list) {
        this.author = author;
        this.text = text;
        this.dict = dict;
        this.list = list;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
        System.out.println("the setter was called");
        User user1 = new User("dhruv", "1000", new ArrayList<>(), new ArrayList<>());
        this.user = user1;
        System.out.println(this.user.getUsername());
    }

    public String getText() {
        return text;
    }
}
