package com.olehmesh.repository.models

import com.google.gson.annotations.SerializedName

data class MenuModel(

    @SerializedName("name")
    var name: String?,

    @SerializedName("function")
    var function: String?,

    @SerializedName("param")
    var param: String?
)