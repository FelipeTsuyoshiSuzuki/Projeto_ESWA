package com.example.projeto_eswa.ui.components.usageList

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projeto_eswa.R
import com.example.projeto_eswa.utils.DEEPBLUE
import com.example.projeto_eswa.utils.LIGHTBLUE
import kotlin.math.roundToInt


@Composable
fun HomeListItem(
    icon: Drawable?,
    appName: String,
    textTime: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 4.dp),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(2.dp, DEEPBLUE)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LIGHTBLUE)
                .padding(12.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(48.dp)
                    .drawBehind {
                        drawIntoCanvas { canvas ->
                            icon?.let {
                                it.setBounds(0, 0, size.width.roundToInt(), size.height.roundToInt())
                                it.draw(canvas.nativeCanvas)
                            }
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = appName)
                Text(text = textTime)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeListItemPreview() {
    val context = LocalContext.current
    MaterialTheme {
        HomeListItem(
            context.getDrawable(R.drawable.ic_default),
            "Projeto ESWA",
            "25:30:10"
        )
    }
}