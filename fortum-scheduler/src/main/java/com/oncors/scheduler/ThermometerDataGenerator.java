package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import com.oncors.model.DeviceType;

import java.time.LocalDateTime;
import java.util.Locale;

public class ThermometerDataGenerator implements DataGenerator{

    private final int MAX_TEMP_OUTSIDE = 35;
    private final int MIN_TEMP_OUTSIDE = 10;



    private double outsideTemp = MIN_TEMP_OUTSIDE;
    private String deviceName;
    private int iteration;
    private boolean temp_up = true;

    public ThermometerDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
    }


    @Override
    public DeviceEvent generate() {

        double deltaOutside = (MAX_TEMP_OUTSIDE - MIN_TEMP_OUTSIDE);

        double x = 2*Math.PI*((1.0/60.0)*iteration);
        double inside = 24;

        if(outsideTemp > MAX_TEMP_OUTSIDE)
        {
            temp_up = false;
        }
        if (outsideTemp < MIN_TEMP_OUTSIDE){
            temp_up = true;
        }

        if (temp_up){
            outsideTemp += 0.1;
        }else
        {
            outsideTemp -= 0.1;
        }

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
                .value(String.format(Locale.US, "%d;%d;%d;%d", (int)inside, (int)outsideTemp, ac, heat))
                .build();
    }
}
