package com.ysj.lib.permission.monitor

import android.hardware.camera2.CameraDevice
import com.ysj.lib.bcu.modifier.aspect.api.*

/**
 * 相机
 *
 * @author Ysj
 * Create time: 2023/10/14
 */
@Aspect
class MonitorCamera {

    @Pointcut(
        target = "class:android/hardware/Camera",
        funName = "startPreview",
        position = POSITION_CALL
    )
    fun startPreview(callingPoint: CallingPoint) {
        outputInfo(callingPoint)
        callingPoint.call()
    }

    @Pointcut(
        target = "class:android/hardware/camera2/CameraManager",
        funName = "openCameraDeviceUserAsync",
        position = POSITION_CALL
    )
    fun openCameraDeviceUserAsync(callingPoint: CallingPoint): CameraDevice? {
        outputInfo(callingPoint)
        return callingPoint.call() as CameraDevice?
    }

}