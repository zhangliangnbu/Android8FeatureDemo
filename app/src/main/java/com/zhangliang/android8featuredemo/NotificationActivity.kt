package com.zhangliang.android8featuredemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.graphics.drawable.Icon
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_notification.*
import java.util.*

class NotificationActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "NotificationActivity"
        private const val CHANNEL_1_ID = "feature_channel_1_id"
        private const val CHANNEL_1_NAME = "channel_1_name"
        private const val CHANNEL_2_ID = "feature_channel_2_id"
        private const val CHANNEL_2_NAME = "channel_2_name"
    }

    private val notificationManager: NotificationManager by lazy {
        getSystemService(NotificationManager::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        ButterKnife.bind(this)
        // create channel
        val channel1 = NotificationChannel(CHANNEL_1_ID, CHANNEL_1_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel1.description = "channel1 description"
        channel1.lightColor = Color.RED
        channel1.enableLights(true)

        val channel2 = NotificationChannel(CHANNEL_2_ID, CHANNEL_2_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel1.description = "channel2 description"
        channel1.lightColor = Color.GREEN
        channel1.enableLights(true)

        notificationManager.createNotificationChannels(Arrays.asList(channel1, channel2))

        btn_ch1_nt1.setOnClickListener {
            sendNotification(CHANNEL_1_ID, "ch1 notification1 title", "ch1 notification1 text", 10001)
        }

        btn_ch1_nt2.setOnClickListener {
            sendNotification(CHANNEL_1_ID, "ch1 notification2 title", "ch1 notification2 text", 10002)
        }

        btn_ch2_nt1.setOnClickListener {
            sendNotification(CHANNEL_2_ID, "ch2 notification1 title", "ch2 notification1 text", 20001)
        }

        btn_ch2_nt2.setOnClickListener {
            sendNotification(CHANNEL_2_ID, "ch2 notification2 title", "ch2 notification2 text", 20002)
        }
    }

    fun sendNotification(channel_id: String, title: String, text: String, id: Int) {
        val notification = Notification.Builder(this, channel_id)
                .setSmallIcon(getSmallIcon())
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .build()
        notificationManager.notify(id, notification)
    }

    private fun getSmallIcon(): Int {
        return android.R.drawable.stat_notify_chat
    }


}
