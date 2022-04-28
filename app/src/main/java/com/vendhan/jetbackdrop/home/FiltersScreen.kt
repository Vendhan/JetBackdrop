package com.vendhan.jetbackdrop.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vendhan.jetbackdrop.Filters
import com.vendhan.jetbackdrop.FiltersData
import com.vendhan.jetbackdrop.Price
import kotlin.math.roundToInt

@Composable
fun FiltersScreen(data: FiltersData) {
    FiltersScreenList(
        data = data
    )
}

@Composable
fun FiltersScreenList(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    data: FiltersData
) {
    LazyColumn(
        state = state,
        modifier = modifier
    ) {
        item {
            PriceSliderView(data.price)
        }
        items(data.filters) { filter ->
            FiltersItem(filters = filter)
        }
    }
}

@Composable
fun FiltersItem(filters: Filters) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = filters.categoryName,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                letterSpacing = 0.15.sp
            )
        )
        val textChipRememberOneState = remember {
            mutableStateOf(false)
        }
        LazyRow(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(filters.data) {
                TextChipWithIconVisibility(
                    isSelected = textChipRememberOneState.value,
                    text = it,
                    onChecked = {
                        textChipRememberOneState.value = it
                    }
                )
            }
        }
    }
}

@Composable
fun PriceSliderView(price: Price) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        var sliderPosition by remember { mutableStateOf(0f) }
        Text(
            text = "${price.categoryName} Rs.${sliderPosition.roundToInt()} - Rs.${price.max}",
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                letterSpacing = 0.15.sp
            )
        )
        Slider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = price.min.toFloat()..price.max.toFloat(),
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Black.copy(alpha = 0.5f),
                inactiveTrackColor = Color.White
            )
        )
    }
}

@Composable
fun TextChipWithIconVisibility(
    isSelected: Boolean,
    text: String,
    onChecked: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .clickable {
                onChecked(!isSelected)
            },
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Color.Black.copy(alpha = 0.25f)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(42.dp)
                .padding(4.dp)
        ) {
            if (isSelected) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    imageVector = Icons.Default.Check,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp
                ),
                text = text,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TextChipWithIconVisibility(
        isSelected = false,
        text = "Some Filter",
        onChecked = {}
    )
}
