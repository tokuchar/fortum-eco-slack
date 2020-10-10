package com.oncors.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class DeviceEvent {
    LocalDate notificationTime;
    DeviceType deviceType;
    String deviceName;
    DeviceDetails deviceDetails;
}
