package com.example.mytunes.resultlist.model

data class SearchResultItem (
    val artistName: String,
    val trackName: String,
    val trackPrice: String,
    val releaseDate: String,
    val primaryGenreName: String
) {
    // moshi may use this no arg constructor
    @Suppress("unused")
    constructor(): this(
           "",
           "",
            "",
           "",
           "",
    )
}