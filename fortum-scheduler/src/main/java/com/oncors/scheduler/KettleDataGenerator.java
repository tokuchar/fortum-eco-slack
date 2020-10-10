package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import com.oncors.model.DeviceType;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@NoArgsConstructor
public class KettleDataGenerator implements DataGenerator {

    private final int START_KETTLE = 10;
    private final int STOP_KETTLE = 30;
    private final int END_CYCLE = 60;

    private final double ON_VALUE = 2;
    private final double ON_DEVIATION = 0.3;

    private String deviceName;
    private int iteration;

    public KettleDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
    }

    @Override
    public DeviceEvent generate() {
        iteration++;
        double kettleValue = 0;
        DeviceEvent deviceEvent = DeviceEvent.builder()
                .deviceName(deviceName)
                .deviceType(DeviceType.KETTLE)
                .notificationTime(LocalDateTime.now())
                .build();

        if(iteration >= START_KETTLE && iteration <= STOP_KETTLE){
            kettleValue = ON_VALUE + (ON_DEVIATION * (1 - (Math.random() * 2 )));
        }

        if (iteration > END_CYCLE) {
            iteration = 0;
        }

        deviceEvent.setValue(String.format(Locale.US, "%.2f" , kettleValue));
        return deviceEvent;
    }
}
