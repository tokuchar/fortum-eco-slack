package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import com.oncors.model.DeviceType;

import java.time.LocalDateTime;
import java.util.Locale;

public class ThermometerDataGenerator implements DataGenerator{

    private final int MAX_TEMP_INSIDE = 25;
    private final int MIN_TEMP_INSIDE = 20;
    private final int MAX_TEMP_OUTSIDE = 25;
    private final int MIN_TEMP_OUTSIDE = 20;

    private String deviceName;
    private int iteration;

    public ThermometerDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
    }


    @Override
    public DeviceEvent generate() {
        double deltaInside = (MAX_TEMP_INSIDE - MIN_TEMP_INSIDE);
        double deltaOutside = (MAX_TEMP_OUTSIDE - MIN_TEMP_OUTSIDE);

        double x = 2*Math.PI*((1.0/60.0)*iteration);
        double inside = (double)((MAX_TEMP_INSIDE + MIN_TEMP_INSIDE)/2.0)+ (deltaInside * Math.sin(x));
        double outside = (double)((MAX_TEMP_OUTSIDE + MIN_TEMP_OUTSIDE)/2.0) + (deltaOutside * Math.sin(x + Math.PI/2.0));
        int ac = 0;
        int heat = 0;

        if(iteration%90 > 30)
        {
            ac = 1;
            heat = 0;
        } else if (iteration%90 > 60){
            ac = 0;
            heat = 1;
        }

        iteration++;

        return DeviceEvent.builder()
                .deviceName(deviceName)
                .deviceType(DeviceType.DISHWASHER)
                .notificationTime(LocalDateTime.now())
                .value(String.format(Locale.US, "%d;%d;%d;%d", (int)inside, (int)outside, ac, heat))
                .build();
    }
}
