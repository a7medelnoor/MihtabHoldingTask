package com.a7medelnoor.mihtabholdingtask

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    private lateinit var buttonRecordVideo : Button
    private val  VIDEO_CAPTURE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         buttonRecordVideo = findViewById(R.id.button_record_a_video)
        buttonRecordVideo.isEnabled = hasCamera()
        buttonRecordVideo.setOnClickListener {
            startRecording(buttonRecordVideo)
        }
    }
    private fun startRecording(view:View){
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent,VIDEO_CAPTURE)
    }
    private fun hasCamera(): Boolean {
        return packageManager.hasSystemFeature(
            PackageManager.FEATURE_CAMERA_ANY
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val videoUri = data!!.data
        if (resultCode == Activity.RESULT_OK && requestCode == VIDEO_CAPTURE){
            Log.d(TAG,"OnActivityResultVideo Saved"+videoUri)
                Toast.makeText(this, "Video Successfully saved to Gallery: \n$videoUri",Toast.LENGTH_SHORT).show()

            }else if(resultCode == Activity.RESULT_CANCELED){
            Log.d(TAG,"OnActivityResultVideo cancled"+videoUri)

            Toast.makeText(this,"Video recording canceled",Toast.LENGTH_SHORT).show()
            }else {
            Log.d(TAG,"OnActivityResultVideo else"+videoUri)

            Toast.makeText(this,"Failed to record a Video",Toast.LENGTH_SHORT).show()


        }
    }
}