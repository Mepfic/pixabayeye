@file:Suppress(
    "LongMethod",
    "FunctionNaming",
    "MagicNumber",
    "NewLineAtEndOfFile"
)
package com.myapps.pixabayeye.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.details.model.StubModels.hitDetailsState
import com.myapps.pixabayeye.details.state.UiState
import com.myapps.pixabayeye.test.common.TestTags

@Composable
fun DetailsScreen(state: UiState.DetailsState) {
    Surface(
        shape = MaterialTheme.shapes.large,
        tonalElevation = 2.dp,
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .testTag(TestTags.DETAILS_SCREEN)
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
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(shape = MaterialTheme.shapes.large)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 4.dp)
                    }
                    .testTag(TestTags.DETAILS_IMAGE)
            )

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(statistic) {
                        top.linkTo(image.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_heart),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .testTag(TestTags.DETAILS_LIKES)

                )
                Text(
                    text = state.likes.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Icon(
                    painter = painterResource(R.drawable.ic_icon_download),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 32.dp, end = 8.dp)
                        .testTag(TestTags.DETAILS_DOWNLOADS)
                )
                Text(
                    text = state.downloads.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Icon(
                    painter = painterResource(R.drawable.ic_icon_comment),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 32.dp, end = 8.dp)
                        .testTag(TestTags.DETAILS_COMMENTS)
                )
                Text(
                    text = state.comments.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
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
                    .testTag(TestTags.DETAILS_TAGS)
            ) {
                items(state.tags) {
                    SuggestionChip(
                        onClick = { },
                        enabled = false,
                        modifier = Modifier
                            .padding(start = 8.dp),
                        label = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        })
                }
            }
            Text(
                text = stringResource(R.string.author_name_prefix, state.userName),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .wrapContentSize()
                    .constrainAs(author) {
                        top.linkTo(tags.bottom)
                        end.linkTo(parent.end, margin = 30.dp)
                        bottom.linkTo(parent.bottom, margin = 2.dp)
                    }
                    .testTag(TestTags.DETAILS_AUTHOR)
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(state = hitDetailsState)
}