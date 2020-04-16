package com.zjhj.maxapp.screensame.util

import com.zjhj.maxapp.utils.L
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.cybergarage.upnp.ControlPoint
import org.cybergarage.upnp.Device
import org.cybergarage.upnp.Service
import org.cybergarage.upnp.device.DeviceChangeListener
import org.cybergarage.upnp.event.EventListener
import java.net.URL

/**
 * CreateTime 2020/4/16 10:06
 * Author LiuShiHua
 * Description：投屏工具类
 */
class DLanUtil {
    var controlPoint = ControlPoint()
    val deviceList = mutableListOf<Device>()
    val DMR = "urn:schemas-upnp-org:device:MediaRenderer:1"//DMR设备服务类型

    init {
        controlPoint.addDeviceChangeListener(object : DeviceChangeListener {
            override fun deviceRemoved(dev: Device?) {
                L.d("设备离线：" + dev?.friendlyName)
                if (dev != null)
                    deviceList.remove(dev)
            }

            override fun deviceAdded(dev: Device?) {
                L.d("设备加入：" + dev?.friendlyName)
                //支持投屏播放的设备的设备类型主要为DMR
                if (dev != null && DMR.equals(dev.getDeviceType())) {//判断是否为DMR
                    deviceList.add(dev)
                }
            }
        })
        //添加设备发生事件时的回调【服务终端停止等】
        controlPoint.addEventListener(object : EventListener {
            override fun eventNotifyReceived(uuid: String?, seq: Long, varName: String?, value: String?) {

            }
        })
//        controlPoint.addNotifyListener({
//            L.d("搜索到设备地址：" + it.remoteAddress)
//        })
    }

    fun startSearchDevices() {
        GlobalScope.launch {
            controlPoint.start()
            controlPoint.search() //搜索提供DLan服务的设备
        }
    }

    //请求设备服务-投屏
    fun reqDlanPlay(device: Device) {
        // 实例ID
        val instanceID = "0"
        // 播放视频地址
        var currentURI =
            "http://hc.yinyuetai.com/uploads/videos/common/026E01578953FD0EF0E47204247B5D13.flv?sc=2d17ae37a9186da6&br=780&vid=2693509&aid=623&area=US&vst=2"
        // 获取服务
        val service = device.getService("urn:schemas-upnp-org:service:AVTransport:1")
        // 获取动作
        val transportAction = service.getAction("SetAVTransportURI")
        // 设置参数
        transportAction.setArgumentValue("InstanceID", instanceID)
        transportAction.setArgumentValue("CurrentURI", currentURI)
        // SetAVTransportURI
        if (transportAction.postControlAction()) {
            // 成功
            val playAction = service.getAction("Play")
            playAction.setArgumentValue("InstanceID", instanceID)
            // Play
            if (!playAction.postControlAction()) {
                L.e("upnpErr" + playAction.getStatus().getDescription())
            } else {
                L.d("请求成功")
            }
        } else {
            // 失败
            L.e("upnpErr:" + transportAction.getStatus().getDescription())
        }
    }

    fun getDeviceServerDec(device: Device) {
        // 设备描述文档
        val locationUrl: String = device.getLocation()
        // 获取服务
        val service: Service = device.getService("urn:schemas-upnp-org:service:AVTransport:1")
        val url = URL(locationUrl)
        // SDD
        val sddUrl: String = "locationUrl的ip地址和端口号 " + service.getSCPDURL()
        L.d(sddUrl)
    }

    //订阅设备
    fun subscribeDevice(device: Device) {
        // 获取服务
        val service: Service = device.getService("urn:schemas-upnp-org:service:AVTransport:1")
        if (controlPoint.subscribe(service)) { // 订阅成功
            L.d("订阅成功")
        } else { // 订阅失败
            L.d("订阅失败")
        }
    }
}