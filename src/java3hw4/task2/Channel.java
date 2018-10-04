package java3hw4.task2;

public interface Channel {
    void sendMessage(Message message);

    void sendMessage(String message);

    Message getMessage();

    String getStringMessage();

    boolean hasNextLine();
}