package com.example.unit3

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    viewModel: ProductViewModel = viewModel(),
    onProductClick: (Product) -> Unit
) {
    val products = viewModel.products.collectAsState()
    val context = LocalContext.current
    val localViewModel: LocalProductViewModel = viewModel(
        factory = LocalProductViewModelFactory(context.applicationContext as Application)
    )
    val localProducts by localViewModel.localProductsFlow.collectAsState()
    var showLocal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Products from API & Local DB") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { localViewModel.addProduct("Phone", 999.99, "Latest smartphone") }) {
                    Text("Add Product")
                }

                Button(onClick = {
                    showLocal = !showLocal
                    if (showLocal) localViewModel.loadProducts()
                }) {
                    Text(if (showLocal) "Hide Local" else "Show Local")
                }

                Button(onClick = {
                    localViewModel.clearAll()
                    showLocal = false
                }) {
                    Text("Clear Local DB")
                }
            }

            Text(
                text = "Products from API:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                items(products.value) { product ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { onProductClick(product) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "${product.price}$", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            if (showLocal && localProducts.isNotEmpty()) {
                Text(
                    text = "Local products:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    items(localProducts) { product ->
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "${product.price}$", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}
