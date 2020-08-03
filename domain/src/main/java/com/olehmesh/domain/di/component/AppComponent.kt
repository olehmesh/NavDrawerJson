package com.olehmesh.domain.di.component

import com.olehmesh.domain.MainViewModel
import com.olehmesh.domain.di.module.NetworkModule
import com.olehmesh.domain.di.scope.ApiScope
import dagger.Component

@ApiScope
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
}