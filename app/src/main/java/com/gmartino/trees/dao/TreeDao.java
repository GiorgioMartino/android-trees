package com.gmartino.trees.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gmartino.trees.entity.Tree;

import java.util.List;

@Dao
public interface TreeDao {

    @Query("SELECT * FROM tree ORDER BY name ASC")
    LiveData<List<Tree>> getTrees();

    @Query("SELECT * FROM tree ")
    List<Tree> getAll();

    @Query("SELECT * FROM tree WHERE name = :name")
    Tree findByName(String name);

    @Insert
    void insert(Tree tree);

}
