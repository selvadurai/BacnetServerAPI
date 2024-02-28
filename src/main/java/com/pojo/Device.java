package com.pojo;

public class Device {
    private int devId;
    private int devTempId;
    private String name;
    private int active;

    // Constructors
    public Device() {
    }

    public Device(int devId, int devTempId, String name, int active) {
        this.devId = devId;
        this.devTempId = devTempId;
        this.name = name;
        this.active = active;
    }

    // Getters and setters for each field
    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getDevTempId() {
        return devTempId;
    }

    public void setDevTempId(int devTempId) {
        this.devTempId = devTempId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    // toString method for better logging or debugging
    @Override
    public String toString() {
        return "Device{" +
                "devId=" + devId +
                ", devTempId=" + devTempId +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
