package com.myapps.pixabayeye.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myapps.pixabayeye.common.R

@Composable
fun StatsRow(
    likes: Int,
    downloads: Int,
    comments: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatItem(R.drawable.ic_icon_heart, likes)
        StatItem(R.drawable.ic_icon_download, downloads)
        StatItem(R.drawable.ic_icon_comment, comments)
    }
}

@Composable
private fun StatItem(
    icon: Int,
    value: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
        Spacer(Modifier.width(4.dp))
        Text(text = value.toString())
    }
}