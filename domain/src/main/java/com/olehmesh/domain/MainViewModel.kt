package com.olehmesh.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olehmesh.domain.di.App
import androidx.lifecycle.viewModelScope
import com.olehmesh.repository.models.MenuModel
import com.olehmesh.repository.network.ApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var api: ApiService

    init {
        App.component.inject(this)
    }

    private var liveData = MutableLiveData<List<MenuModel>>().apply {
        viewModelScope.launch {
            try {
                val response = api.getMenuItems()
                when {
                    response.isSuccessful -> {
                        value = response.body()?.listMenu
                    }
                }

            } catch (exception: Exception) {
                Log.e("Error: ", "No data")
            }
        }
    }

    fun getData(): LiveData<List<MenuModel>> {
        return liveData
    }

}


