package com.gmartino.trees.dao;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gmartino.trees.entity.Tree;
import com.gmartino.trees.entity.User;
import com.gmartino.trees.entity.UserTree;
import com.gmartino.trees.service.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {User.class, Tree.class, UserTree.class}, version = 7, autoMigrations = {
        @AutoMigration(from = 1, to = 2), @AutoMigration(from = 2, to = 3), @AutoMigration(from = 3, to = 4),
        @AutoMigration(from = 4, to = 5), @AutoMigration(from = 5, to = 6)
})
@TypeConverters({Converters.class})
public abstract class TreeDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract TreeDao treeDao();

    public abstract GardenDao gardenDao();

    private static volatile TreeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TreeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TreeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TreeDatabase.class, "tree_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
