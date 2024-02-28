package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pojo.DeviceTemplate;

public class DeviceTempDAO {
   
	private String url= SQLiteDB.url;	
    public ConcurrentHashMap<String, Integer> nameGetsId = new ConcurrentHashMap<>();
    private List<DeviceTemplate> deviceTemplateList = new CopyOnWriteArrayList<>();



	
	// Establish a database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }
    
    
    
    
    // Insert a new person
    public void addDeviceTemplate(String name,String jsonPayload) {
        String sql = "INSERT INTO Device_Template (name,payload) VALUES (?, ?)";
        try (Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,name);
            statement.setString(2, jsonPayload);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void updateDeviceTemplate(int id, String name, String jsonPayload) {
        String sql = "UPDATE Device_Template SET name = ?, payload = ? WHERE dev_temp_id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, jsonPayload);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    
    //Delete Object 
    public void deleteDeviceTemplate(int id) {
        String sql = "DELETE FROM Device_Template WHERE dev_temp_id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ConcurrentHashMap<String, Integer> getNameIDMap() {
        /*
    	if (!deviceTemplateMap.isEmpty()) {
            // If the ConcurrentHashMap is not empty, return it directly
            return new ConcurrentHashMap<>(deviceTemplateMap);
        }
        */

        String sql = "SELECT * FROM Device_Template";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("dev_temp_id");
                String name = resultSet.getString("name");
                String payload = resultSet.getString("payload");

                // Create a DeviceTemplate object
                //DeviceTemplate deviceTemplate = new DeviceTemplate();
                //deviceTemplate.setId(id);
               // deviceTemplate.setName(name);
                //deviceTemplate.setPayload(payload);

                // Put the object in the ConcurrentHashMap
                nameGetsId.put(name, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ConcurrentHashMap<>(nameGetsId);
    }
    

    // Existing methods...

    public List<DeviceTemplate> getAllDeviceTemplates() {
        /*
        if (!deviceTemplateList.isEmpty()) {
            // If the list is not empty, return a copy of it directly
            return new ArrayList<>(deviceTemplateList);
        }
        */

        String sql = "SELECT * FROM Device_Template";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("dev_temp_id");
                String name = resultSet.getString("name");
                String payload = resultSet.getString("payload");

                // Create a DeviceTemplate object
                DeviceTemplate deviceTemplate = new DeviceTemplate();
                deviceTemplate.setId(id);
                deviceTemplate.setName(name);
                deviceTemplate.setPayload(payload);

                // Add the object to the list
                deviceTemplateList.add(deviceTemplate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new CopyOnWriteArrayList<>(deviceTemplateList);
    }
    
}
