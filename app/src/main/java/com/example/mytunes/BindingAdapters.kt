@file:Suppress("unused")

package com.example.mytunes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytunes.resultlist.ResultListViewModel.SearchApiStatus
import com.example.mytunes.resultlist.SearchResultCardAdapter
import com.example.mytunes.resultlist.model.SearchResultItem

// set the data for the recycler view adapter
@BindingAdapter("listData")
fun setRecyclerViewProperties(recyclerView: RecyclerView,
                                  items: List<SearchResultItem>) {
    if (recyclerView.adapter is SearchResultCardAdapter) {
        (recyclerView.adapter as SearchResultCardAdapter).setData(items)
    }
}

// set the loading image visibility based on current api status
@BindingAdapter("resultsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: SearchApiStatus?) {

    when (status) {
        SearchApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        SearchApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.baseline_error_outline_black_24dp)
        }
        SearchApiStatus.LOADED -> {
            statusImageView.visibility = View.GONE
        }
    }
}

// set the text for the Results header text field based on api status and number of results returned
@BindingAdapter("resultsApiStatus", "resultsSize")
fun bindResultHeader(headerTextView: TextView,
                     status: SearchApiStatus?,
                     resultsSize: Int) {

    when (status) {
        SearchApiStatus.LOADING -> {
            headerTextView.text = headerTextView.context.getString(R.string.results_header_loading)
        }
        SearchApiStatus.ERROR -> {
            headerTextView.text = headerTextView.context.getString(R.string.results_header_error)
        }
        SearchApiStatus.LOADED -> {
            if (resultsSize > 0) {
                headerTextView.text = headerTextView.context.getString(R.string.results_header_loaded)
            }
            else {
                headerTextView.text = headerTextView.context.getString(R.string.results_header_no_results)
            }
        }
    }
}

// set the text for the artist name
@BindingAdapter("artistName")
fun bindArtistName(artistNameTextView: TextView,
                  artistName: String) {

    val artistNameString = if (artistName.isEmpty()) {
        artistNameTextView.context.getString(R.string.result_item_artist_not_found)
    }
    else {
        artistName.trim()
    }
    artistNameTextView.text = artistNameString
}

// set the text for the track name
@BindingAdapter("trackName")
fun bindTrackName(trackNameTextView: TextView,
                    trackName: String) {

    val trackNameString = if (trackName.isEmpty()) {
        trackNameTextView.context.getString(R.string.result_item_track_name_not_found)
    }
    else {
        trackName.trim()
    }
    trackNameTextView.text = trackNameString
}

// set the text for the track price
@BindingAdapter("trackPrice")
fun bindTrackPrice(priceTextView: TextView,
                     price: String) {

    val priceString = if (price.isEmpty()) {
        priceTextView.context.getString(R.string.result_item_track_price_not_found)
    }
    else {
        priceTextView.context.getString(R.string.result_track_price, price.trim())
    }
    priceTextView.text = priceString
}

// set the text for the artist genre
@BindingAdapter("genre")
fun bindGenre(genreTextView: TextView,
                   genre: String) {

    val genreString = if (genre.isEmpty()) {
        genreTextView.context.getString(R.string.result_item_genre_not_found)
    }
    else {
        genreTextView.context.getString(R.string.result_genre, genre.trim())
    }
    genreTextView.text = genreString
}

// set the text for the release date
@BindingAdapter("releaseDate")
fun bindReleaseDate(releaseDateTextView: TextView,
               releaseDate: String) {

    val releaseDateString = if (releaseDate.isEmpty()) {
        releaseDateTextView.context.getString(R.string.result_item_release_date_not_found)
    }
    else {
        releaseDateTextView.context.getString(R.string.result_release_date, releaseDate)
    }
    releaseDateTextView.text = releaseDateString
}


