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
import java.util.ArrayList;
import java.util.List;

import com.pojo.BacnetSettings;

public class BacnetSettingsDAO {

    private static final String URL = SQLiteDB.url;; // Change the database URL accordingly

    // Establish a database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Insert or update a BacnetSettings record
    public void addOrUpdateBacnetSettings(BacnetSettings bacnetSettings) {
        if (recordExists()) {
            updateBacnetSettings(bacnetSettings);
        } else {
            addBacnetSettings(bacnetSettings);
        }
    }

    // Check if a record already exists
    public boolean recordExists() {
        String sql = "SELECT COUNT(*) FROM Bacnet_Settings";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing BacnetSettings record
    private void updateBacnetSettings(BacnetSettings bacnetSettings) {
        String sql = "UPDATE Bacnet_Settings SET ipaddress = ?, submask = ?, networkPrefix = ?, instanceId = ?, bacnetPort = ?, bacnetServerName = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bacnetSettings.getIpAddress());
            statement.setString(2, bacnetSettings.getSubmask());
            statement.setInt(3, bacnetSettings.getNetworkPrefix());
            statement.setInt(4, bacnetSettings.getInstanceId());
            statement.setInt(5, bacnetSettings.getBacnetPort());
            statement.setString(6, bacnetSettings.getBacnetServerName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a new BacnetSettings record
    private void addBacnetSettings(BacnetSettings bacnetSettings) {
        String sql = "INSERT INTO Bacnet_Settings (ipaddress, submask, networkPrefix, instanceId, bacnetPort, bacnetServerName) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bacnetSettings.getIpAddress());
            statement.setString(2, bacnetSettings.getSubmask());
            statement.setInt(3, bacnetSettings.getNetworkPrefix());
            statement.setInt(4, bacnetSettings.getInstanceId());
            statement.setInt(5, bacnetSettings.getBacnetPort());
            statement.setString(6, bacnetSettings.getBacnetServerName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    // Retrieve all BacnetSettings records
    public ResultSet getAllBacnetSettings() {
        String sql = "SELECT * FROM Bacnet_Settings";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    
    public List<BacnetSettings> getAllBacnetSettings() {
        List<BacnetSettings> bacnetSettingsList = new ArrayList<>();
        String sql = "SELECT * FROM Bacnet_Settings";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BacnetSettings bacnetSettings = new BacnetSettings();
                bacnetSettings.setIpAddress(resultSet.getString("ipaddress"));
                bacnetSettings.setSubmask(resultSet.getString("submask"));
                bacnetSettings.setNetworkPrefix(resultSet.getInt("networkPrefix"));
                bacnetSettings.setInstanceId(resultSet.getInt("instanceId"));
                bacnetSettings.setBacnetPort(resultSet.getInt("bacnetPort"));
                bacnetSettings.setBacnetServerName(resultSet.getString("bacnetServerName"));
                
                bacnetSettingsList.add(bacnetSettings);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging instead
        }

        return bacnetSettingsList;
    }

    // Other methods for updating, deleting, and specific queries can be added based on your requirements
}
