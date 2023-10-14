package com.ysj.lib.permission.monitor

import android.location.Location
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 定位
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorLocation {

    @Pointcut(
        target = "class:android/location/LocationManager",
        funName = "getLastLocation",
        position = POSITION_CALL
    )
    fun getLastLocation(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/location/LocationManager",
        funName = "getLastKnownLocation",
        position = POSITION_CALL
    )
    fun getLastKnownLocation(callingPoint: CallingPoint): Location? {
        outputInfo(callingPoint)
        return callingPoint.call() as Location?
    }

}