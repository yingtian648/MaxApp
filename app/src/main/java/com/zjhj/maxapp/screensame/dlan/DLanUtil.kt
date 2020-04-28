package com.zjhj.maxapp.screensame.dlan

import android.app.Activity
import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.screensame.util.EventDevicesBean
import com.zjhj.maxapp.utils.L
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.cybergarage.upnp.ControlPoint
import org.cybergarage.upnp.Device
import org.cybergarage.upnp.Service
import org.cybergarage.upnp.device.DeviceChangeListener
import org.cybergarage.upnp.event.EventListener
import org.greenrobot.eventbus.EventBus
import java.net.URL

/**
 * CreateTime 2020/4/16 10:06
 * Author LiuShiHua
 * Description：投屏工具类
 */
class DLanUtil(val conext: Activity) {
    var controlPoint = ControlPoint()
    val deviceList = mutableListOf<Device>()
    val DMR = "urn:schemas-upnp-org:device:MediaRenderer:1"//DMR设备服务类型【命名空间+设备类型+版本号】
    val eventBus = EventBus.getDefault()

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
                    eventBus.post(
                        EventDevicesBean(
                            "发现新设备",
                            dev,
                            Constants.EVENT_TYPE_DLAN_DEVICES_ADD
                        )
                    )
                } else {
//                    L.d("未添加设备：" + dev?.deviceType)
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

    fun getDeviceServerDec(device: Device) {
        // 设备描述文档
        val locationUrl: String = device.getLocation()
        // 获取服务 渲染设备服务类型ConnectionManagerService，AVTransportService，AudioRenderingControl。
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