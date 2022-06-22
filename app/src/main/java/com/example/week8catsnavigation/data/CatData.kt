package com.example.week8catsnavigation.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Parcelize
@Serializable
data class CatData(
    @PrimaryKey
    @SerialName("id")
    val id: String,
    @ColumnInfo(name = "url")
    @SerialName("url")
    val catImageUrl: String
) : Parcelable
