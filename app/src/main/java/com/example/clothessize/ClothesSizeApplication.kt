package com.example.clothessize

import android.app.Application
import com.example.clothessize.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ClothesSizeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ClothesSizeApplication)
            modules(appModules)
        }
    }
}