package com.ysj.lib.permission.monitor

import com.ysj.lib.bcu.modifier.aspect.api.*
import java.net.NetworkInterface
import java.util.*

/**
 * 网络
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorNetwork {

    @Pointcut(
        target = "class:android/net/wifi/WifiInfo",
        funName = "getMacAddress",
        position = POSITION_CALL
    )
    fun getMacAddress(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:java/net/NetworkInterface",
        funName = "getHardwareAddress",
        position = POSITION_CALL
    )
    fun getHardwareAddress(callingPoint: CallingPoint): ByteArray? {
        outputInfo(callingPoint)
        return callingPoint.call() as ByteArray?
    }

    @Pointcut(
        target = "class:java/net/NetworkInterface",
        funName = "getNetworkInterfaces",
        position = POSITION_CALL
    )
    fun getNetworkInterfaces(callingPoint: CallingPoint): Enumeration<NetworkInterface>? {
        outputInfo(callingPoint)
        return callingPoint.call() as Enumeration<NetworkInterface>?
    }

    @Pointcut(
        target = "class:android/net/wifi/WifiManager",
        funName = "setWifiEnabled",
        position = POSITION_CALL
    )
    fun setWifiEnabled(callingPoint: CallingPoint): Boolean {
        outputInfo(callingPoint)
        return callingPoint.call() as Boolean
    }

    @Pointcut(
        target = "class:android/net/wifi/WifiManager",
        funName = "isWifiEnabled",
        position = POSITION_CALL
    )
    fun isWifiEnabled(callingPoint: CallingPoint): Boolean {
        outputInfo(callingPoint)
        return callingPoint.call() as Boolean
    }

    @Pointcut(
        target = "class:android/net/wifi/WifiManager",
        funName = "startScan",
        position = POSITION_CALL
    )
    fun startScan(callingPoint: CallingPoint): Boolean {
        outputInfo(callingPoint)
        return callingPoint.call() as Boolean
    }

}