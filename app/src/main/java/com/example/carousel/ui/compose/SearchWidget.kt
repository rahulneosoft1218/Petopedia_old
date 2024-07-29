package com.example.carousel.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.carousel.ui.dimen.Dimensions

@Composable
fun SearchWidget(
    searchText: MutableState<String>,
    performSearch: (String) -> Unit,
) {
    val localDim = compositionLocalOf { Dimensions() }
    TextField(
        value = searchText.value,
        onValueChange = {
            performSearch(it)
            searchText.value = it
        },
        placeholder = { Text("Search", color = Color.Gray) },
        singleLine = true,
        shape = RoundedCornerShape(localDim.current.spaceSmall),
        leadingIcon = {
            Icon(Icons.Filled.Search, "", tint = Color.Gray)
        },
        colors =
            TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
                unfocusedContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
                disabledContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        modifier =
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(localDim.current.spaceSmall))
                .padding(vertical = localDim.current.spaceSmall, horizontal = localDim.current.marginLarge),
    )
}
