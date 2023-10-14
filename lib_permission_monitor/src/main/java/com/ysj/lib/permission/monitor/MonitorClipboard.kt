package com.ysj.lib.permission.monitor

import android.content.ClipData
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 剪贴板
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorClipboard {

    @Pointcut(
        target = "class:android/content/ClipboardManager",
        funName = "getPrimaryClip",
        position = POSITION_CALL
    )
    fun getPrimaryClip(callingPoint: CallingPoint): ClipData? {
        outputInfo(callingPoint)
        return callingPoint.call() as ClipData?
    }

    @Pointcut(
        target = "class:android/content/ClipboardManager",
        funName = "getText",
        position = POSITION_CALL
    )
    fun getText(callingPoint: CallingPoint): CharSequence? {
        outputInfo(callingPoint)
        return callingPoint.call() as CharSequence?
    }

    @Pointcut(
        target = "class:android/content/ClipboardManager",
        funName = "setText",
        position = POSITION_CALL
    )
    fun setText(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/content/ClipboardManager",
        funName = "setPrimaryClip",
        position = POSITION_CALL
    )
    fun setPrimaryClip(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/content/ClipData",
        funName = "getItemAt",
        position = POSITION_CALL
    )
    fun getItemAt(callingPoint: CallingPoint): ClipData.Item? {
        outputInfo(callingPoint)
        return callingPoint.call() as ClipData.Item?
    }

}