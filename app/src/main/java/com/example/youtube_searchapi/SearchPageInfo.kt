package com.example.youtube_searchapi

import com.google.gson.annotations.SerializedName


data class SearchPageInfo(
    @SerializedName("totalResults") var totalResults : Int,
    @SerializedName("resultsPerPage") var resultsPerPage : Int
)
