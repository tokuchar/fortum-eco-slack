package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import com.oncors.model.DeviceType;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class DishwasherDataGenerator implements DataGenerator {

    private final int START = 5;
    private final int STOP = 100;
    private final int END_CYCLE = 120;


    private final String DISHWASHER_FINISHED = "finished";
    private final String DISHWASHER_WILL_START = "start";

    private String deviceName;
    private int iteration;

    public DishwasherDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
    }

    @Override
    public DeviceEvent generate() {
        DeviceEvent deviceEvent = DeviceEvent.builder()
                .deviceName(deviceName)
                .deviceType(DeviceType.DISHWASHER)
                .notificationTime(LocalDateTime.now())
                .build();

        if (iteration > END_CYCLE) {
            iteration = 0;
        } else {
            iteration++;
        }

        switch (iteration) {
            case START:
                deviceEvent.setValue(DISHWASHER_WILL_START);
                return deviceEvent;
            case STOP:
                deviceEvent.setValue(DISHWASHER_FINISHED);
                return deviceEvent;
            default:
                return null;
        }

    }
}
