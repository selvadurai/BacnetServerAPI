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
