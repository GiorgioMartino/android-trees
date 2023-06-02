package com.gmartino.trees.dto

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import java.util.*

class UserTreeDTO : Parcelable {
    var id: Long
    var userEmail: String?
    var treeName: String?
    var addedDate: Date
    var nickname: String?

    constructor(id: Long, userEmail: String?, treeName: String?, addedDate: Date, nickname: String?) {
        this.id = id
        this.userEmail = userEmail
        this.treeName = treeName
        this.addedDate = addedDate
        this.nickname = nickname
    }

    constructor(`in`: Parcel) {
        id = `in`.readLong()
        userEmail = `in`.readString()
        treeName = `in`.readString()
        addedDate = Date(`in`.readLong())
        nickname = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(userEmail)
        dest.writeString(treeName)
        dest.writeLong(addedDate.time)
        dest.writeString(nickname)
    }


    companion object CREATOR : Creator<UserTreeDTO> {
        override fun createFromParcel(parcel: Parcel) = UserTreeDTO(parcel)

        override fun newArray(size: Int) = arrayOfNulls<UserTreeDTO>(size)
    }
}