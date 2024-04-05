package com.stupa.app

import android.app.Application
import com.stupa.di.databaseModule
import com.stupa.di.preferenceModule
import com.stupa.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

//Android Application class
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            // Reference Android context
            androidContext(this@MyApplication)
            // define your modules here
            modules(preferenceModule, viewModelModule, databaseModule, preferenceModule)
        }
    }
}