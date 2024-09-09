package com.example.blocksapplication.data.response

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("title")
    val title: String?,
    @SerializedName("image")
    val image: String?
)