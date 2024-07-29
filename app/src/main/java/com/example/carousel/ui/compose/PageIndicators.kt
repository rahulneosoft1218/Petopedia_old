package com.example.carousel.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.carousel.ui.dimen.Dimensions

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicators(pagerState: PagerState) {
    val localDim = compositionLocalOf { Dimensions() }
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(localDim.current.spaceSmall),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.inversePrimary else Color.LightGray
            Box(
                modifier =
                    Modifier
                        .padding(localDim.current.spaceMirco)
                        .clip(CircleShape)
                        .background(color)
                        .size(localDim.current.spaceSmall),
            )
        }
    }
}
