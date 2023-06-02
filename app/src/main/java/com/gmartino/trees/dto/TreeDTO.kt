package com.gmartino.trees.dto

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class TreeDTO : Parcelable {
    var name: String?
    var scientificName: String?
    var country: String?
    var co2: String?

    constructor(name: String?, scientificName: String?, country: String?, co2: String?) {
        this.name = name
        this.scientificName = scientificName
        this.country = country
        this.co2 = co2
    }

    constructor(`in`: Parcel) {
        name = `in`.readString()
        scientificName = `in`.readString()
        country = `in`.readString()
        co2 = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(scientificName)
        dest.writeString(country)
        dest.writeString(co2)
    }

    companion object CREATOR : Creator<TreeDTO> {
        override fun createFromParcel(parcel: Parcel) = TreeDTO(parcel)

        override fun newArray(size: Int) = arrayOfNulls<TreeDTO>(size)
    }
}