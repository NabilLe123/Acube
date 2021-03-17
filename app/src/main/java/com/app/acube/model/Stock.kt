package com.app.acube.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock")
data class Stock(
    @PrimaryKey
    var stockName: String,
    var stockCost: String
)