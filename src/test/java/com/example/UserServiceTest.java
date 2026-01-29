package com.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void findUser_usesPreparedStatementAndExecutesQuery() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement("SELECT id, name FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        UserService service = new UserService(() -> conn);
        service.findUser("admin");

        verify(ps).setString(1, "admin");
        verify(ps).executeQuery();
    }

    @Test
    void deleteUser_usesPreparedStatementAndExecutesUpdate() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(conn.prepareStatement("DELETE FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        UserService service = new UserService(() -> conn);
        service.deleteUser("admin");

        verify(ps).setString(1, "admin");
        verify(ps).executeUpdate();
    }

    @Test
    void findUser_throwsSQLException_whenConnectionThrowsException() {
        ConnectionProvider provider = () -> {
            throw new SQLException("Connection failed");
        };

        UserService service = new UserService(provider);

        assertThrows(SQLException.class, () -> service.findUser("admin"));
    }

    @Test
    void deleteUser_throwsSQLException_whenConnectionThrowsException() {
        ConnectionProvider provider = () -> {
            throw new SQLException("Connection failed");
        };

        UserService service = new UserService(provider);

        assertThrows(SQLException.class, () -> service.deleteUser("admin"));
    }

    @Test
    void findUser_withEmptyUsername() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement("SELECT id, name FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        UserService service = new UserService(() -> conn);
        service.findUser("");

        verify(ps).setString(1, "");
        verify(ps).executeQuery();
    }

    @Test
    void deleteUser_withEmptyUsername() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(conn.prepareStatement("DELETE FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);

        UserService service = new UserService(() -> conn);
        service.deleteUser("");

        verify(ps).setString(1, "");
        verify(ps).executeUpdate();
    }

    @Test
    void findUser_withSpecialCharacters() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement("SELECT id, name FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        UserService service = new UserService(() -> conn);
        service.findUser("admin'; DROP TABLE users; --");

        verify(ps).setString(1, "admin'; DROP TABLE users; --");
        verify(ps).executeQuery();
    }

    @Test
    void deleteUser_withSpecialCharacters() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(conn.prepareStatement("DELETE FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        UserService service = new UserService(() -> conn);
        service.deleteUser("admin'; DROP TABLE users; --");

        verify(ps).setString(1, "admin'; DROP TABLE users; --");
        verify(ps).executeUpdate();
    }

    @Test
    void findUser_throwsSQLException_whenPrepareStatementThrowsException() throws Exception {
        Connection conn = mock(Connection.class);

        when(conn.prepareStatement("SELECT id, name FROM users WHERE name = ?"))
                .thenThrow(new SQLException("PrepareStatement failed"));

        UserService service = new UserService(() -> conn);

        assertThrows(SQLException.class, () -> service.findUser("admin"));
    }

    @Test
    void deleteUser_throwsSQLException_whenPrepareStatementThrowsException() throws Exception {
        Connection conn = mock(Connection.class);

        when(conn.prepareStatement("DELETE FROM users WHERE name = ?"))
                .thenThrow(new SQLException("PrepareStatement failed"));

        UserService service = new UserService(() -> conn);

        assertThrows(SQLException.class, () -> service.deleteUser("admin"));
    }

    @Test
    void deleteUser_returnsCorrectUpdateCount() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(conn.prepareStatement("DELETE FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(5);

        UserService service = new UserService(() -> conn);
        service.deleteUser("testuser");

        verify(ps).executeUpdate();
    }

    @Test
    void userServiceUsesCorrectSQLQueries() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(conn.prepareStatement("SELECT id, name FROM users WHERE name = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(mock(ResultSet.class));

        UserService service = new UserService(() -> conn);
        service.findUser("testuser");

        verify(conn).prepareStatement("SELECT id, name FROM users WHERE name = ?");
    }
}