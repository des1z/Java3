package java3hw4.task2;

public interface Controller {

    void sendMessage(String msg);

    void closeConnection();

    void showUi(ClientUi clientUi);

    void sendMessage(AuthMessage authMessage);
}