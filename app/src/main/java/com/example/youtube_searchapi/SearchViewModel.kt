package com.example.youtube_searchapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance

class SearchViewModel : ViewModel() {
    private val repository: Repository = Repository.instance
    private val _liveData : MutableLiveData<SearchResult> = repository.getSearchResult()

    var liveData: LiveData<SearchResult> = _liveData

     fun getSearchResult() : LiveData<SearchResult> {
        return liveData
    }

    fun search(searchText : String) {
        repository.search(searchText)
    }

    fun onResultChange() {

    }
}