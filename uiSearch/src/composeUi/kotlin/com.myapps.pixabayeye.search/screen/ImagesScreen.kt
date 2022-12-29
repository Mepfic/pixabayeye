package com.myapps.pixabayeye.search.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.PagingData
import com.myapps.pixabayeye.search.state.ImagesState
import com.myapps.pixabayeye.search.state.SearchItemState

@ExperimentalMaterialApi
@Composable
fun ImagesScreen(state: PagingData<SearchItemState>, navigateToDetails: (Long) -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
    ) {
        val (image, tags, author, statistic) = createRefs()
        Text(
            text = "state..toString()",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface,
        )
    }
}