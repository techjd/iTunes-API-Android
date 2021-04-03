package com.techjd.itunesapi.api

import com.techjd.itunesapi.data.artists.Artists

data class apiResponse(
    val results: List<Artists>
)