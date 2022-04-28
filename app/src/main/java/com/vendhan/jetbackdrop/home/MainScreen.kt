@file:OptIn(ExperimentalMaterialApi::class)

package com.vendhan.jetbackdrop.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vendhan.jetbackdrop.ListCard
import com.vendhan.jetbackdrop.MyAppBar
import com.vendhan.jetbackdrop.getData
import kotlinx.coroutines.launch

@Composable
fun BackDropScaffoldScreen() {

    val backDropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val coroutineScope = rememberCoroutineScope()
    val title = "Buy Furnitures"
    BackdropScaffold(
        scaffoldState = backDropState,
        appBar = {
            MyAppBar(
                title = title,
                backdropScaffoldState = backDropState,
                onClickActionFilter = {
                    coroutineScope.launch {
                        backDropState.reveal()
                    }
                },
                onClickCloseFilter = {
                    coroutineScope.launch {
                        backDropState.conceal()
                    }
                }
            )
        },
        frontLayerContent = {
            Column {
                if (backDropState.isRevealed)
                    Text(
                        text = "253 Results",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        modifier = Modifier
                            .height(
                                BackdropScaffoldDefaults.HeaderHeight
                            )
                            .padding(
                                start = 16.dp,
                                top = 8.dp
                            )
                            .align(
                                Alignment.Start
                            )
                    )
                ListView()
            }
        },
        backLayerContent = {
            FiltersScreen(getData())
        },
        gesturesEnabled = false
    )
}

@Composable
fun ListView(
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = state
    ) {
        repeat(20) {
            item {
                ListCard()
            }
        }
    }
}
