package com.dao;


/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * Author: Jonathan Kevin Selvadurai
 * Date: May 7 2024
 */



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
