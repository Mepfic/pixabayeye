package com.myapps.pixabayeye.common.component

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.myapps.pixabayeye.common.R

@Composable
fun DetailsImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.ic_icon_placeholder),
        error = painterResource(R.drawable.ic_icon_placeholder),
        modifier = Modifier
            .sizeIn(minWidth = 160.dp, minHeight = 160.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}