package com.myapps.pixabayeye.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.search.state.SearchItemState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesPage(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<SearchItemState>,
    onSearchClick: (String) -> Unit,
    navigateToDetails: (Long) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {

        val refreshing = items.loadState.refresh is LoadState.Loading
        val state = rememberPullToRefreshState()
        val keyboardController = LocalSoftwareKeyboardController.current

        var query by rememberSaveable { mutableStateOf("") }

        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { query = it },
                    onSearch = {
                        onSearchClick(query)
                        keyboardController?.hide()
                    },
                    placeholder = { Text(stringResource(R.string.search_hint)) },
                    expanded = false,
                    onExpandedChange = {},
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { query = "" }) {
                                Icon(
                                    painter = painterResource(android.R.drawable.ic_delete),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                )
            },
            expanded = false,
            onExpandedChange = {},
            windowInsets = WindowInsets(),
        ) {}

        PullToRefreshBox(
            isRefreshing = refreshing,
            onRefresh = { items.refresh() },
            modifier = modifier.fillMaxSize(),
            state = state,
            indicator = {
                Indicator(
                    modifier = modifier.align(Alignment.TopCenter),
                    isRefreshing = refreshing,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    state = state
                )
            },
        ) {
            ImagesList(
                items = items,
                navigateToDetails = navigateToDetails
            )
        }
    }
}