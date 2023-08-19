package com.example.flashlightappkotlin

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onButton = findViewById<Button>(R.id.flash_on_btn)
        val offButton = findViewById<Button>(R.id.flash_off_btn)

        onButton.setOnClickListener {
            onFlash()
        }

        offButton.setOnClickListener {
            offFlash()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onFlash(){
        var cameraManager : CameraManager? = null
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        try{
            var cameraId : String? = null
            cameraId = cameraManager.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager!!.setTorchMode(cameraId,true)
                Toast.makeText(this, "FLash ON",Toast.LENGTH_SHORT).show()
            }

        }catch (e: CameraAccessException){
//            Toast.makeText(this,"Exception: "+e.message).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun offFlash(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val cameraManage = getSystemService(CAMERA_SERVICE) as CameraManager

            try {
                val cameraId = cameraManage.cameraIdList[0]
                cameraManage.setTorchMode(cameraId,false)
                Toast.makeText(this, "FLash OFF",Toast.LENGTH_SHORT).show()
            }catch (e: CameraAccessException){

            }
        }
    }
}