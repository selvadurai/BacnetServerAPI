package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pojo.Device;

public class DeviceDAO {
    private String url = SQLiteDB.url;

    
    
    public void addDevice(Device device) {
        String sql = "INSERT INTO Device (dev_temp_id, name, active) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, device.getDevTempId());
            statement.setString(2, device.getName());
            statement.setInt(3, device.getActive());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    

    // Method to update a device entry
    public void updateDevice(Device device) {
        String updateQuery = "UPDATE Device SET dev_temp_id = ?, Name = ?, Active = ? WHERE dev_id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, device.getDevTempId());
            preparedStatement.setString(2, device.getName());
            preparedStatement.setInt(3, device.getActive());
            preparedStatement.setInt(4, device.getDevId());

            preparedStatement.executeUpdate();
            System.out.println("Device updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a device entry by ID
    public void deleteDeviceById(int deviceId) {
        String deleteQuery = "DELETE FROM Device WHERE dev_id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, deviceId);

            preparedStatement.executeUpdate();
            System.out.println("Device deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteDeviceByDevTempId(int tempId) {
        String deleteQuery = "DELETE FROM Device WHERE dev_temp_id = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, tempId);

            preparedStatement.executeUpdate();
            System.out.println("Device deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a device entry by ID
    public Device getDeviceById(int deviceId) {
        String selectByIdQuery = "SELECT * FROM Device WHERE dev_id = ?";
        Device device = new Device();

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(selectByIdQuery)) {
            preparedStatement.setInt(1, deviceId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    device.setDevId(resultSet.getInt("dev_id"));
                    device.setDevTempId(resultSet.getInt("dev_temp_id"));
                    device.setName(resultSet.getString("Name"));
                    device.setActive(resultSet.getInt("Active"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return device;
    }


    public List<Device> getAllDevices() {
        String selectAllQuery = "SELECT * FROM Device";
        List<Device> devices = new CopyOnWriteArrayList<>();

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllQuery)) {
        	

            while (resultSet.next()) {
                Device device = new Device();
            	System.out.println(resultSet.getInt("dev_id"));

                device.setDevId(resultSet.getInt("dev_id"));
                device.setDevTempId(resultSet.getInt("dev_temp_id"));
                device.setName(resultSet.getString("Name"));
                device.setActive(resultSet.getInt("Active"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return devices;
    }




}
