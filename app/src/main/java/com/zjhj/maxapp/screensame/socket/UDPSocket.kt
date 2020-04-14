package com.zjhj.maxapp.screensame.socket

import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.utils.Tools
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.io.IOException
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.MulticastSocket

/**
 * CreateTime 2020/4/14 09:47
 * Author LiuShiHua
 * Description：udp最大单次传送量是64k
 */
class UDPSocket : Thread() {
    val broadcastPort = 5678
    val groupAddress = InetAddress.getByName("224.9.1.1")//224.0.0.0至239.255.255.255多点广播地址范围
    val localHostAddress = Tools.getLocalHostIpIPV4()
    val socket = MulticastSocket(broadcastPort)
    val receiveBytes = ByteArray(60 * 1024)
    val sendDataDp = DatagramPacket(byteArrayOf(), 0, groupAddress, broadcastPort)
    val receiveDataDp = DatagramPacket(receiveBytes, receiveBytes.size)
    val eventBus = EventBus.getDefault()

    init {
        socket.joinGroup(groupAddress)
    }

    /**
     * 一直接收udp
     * receiveDataDp.lengt是实际数据长度
     */
    override fun run() {
        while (true) {
            socket.receive(receiveDataDp)
            //有数据，且非自己发送
            if (!receiveDataDp.address.hostAddress.equals(localHostAddress)) {
                if (receiveDataDp.length > 0) {
                    val data = receiveDataDp.data.copyOfRange(0, receiveDataDp.length)//实际传输数据
                    val msgType = getMsgType(data)
                    val content = getMsgContent(data)
                    if (msgType == Constants.MSG_TYPE_STRING) {
                        L.d("收到文字消息")
                        eventBus.post(
                            EventBean(
                                receiveDataDp.address.hostAddress,
                                if (content == null) "" else String(content, Charsets.UTF_8),
                                Constants.EVENT_TYPE_RECEIVE_MSG
                            )
                        )
                    }
                    if (msgType == Constants.MSG_TYPE_IMAGE) {
                        L.d("收到图片消息")
                        eventBus.post(
                            EventBean(
                                receiveDataDp.address.hostAddress,
                                String(data, Charsets.UTF_8),
                                Constants.EVENT_TYPE_RECEIVE_MSG
                            )
                        )
                    }
                } else {
                    eventBus.post(
                        EventBean(
                            receiveDataDp.address.hostAddress,
                            "",
                            Constants.EVENT_TYPE_RECEIVE_MSG
                        )
                    )
                }
            }
            L.d(
                "收到" + receiveDataDp.address.hostAddress + "消息：" + String(
                    receiveDataDp.data,
                    0,
                    receiveDataDp.length,
                    Charsets.UTF_8
                )
            )
        }
    }

    fun sendUDPMsg(data: ByteArray, msgType: Byte) {
        eventBus.post(EventBean(String(data, Charsets.UTF_8), Constants.EVENT_TYPE_SEND_MSG))
        val sendData = addMsgTyoe(data, msgType)
        GlobalScope.launch {
            if (data.size > 60 * 1024) {
                var indexTotal = data.size / (60 * 1024)
                var index = 0
                while (index < indexTotal) {
                    var bytes: ByteArray = ByteArray(60 * 1024)
                    if (index == (indexTotal - 1)) {
                        bytes = sendData.copyOfRange(index * (60 * 1024), sendData.size)
                    } else {
                        bytes = sendData.copyOfRange(index * (60 * 1024), (index++) * ((60 * 1024)))
                    }
                    sendData(bytes)
                }
            } else {
                sendData(sendData)
            }
        }
    }

    private fun sendData(data: ByteArray) {
        L.d("发送数据：" + data.size)
        try {
            sendDataDp.setData(data)
            socket.send(sendDataDp)
        } catch (e: Exception) {
            L.e("sendUDPMsg Exception:" + e.message)
        } finally {

        }
    }
}