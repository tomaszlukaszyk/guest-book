package com.codecool.guest.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GuestBookDAOFactory {

    final String URL = "jdbc:postgresql://localhost:5432/guestbook";
    final String USER = "guestbookadmin";
    final String PASS = "backto90";

    private static Connection connection = null;

    public GuestBookDAOFactory() {
        if (connection == null) {
            createConnection();
        }
    }

    public GuestBookDAO getDao() {
        return new DbGuestBookDAO(connection);
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection(this.URL, this.USER, this.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
