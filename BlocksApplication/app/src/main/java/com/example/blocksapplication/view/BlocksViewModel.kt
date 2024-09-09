package com.example.blocksapplication.view

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blocksapplication.utils.Resource
import com.example.blocksapplication.domain.uimodel.BlockUiModel
import com.example.blocksapplication.domain.usecase.GetBlocksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlocksViewModel @Inject constructor(
    private val getBlocksUseCase: GetBlocksUseCase
): ViewModel() {

    private var allBlocks = listOf<BlockUiModel>()
    private val _blockState = mutableStateOf(BlocksState())
    val blockState: State<BlocksState> = _blockState

    init {
        getBlocks()
    }

    private fun getBlocks() {
        getBlocksUseCase.blocks.onEach { result ->
            when (result) {
                Resource.Loading -> _blockState.value = BlocksState(isLoading = true)
                is Resource.Success -> {
                    result.data.let {
                        _blockState.value = BlocksState(blocks = it)
                        allBlocks = it
                        Log.d("Tag","allBlocks ${allBlocks.toString()}")
                    }
                }
                is Resource.Error -> _blockState.value = BlocksState(error = result.errorMessage)
            }
        }.launchIn(viewModelScope)
    }
}