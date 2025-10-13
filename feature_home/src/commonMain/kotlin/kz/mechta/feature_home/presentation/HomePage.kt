package kz.mechta.feature_home.presentation

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class HomePage: Screen {

    @Composable
    override fun Content() {
        Scaffold (
            modifier = Modifier.safeContentPadding()
        ) {
            Button(
                onClick = {},
            ) {

            }
        }
    }
}