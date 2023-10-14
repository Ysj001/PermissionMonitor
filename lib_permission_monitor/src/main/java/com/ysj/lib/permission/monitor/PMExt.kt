package com.ysj.lib.permission.monitor

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.content.ClipData
import android.content.ClipboardManager
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.location.LocationManager
import android.media.AudioRecord
import android.media.ImageReader
import android.media.MediaRecorder
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import com.ysj.lib.bcu.modifier.aspect.api.CallingPoint
import java.lang.reflect.Method
import java.net.NetworkInterface

/*
 * 权限监控扩展
 *
 * @author Ysj
 * Create time: 2023/10/14
 */

const val PM_TAG = "PermissionMonitor"

/** 获取调用者的 [Class] */
val CallingPoint.callerClass get() = run { if (isStatic) caller as Class<*> else caller.javaClass }

inline fun CallingPoint.pmFilter(block: (CallingPoint) -> Unit) {
    val mCaller = caller
    if (mCaller !is Method) {
        if (callerClass in PM_FILTERS) block(this) else return
    } else PM_FILTERS.forEach {
        if (it.name !in mCaller.toString()) return@forEach
        block(this)
        return
    }
}

val PM_FILTERS: Array<Class<*>> = arrayOf(
    TelephonyManager::class.java,
    Build::class.java,
    WifiInfo::class.java,
    NetworkInterface::class.java,
    Settings.Secure::class.java,
    Settings.System::class.java,
    LocationManager::class.java,
//    SurfaceControl::class.java,
    MediaRecorder::class.java,
    AudioRecord::class.java,
    Camera::class.java,
    CameraManager::class.java,
    ImageReader::class.java,
    WifiManager::class.java,
    BluetoothAdapter::class.java,
    BluetoothLeScanner::class.java,
    BluetoothDevice::class.java,
    ClipboardManager::class.java,
    ClipData::class.java,
)

/**
 * 隐私调用信息输出。
 */
fun outputInfo(callingPoint: CallingPoint) {
    Log.w(
        PM_TAG,
        """
        检测到敏感权限调用
        - 调用方法：${callingPoint.callerClass.name}.${callingPoint.method.name}
        - 调用者：${callingPoint.caller}
        - 参数：${callingPoint.args.contentToString()}
        调用栈如下：
        """.trimIndent(),
        Exception(PM_TAG)
    )
}