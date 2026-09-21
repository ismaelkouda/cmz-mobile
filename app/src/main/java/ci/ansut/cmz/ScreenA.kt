package ci.ansut.cmz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

@Composable
fun ScreenA(navigateToScreenB: (name: String) -> Unit) {
    Column(
        modifier = Modifier.background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Screen A",
            fontSize = 24.sp
        )
        Button(
            onClick = { navigateToScreenB("je quitte de l'ecran A vers l'ecran B") }
        ) {
            Text(
                text = "Navigate",
                fontSize = 24.sp
            )
        }
    }
}