package com.example.blocksapplication.data.response

import com.google.gson.annotations.SerializedName


data class BlockResponseItem(
    @SerializedName("sectionType")
    var sectionType : String? = null,
    @SerializedName("items")
    var items: ArrayList<Items> = arrayListOf()
)