package com.oncors.service.device;

import com.oncors.model.DeviceEvent;
import com.oncors.model.OpenWindowColderMessage;
import com.oncors.model.OpenWindowWarmerMessage;
import com.oncors.service.DeviceNotifyLogic;
import com.oncors.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ThermometerNotifier implements DeviceNotifyLogic {
    @Autowired
    SlackService slackService;

    boolean windowOpen;
    @Override
    public void processState(DeviceEvent deviceEvent) {
        String eventValue = deviceEvent.getValue();
        List<String> eventValues = Arrays.asList(eventValue.split(";"));
        //first value - inside temperature , second value - outside temperature
        //third value - AC on(1)/off(0) , forth value - heater on/off
        int insideTemperature = Integer.parseInt(eventValues.get(0));
        int outsideTemperature = Integer.parseInt(eventValues.get(1));
        int AC = Integer.parseInt(eventValues.get(2));
        int heater = Integer.parseInt(eventValues.get(3));
        if(!windowOpen && AC == 1&& outsideTemperature < insideTemperature){
            slackService.postMessage(new OpenWindowColderMessage());
            windowOpen = true;
        }
        else if(! windowOpen && heater == 1&& outsideTemperature > insideTemperature){
            slackService.postMessage(new OpenWindowWarmerMessage());
            windowOpen = true;
        }
        else{
            windowOpen = false;
        }
    }
}
