package com.app.acube.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.app.acube.model.Stock

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStock(stock: Stock)

    //@Query("SELECT * FROM stock")
    //fun findStocks(employeeId: String): LiveData<List<Stock>>?
}