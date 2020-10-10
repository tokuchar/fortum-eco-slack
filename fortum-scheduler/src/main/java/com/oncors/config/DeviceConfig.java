package com.oncors.config;

import com.oncors.scheduler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    @Bean(name = "Kettle")
    public KettleDataGenerator kettleFoo(){
        return  new KettleDataGenerator("MegaCzajnik", 0);
    }

    @Bean(name = "Dishwasher")
    public DishwasherDataGenerator dishwasherFoo(){
        return new DishwasherDataGenerator("Big Dishwasher", 0);
    }

    @Bean(name = "Coffee")
    public CoffeeExpressDataGenerator coffeeFoo(){
        return new CoffeeExpressDataGenerator("Small Coffee Express", 0);
    }

    @Bean(name = "Thermometer")
    public ThermometerDataGenerator thermometerFoo(){
        return new ThermometerDataGenerator("Uber Thermometer", 0);
    }

    @Bean(name = "Smog")
    public SmogDataGenerator smogFoo(){
        return new SmogDataGenerator("Wielki Smog", 0);
    }
}
