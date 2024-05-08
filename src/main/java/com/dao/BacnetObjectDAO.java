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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pojo.BacnetObject;

public class BacnetObjectDAO {

    private String url = SQLiteDB.url;
    private List<BacnetObject> bacnetObjectList = new CopyOnWriteArrayList<>();

    // Establish a database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    // Insert a new BacnetObject
    public void addBacnetObject(int devTempId, String bacObjType, String name) {
        String sql = "INSERT INTO BacnetObject (DevTempId, BacObjType,Name) VALUES (?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, devTempId);
            statement.setString(2, bacObjType);
            statement.setString(3, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void deleteBacnetObject(int bacnetObjId) {
        String sql = "DELETE FROM BacnetObject WHERE BacnetObjId = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bacnetObjId);
            statement.executeUpdate();
            // Remove the deleted BacnetObject from the thread-safe list
            bacnetObjectList.removeIf(obj -> obj.getBacnetObjId() == bacnetObjId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteBacnetObjectTemplateId(int templateId) {
        String sql = "DELETE FROM BacnetObject WHERE devTempId = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, templateId);
            statement.executeUpdate();
            // Remove the deleted BacnetObject from the thread-safe list
            bacnetObjectList.removeIf(obj -> obj.getDevTempId() == templateId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    // Update a BacnetObject by BacnetObjId (primary key)
    public void updateBacnetObject(int bacnetObjId, String bacObjType, int instanceNum, int reqDefault, String dataType, String name) {
        String sql = "UPDATE BacnetObject SET BacObjType = ?, Name = ? WHERE BacnetObjId = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bacObjType);
            statement.setString(2, name);
            statement.setInt(3, bacnetObjId);
            statement.executeUpdate();
            // Update the thread-safe list after successful update
            for (BacnetObject obj : bacnetObjectList) {
                if (obj.getBacnetObjId() == bacnetObjId) {
                    obj.setBacObjType(bacObjType);
                    obj.setName(name);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<BacnetObject> getAllBacnetObjects() {
        /*
        if (!bacnetObjectList.isEmpty()) {
            // If the list is not empty, return a copy of it directly
            return new CopyOnWriteArrayList<>(bacnetObjectList);
        }
        */

        String sql = "SELECT * FROM BacnetObject";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int bacnetObjId = resultSet.getInt("BacnetObjId");
                int devTempId = resultSet.getInt("DevTempId");
                String bacObjType = resultSet.getString("BacObjType");
                String name = resultSet.getString("name");


                // Create a BacnetObject
                BacnetObject bacnetObject = new BacnetObject(bacnetObjId, devTempId, bacObjType, name);

                // Add the object to the list
                bacnetObjectList.add(bacnetObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new CopyOnWriteArrayList<>(bacnetObjectList);
    }


  

    // Other methods for updating, deleting, or querying specific BacnetObjects
    // ...

}

