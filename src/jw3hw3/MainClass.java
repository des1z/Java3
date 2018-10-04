package jw3hw3;

public class MainClass {
    public static void main(String[] args) {
        Controller controller = new ClientController();
        ClientUi clientUi = new Client(controller);
        controller.showUi(clientUi);
    }
}