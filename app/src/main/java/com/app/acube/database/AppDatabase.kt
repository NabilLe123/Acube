package com.app.acube.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.acube.model.Stock

@Database(
    entities = [Stock::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        const val DATABASE_NAME = "acube_db"
    }
}