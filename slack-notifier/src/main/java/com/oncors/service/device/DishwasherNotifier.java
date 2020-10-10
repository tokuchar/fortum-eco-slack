package com.oncors.service.device;

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
        if(DishwasherStatus.DISHWASHER_FINISHED.toString().equals(dishwasherStatus))
                slackService.postMessage(new DishwasherEndMessage());
        if(DishwasherStatus.DISHWASHER_WILL_START.toString().equals(dishwasherStatus))
                slackService.postMessage(new DishwasherStartMessage());
        }
}
