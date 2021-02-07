package com.example.mytunes.network.model

data class SearchResultsDomainObject (
    val resultCount: Int,
    val results: List<SearchResultDomainObject>
)