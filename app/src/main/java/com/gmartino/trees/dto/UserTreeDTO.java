package com.gmartino.trees.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class UserTreeDTO implements Parcelable {

    private long id;
    private String userEmail;
    private String treeName;
    private Date addedDate;
    private String nickname;

    public UserTreeDTO(long id, String userEmail, String treeName, Date addedDate, String nickname) {
        this.id = id;
        this.userEmail = userEmail;
        this.treeName = treeName;
        this.addedDate = addedDate;
        this.nickname = nickname;
    }

    protected UserTreeDTO(Parcel in) {
        id = in.readLong();
        userEmail = in.readString();
        treeName = in.readString();
        addedDate = new Date(in.readLong());
        nickname = in.readString();
    }

    public static final Creator<UserTreeDTO> CREATOR = new Creator<UserTreeDTO>() {
        @Override
        public UserTreeDTO createFromParcel(Parcel in) {
            return new UserTreeDTO(in);
        }

        @Override
        public UserTreeDTO[] newArray(int size) {
            return new UserTreeDTO[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(userEmail);
        dest.writeString(treeName);
        dest.writeLong(addedDate.getTime());
        dest.writeString(nickname);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
