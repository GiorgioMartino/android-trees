package com.gmartino.trees.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@Entity
public class User implements Serializable {

    @ColumnInfo
    private String username;

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String email;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String key;

    @ColumnInfo
    private String iv;

    public User(String username, @NonNull String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
