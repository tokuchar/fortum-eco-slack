package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import com.oncors.model.DeviceType;

import java.time.LocalDateTime;

public class SmogDataGenerator implements DataGenerator {

    private String deviceName;
    private int iteration;
    private boolean isSmog = false;

    public SmogDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
    }

    @Override
    public DeviceEvent generate() {
        iteration++;

        if(iteration%60 == 0){
            if (isSmog){
                isSmog = false;
            }else {
                isSmog = true;
            }
        }

        return DeviceEvent.builder()
                .deviceName(deviceName)
                .deviceType(DeviceType.SMOG)
                .notificationTime(LocalDateTime.now())
                .value(isSmog? "1" : "0")
                .build();
    }
}
