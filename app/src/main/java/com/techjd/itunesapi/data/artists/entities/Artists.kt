package com.techjd.itunesapi.data.artists.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class Artists(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val trackId: String,
    @ColumnInfo(name = "artistName")
    val artistName: String,
    @ColumnInfo(name = "collectionName")
    val collectionName: String,
    @ColumnInfo(name = "trackName")
    val trackName: String,
    @ColumnInfo(name = "photo")
    val artworkUrl100: String
)