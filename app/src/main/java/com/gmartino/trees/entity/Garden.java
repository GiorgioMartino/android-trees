package com.gmartino.trees.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class Garden {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "email",
            entityColumn = "name",
            associateBy = @Junction(value = UserTree.class)
    )
    public List<UserTree> trees;


    public Garden(User user, List<UserTree> trees) {
        this.user = user;
        this.trees = trees;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserTree> getTrees() {
        return trees;
    }

    public void setTrees(List<UserTree> trees) {
        this.trees = trees;
    }
}
