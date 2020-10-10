package com.oncors.service;

import com.oncors.model.DeviceEvent;
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

    @RabbitListener(queues = "kettle-queue")
    public void listen(DeviceEvent kettleEvent) {
       log.info("kettle event: " + kettleEvent);
       kettleNotifier.processState(kettleEvent);
    }
}
