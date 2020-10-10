package com.oncors.config;

import com.oncors.scheduler.KettleDataGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    @Bean(name = "MegaCzajnik")
    public KettleDataGenerator kettleFoo(){
        return  new KettleDataGenerator("MegaCzajnik", 0);
    }
}
