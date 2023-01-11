package com.example.youtube_searchapi

import com.google.gson.annotations.SerializedName

data class SearchItems(
    @SerializedName("kind") var kind: String = "",
    @SerializedName("etag") var etag: String = "",
    @SerializedName("id") var id: SearchItemsId = SearchItemsId(),
    @SerializedName("snippet") var snippet: SearchItemsSnippet = SearchItemsSnippet(),
)

data class SearchItemsId(
    @SerializedName("kind") var kind: String = "",
    @SerializedName("channelId") var channelId: String = "",
    @SerializedName("videoId") var videoId: String = ""
)

data class SearchItemsSnippet(
    @SerializedName("publishedAt") var publishedAt: String = "",
    @SerializedName("channelId") var channelId: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("thumbnails") var thumbnails: SearchItemsThumbnails = SearchItemsThumbnails(),
    @SerializedName("channelTitle") var channelTitle: String = "",
)

data class SearchItemsThumbnails(
    @SerializedName("default") var default: ThumbnailsInfo = ThumbnailsInfo(),
    @SerializedName("medium") var medium: ThumbnailsInfo = ThumbnailsInfo(),
    @SerializedName("high") var high: ThumbnailsInfo = ThumbnailsInfo(),
)

data class ThumbnailsInfo(
    @SerializedName("url") var url: String = "",
    @SerializedName("width") var width: Int = 0,
    @SerializedName("height") var height: Int = 0,
)