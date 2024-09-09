package com.example.blocksapplication.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blocksapplication.utils.ErrorText


@Composable
fun BlockList(viewModel: BlocksViewModel = hiltViewModel()) {
    LazyColumn(contentPadding = PaddingValues(12.dp)) {
        items(viewModel.blockState.value.blocks) { blockItem ->
            when (blockItem.sectionType) {

                "banner" -> {
                    BannerItem(blockItem)
                }

                "horizontalFreeScroll" -> {
                    HorizontalFreeScrollItem(blockItem)
                }

                "splitBanner" -> {
                    SplitBannerItem(blockItem)
                }

            }
        }
    }
}

@Composable
fun CircularProgressBar(viewModel: BlocksViewModel = hiltViewModel()){
    if (viewModel.blockState.value.isLoading) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Blue
            )
        }
    }
}

@Composable
fun BlockError(viewModel: BlocksViewModel = hiltViewModel()){
    Box{
        if (viewModel.blockState.value.error.isNotBlank()){
            ErrorText(viewModel.blockState.value.error, Modifier.align(Alignment.Center))
        }
    }
}