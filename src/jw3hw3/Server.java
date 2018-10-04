package jw3hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private AuthService authService;
    private Map<String, ClientHandler> clients = new HashMap<>();
    public static BufferedWriter bw = null;
    public static FileWriter fw = null;
    public Server(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8890);
            System.out.println("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        }
    }

    public void close() {
        try {
            serverSocket.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        AuthService baseAuthService = (AuthService) new BaseAuthService();
        Server server = new Server(baseAuthService);
        try {
            fw = new FileWriter("LocalHistory.txt");
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
    }
    private void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.put(clientHandler.getNick(), clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(msg);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        return clients.containsKey(nick);
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.put(clientHandler.getNick(), clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler.getNick());
    }

    public void sendPersonalMessage(String senderNick, String recipientNick, String pMsg) {
        ClientHandler nickSender = clients.get(senderNick);
        if (nickSender != null) nickSender.sendMessage(pMsg);
        if (clients.containsKey(recipientNick)) clients.get(recipientNick).sendMessage(pMsg);
    }
    public void writeLocalHistory(String message) {
        String str = message + System.lineSeparator();
        try {
            bw.write(str);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendLocalHistory(String nick) {
        try {
            List<String> history = new ArrayList<>();
            RandomAccessFile raf = new RandomAccessFile("LocalHistory.txt", "r");
            String str;
            while ((str = raf.readLine()) != null) {
                history.add(str);
            }
            if (history.size() > 100) {
                for (int i = history.size() - 100; i < history.size(); i++) {
                    clients.get(nick).sendMessage(history.get(i));
                }
            } else {
                for (int i = 0; i < history.size(); i++) {
                    clients.get(nick).sendMessage(history.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
