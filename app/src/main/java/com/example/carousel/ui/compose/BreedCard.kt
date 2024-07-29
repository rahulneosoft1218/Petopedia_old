package com.example.carousel.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.carousel.R
import com.example.carousel.domain.model.PetDetails
import com.example.carousel.ui.dimen.Dimensions

@Composable
fun BreedCard(item: PetDetails.Species.Breeds) {
    val context = LocalContext.current
    val localDim = compositionLocalOf { Dimensions() }

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = localDim.current.spaceLarge, vertical = localDim.current.spaceMirco)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(localDim.current.spaceSmall),
                ).padding(vertical = localDim.current.marginMedium, horizontal = localDim.current.marginMedium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = "icon",
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .padding(end = localDim.current.marginSmall)
                    .border(
                        shape =
                            RoundedCornerShape(localDim.current.marginSmall),
                        border = BorderStroke(localDim.current.lineWidth, Color.DarkGray),
                    ).size(localDim.current.spaceExtraLarge)
                    .clip(
                        RoundedCornerShape(localDim.current.marginSmall),
                    ).layoutId("breedIcon"),
        )
        Column(
            modifier =
                Modifier
                    .padding(localDim.current.spaceMirco)
                    .fillMaxWidth(),
        ) {
            Text(
                text = item.title,
                modifier = Modifier.fillMaxWidth().layoutId("tvTitle"),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.padding(localDim.current.default))
            Text(
                text = context.getString(R.string.animal_species, item.subTitle),
                modifier = Modifier.fillMaxWidth().layoutId("tvSubtitle"),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
            )
        }
    }
}
