package com.example.carousel.ui.compose

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.example.carousel.ui.dimen.Dimensions

@Composable
fun FabButton(click: () -> Unit) {
    val localDim = compositionLocalOf { Dimensions() }
    FloatingActionButton(
        onClick = click,
        containerColor = MaterialTheme.colorScheme.inversePrimary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = FloatingActionButtonDefaults.elevation(localDim.current.spaceSmall),
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "fabForBottomSheet",
        )
    }
}
