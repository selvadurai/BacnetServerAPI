package com.pojo;

public class DeviceJsonSend {
    private String templateName;
    private String deviceName;
    private int id;

    // Default constructor
    public DeviceJsonSend() {
    }

    // Parameterized constructor
    public DeviceJsonSend(String templateName, String deviceName, int id) {
        this.templateName = templateName;
        this.deviceName = deviceName;
        this.id = id;
    }

    // Getters and setters
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceJsonSend{" +
                "templateName='" + templateName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", id=" + id +
                '}';
    }
}
