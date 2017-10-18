package com.zhangliang.android8featuredemo

import android.app.PictureInPictureParams
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import kotlinx.android.synthetic.main.activity_picture_in_picture.*

class PictureInPictureActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PictureInPictureActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_in_picture)
        tv_hw.setOnClickListener {
            val builder = PictureInPictureParams.Builder()
                    .setAspectRatio(Rational(2,3))// 0.4 - 2.4
            enterPictureInPictureMode(builder.build())
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
}
