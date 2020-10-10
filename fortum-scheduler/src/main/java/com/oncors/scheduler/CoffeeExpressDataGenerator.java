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

    private final String IN_USE = "in_use";
    private final String STANDBY = "standby";
    private final String IS_OFF = "off";

    private String deviceName;
    private int iteration;
    private int coffeeTime;
    private int waitTime;

    public CoffeeExpressDataGenerator(String deviceName, int startIteration) {
        this.deviceName = deviceName;
        this.iteration = startIteration;
        this.coffeeTime = getCoffeeTime();
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
            deviceEvent.setValue(IS_OFF);
        }
        if (iteration >= waitTime && iteration <= waitTime + coffeeTime){
            deviceEvent.setValue(IN_USE);
        }

        if(iteration >= waitTime + coffeeTime && iteration <= waitTime + coffeeTime + STANDBY_TIME){
            deviceEvent.setValue(STANDBY);
        }
        if(iteration >= waitTime + coffeeTime + STANDBY_TIME){
            deviceEvent.setValue(IS_OFF);
        }

        if (iteration > END_CYCLE) {
            iteration = 0;
            coffeeTime = getCoffeeTime();
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
