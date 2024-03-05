package com.pojo;

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
