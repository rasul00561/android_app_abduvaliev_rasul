package data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocalProductViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "product_database"
    ).build()

    private val productDao = db.productDao()

    private val _localProducts = MutableStateFlow<List<ProductEntity>>(emptyList())
    val localProductsFlow: StateFlow<List<ProductEntity>> = _localProducts

    fun addProduct(name: String, price: Double, description: String) {
        viewModelScope.launch {
            val product = ProductEntity(name = name, price = price, description = description)
            productDao.insertProduct(product)
            loadProducts()
        }
    }

    fun loadProducts() {
        viewModelScope.launch {
            _localProducts.value = productDao.getAllProducts()
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            productDao.deleteAll()
            _localProducts.value = emptyList()
        }
    }
}