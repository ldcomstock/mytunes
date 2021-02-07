package com.example.mytunes

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.mytunes.network.model.SearchResultDomainObject
import com.example.mytunes.resultlist.model.SearchResultItem
import java.time.DateTimeException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

// convert the list of domain objects to a list of objects to use in the view
fun List<SearchResultDomainObject>.toSearchResultItems(): List<SearchResultItem> {
    val resultItems: MutableList<SearchResultItem> = mutableListOf()
    for (result in this) {
        resultItems.add(result.toSearchResultItem())
    }
    return resultItems
}

fun SearchResultDomainObject.toSearchResultItem(): SearchResultItem {

    val artistNameString = this.artistName ?: ""
    val trackNameString = this.trackName ?: ""
    val trackPriceString = this.trackPrice.toTrackPriceString()
    val releaseDateString = this.releaseDate?.toDateString() ?: ""
    val primaryGenreString = this.primaryGenreName ?: ""

    return SearchResultItem(
        artistNameString,
        trackNameString,
        trackPriceString,
        releaseDateString,
        primaryGenreString)
}

// if price is non-null and >= 0, return price as string otherwise return empty string
fun Double?.toTrackPriceString() : String {

    // ensure track price is zero or greater
    var trackPriceString = ""
    this?.let { price ->
        trackPriceString = if (price >= 0) {
            price.toString()
        }
        else {
            ""
        }
    }
    return trackPriceString
}

// takes a zoned date string and returns a short format date
// note: if iTunes api returns different date format in future, parse will throw exception
// catch parse error and return empty string instead of crashing the app
fun String.toDateString() : String {

    return try {
        ZonedDateTime
                .parse(this)
                .toLocalDate()
                .format(DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.SHORT)
                        .withLocale(Locale.US))
    }
    catch (e: DateTimeException) {
        // TODO log an error - skipped this so I didn't have to deal with mocking Log.e in unit testing
        // Log.e("Extensions", "Error parsing date string")
        ""
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}