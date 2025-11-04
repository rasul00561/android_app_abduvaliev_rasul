package com.example.unit3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterial3Api
@Composable
fun ProductScreen(
    viewModel: ProductViewModel = viewModel(),
    onProductClick: (Product) -> Unit
) {
    val products = viewModel.products.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Products from API") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(products.value) { product ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { onProductClick(product) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "${product.price}$", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
