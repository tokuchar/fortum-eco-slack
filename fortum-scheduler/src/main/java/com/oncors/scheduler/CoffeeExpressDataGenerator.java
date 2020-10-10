package com.oncors.scheduler;

import com.oncors.model.DeviceEvent;
import com.oncors.model.DeviceType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CoffeeExpressDataGenerator implements DataGenerator {
    private final int END_CYCLE = 60;
    private final int STANDBY_TIME = 30;

    enum CoffeeExpressState {
        IN_USE,
        STANDBY,
        OFF
    }

    private String deviceName;
    private int iteration;
    private int coffeTime;
    private int waitTime;

    public CoffeeExpressDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
        this.coffeTime = getCoffeeTime();
        this.waitTime = getWaitTime();

    }

    @Override
    public DeviceEvent generate() {
        iteration++;

        DeviceEvent deviceEvent = DeviceEvent.builder()
                .deviceName(deviceName)
                .deviceType(DeviceType.COFFEE_MACHINE)
                .notificationTime(LocalDateTime.now())
                .build();

        if(iteration <= waitTime){
            deviceEvent.setValue(CoffeeExpressState.OFF.toString());
        }
        if (iteration >= waitTime && iteration <= waitTime + coffeTime){
            deviceEvent.setValue(CoffeeExpressState.IN_USE.toString());
        }

        if(iteration >= waitTime + coffeTime && iteration <= waitTime + coffeTime + STANDBY_TIME){
            deviceEvent.setValue(CoffeeExpressState.STANDBY.toString());
        }
        if(iteration >= waitTime + coffeTime + STANDBY_TIME){
            deviceEvent.setValue(CoffeeExpressState.OFF.toString());
        }

        if (iteration > END_CYCLE) {
            iteration = 0;
            coffeTime = getCoffeeTime();
            waitTime = getWaitTime();
        }

        return deviceEvent;
    }

    private int getCoffeeTime(){

        List<Integer> givenList = Arrays.asList(5,10,15);
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }

    private int getWaitTime(){
        return ThreadLocalRandom.current().nextInt(1, 11);
    }
}
