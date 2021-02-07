package com.example.mytunes

import com.example.mytunes.network.model.SearchResultDomainObject
import junit.framework.Assert.fail
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.time.DateTimeException

class ExtensionsTest {

    @Test
    fun trackPriceNegative_emptyPriceString() {
        val price: Double = -5.0
        val priceString = price.toTrackPriceString()
        Assert.assertEquals("", priceString)
    }

    @Test
    fun trackPriceZero_zeroPriceString() {
        val price = 0.0
        val priceString = price.toTrackPriceString()
        Assert.assertEquals("0.0", priceString)
    }

    @Test
    fun trackPriceValid_validPriceString() {
        val price = 3.49
        val priceString = price.toTrackPriceString()
        Assert.assertEquals("3.49", priceString)
    }

    @Test
    fun trackPriceEmpty_emptyPriceString() {
        val price = null
        val priceString = price.toTrackPriceString()
        Assert.assertEquals("", priceString)
    }

    @Test
    fun zonedDateValid_validDateString() {
        val zonedDate = "2010-03-26T12:00:00Z"
        val dateString = zonedDate.toDateString()
        Assert.assertEquals("3/26/10", dateString)
    }

    @Test
    fun emptyZonedDate_emptyDateString() {
        val zonedDate = ""
        var dateString: String = ""
        dateString = zonedDate.toDateString()
        Assert.assertEquals("", dateString)
    }

    @Test
    fun unzonedDate_emptyDateString() {
        val zonedDate = "2010-03-26T12:00:00"
        var dateString = ""
        dateString = zonedDate.toDateString()
        Assert.assertEquals("", dateString)
    }

    @Test
    fun validSearchDomainObject_validSearchItem() {
        val searchDomainObject = SearchResultDomainObject(
                "",
                "",
                0,
                0,
                0,
                "Madonna",
                "",
                "Ray of Light",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0.0,
                1.29,
                "1998-02-22T08:00:00Z",
                "",
                "",
                0,
                0,
                0,
                0,
                0,
                "",
                "",
                "Pop",
                false
        )

        val searchResultItem = searchDomainObject.toSearchResultItem()
        Assert.assertEquals("Madonna", searchResultItem.artistName)
        Assert.assertEquals("Ray of Light", searchResultItem.trackName)
        Assert.assertEquals("2/22/98", searchResultItem.releaseDate)
        Assert.assertEquals("1.29", searchResultItem.trackPrice)
        Assert.assertEquals("Pop", searchResultItem.primaryGenreName)
    }
}