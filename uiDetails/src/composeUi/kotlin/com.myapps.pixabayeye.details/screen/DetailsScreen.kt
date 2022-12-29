package com.myapps.pixabayeye.details.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.details.screen.StubModels.hitDetailsState
import com.myapps.pixabayeye.details.state.UiState

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(state: UiState.DetailsState) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
        ) {
            val (image, tags, author, statistic) = createRefs()
            val painter = rememberAsyncImagePainter(state.largeImageUrl)

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp)
                    .clip(shape = MaterialTheme.shapes.large)
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 4.dp)
                    }
            )

            Row(modifier = Modifier
                .wrapContentHeight()
                .constrainAs(statistic) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_heart),
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)

                )
                Text(
                    text = state.likes.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                )
                Icon(
                    painter = painterResource(R.drawable.ic_icon_download),
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 32.dp, end = 8.dp)
                )
                Text(
                    text = state.downloads.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                )
                Icon(
                    painter = painterResource(R.drawable.ic_icon_comment),
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 32.dp, end = 8.dp)
                )
                Text(
                    text = state.comments.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                )
            }
            LazyRow(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(tags) {
                        top.linkTo(statistic.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                items(state.tags) {
                    Chip(
                        onClick = { }, enabled = false, modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface,
                        )
                    }
                }
            }
            Text(
                text = stringResource(R.string.author_name_prefix, state.userName),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .wrapContentSize()
                    .constrainAs(author) {
                        top.linkTo(tags.bottom)
                        end.linkTo(parent.end, margin = 30.dp)
                        bottom.linkTo(parent.bottom, margin = 2.dp)
                    }
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(state = hitDetailsState)
}