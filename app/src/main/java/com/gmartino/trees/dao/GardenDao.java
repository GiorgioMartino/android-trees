package com.gmartino.trees.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.gmartino.trees.entity.Garden;
import com.gmartino.trees.entity.UserTree;

import java.util.List;

@Dao
public interface GardenDao {

    @Transaction
    @Query("SELECT * FROM User")
    List<Garden> getGardens();

    @Transaction
    @Query("SELECT * FROM User WHERE email = :email")
    Garden getGarden(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserTree userTree);
}
