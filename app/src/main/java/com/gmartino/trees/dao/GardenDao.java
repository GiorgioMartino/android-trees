package com.gmartino.trees.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.gmartino.trees.entity.Garden;
import com.gmartino.trees.entity.UserTree;

import java.util.List;

@Dao
public interface GardenDao {

    @Query("SELECT * FROM UserTree")
    LiveData<List<UserTree>> getAllGardens();

    @Transaction
    @Query("SELECT * FROM User WHERE email = :email")
    Garden getGarden(String email);

    @Transaction
    @Query("SELECT * FROM UserTree WHERE email = :email")
    List<UserTree> getUserTrees(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserTree userTree);

    @Update
    void update(UserTree userTree);
}
