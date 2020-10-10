package com.oncors.service.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oncors.model.DeviceEvent;
import com.oncors.model.KettleMessage;
import com.oncors.service.DeviceNotifyLogic;
import com.oncors.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KettleNotifier implements DeviceNotifyLogic {
    private static boolean kettleIsWorking = false;

    @Autowired
    SlackService slackService;
    @Override
    public void processState(DeviceEvent deviceEvent) {
        double power = Double.parseDouble(deviceEvent.getValue());
        if(kettleIsWorking && power == 0){
            try {
                slackService.postMessage(new KettleMessage());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        kettleIsWorking = power > 0;
    }
}
