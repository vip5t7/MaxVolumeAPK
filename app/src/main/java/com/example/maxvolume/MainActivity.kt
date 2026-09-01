package com.example.maxvolume

import android.app.Activity
import android.os.Bundle
import android.content.Intent


class MainActivity : Activity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        startForegroundService(
            Intent(
                this,
                VolumeService::class.java
            )
        )

        finish()
    }
}