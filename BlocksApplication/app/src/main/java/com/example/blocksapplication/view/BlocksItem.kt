package com.example.blocksapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.blocksapplication.data.response.Items
import com.example.blocksapplication.domain.uimodel.BlockUiModel


@Composable
fun HorizontalFreeScrollItem(blockUiModel: BlockUiModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Blue,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .border(1.dp, Color.LightGray)
    ) {
        LazyRow (contentPadding = PaddingValues(12.dp)) {
            items(blockUiModel.items.size) { index ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color.White)
                        .wrapContentSize()
                        .padding(8.dp)
                        .border(1.dp, Color.LightGray)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(blockUiModel.items[index].image),
                        contentDescription = "gfg image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(124.dp)
                            .border(1.dp, Color.LightGray)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    blockUiModel.items[index].title?.let {
                        Text(text = it,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding( 8.dp)
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun BannerItem(blockUiModel: BlockUiModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Blue,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .border(1.dp, Color.LightGray)
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
                .background(color = Color.White)) {
            ImageItem(modifier = Modifier.height(280.dp), blockUiModel.items[0])
        }
    }
}

@Composable
fun SplitBannerItem(blockUiModel: BlockUiModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Blue,
        ),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.LightGray)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
                .background(color = Color.White)) {

            ImageItem(modifier = Modifier
                .height(280.dp)
                .weight(1f), blockUiModel.items[0])
            Spacer(modifier = Modifier.width(8.dp))
            ImageItem(modifier = Modifier
                .height(280.dp)
                .weight(1f), blockUiModel.items[1])
        }
    }
}

@Composable
fun ImageItem(modifier: Modifier, item: Items) {
    Column(modifier = modifier.border(1.dp, Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = rememberAsyncImagePainter(item.image),
            contentDescription = "gfg image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(240.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        item.title?.let {
            Text(text = it,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().border(1.dp, Color.LightGray).padding(8.dp)
            )
        }
    }
}