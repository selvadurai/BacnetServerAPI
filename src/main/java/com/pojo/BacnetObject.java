package com.pojo;

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

