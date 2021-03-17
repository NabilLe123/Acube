package com.app.acube.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.acube.model.Stock

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStock(stock: Stock)

    @Query("SELECT * FROM stock")
    fun fetchAllStocks(): LiveData<List<Stock>?>
}