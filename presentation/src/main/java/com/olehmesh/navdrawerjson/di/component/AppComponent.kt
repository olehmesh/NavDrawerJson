package com.olehmesh.navdrawerjson.di.component

import com.olehmesh.navdrawerjson.MainActivity
import com.olehmesh.navdrawerjson.di.module.NetworkModule
import com.olehmesh.navdrawerjson.di.scope.ApiScope
import dagger.Component

@ApiScope
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}