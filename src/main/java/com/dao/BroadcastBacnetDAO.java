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

import com.pojo.BroadcastBacnet;

public class BroadcastBacnetDAO {

    private  final String URL = SQLiteDB.url; // Replace with your actual database URL
    
    
    public BroadcastBacnetDAO() {
    	
    }

    // Insert a new BroadcastBacnet record into the database
    public void insertBroadcastBacnet(int instanceNum, String objectName, String objectType, String deviceName, String keyName) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO BroadcastBacnet (instanceNum, objectName, objectType, deviceName, keyName) VALUES (?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, instanceNum);
            preparedStatement.setString(2, objectName);
            preparedStatement.setString(3, objectType);
            preparedStatement.setString(4, deviceName);
            preparedStatement.setString(5, keyName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions according to your application's needs
        }
    }

    // Delete a BroadcastBacnet record from the database based on instanceNum
    public void deleteBroadcastBacnet(int instanceNum) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM BroadcastBacnet WHERE instanceNum = ?"
             )) {
            preparedStatement.setInt(1, instanceNum);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions according to your application's needs
        }
    }
    
    // Delete a BroadcastBacnet record from the database based on instanceNum
    public void deleteBroadcastBacnetName(String deviceName) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM BroadcastBacnet WHERE deviceName = ?"
             )) {
            preparedStatement.setString(1, deviceName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions according to your application's needs
        }
    }

    // Insert a new BroadcastBacnet record into the database
    public void insertBroadcastBacnet(BroadcastBacnet broadcastBacnet) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO BroadcastBacnet (instanceNum, objectName, objectType, deviceName, keyName) VALUES (?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setInt(1, broadcastBacnet.getInstanceNum());
            preparedStatement.setString(2, broadcastBacnet.getObjectName());
            preparedStatement.setString(3, broadcastBacnet.getObjectType());
            preparedStatement.setString(4, broadcastBacnet.getDeviceName());
            preparedStatement.setString(5, broadcastBacnet.getKeyName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions according to your application's needs
        }
    }

    // Retrieve all BroadcastBacnet records from the database
    public List<BroadcastBacnet> getAllBroadcastBacnets() {
        List<BroadcastBacnet> broadcastBacnets = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM BroadcastBacnet"
             )) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BroadcastBacnet broadcastBacnet = new BroadcastBacnet();
                broadcastBacnet.setInstanceNum(resultSet.getInt("instanceNum"));
                broadcastBacnet.setObjectName(resultSet.getString("objectName"));
                broadcastBacnet.setObjectType(resultSet.getString("objectType"));
                broadcastBacnet.setDeviceName(resultSet.getString("deviceName"));
                broadcastBacnet.setKeyName(resultSet.getString("keyName"));

                broadcastBacnets.add(broadcastBacnet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions according to your application's needs
        }
        return broadcastBacnets;
    }

    // You can add more methods for updating, deleting, or querying specific records
}
