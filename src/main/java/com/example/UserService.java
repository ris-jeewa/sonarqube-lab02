package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    private final ConnectionProvider connectionProvider;

    public UserService(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void findUser(String username) throws SQLException {
        String sql = "SELECT id, name FROM users WHERE name = ?";

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = connectionProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}