package com.oncors.service.device;

import com.oncors.model.CoffeeExpressStatus;
import com.oncors.model.CoffeeMessage;
import com.oncors.model.DeviceEvent;
import com.oncors.service.DeviceNotifyLogic;
import com.oncors.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeNotifier implements DeviceNotifyLogic {

    @Autowired
    SlackService slackService;

    static boolean on_standby;

    @Override
    public void processState(DeviceEvent deviceEvent) {
        String status = deviceEvent.getValue();
        if(CoffeeExpressStatus.STANDBY.name().equals(status)){
            if(!on_standby) {
                slackService.postMessage(new CoffeeMessage());
                on_standby = true;
            }
        }
        if(CoffeeExpressStatus.OFF.name().equals(status) && on_standby){
            on_standby = false;
        }
    }
}
