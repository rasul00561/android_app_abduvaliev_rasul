package com.example.unit3

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ResponsiveLayout(content: @Composable (isWide: Boolean) -> Unit) {
    BoxWithConstraints {
        val isWide = maxWidth > 600.dp
        content(isWide)
    }
}
