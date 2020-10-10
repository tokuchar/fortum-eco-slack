package com.oncors.service.device;

import com.oncors.model.CloseWindowOnSmog;
import com.oncors.model.DeviceEvent;
import com.oncors.service.DeviceNotifyLogic;
import com.oncors.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmogNotifier implements DeviceNotifyLogic {
    private static boolean smogAlreadyNotified = false;

    @Autowired
    SlackService slackService;

    @Override
    public void processState(DeviceEvent deviceEvent) {
        int smogStatus = Integer.parseInt(deviceEvent.getValue());
        if(smogStatus == 1 && !smogAlreadyNotified){
            slackService.postMessage(new CloseWindowOnSmog());
            smogAlreadyNotified = true;
        }
        if(smogStatus != 1){
            smogAlreadyNotified = false;
        }
    }
}
