package com.pojo;

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



public class BacnetObject {

    private int bacnetObjId;
    private int devTempId;
    private String bacObjType;
    //private int instanceNum;
    //private int reqDefault;
    //private String dataType;
    private String name;

    // Constructors, getters, and setters

    // Default constructor
    public BacnetObject() {
    }

    // Parameterized constructor
    public BacnetObject(int bacnetObjId, int devTempId, String bacObjType, String name) {
        this.bacnetObjId = bacnetObjId;
        this.devTempId = devTempId;
        this.bacObjType = bacObjType;
        this.name = name;
    }

    // Getters and setters for each field
    public int getBacnetObjId() {
        return bacnetObjId;
    }

    public void setBacnetObjId(int bacnetObjId) {
        this.bacnetObjId = bacnetObjId;
    }

    public int getDevTempId() {
        return devTempId;
    }

    public void setDevTempId(int devTempId) {
        this.devTempId = devTempId;
    }

    public String getBacObjType() {
        return bacObjType;
    }

    public void setBacObjType(String bacObjType) {
        this.bacObjType = bacObjType;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString method for better logging or debugging
    @Override
    public String toString() {
        return "BacnetObject{" +
                "bacnetObjId=" + bacnetObjId +
                ", devTempId=" + devTempId +
                ", bacObjType='" + bacObjType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

