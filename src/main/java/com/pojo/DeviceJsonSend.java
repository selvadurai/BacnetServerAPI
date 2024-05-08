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
