package com.evg_ivanoff.kinomix

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class FilmListItem(
    @SerializedName("Search"       ) var search       : ArrayList<FilmListItemDetail> = arrayListOf(),
    @SerializedName("totalResults" ) var totalResults : String?           = null,
    @SerializedName("Response"     ) var response     : String?           = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("search"),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(this.totalResults)
        p0?.writeString(this.response)
    }

    companion object CREATOR : Parcelable.Creator<FilmListItem> {
        override fun createFromParcel(parcel: Parcel): FilmListItem {
            return FilmListItem(parcel)
        }

        override fun newArray(size: Int): Array<FilmListItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class FilmListItemDetail(
    @SerializedName("Title"  ) var title  : String? = null,
    @SerializedName("Year"   ) var year   : String? = null,
    @SerializedName("imdbID" ) var imdbID : String? = null,
    @SerializedName("Type"   ) var type   : String? = null,
    @SerializedName("Poster" ) var poster : String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(this.title)
        parcel?.writeString(this.year)
        parcel?.writeString(this.imdbID)
        parcel?.writeString(this.type)
        parcel?.writeString(this.poster)
    }

    companion object CREATOR : Parcelable.Creator<FilmListItemDetail> {
        override fun createFromParcel(parcel: Parcel): FilmListItemDetail {
            return FilmListItemDetail(parcel)
        }

        override fun newArray(size: Int): Array<FilmListItemDetail?> {
            return arrayOfNulls(size)
        }
    }
}

data class Film(
    @SerializedName("Title") var title: String? = null,
    @SerializedName("Year") var year: String? = null,
    @SerializedName("Rated") var rated: String? = null,
    @SerializedName("Released") var released: String? = null,
    @SerializedName("Runtime") var runtime: String? = null,
    @SerializedName("Genre") var genre: String? = null,
    @SerializedName("Director") var director: String? = null,
    @SerializedName("Writer") var writer: String? = null,
    @SerializedName("Actors") var actors: String? = null,
    @SerializedName("Plot") var plot: String? = null,
    @SerializedName("Language") var language: String? = null,
    @SerializedName("Country") var country: String? = null,
    @SerializedName("Awards") var awards: String? = null,
    @SerializedName("Poster") var poster: String? = null,
    @SerializedName("Ratings") var ratings: ArrayList<Ratings> = arrayListOf(),
    @SerializedName("Metascore") var metascore: String? = null,
    @SerializedName("imdbRating") var imdbRating: String? = null,
    @SerializedName("imdbVotes") var imdbVotes: String? = null,
    @SerializedName("imdbID") var imdbID: String? = null,
    @SerializedName("Type") var type: String? = null,
    @SerializedName("DVD") var dvd: String? = null,
    @SerializedName("BoxOffice") var boxOffice: String? = null,
    @SerializedName("Production") var production: String? = null,
    @SerializedName("Website") var website: String? = null,
    @SerializedName("Response") var response: String? = null


)

data class Ratings(
    @SerializedName("Source") var source: String? = null,
    @SerializedName("Value") var value: String? = null
)
