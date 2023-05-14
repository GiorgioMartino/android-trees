package com.gmartino.trees.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Date;

@Entity(primaryKeys = {"email", "name"})
public class UserTree {

    @ColumnInfo(name = "email")
    @NonNull
    private String userEmail;

    @ColumnInfo(name = "name")
    @NonNull
    private String treeName;

    private Date addedDate;
    private String nickname;

    public UserTree(@NonNull String userEmail, @NonNull String treeName, Date addedDate,
                    String nickname) {
        this.userEmail = userEmail;
        this.treeName = treeName;
        this.addedDate = addedDate;
        this.nickname = nickname;
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
}
