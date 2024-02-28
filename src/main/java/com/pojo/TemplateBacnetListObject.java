package com.pojo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TemplateBacnetListObject {

    private DeviceTemplate deviceTemplate;
    private List<BacnetObject> bacnetObjectList = new CopyOnWriteArrayList<>();

    // Constructors
    public TemplateBacnetListObject(DeviceTemplate deviceTemplate, List<BacnetObject> bacnetObjectList) {
        this.deviceTemplate = deviceTemplate;
        this.bacnetObjectList.addAll(bacnetObjectList);
    }

    // Getters and Setters
    public DeviceTemplate getDeviceTemplate() {
        return deviceTemplate;
    }

    public void setDeviceTemplate(DeviceTemplate deviceTemplate) {
        this.deviceTemplate = deviceTemplate;
    }

    public List<BacnetObject> getBacnetObjectList() {
        return bacnetObjectList;
    }

    public void setBacnetObjectList(List<BacnetObject> bacnetObjectList) {
        this.bacnetObjectList = bacnetObjectList;
    }
    
    // Updated Getter and Setter Names
    public DeviceTemplate retrieveDeviceTemplate() {
        return deviceTemplate;
    }

    public void updateDeviceTemplate(DeviceTemplate deviceTemplate) {
        this.deviceTemplate = deviceTemplate;
    }

    public List<BacnetObject> fetchBacnetObjectList() {
        return bacnetObjectList;
    }

    public void modifyBacnetObjectList(List<BacnetObject> bacnetObjectList) {
        this.bacnetObjectList = bacnetObjectList;
    }
}

