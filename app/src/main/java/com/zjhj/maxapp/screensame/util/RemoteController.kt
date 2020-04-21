package com.zjhj.maxapp.screensame.util

import android.text.TextUtils
import org.cybergarage.upnp.Device

/**
 * CreateTime 2020/4/21 14:47
 * Author LiuShiHua
 * Description：
 */
class RemoteController {
    private val AVTransport1 = "urn:schemas-upnp-org:service:AVTransport:1"
    private val SetAVTransportURI = "SetAVTransportURI"
    private val RenderingControl = "urn:schemas-upnp-org:service:RenderingControl:1"
    private val Play = "Play"
    private val InstanceID = 0 //实例ID

    /**
     * 必须在子线程 或 协程中调用
     */
    fun play(device: Device, path: String?): Boolean {
        val service = device.getService(AVTransport1) ?: return false
        val action = service.getAction(SetAVTransportURI) ?: return false
        val playAction = service.getAction(Play) ?: return false
        if (TextUtils.isEmpty(path)) {
            return false
        }
        action.setArgumentValue("InstanceID", InstanceID)
        action.setArgumentValue("CurrentURI", path)
        action.setArgumentValue("CurrentURIMetaData", 0)
        if (!action.postControlAction()) {
            return false
        }
        playAction.setArgumentValue("InstanceID", InstanceID)
        playAction.setArgumentValue("Speed", "1")
        return playAction.postControlAction()
    }

    /**
     * 继续播放 - 先查询当前播放位置，再从当前位置继续播放
     */
    fun continuePlay(device: Device): Boolean {
        val pausePosition = getPositionInfo(device) ?: return false
        val localService = device.getService(AVTransport1) ?: return false
        val localAction = localService.getAction("Seek") ?: return false
        localAction.setArgumentValue("InstanceID", InstanceID)
        // 测试解决播放暂停后时间不准确
        localAction.setArgumentValue("Unit", "ABS_TIME")
        localAction.setArgumentValue("Target", pausePosition)
        localAction.postControlAction()
        val playAction = localService.getAction("Play") ?: return false
        playAction.setArgumentValue("InstanceID", InstanceID)
        playAction.setArgumentValue("Speed", "1")
        return playAction.postControlAction()
    }

    fun getTransportState(device: Device): String? {
        val localService = device.getService(AVTransport1) ?: return null
        val localAction = localService.getAction("GetTransportInfo") ?: return null
        localAction.setArgumentValue("InstanceID", InstanceID)
        return if (localAction.postControlAction()) {
            localAction.getArgumentValue("CurrentTransportState")
        } else {
            null
        }
    }

    fun getVolumeDbRange(device: Device, argument: String?): String? {
        val localService = device.getService(RenderingControl) ?: return null
        val localAction = localService.getAction("GetVolumeDBRange") ?: return null
        localAction.setArgumentValue("InstanceID", InstanceID)
        localAction.setArgumentValue("Channel", "Master")
        return if (!localAction.postControlAction()) {
            null
        } else {
            localAction.getArgumentValue(argument)
        }
    }

    fun getMinVolumeValue(device: Device): Int {
        val minValue = getVolumeDbRange(device, "MinValue")
        return if (TextUtils.isEmpty(minValue)) {
            0
        } else minValue!!.toInt()
    }

    fun getMaxVolumeValue(device: Device): Int {
        val maxValue = getVolumeDbRange(device, "MaxValue")
        return if (TextUtils.isEmpty(maxValue)) {
            100
        } else maxValue!!.toInt()
    }

    fun seek(device: Device, targetPosition: String?): Boolean {
        val localService = device.getService(AVTransport1) ?: return false
        val localAction = localService.getAction("Seek") ?: return false
        localAction.setArgumentValue("InstanceID", InstanceID)
        localAction.setArgumentValue("Unit", "ABS_TIME")
        // }
        localAction.setArgumentValue("Target", targetPosition)
        val postControlAction = localAction.postControlAction()
        return if (!postControlAction) {
            localAction.setArgumentValue("Unit", "REL_TIME")
            localAction.setArgumentValue("Target", targetPosition)
            localAction.postControlAction()
        } else {
            postControlAction
        }
    }

    fun getPositionInfo(device: Device): String? {
        val localService = device.getService(AVTransport1) ?: return null
        val localAction = localService.getAction("GetPositionInfo") ?: return null
        localAction.setArgumentValue("InstanceID", InstanceID)
        val isSuccess = localAction.postControlAction()
        return if (isSuccess) {
            localAction.getArgumentValue("AbsTime")
        } else {
            null
        }
    }

    /**
     * 获取媒体的播放时长
     */
    fun getMediaDuration(device: Device): String? {
        val localService = device.getService(AVTransport1) ?: return null
        val localAction = localService.getAction("GetMediaInfo") ?: return null
        localAction.setArgumentValue("InstanceID", InstanceID)
        return if (localAction.postControlAction()) {
            localAction.getArgumentValue("MediaDuration")
        } else {
            null
        }
    }


    fun setMute(mediaRenderDevice: Device, targetValue: String?): Boolean {
        val service = mediaRenderDevice.getService(RenderingControl) ?: return false
        val action = service.getAction("SetMute") ?: return false
        action.setArgumentValue("InstanceID", InstanceID)
        action.setArgumentValue("Channel", "Master")
        action.setArgumentValue("DesiredMute", targetValue)
        return action.postControlAction()
    }

    fun getMute(device: Device): String? {
        val service = device.getService(RenderingControl) ?: return null
        val getAction = service.getAction("GetMute") ?: return null
        getAction.setArgumentValue("InstanceID", InstanceID)
        getAction.setArgumentValue("Channel", "Master")
        getAction.postControlAction()
        return getAction.getArgumentValue("CurrentMute")
    }

    fun setVoice(device: Device, value: Int): Boolean {
        val service = device.getService(RenderingControl) ?: return false
        val action = service.getAction("SetVolume") ?: return false
        action.setArgumentValue("InstanceID", InstanceID)
        action.setArgumentValue("Channel", "Master")
        action.setArgumentValue("DesiredVolume", value)
        return action.postControlAction()
    }

    /**
     * 获取音量
     */
    fun getVoice(device: Device): Int {
        val service = device.getService(RenderingControl) ?: return -1
        val getAction = service.getAction("GetVolume") ?: return -1
        getAction.setArgumentValue("InstanceID", InstanceID)
        getAction.setArgumentValue("Channel", "Master")
        return if (getAction.postControlAction()) {
            getAction.getArgumentIntegerValue("CurrentVolume")
        } else {
            -1
        }
    }

    /**
     * 停止
     */
    fun stop(device: Device): Boolean {
        val service = device.getService(AVTransport1) ?: return false
        val stopAction = service.getAction("Stop") ?: return false
        stopAction.setArgumentValue("InstanceID", InstanceID)
        return stopAction.postControlAction()
    }

    /**
     * 暂停
     */
    fun pause(mediaRenderDevice: Device): Boolean {
        val service = mediaRenderDevice.getService(AVTransport1) ?: return false
        val pauseAction = service.getAction("Pause") ?: return false
        pauseAction.setArgumentValue("InstanceID", InstanceID)
        return pauseAction.postControlAction()
    }

}
