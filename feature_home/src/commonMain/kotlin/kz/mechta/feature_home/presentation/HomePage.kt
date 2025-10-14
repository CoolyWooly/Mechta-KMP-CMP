package kz.mechta.feature_home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomePage(text : String,  onClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.safeContentPadding().fillMaxSize()
    ) {
        Button(
            onClick = onClick,
        ) {
            Text(text)
        }
    }
}