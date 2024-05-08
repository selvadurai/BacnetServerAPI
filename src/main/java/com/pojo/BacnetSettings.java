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



public class BacnetSettings {

    private String ipAddress;
    private String submask;
    private int networkPrefix;
    private int instanceId;
    private int bacnetPort;
    private String bacnetServerName;

    // Constructors
    public BacnetSettings() {
        // Default constructor
    }

    public BacnetSettings(String ipAddress, String submask, int networkPrefix, int instanceId, int bacnetPort, String bacnetServerName) {
        this.ipAddress = ipAddress;
        this.submask = submask;
        this.networkPrefix = networkPrefix;
        this.instanceId = instanceId;
        this.bacnetPort = bacnetPort;
        this.bacnetServerName = bacnetServerName;
    }

    // Getter and Setter methods
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSubmask() {
        return submask;
    }

    public void setSubmask(String submask) {
        this.submask = submask;
    }

    public int getNetworkPrefix() {
        return networkPrefix;
    }

    public void setNetworkPrefix(int networkPrefix) {
        this.networkPrefix = networkPrefix;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public int getBacnetPort() {
        return bacnetPort;
    }

    public void setBacnetPort(int bacnetPort) {
        this.bacnetPort = bacnetPort;
    }

    public String getBacnetServerName() {
        return bacnetServerName;
    }

    public void setBacnetServerName(String bacnetServerName) {
        this.bacnetServerName = bacnetServerName;
    }

    @Override
    public String toString() {
        return "BacnetSettings{" +
                "ipAddress='" + ipAddress + '\'' +
                ", submask='" + submask + '\'' +
                ", networkPrefix=" + networkPrefix +
                ", instanceId=" + instanceId +
                ", bacnetPort=" + bacnetPort +
                ", bacnetServerName='" + bacnetServerName + '\'' +
                '}';
    }
}
