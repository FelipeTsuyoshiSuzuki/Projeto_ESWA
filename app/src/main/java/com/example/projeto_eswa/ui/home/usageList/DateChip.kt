package com.example.projeto_eswa.ui.home.usageList

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projeto_eswa.model.DateOption
import com.example.projeto_eswa.utils.DEEPBLUE
import com.example.projeto_eswa.utils.LIGHTBLUE
import com.example.projeto_eswa.utils.LIGHTGRAY
import com.example.projeto_eswa.utils.VIVIDBLUE
import com.example.projeto_eswa.utils.WHITE

@Composable
fun DateChip(
    categories: List<DateOption> = listOf(
        DateOption.TODAY,
        DateOption.WEEK,
        DateOption.MONTH,
        DateOption.YEAR,
        DateOption.CUSTOM
    ),
    selectedCategory: DateOption,
    onCategorySelected: (DateOption) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        divider = {},
        edgePadding = 12.dp,
        indicator = emptyTabIndicator,
        modifier = modifier,
        containerColor = Color.Transparent
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) }
            ) {
                Log.d("CATEGORIE", "$selectedIndex")
                ChoiceChipContent(
                    text = category.name,
                    selected = index == selectedIndex,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun ChoiceChipContent(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        color = when {
            selected -> VIVIDBLUE
            else -> LIGHTBLUE
        },
        contentColor = when {
            selected -> WHITE
            else -> DEEPBLUE
        },
        border = BorderStroke(
            2.dp,
        when{
            selected -> LIGHTBLUE
            else -> LIGHTGRAY
        }
        ),
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {}
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MaterialTheme {
        DateChip(selectedCategory = DateOption.WEEK)
    }
}