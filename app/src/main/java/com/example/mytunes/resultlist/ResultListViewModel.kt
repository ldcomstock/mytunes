package com.example.mytunes.resultlist

import android.util.Log
import androidx.lifecycle.*
import com.example.mytunes.network.api.TracksApi
import com.example.mytunes.resultlist.model.SearchResultItem
import com.example.mytunes.toSearchResultItems
import kotlinx.coroutines.launch

class ResultListViewModel : ViewModel() {

    private val TAG = ResultListViewModel::class.qualifiedName
    private val _status = MutableLiveData<SearchApiStatus>()
    private val _results = MutableLiveData<List<SearchResultItem>>()

    enum class SearchApiStatus { LOADING, ERROR, LOADED }

    val status: LiveData<SearchApiStatus>
        get() = _status

    val results: LiveData<List<SearchResultItem>>
        get() = _results

    init {
        clearResults()
    }

    /**
     * Uses coroutines to fetch tracks for artist and sets the appropriate value for API status
     */
     fun getResults(searchTerm: String) {
        // viewModelScope is the built-in coroutine scope defined for each ViewModel in the app
        // Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared
        viewModelScope.launch {
            _status.value = SearchApiStatus.LOADING
            try {
                // make the network call on a background thread using coroutines
                val resultDomainObject = TracksApi.retrofitService.searchTracks(searchTerm)
                val resultDomainObjects = resultDomainObject.results

                // ---> execution resumes here when api response is received <--- yay for coroutines!
                // convert domain models to view models
                val searchResultItems = resultDomainObjects.toSearchResultItems()
                _results.value = searchResultItems
                _status.value = SearchApiStatus.LOADED
            } catch (e: Exception) {
                _status.value = SearchApiStatus.ERROR
                Log.e(TAG, "Failed to fetch results: ${e.message}")
            }
        }
    }

    fun clearResults() {
        _results.value = listOf()
    }
}