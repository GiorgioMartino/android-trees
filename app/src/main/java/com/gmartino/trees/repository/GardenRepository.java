package com.gmartino.trees.repository;

import android.app.Application;
import android.util.Log;

import com.gmartino.trees.dao.GardenDao;
import com.gmartino.trees.dao.TreeDatabase;
import com.gmartino.trees.entity.Garden;
import com.gmartino.trees.entity.Tree;
import com.gmartino.trees.entity.User;
import com.gmartino.trees.entity.UserTree;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class GardenRepository {

    private static final String LOG_TAG = GardenRepository.class.getSimpleName();

    private final GardenDao gardenDao;

    public GardenRepository(Application application) {
        TreeDatabase db = TreeDatabase.getDatabase(application);
        gardenDao = db.gardenDao();
    }

    public Garden getGarden(User user) {
        try {
            return TreeDatabase.databaseWriteExecutor.submit(() -> gardenDao.getGarden(user.getEmail())).get();
        } catch (ExecutionException | InterruptedException e) {
            Log.d(LOG_TAG, "Cannot find user with mail: " + user.getEmail());
            return null;
        }
    }

    public void insert(UserTree userTree) {
        TreeDatabase.databaseWriteExecutor.execute(() -> gardenDao.insert(userTree));
    }
}
