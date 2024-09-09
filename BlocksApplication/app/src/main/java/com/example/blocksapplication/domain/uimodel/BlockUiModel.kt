package com.example.blocksapplication.domain.uimodel

import com.example.blocksapplication.data.response.Items


data class BlockUiModel(
    var sectionType : String?,
    var items: List<Items> = arrayListOf()
)