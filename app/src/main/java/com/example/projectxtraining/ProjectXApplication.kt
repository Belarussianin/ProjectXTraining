package com.example.projectxtraining

import android.app.Application
import com.example.projectxtraining.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class ProjectXApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@ProjectXApplication)
            modules(AppModule().module)
        }
    }
}