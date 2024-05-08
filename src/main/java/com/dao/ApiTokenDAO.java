package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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




public class ApiTokenDAO {

	    private static final String URL = SQLiteDB.url; // Replace with your actual database URL
	    private static Connection connection;

	    private static Connection getConnection() throws SQLException {
	        if (connection == null || connection.isClosed()) {
	            connection = DriverManager.getConnection(URL);
	        }
	        return connection;
	    }

	    public boolean updateOrCreateToken(String token) {
	        try (Connection connection = getConnection()) {
	            // Check if the table exists
	            checkTableExistence(connection);

	            // Check if a record already exists
	            boolean recordExists = recordExists(connection);

	            if (recordExists) {
	                // If the record exists, update the existing token
	                return updateToken(connection, token);
	            } else {
	                // If the record doesn't exist, insert a new record with the token
	                return insertToken(connection, token);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions according to your application's needs
	            return false;
	        }
	    }

	    public String getToken() {
	        try (Connection connection = getConnection()) {
	            // Check if the table exists
	            checkTableExistence(connection);

	            String getTokenQuery = "SELECT token FROM ApiToken LIMIT 1";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(getTokenQuery);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getString("token");
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions according to your application's needs
	        }

	        // Return null if no token is found
	        return null;
	    }

	    private void checkTableExistence(Connection connection) throws SQLException {
	        String createTableQuery = "CREATE TABLE IF NOT EXISTS ApiToken (token TEXT NOT NULL UNIQUE);";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
	            preparedStatement.executeUpdate();
	        }
	    }

	    private boolean recordExists(Connection connection) throws SQLException {
	        String checkRecordQuery = "SELECT COUNT(*) FROM ApiToken";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(checkRecordQuery);
	             ResultSet resultSet = preparedStatement.executeQuery()) {
	            return resultSet.getInt(1) > 0;
	        }
	    }

	    private boolean updateToken(Connection connection, String token) throws SQLException {
	        String updateTokenQuery = "UPDATE ApiToken SET token = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateTokenQuery)) {
	            preparedStatement.setString(1, token);
	            return preparedStatement.executeUpdate() > 0;
	        }
	    }

	    private boolean insertToken(Connection connection, String token) throws SQLException {
	        String insertTokenQuery = "INSERT INTO ApiToken (token) VALUES (?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTokenQuery)) {
	            preparedStatement.setString(1, token);
	            return preparedStatement.executeUpdate() > 0;
	        }
	    }


}

    

    // You can add more methods for other operations if needed

