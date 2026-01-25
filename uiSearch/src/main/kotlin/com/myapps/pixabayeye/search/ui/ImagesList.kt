package com.myapps.pixabayeye.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.myapps.pixabayeye.search.state.SearchItemState
import com.myapps.pixabayeye.test.common.TestTags

@Composable
fun ImagesList(
    items: LazyPagingItems<SearchItemState>,
    navigateToDetails: (Long) -> Unit,
) {
    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            items(
                count = items.itemCount,
                key = items.itemKey { it.imageId }
            ) { index ->
                items[index]?.let { item ->
                    SearchItem(
                        item = item
                    ) {
                        navigateToDetails(item.imageId)
                    }
                }
            }
            if (items.loadState.append is LoadState.Loading) {
                item(key = "append_loader") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(Modifier.testTag(TestTags.LOAD_MORE_INDICATOR))
                    }
                }
            }
        }

        when (items.loadState.refresh) {
            is LoadState.Loading ->
                Box(modifier = Modifier.align(Alignment.Center)) {
                    CircularProgressIndicator(Modifier.testTag(TestTags.SEARCH_LOADING))
                }

            is LoadState.Error ->
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                        .testTag(TestTags.ERROR_MESSAGE),
                    text = "Error loading more"
                )

            is LoadState.NotLoading -> {}
        }
    }
}