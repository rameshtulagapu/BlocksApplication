package com.example.blocksapplication.view

import com.example.blocksapplication.domain.uimodel.BlockUiModel

data class BlocksState(
    val isLoading: Boolean = false,
    val blocks: List<BlockUiModel> = emptyList(),
    val error: String = ""
)