package com.example.unit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.unit3.ui.theme.Unit3Theme
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit3Theme {
                AppNavigation()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AppNavigation(viewModel: ProductViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ProductListScreen(
                products = viewModel.products.collectAsState().value,
                onProductClick = { product ->
                    viewModel.selectProduct(product)
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            viewModel.selectedProduct.collectAsState().value?.let {
                ProductDetailScreen(it, onBack = { navController.popBackStack() })
            }
        }
    }
}
