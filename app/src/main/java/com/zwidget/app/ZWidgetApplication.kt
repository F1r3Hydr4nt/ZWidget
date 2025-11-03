package com.zwidget.app

import android.app.Application
import com.zwidget.app.worker.WidgetUpdateWorker

class ZWidgetApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize WorkManager for periodic updates
        WidgetUpdateWorker.enqueueWork(this)
    }
}
