package com.oncors.service;

import com.oncors.model.DeviceEvent;

public interface DeviceNotifyLogic {
    public void processState(DeviceEvent deviceEvent);
}
