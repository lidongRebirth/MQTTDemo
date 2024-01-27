package com.lidongrebirth.android.project.mqttdemo

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.lidongrebirth.mqttdemo.R
import com.lidongrebirth.android.project.mqttdemo.service.MqttService

/**
 * @Author LD
 * @Time 2024/1/27 11:19
 * @Describe 待完成(前台service实现后台可接收消息)
 * @Modify
 */
class MyService: MqttService() {

    override fun onCreate() {
        super.onCreate()
        //创建前台服务，需要依托一个Notification
        var notification = createNotification()
        startForeground(1, notification)
    }
    private fun createNotification(): Notification {
        val channelId = "MQTTService"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //8.0及以上,需要消息通道
            val channelName = "MQTT"
            val importance = NotificationManager.IMPORTANCE_LOW
            createNotificationChannel(channelId, channelName, importance)
        }
//        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
//            PendingIntent.getActivity(this, 0, notificationIntent, 0)
//        }
        val builder: Notification.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
                this,
                channelId
            ) else Notification.Builder(this)

        return builder
            .setContentTitle("MQTT服务")
            .setContentText("若要在后台可接收消息这是必要的")
            .setSmallIcon(R.drawable.ic_notification)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            //通知的内容将在锁屏时隐藏，但在解锁后可见。
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setOngoing(true)
//            .setColor(ContextCompat.getColor(context, R.color.primary_color))
//            .setContentIntent(pendingIntent)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setTicker("Ticker text")
//            .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
            .build()
    }

    /**
     * 创建通知渠道
     */
    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String, importance: Int) {
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = getSystemService(
            NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}