package com.example.youtube_searchapi

import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

data class SearchResult(
    @SerializedName("kind") var kind : String? = null,
    @SerializedName("etag") var etag : String? = null,
    @SerializedName("nextPageToken") var nextPageToken : String? = null,
    @SerializedName("pageInfo") var pageInfo : SearchPageInfo? = null,
    @SerializedName("items") val items : ArrayList<SearchItems> = ArrayList<SearchItems>()
)
