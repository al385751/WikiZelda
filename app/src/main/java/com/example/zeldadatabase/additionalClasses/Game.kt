package com.example.zeldadatabase.additionalClasses

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Game")
class Game (val title : String, @PrimaryKey val id: String, val description: String, val released : String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!) {
    }

    override fun toString(): String {
        return title
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(id)
        parcel.writeString(description)
        parcel.writeString(released)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}