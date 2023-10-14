package com.ysj.demo.pm

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.ysj.demo.pm.databinding.ActivityMainBinding

/**
 * 测试敏感权限监控功能。
 * - 测试前得先授予权限，否则点击测试时会崩溃。
 * - 在 logcat 中过滤 tag:PermissionMonitor 即可看到调用栈。
 *
 * @author Ysj
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.btnCommonTest.setOnClickListener {
            commonTest()
        }
        vb.btnReflectTest.setOnClickListener {
            reflectTest()
        }
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}.launch(
            arrayOf(
                Manifest.permission.READ_PHONE_STATE,
            )
        )
    }

    private fun commonTest() {
        // test 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serial = Build.getSerial()
            Log.i(TAG, "commonTest 1: $serial")
        }
        // test 2
        val telephonyManager: TelephonyManager = getSystemService()!!
        val simSerialNumber = telephonyManager.simSerialNumber
        Log.i(TAG, "commonTest 2: $simSerialNumber")
    }

    private fun reflectTest() {
        // test 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serial = Class
                .forName("android.os.Build")
                .getDeclaredMethod("getSerial")
                .invoke(null)
            Log.i(TAG, "reflectTest 1: $serial")
        }
        // test 2
        val telephonyManager: TelephonyManager = getSystemService()!!
        val simSerialNumber = telephonyManager
            .javaClass
            .getDeclaredMethod("getSimSerialNumber")
            .invoke(telephonyManager)
        Log.i(TAG, "reflectTest 2: $simSerialNumber")
    }

}