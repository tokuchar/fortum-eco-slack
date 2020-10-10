package com.oncors.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DeviceEvent implements Serializable {
    LocalDateTime notificationTime;
    DeviceType deviceType;
    String deviceName;
    String value;

    @Override
    public String toString() {
        return "DeviceEvent{" +
                "notificationTime=" + notificationTime +
                ", deviceType=" + deviceType +
                ", deviceName='" + deviceName +
                ", value=" + value +
                '}';
    }
}
