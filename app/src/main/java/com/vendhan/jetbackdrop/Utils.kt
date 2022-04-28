@file:OptIn(ExperimentalMaterialApi::class)

package com.vendhan.jetbackdrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyAppBar(
    title: String,
    backdropScaffoldState: BackdropScaffoldState,
    onClickActionFilter: () -> Unit,
    onClickCloseFilter: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            ) {
                Text(
                    text = if (backdropScaffoldState.isRevealed) {
                        "Filter Results"
                    } else
                        title,
                    style = MaterialTheme.typography.body1,
                    color = LocalContentColor.current,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1.5f),
                    fontSize = 18.sp
                )
            }
        },
        navigationIcon = {
            if (backdropScaffoldState.isRevealed)
                IconButton(onClick = {
                    onClickCloseFilter()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            else
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
        },
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            if (!backdropScaffoldState.isRevealed)
                IconButton(onClick = {
                    onClickActionFilter()
                }) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
        }
    )
}

@Composable
fun ListCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable { }
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.img), contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(end = 12.dp)
            )
            Column(
                modifier = Modifier.height(120.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Good Furniture",
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = "Rs.2000",
                    style = MaterialTheme.typography.body1,
                    color = Color.DarkGray,
                )
                Text(
                    text = "4 Start Rated",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListCard() {
    ListCard()
}
