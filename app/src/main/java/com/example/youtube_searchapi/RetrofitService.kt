package com.example.youtube_searchapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface RetrofitService {
    @GET("youtube/v3/search")
    fun search(
        @QueryMap map: Map<String,String>
    ) : Call<SearchResult>
}