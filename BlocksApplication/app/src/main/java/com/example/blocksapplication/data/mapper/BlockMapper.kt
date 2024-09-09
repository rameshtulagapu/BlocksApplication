package com.example.blocksapplication.data.mapper

import com.example.blocksapplication.data.response.BlockResponseItem
import com.example.blocksapplication.domain.uimodel.BlockUiModel


fun BlockResponseItem.toBlockUiModel() = BlockUiModel(
    sectionType = sectionType,
    items = items,
)

