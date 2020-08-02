package com.olehmesh.repository.models

import com.google.gson.annotations.SerializedName

class ApiResponse(@SerializedName("menu") var listMenu: List<MenuModel>)