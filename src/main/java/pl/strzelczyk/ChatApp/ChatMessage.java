package pl.strzelczyk.ChatApp;

public class ChatMessage {

    private String value;
    private String user;
    private String userColor;

    public String getUserColor() {
        return userColor;
    }

    public void setUserColor(String userColor) {
        this.userColor = userColor;
    }

    public ChatMessage(String value) {
        this.value = value;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ChatMessage() {
    }

    public ChatMessage(String value, String user) {
        this.value = value;
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
