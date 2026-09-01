package com.example.maxvolume

import android.app.Activity
import android.os.Bundle
import android.media.AudioManager
import android.content.Context

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        audio.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            max,
            0
        )

        finish()
    }
}
