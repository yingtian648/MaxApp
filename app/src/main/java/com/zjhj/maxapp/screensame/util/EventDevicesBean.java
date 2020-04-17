package com.zjhj.maxapp.screensame.util;

import org.cybergarage.upnp.Device;

/**
 * CreateTime 2020/4/16 10:11
 * Author LiuShiHua
 * Description：
 */
public class EventDevicesBean extends EventBean {

    public EventDevicesBean(String msg, Device device, Integer type) {
        super(msg, type);
        this.device = device;
    }

    private Device device;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
