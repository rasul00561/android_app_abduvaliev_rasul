package models

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import network.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val api: ProductApi = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)

    override suspend fun doWork(): Result {
        return try {
            val products: List<Product> = api.getProducts()
            Log.d("ProductSyncWorker", "Loaded ${products.size} products")
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}
