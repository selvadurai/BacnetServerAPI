package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojo.User;

public class UserDAO {

    private static final String URL = SQLiteDB.url; // Replace with your actual database URL

    // Establish a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Insert or update a User record
    public void insertOrUpdateUser(User user) {
        if (recordExists()) {
            updateUser(user);
        } else {
            insertUser(user);
        }
    }

    // Check if a record already exists
    public boolean recordExists() {
        String sql = "SELECT COUNT(*) FROM User";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 // Update an existing User record
    private void updateUser(User user) {
        String sql = "UPDATE User SET password = ? , username = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Insert a new User record
    private void insertUser(User user) {
        String sql = "INSERT INTO User (username, password) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve the User record
    public User getUser() {
        String sql = "SELECT * FROM User LIMIT 1";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new User(username, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return null if no user record is found
        return null;
    }

    // Other methods for updating, deleting, and specific queries can be added based on your requirements
}
