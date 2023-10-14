package com.ysj.lib.permission.monitor

import android.bluetooth.BluetoothDevice
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 蓝牙
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorBluetooth {

    @Pointcut(
        target = "class:android/bluetooth/BluetoothAdapter",
        funName = "enable",
        position = POSITION_CALL
    )
    fun enable(callingPoint: CallingPoint): Boolean {
        outputInfo(callingPoint)
        return callingPoint.call() as Boolean
    }

    @Pointcut(
        target = "class:android/bluetooth/BluetoothAdapter",
        funName = "startLeScan",
        position = POSITION_CALL
    )
    fun startLeScan(callingPoint: CallingPoint): Boolean {
        outputInfo(callingPoint)
        return callingPoint.call() as Boolean
    }

    @Pointcut(
        target = "class:android/bluetooth/BluetoothAdapter",
        funName = "getProfileConnectionState",
        position = POSITION_CALL
    )
    fun getProfileConnectionState(callingPoint: CallingPoint): Int {
        outputInfo(callingPoint)
        return callingPoint.call() as Int
    }

    @Pointcut(
        target = "class:android/bluetooth/BluetoothAdapter",
        funName = "getBondedDevices",
        position = POSITION_CALL
    )
    fun getBondedDevices(callingPoint: CallingPoint): Set<BluetoothDevice>? {
        outputInfo(callingPoint)
        return callingPoint.call() as Set<BluetoothDevice>?
    }

    @Pointcut(
        target = "class:android/bluetooth/le/BluetoothLeScanner",
        funName = "startScan",
        position = POSITION_CALL
    )
    fun startScan(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/bluetooth/BluetoothDevice",
        funName = "createBond",
        position = POSITION_CALL
    )
    fun createBond(callingPoint: CallingPoint): Boolean {
        outputInfo(callingPoint)
        return callingPoint.call() as Boolean
    }

}