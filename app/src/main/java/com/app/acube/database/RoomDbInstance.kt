package com.app.acube.database

import android.content.Context
import androidx.room.Room

object RoomDbInstance {
    private var appDatabase: AppDatabase? = null

    fun getAppDb(context: Context?): AppDatabase {
        return if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                context!!,
                AppDatabase::class.java, AppDatabase.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

            appDatabase!!
        } else
            appDatabase!!
    }
}