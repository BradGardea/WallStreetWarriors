package app;

/**
 * Sample class for testing firestore functionality
 */
class Message {
    private String author;
    private String text;

    private Message() {}

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}
