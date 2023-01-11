package com.example.youtube_searchapi

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide.init
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Repository private constructor() {
    companion object {
        val instance = Repository()
    }

    var searchResultMutableLiveData: MutableLiveData<SearchResult> = MutableLiveData()

    private var searchResultMutableList = mutableStateListOf<SearchResult>()

    init {
        searchResultMutableLiveData.value = SearchResult()
    }

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    private val service: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    fun search(searchText: String, order: String) {
        val call = service.search(setMap(searchText, order))
        call.enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                searchResultMutableLiveData.postValue(response.body())
//                searchResultMutableList[0] = response.body()!!
                Log.d("Repository-search", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.d("autoLogin", "onFailure: $t")
            }
        })
    }

    private fun setMap(searchText: String, order: String): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["part"] = "snippet"
        map["q"] = searchText
        map["type"] = "video"
        map["maxResults"] = "10"
        map["order"] = order
        map["key"] = ApplicationContext.getContext().resources.getString(R.string.api_key)
        return map
    }

    fun getSearchResult(): MutableLiveData<SearchResult> {
        return searchResultMutableLiveData
    }

    fun getSearchResultList(): MutableList<SearchResult> {
        return searchResultMutableList
    }
}