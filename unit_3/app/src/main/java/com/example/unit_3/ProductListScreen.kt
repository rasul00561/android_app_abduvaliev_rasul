package com.example.unit3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun ProductListScreen(products: List<Product>, onProductClick: (Product) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Products") }) }
    ) { padding ->
        ResponsiveLayout { isWide ->
            if (isWide) {
                // 📱 На широком экране показываем два столбца
                Row(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(products) { product ->
                            ProductCard(product, onProductClick)
                        }
                    }
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(products) { product ->
                            ProductCard(product, onProductClick)
                        }
                    }
                }
            } else {
                // 📱 На телефоне — обычный список
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    items(products) { product ->
                        ProductCard(product, onProductClick)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(product) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(product.name, style = MaterialTheme.typography.titleMedium)
            Text(product.price, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
