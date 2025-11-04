package com.example.unit3

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.work.*
import com.example.unit3.ui.theme.Unit3Theme
import models.Product
import models.ProductSyncWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scheduleProductSync(this)

        setContent {
            Unit3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedProduct by remember { mutableStateOf<Product?>(null) }

                    ResponsiveLayout { isWide ->
                        if (isWide) {
                            Row {
                                ProductScreen(onProductClick = { selectedProduct = it })

                                selectedProduct?.let {
                                    ProductDetailScreen(
                                        product = it,
                                        onBack = { selectedProduct = null }
                                    )
                                }
                            }
                        } else {
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
    }

    private fun scheduleProductSync(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWork = PeriodicWorkRequestBuilder<ProductSyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "product_sync_work",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWork
        )
    }
}
