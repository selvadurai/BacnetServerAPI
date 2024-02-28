package com.pojo;

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

