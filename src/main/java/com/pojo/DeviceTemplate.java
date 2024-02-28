package com.pojo;

public class DeviceTemplate {
    private int id;
    private String name;
    private String payload;

    // Constructors
    public DeviceTemplate() {
        // Default constructor
    }

    public DeviceTemplate(int id, String name, String payload) {
        this.id = id;
        this.name = name;
        this.payload = payload;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "DeviceTemplate{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", payload='" + payload + '\'' +
               '}';
    }
}
