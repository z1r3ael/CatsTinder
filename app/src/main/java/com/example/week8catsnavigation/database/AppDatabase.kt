package com.example.week8catsnavigation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.week8catsnavigation.data.CatData

@Database(entities = [CatData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCatDao(): FavoriteCatDao
}