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


public class BroadcastBacnet {
    private int instanceNum;
    private String objectName;
    private String objectType;
    private String deviceName;
    private String keyName;

    // Default constructor
    public BroadcastBacnet() {
    }

    // Parameterized constructor
    public BroadcastBacnet(int instanceNum, String objectName, String objectType, String deviceName, String keyName) {
        this.instanceNum = instanceNum;
        this.objectName = objectName;
        this.objectType = objectType;
        this.deviceName = deviceName;
        this.keyName = keyName;
    }

    // Getters and setters
    public int getInstanceNum() {
        return instanceNum;
    }

    public void setInstanceNum(int instanceNum) {
        this.instanceNum = instanceNum;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "BroadcastBacnet{" +
                "instanceNum=" + instanceNum +
                ", objectName='" + objectName + '\'' +
                ", objectType='" + objectType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", keyName='" + keyName + '\'' +
                '}';
    }
}

