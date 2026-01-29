package com.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}