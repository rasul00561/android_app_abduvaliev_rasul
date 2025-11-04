package data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LocalProductViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocalProductViewModel::class.java)) {
            return LocalProductViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}