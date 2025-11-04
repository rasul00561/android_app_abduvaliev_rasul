package com.example.unit3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Product(val id: Int, val name: String, val price: String, val description: String)

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow(
        listOf(
            Product(1, "Laptop", "$999", "A powerful laptop for work, study, and gaming."),
            Product(2, "Phone", "$499", "A modern smartphone with a high-quality camera and fast performance."),
            Product(3, "Headphones", "$99", "Wireless headphones with noise cancelling and long battery life."),
            Product(4, "Camera", "$799", "A compact digital camera perfect for photography lovers."),
            Product(5, "Smart Watch", "$199", "A stylish smartwatch that tracks fitness and notifications."),
            Product(6, "Keyboard", "$59", "A mechanical keyboard with RGB lighting and smooth keys."),
            Product(7, "Mouse", "$39", "An ergonomic wireless mouse for daily use.")
        )
    )
    val products: StateFlow<List<Product>> = _products

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }
}
