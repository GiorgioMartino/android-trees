package com.gmartino.trees.dao;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gmartino.trees.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {User.class}, version = 1)
public abstract class TreeDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile TreeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TreeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TreeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TreeDatabase.class, "tree_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
