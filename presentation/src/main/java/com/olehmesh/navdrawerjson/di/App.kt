package com.olehmesh.navdrawerjson.di

import android.app.Application
import com.olehmesh.navdrawerjson.di.component.AppComponent
import com.olehmesh.navdrawerjson.di.component.DaggerAppComponent
import com.olehmesh.navdrawerjson.di.module.NetworkModule

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