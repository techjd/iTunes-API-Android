package com.techjd.itunesapi.data.artists

import androidx.room.*
import com.techjd.itunesapi.data.artists.entities.Artists

@Dao
interface ArtistsDao {

    @Query("SELECT * FROM artists")
    fun getAllArtists(): List<Artists>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artists: List<Artists>)

    @Query("DELETE FROM artists")
    fun deleteAllArtists()

}