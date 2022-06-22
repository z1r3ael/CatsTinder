package com.example.week8catsnavigation.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.week8catsnavigation.data.CatData

@Dao
interface FavoriteCatDao {

    @Query("SELECT * FROM catdata")
    fun getAll() : List<CatData>

    @Query("SELECT * FROM catdata")
    fun get() : CatData?

    @Insert
    fun insert(favoriteCat: CatData)

    @Delete
    fun delete(favoriteCat: CatData)
}