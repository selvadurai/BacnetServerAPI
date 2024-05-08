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

