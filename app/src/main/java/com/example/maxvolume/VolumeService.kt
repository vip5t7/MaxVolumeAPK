package com.example.maxvolume

import android.app.*
import android.content.*
import android.media.AudioManager
import android.os.*

class VolumeService : Service() {

    private val handler = Handler(Looper.getMainLooper())

    private val checkVolume = object : Runnable {
        override fun run() {

            val audio =
                getSystemService(Context.AUDIO_SERVICE)
                as AudioManager

            val max =
                audio.getStreamMaxVolume(
                    AudioManager.STREAM_MUSIC
                )

            val current =
                audio.getStreamVolume(
                    AudioManager.STREAM_MUSIC
                )

            if (current < max) {
                audio.setStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    max,
                    0
                )
            }

            handler.postDelayed(this, 300)
        }
    }


    override fun onCreate() {
        super.onCreate()

        val channel =
            NotificationChannel(
                "volume_lock",
                "Volume Lock",
                NotificationManager.IMPORTANCE_LOW
            )

        getSystemService(
            NotificationManager::class.java
        ).createNotificationChannel(channel)


        val notification =
            Notification.Builder(
                this,
                "volume_lock"
            )
            .setContentTitle("Volume Lock")
            .setContentText("Media volume locked")
            .setSmallIcon(
                android.R.drawable.ic_lock_silent_mode_off
            )
            .build()


        startForeground(
            1,
            notification
        )


        handler.post(checkVolume)
    }


    override fun onDestroy() {
        handler.removeCallbacks(checkVolume)
        super.onDestroy()
    }


    override fun onBind(intent: Intent?) = null
}