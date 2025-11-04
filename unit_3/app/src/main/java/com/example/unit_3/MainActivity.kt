package com.example.unit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.unit3.ui.theme.Unit3Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedProduct by remember { mutableStateOf<Product?>(null) }

                    if (selectedProduct == null) {
                        ProductScreen(onProductClick = { selectedProduct = it })
                    } else {
                        ProductDetailScreen(
                            product = selectedProduct!!,
                            onBack = { selectedProduct = null }
                        )
                    }
                }
            }
        }
    }
}
