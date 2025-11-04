package data

import androidx.room.Database
import androidx.room.RoomDatabase
import data.ProductDao

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}