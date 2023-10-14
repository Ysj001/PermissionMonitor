package com.ysj.lib.permission.monitor

import android.media.Image
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 屏幕
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorScreen {

    @Pointcut(
        target = "class:android/view/SurfaceControl",
        funName = "screenshot",
        position = POSITION_CALL
    )
    fun screenshot(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/media/MediaRecorder",
        funName = "start",
        position = POSITION_CALL
    )
    fun start(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/media/MediaRecorder",
        funName = "prepare",
        position = POSITION_CALL
    )
    fun prepare(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/media/ImageReader",
        funName = "acquireLatestImage",
        position = POSITION_CALL
    )
    fun acquireLatestImage(callingPoint: CallingPoint): Image? {
        outputInfo(callingPoint)
        return callingPoint.call() as Image?
    }

}