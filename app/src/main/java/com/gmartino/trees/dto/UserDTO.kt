package com.gmartino.trees.dto

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class UserDTO : Parcelable {
    var username: String?
    var email: String?
    var password: String?

    constructor(username: String?, email: String?, password: String?) {
        this.username = username
        this.email = email
        this.password = password
    }

    constructor(`in`: Parcel) {
        username = `in`.readString()
        email = `in`.readString()
        password = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(username)
        dest.writeString(email)
        dest.writeString(password)
    }

    companion object CREATOR : Creator<UserDTO> {
        override fun createFromParcel(parcel: Parcel) = UserDTO(parcel)


        override fun newArray(size: Int) = arrayOfNulls<UserDTO>(size)
    }
}