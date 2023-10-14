package com.ysj.lib.permission.monitor

import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 媒体
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorMedia {

    @Pointcut(
        target = "class:android/media/AudioRecord",
        funName = "startRecording",
        position = POSITION_CALL
    )
    fun startRecording(callingPoint: CallingPoint) {
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

}