package com.oncors.service.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oncors.model.*;
import com.oncors.service.DeviceNotifyLogic;
import com.oncors.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishwasherNotifier implements DeviceNotifyLogic {

    @Autowired
    SlackService slackService;

    @Override
    public void processState(DeviceEvent deviceEvent) {
        String dishwasherStatus = deviceEvent.getValue();
        if(DishwasherStatus.DISHWASHER_FINISHED.toString().equals(dishwasherStatus)){
            try {
                slackService.postMessage(new DishwasherEndMessage());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(DishwasherStatus.DISHWASHER_WILL_START.toString().equals(dishwasherStatus)){
            try {
                slackService.postMessage(new DishwasherStartMessage());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
