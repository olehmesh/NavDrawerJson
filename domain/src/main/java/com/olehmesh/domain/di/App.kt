package com.olehmesh.domain.di

import android.app.Application
import com.olehmesh.domain.di.component.AppComponent
import com.olehmesh.domain.di.component.DaggerAppComponent
import com.olehmesh.domain.di.module.NetworkModule

class App : Application() {

    companion object {

        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }

}