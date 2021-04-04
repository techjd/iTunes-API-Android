package com.techjd.itunesapi.ui.artists

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techjd.itunesapi.api.apiResponse
import com.techjd.itunesapi.api.iTunesApi
import com.techjd.itunesapi.data.artists.Artists
import com.techjd.itunesapi.data.artists.ArtistsDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsViewModel @ViewModelInject constructor(
    private val iTunesApi: iTunesApi,
    private val artistsDatabase: ArtistsDatabase
) :
    ViewModel() {

    private val artists: MutableLiveData<apiResponse>
    private var defaultquery: String = "One Direction"
    var query = MutableLiveData<String>()

    init {
        artists = MutableLiveData<apiResponse>()
        query.value = defaultquery
    }

    fun getList(): MutableLiveData<apiResponse> {
        return artists
    }

    fun makeCall() {
        if (query.value!! == "") {
            query.value = "One Direction"
        }
        iTunesApi.searchArtists(query.value!!).enqueue(object : Callback<apiResponse> {
            override fun onResponse(call: Call<apiResponse>, response: Response<apiResponse>) {
                artists.postValue(response.body())
            }

            override fun onFailure(call: Call<apiResponse>, t: Throwable) {
                artists.postValue(null)
            }

        })
    }

//    fun getartistsFromDB(): List<Artists> {
//        return artistsDatabase.artistDao().getAllArtists()
//    }


}