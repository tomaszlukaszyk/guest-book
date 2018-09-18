package com.codecool.guest.book;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbGuestBookDAO implements GuestBookDAO {

    private Connection connection;

    public DbGuestBookDAO(Connection connection) {
        this.connection = connection;
    }

    private Entry extractEntryFormResultSet(ResultSet resultSet) throws SQLException {
        Entry entry = new Entry();
        entry.setId(resultSet.getInt("id"));
        entry.setMessage(resultSet.getString("message"));
        entry.setName(resultSet.getString("name"));
        entry.setDate(resultSet.getObject("post_date", LocalDateTime.class));
        return entry;
    }

    @Override
    public List<Entry> getAllEntries() {
        List<Entry> entries = new ArrayList<>();
        String sql = "SELECT id, message, name, post_date FROM entries;";
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entries.add(extractEntryFormResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    public boolean addEntry(Entry entry) {
        String sql = "INSERT INTO entries (message, name, post_date) VALUES (?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entry.getMessage());
            statement.setString(2, entry.getName());
            statement.setObject(3, entry.getDate());
            int i = statement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
