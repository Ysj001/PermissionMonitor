package com.ysj.lib.permission.monitor

import android.telephony.TelephonyManager
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * [TelephonyManager]
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorTelephonyManager {

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getDeviceId",
        position = POSITION_CALL
    )
    fun getDeviceId(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getSubscriberId",
        position = POSITION_CALL
    )
    fun getSubscriberId(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getImei",
        position = POSITION_CALL
    )
    fun getImei(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getMeid",
        position = POSITION_CALL
    )
    fun getMeid(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getSimSerialNumber",
        position = POSITION_CALL
    )
    fun getSimSerialNumber(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getSimOperatorNameForPhone",
        position = POSITION_CALL
    )
    fun getSimOperatorNameForPhone(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getTypeAllocationCode",
        position = POSITION_CALL
    )
    fun getTypeAllocationCode(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getManufacturerCode",
        position = POSITION_CALL
    )
    fun getManufacturerCode(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getNaiBySubscriberId",
        position = POSITION_CALL
    )
    fun getNaiBySubscriberId(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getPhoneTypeFromNetworkType",
        position = POSITION_CALL
    )
    fun getPhoneTypeFromNetworkType(callingPoint: CallingPoint): Int? {
        outputInfo(callingPoint)
        return callingPoint.call() as Int?
    }

    @Pointcut(
        target = "class:android/telephony/TelephonyManager",
        funName = "getTelephonyProperty",
        position = POSITION_CALL
    )
    fun getTelephonyProperty(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

}