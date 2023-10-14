package com.ysj.lib.permission.monitor

import android.provider.Settings
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 其它一些监控，包含反射调用敏感权限的监控。
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorOther {

    @Pointcut(
        target = "class:android/os/Build",
        funName = "getSerial",
        position = POSITION_CALL
    )
    fun getSerial(callingPoint: CallingPoint): String? {
        outputInfo(callingPoint)
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:android/provider/Settings\\\$Secure",
        funName = "getString",
        position = POSITION_CALL
    )
    fun getAndroidId(callingPoint: CallingPoint): String? {
        if (callingPoint.args.size >= 2 && callingPoint.args[1] == Settings.Secure.ANDROID_ID) {
            outputInfo(callingPoint)
        }
        return callingPoint.call() as String?
    }

    @Pointcut(
        target = "class:java/lang/reflect/Method",
        funName = "invoke",
        position = POSITION_CALL
    )
    fun reflectMethodInvoke(callingPoint: CallingPoint): Any? {
        callingPoint.pmFilter(::outputInfo)
        return callingPoint.call()
    }

}