package com.gmartino.trees.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class UserTree implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "email")
    private String userEmail;

    @ColumnInfo(name = "name")
    private String treeName;

    private Date addedDate;
    private String nickname;

    @Ignore
    public UserTree(String userEmail, String treeName, Date addedDate,
                    String nickname) {
        this.userEmail = userEmail;
        this.treeName = treeName;
        this.addedDate = addedDate;
        this.nickname = nickname;
    }

    public UserTree(long id, String userEmail, String treeName, Date addedDate,
                    String nickname) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
