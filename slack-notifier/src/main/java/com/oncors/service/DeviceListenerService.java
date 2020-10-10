package com.oncors.service;

import com.oncors.model.DeviceEvent;
import com.oncors.service.device.DishwasherNotifier;
import com.oncors.service.device.KettleNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceListenerService {
    @Autowired
    KettleNotifier kettleNotifier;

    @Autowired
    DishwasherNotifier dishwasherNotifier;

    @RabbitListener(queues = "kettle-queue")
    public void listenKettle(DeviceEvent kettleEvent) {
       log.info("kettle event: " + kettleEvent);
       kettleNotifier.processState(kettleEvent);
    }

    @RabbitListener(queues = "dishwasher-queue")
    public void listenDishwasher(DeviceEvent dishwasherEvent) {
        log.info("kettle event: " + dishwasherEvent);
        dishwasherNotifier.processState(dishwasherEvent);
    }
}
