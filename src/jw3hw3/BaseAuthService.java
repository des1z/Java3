package jw3hw3;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseAuthService implements AuthService {
    private Map<String, User> users = new HashMap<>();
    private static final String DB_PATH = "D:/Java3/user.db";
    private static Connection conn;
    private static Statement stmt;

    public BaseAuthService() {
        try {
            connect();
            ResultSet rs = stmt.executeQuery("SELECT nickname,login, password FROM users;");
            while(rs.next()){
                String nickname = rs.getString(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                users.put(nickname, new User(login,password,nickname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws SQLException{
        conn = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        stmt = conn.createStatement();
    }

    @Override
    public String authByLoginAndPassword(String login, String pass) {
        for (User user : users.values()) {
            if (login.equals(user.getLogin()) && pass.equals(user.getPassword()) && user.isActive()) {
                return user.getNickname();
            }
        }
        return null;
    }

    @Override
    public User createOrActivateUser(String login, String password, String nick) {
        User user = new User(login, password, nick);
        if (users.containsKey(nick)) {
            user.setActive(true);
            System.out.println("jw3hw3.User with nick" + nick + "already exist");
        } else users.put(nick, user);
        return user;
    }

    @Override
    public boolean deactivateUser(String nick) {
        User user = users.get(nick);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }
}