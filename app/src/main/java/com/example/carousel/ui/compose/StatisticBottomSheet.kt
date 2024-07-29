package com.example.carousel.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.example.carousel.ui.dimen.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticBottomSheet(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val localDim = compositionLocalOf { Dimensions() }

    ModalBottomSheet(
        content = {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(localDim.current.spaceMedium),
            ) {
                content()
            }
        },
        onDismissRequest = onDismissRequest,
    )
}
