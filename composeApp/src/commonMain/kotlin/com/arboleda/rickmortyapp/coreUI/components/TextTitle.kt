package com.arboleda.rickmortyapp.coreUI.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.arboleda.rickmortyapp.coreUI.DefaultTextColor

@Composable
fun TextTitle(text: String) {
    Text(text = text.uppercase(), fontWeight = FontWeight.Bold, color = DefaultTextColor)
}
