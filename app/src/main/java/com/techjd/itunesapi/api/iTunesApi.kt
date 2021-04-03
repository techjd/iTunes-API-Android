package com.techjd.itunesapi.api

import com.techjd.itunesapi.data.artists.Artists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesApi {

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
    }

    @GET("search")
    fun searchArtists(
        @Query("term") query: String
    ): Call<apiResponse>

}

