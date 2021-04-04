package com.techjd.itunesapi.data.artists

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techjd.itunesapi.data.artists.entities.Artists

@Database(entities = [Artists::class], version = 1)
abstract class ArtistsDatabase : RoomDatabase() {

    abstract fun artistDao(): ArtistsDao
}